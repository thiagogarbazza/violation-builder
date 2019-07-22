package com.github.thiagogarbazza.violationbuilder;

import com.github.thiagogarbazza.simplemessage.SimpleMessage;
import lombok.Getter;

import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

public class ViolationException extends RuntimeException {

  @Getter
  private final Collection<SimpleMessage> violations;

  public ViolationException(final String message, final Collection<SimpleMessage> violations) {
    super(message);
    this.violations = unmodifiableCollection(violations);
  }
}
