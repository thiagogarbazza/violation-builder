package com.github.thiagogarbazza.violationbuilder.runner;

import com.github.thiagogarbazza.violationbuilder.ViolationBuilder;

/**
 * Validation rule to be execution on the data.
 * When a rule is violated, the flow of execution of rules must continue.
 *
 * @param <T> Type of data to be validated.
 *
 * @since 1.0.0
 */
@FunctionalInterface
public interface ValidationRuleContinueFlow<T> extends ValidationRule<T> {

  /**
   * Validation rule to be execution on the data.
   * When a rule is violated, the flow of execution of rules must continue.
   *
   * @param violationBuilder Builder of violations, accumulating the validations to make the return of problems simpler.
   * @param data Data to be validated.
   */
  void runContinueFlow(final ViolationBuilder violationBuilder, final T data);

  /**
   * Validation rule to be execution on the data.
   * When a rule is violated, the flow of execution of rules must continue.
   *
   * @param violationBuilder Builder of violations, accumulating the validations to make the return of problems simpler.
   * @param data Data to be validated.
   *
   * @return The flow control of execution of rule.
   */
  @Override
  default Rulesflow run(final ViolationBuilder violationBuilder, final T data) {
    runContinueFlow(violationBuilder, data);

    return Rulesflow.CONTINUE;
  }
}
