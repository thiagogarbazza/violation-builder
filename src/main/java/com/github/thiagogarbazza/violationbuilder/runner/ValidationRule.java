package com.github.thiagogarbazza.violationbuilder.runner;

import com.github.thiagogarbazza.violationbuilder.ViolationBuilder;

public interface ValidationRule<T> {

  void run(final ViolationBuilder violationBuilder, final T data);
}
