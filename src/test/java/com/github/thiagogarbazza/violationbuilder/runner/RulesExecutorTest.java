package com.github.thiagogarbazza.violationbuilder.runner;

import com.github.thiagogarbazza.simplemessage.SimpleMessage;
import com.github.thiagogarbazza.violationbuilder.ViolationBuilder;
import com.github.thiagogarbazza.violationbuilder.ViolationException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;

import static com.github.thiagogarbazza.simplemessage.SimpleMessageType.ERROR;
import static com.github.thiagogarbazza.violationbuilder.util.AssertMessage.assertMessage;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;


class RulesExecutorTest {

  final ValidationRule<String> validationRule = (violationBuilder, data) -> {
    violationBuilder.error(data == null, "key-simple", "Message simple rule");

    return Rulesflow.CONTINUE;
  };
  final ValidationRule<String> validationRuleContinueFlow = (violationBuilder, data) -> {
    violationBuilder.error(!data.equals("Some valid value."), "key-cumulative-01", "Message cumulative rule 01.");

    return Rulesflow.CONTINUE;
  };
  final ValidationRule<String> validationRuleContinueFlow02 = (violationBuilder, data) -> {
    violationBuilder.error(!data.equals(""), "key-cumulative-02", "Message cumulative rule 02.");

    return Rulesflow.CONTINUE;
  };
  final ValidationRule<String> validationRuleStopFlow = (violationBuilder, data) -> {
    violationBuilder.error(data.equals("Some block value."), "key-blocker", "Message blocker rule.");

    return Rulesflow.STOP;
  };

  @Test
  void verifyAnyRulesBlockerNotPassNotCallingCumulative() {
    final Collection<ValidationRule<String>> rules = asList(validationRule, validationRuleStopFlow, validationRuleContinueFlow);

    final ViolationException exception = assertThrows(ViolationException.class, () -> RulesExecutor.rulesExecutor(rules, "Some block value."));
    final Iterator<SimpleMessage> iterator = exception.getViolations().iterator();

    assertAll(
      () -> assertEquals("There was some violation.", exception.getMessage()),
      () -> assertEquals(1, exception.getViolations().size()),
      () -> assertMessage(iterator.next(), ERROR, "key-blocker", "Message blocker rule.")
    );
  }

  @Test
  void verifyAnyRulesBlockerPass() {
    Collection<ValidationRule<String>> rules = asList(validationRule, validationRuleStopFlow, validationRuleContinueFlow);

    assertDoesNotThrow(() -> RulesExecutor.rulesExecutor(rules, "Some valid value."));
  }

  @Test
  void verifyAnyRulesCumulative() {
    final Collection<ValidationRule<String>> rules = asList(validationRule, validationRuleContinueFlow,
      validationRuleContinueFlow02, validationRuleStopFlow);

    final ViolationException exception = assertThrows(ViolationException.class, () -> RulesExecutor.rulesExecutor(rules, "Some invalid value."));
    final Iterator<SimpleMessage> iterator = exception.getViolations().iterator();

    assertAll(
      () -> assertEquals("There was some violation.", exception.getMessage()),
      () -> assertEquals(2, exception.getViolations().size()),
      () -> assertMessage(iterator.next(), ERROR, "key-cumulative-01", "Message cumulative rule 01."),
      () -> assertMessage(iterator.next(), ERROR, "key-cumulative-02", "Message cumulative rule 02.")
    );
  }

  @Test
  void verifyAnyRulesNotPass() {
    Collection<ValidationRule<String>> rules = asList(validationRule, validationRuleContinueFlow, validationRuleStopFlow);

    ViolationException exception = assertThrows(ViolationException.class, () -> RulesExecutor.rulesExecutor(rules, "Some invalid value."));

    final Iterator<SimpleMessage> iterator = exception.getViolations().iterator();

    assertAll(
      () -> assertEquals("There was some violation.", exception.getMessage()),
      () -> assertEquals(1, exception.getViolations().size()),
      () -> assertMessage(iterator.next(), ERROR, "key-cumulative-01", "Message cumulative rule 01.")
    );
  }

  @Test
  void verifyValidationRuleContinueFlow() {
    assertDoesNotThrow(() -> RulesExecutor.rulesExecutor(
      singletonList((ValidationRuleContinueFlow<String>) (violationBuilder, data) -> { /* execute something*/ }),
      "Some invalid value."));
  }

  @Test
  void verifyValidationRuleStopFlow() {
    assertDoesNotThrow(() -> RulesExecutor.rulesExecutor(
      singletonList((ValidationRuleStopFlow<String>) (violationBuilder, data) -> { /* execute something*/ }),
      "Some invalid value."));
  }

  @Nested
  static class WithSuperInterface{

    @FunctionalInterface
    static interface RuleSuperInterface extends ValidationRule<String> {

      void runX(final ViolationBuilder violationBuilder, final String data);

      @Override
      default Rulesflow run(final ViolationBuilder violationBuilder, final String data) {
        runX(violationBuilder, data);

        return Rulesflow.CONTINUE;
      }
    }

    @Test
    void verifyAnyRulesNotPass() {
      final Collection<RuleSuperInterface> rules = singletonList(
        (violationBuilder, data) -> violationBuilder.error("key-cumulative-01", "Message cumulative rule 01."));

      final ViolationException exception = assertThrows(ViolationException.class,
        () -> RulesExecutor.rulesExecutor(rules, "Some invalid value."));

      final Iterator<SimpleMessage> iterator = exception.getViolations().iterator();

      assertAll(
        () -> assertEquals("There was some violation.", exception.getMessage()),
        () -> assertEquals(1, exception.getViolations().size()),
        () -> assertMessage(iterator.next(), ERROR, "key-cumulative-01", "Message cumulative rule 01.")
      );
    }
  }
}
