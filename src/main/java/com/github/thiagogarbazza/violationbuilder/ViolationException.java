package com.github.thiagogarbazza.violationbuilder;

import com.github.thiagogarbazza.simplemessage.SimpleMessage;
import lombok.Getter;

import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

/**
 * Exception of violations.
 *
 * @since 1.0.0
 */
public class ViolationException extends RuntimeException {

  /**
   * Recorded violations.
   *
   * @since 1.0.0
   */
  @Getter
  private final Collection<SimpleMessage> violations;

  /**
   * Exceção de violações.
   *
   * @param message Exception message.
   * @param violations Recorded violations.
   *
   * @since 1.0.0
   */
  public ViolationException(final String message, final Collection<SimpleMessage> violations) {
    super(message);
    this.violations = unmodifiableCollection(violations);
  }
}
