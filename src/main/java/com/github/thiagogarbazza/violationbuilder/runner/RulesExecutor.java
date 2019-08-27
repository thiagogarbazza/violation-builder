package com.github.thiagogarbazza.violationbuilder.runner;

import com.github.thiagogarbazza.violationbuilder.ViolationBuilder;
import lombok.experimental.UtilityClass;

import java.util.Collection;

@UtilityClass
public class RulesExecutor {

  public static <T> void ruleExecutor(final ViolationBuilder violationBuilder, final ValidationRule<T> rule, final T data) {
    rule.run(violationBuilder, data);

    if (rule instanceof ValidationRuleBlocker) {
      violationBuilder.build();
    }
  }

  public static <T> void rulesExecutor(final ViolationBuilder violationBuilder, final Collection<ValidationRule<T>> rules, final T data) {
    for (ValidationRule rule : rules) {
      ruleExecutor(violationBuilder, rule, data);
    }
  }

  public static <T> void rulesExecutor(final Collection<ValidationRule<T>> rules, final T data) {
    final ViolationBuilder violationBuilder = new ViolationBuilder();
    rulesExecutor(violationBuilder, rules, data);
    violationBuilder.build();
  }
}
