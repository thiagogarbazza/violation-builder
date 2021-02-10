package com.github.thiagogarbazza.violationbuilder.runner;

import com.github.thiagogarbazza.violationbuilder.ViolationBuilder;

/**
 * Validation rule to be execution on the data.
 *
 * @param <T> Type of data to be validated.
 *
 * @since 1.0.0
 */
public interface ValidationRule<T> {

  /**
   * Validation rule to be execution on the data.
   *
   * @param violationBuilder Builder of violations, accumulating the validations to make the return of problems simpler.
   * @param data Data to be validated.
   *
   * @return The flow control of execution of rule.
   */
  Rulesflow run(final ViolationBuilder violationBuilder, final T data);
}
