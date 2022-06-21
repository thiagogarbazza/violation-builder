package com.github.thiagogarbazza.violationbuilder;

/**
 * Lazy description builder of the violation.
 *
 * @since 1.0.0
 */
@FunctionalInterface
public interface ViolationTextFactory {

  /**
   * @return Lazy description builder of the violation.
   */
  String create();
}
