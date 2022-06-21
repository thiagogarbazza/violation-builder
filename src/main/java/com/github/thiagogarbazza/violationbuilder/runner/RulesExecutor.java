package com.github.thiagogarbazza.violationbuilder.runner;

import com.github.thiagogarbazza.violationbuilder.ViolationBuilder;
import lombok.experimental.UtilityClass;

import java.util.Collection;

/**
 * Validation rules executor.
 *
 * @since 1.1.0
 */
@UtilityClass
public class RulesExecutor {

  /**
   * Validation rules executor.
   *
   * @param violationBuilder Builder of violations, accumulating the validations to make the return of problems simpler.
   * @param rule Collection of rules to be executed
   * @param data Data to be validated.
   * @param <T> Type of data to be validated.
   *
   * @throws com.github.thiagogarbazza.violationbuilder.ViolationException when there is a violation.
   *
   * @since 1.1.0
   */
  public static <T> void ruleExecutor(final ViolationBuilder violationBuilder, final ValidationRule<T> rule, final T data) {
    final Rulesflow rulesflow = rule.run(violationBuilder, data);

    if (Rulesflow.STOP.equals(rulesflow)) {
      violationBuilder.build();
    }
  }

  /**
   * Validation rules executor.
   *
   * @param violationBuilder Builder of violations, accumulating the validations to make the return of problems simpler.
   * @param rules Collection of rules to be executed
   * @param data Data to be validated.
   * @param <T> Type of data to be validated.
   *
   * @throws com.github.thiagogarbazza.violationbuilder.ViolationException when there is a violation.
   *
   * @since 1.1.0
   */
  public static <T> void rulesExecutor(final ViolationBuilder violationBuilder, final Collection<? extends ValidationRule<T>> rules, final T data) {
    for (ValidationRule<T> rule : rules) {
      ruleExecutor(violationBuilder, rule, data);
    }
  }

  /**
   * Validation rules executor.
   *
   * @param rules Collection of rules to be executed
   * @param data Data to be validated.
   * @param <T> Type of data to be validated.
   *
   * @throws com.github.thiagogarbazza.violationbuilder.ViolationException when there is a violation.
   *
   * @since 1.1.0
   */
  public static <T> void rulesExecutor(final Collection<? extends ValidationRule<T>> rules, final T data) {
    final ViolationBuilder violationBuilder = ViolationBuilder.builder();
    rulesExecutor(violationBuilder, rules, data);
    violationBuilder.build();
  }
}
