package com.github.thiagogarbazza.violationbuilder.runner;

import com.github.thiagogarbazza.simplemessage.SimpleMessage;
import com.github.thiagogarbazza.violationbuilder.ViolationBuilder;
import com.github.thiagogarbazza.violationbuilder.ViolationException;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;

import static com.github.thiagogarbazza.simplemessage.SimpleMessageType.ERROR;
import static com.github.thiagogarbazza.violationbuilder.util.AssertMessageUtil.assertMessage;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RulesExecutorTest {

  final ValidationRule validationRule = new ValidationRule<String>() {
    @Override
    public void run(final ViolationBuilder violationBuilder, final String data) {
      violationBuilder.error(data == null, "key-simple", "Message simple rule");
    }
  };

  ValidationRule validationRuleBlocker = new ValidationRuleBlocker<String>() {
    @Override
    public void run(final ViolationBuilder violationBuilder, final String data) {
      violationBuilder.error(data.equals("Some block value."), "key-blocker", "Message blocker rule.");
    }
  };

  ValidationRule validationRuleCumulative = new ValidationRuleCumulative<String>() {
    @Override
    public void run(final ViolationBuilder violationBuilder, final String data) {
      violationBuilder.error(!data.equals("Some valid value."), "key-cumulative-01", "Message cumulative rule 01.");
    }
  };

  ValidationRule validationRuleCumulative02 = new ValidationRuleCumulative<String>() {
    @Override
    public void run(final ViolationBuilder violationBuilder, final String data) {
      violationBuilder.error(!data.equals(""), "key-cumulative-02", "Message cumulative rule 02.");
    }
  };

  @Test
  void verifyAnyRulesBlockerNotPassNotCallingCumulative() {
    Collection<ValidationRule> rules = asList(validationRule, validationRuleBlocker, validationRuleCumulative);

    ViolationException exception = assertThrows(ViolationException.class, () -> RulesExecutor.rulesExecutor(rules, "Some block value."));

    assertEquals("There was some violation.", exception.getMessage());
    assertEquals(1, exception.getViolations().size());
    final Iterator<SimpleMessage> iterator = exception.getViolations().iterator();
    assertMessage(iterator.next(), ERROR, "key-blocker", "Message blocker rule.");
  }

  @Test
  void verifyAnyRulesBlockerPass() {
    Collection<ValidationRule> rules = asList(validationRule, validationRuleBlocker, validationRuleCumulative);

    assertDoesNotThrow(() -> RulesExecutor.rulesExecutor(rules, "Some valid value."));
  }

  @Test
  void verifyAnyRulesCumulative() {
    Collection<ValidationRule> rules = asList(validationRule, validationRuleCumulative, validationRuleCumulative02, validationRuleBlocker);

    ViolationException exception = assertThrows(ViolationException.class, () -> RulesExecutor.rulesExecutor(rules, "Some invalid value."));

    assertEquals("There was some violation.", exception.getMessage());
    assertEquals(2, exception.getViolations().size());
    final Iterator<SimpleMessage> iterator = exception.getViolations().iterator();
    assertMessage(iterator.next(), ERROR, "key-cumulative-01", "Message cumulative rule 01.");
    assertMessage(iterator.next(), ERROR, "key-cumulative-02", "Message cumulative rule 02.");
  }

  @Test
  void verifyAnyRulesNotPass() {
    Collection<ValidationRule> rules = asList(validationRule, validationRuleCumulative, validationRuleBlocker);

    ViolationException exception = assertThrows(ViolationException.class, () -> RulesExecutor.rulesExecutor(rules, "Some invalid value."));

    assertEquals("There was some violation.", exception.getMessage());
    assertEquals(1, exception.getViolations().size());
    final Iterator<SimpleMessage> iterator = exception.getViolations().iterator();
    assertMessage(iterator.next(), ERROR, "key-cumulative-01", "Message cumulative rule 01.");
  }
}
