package com.github.thiagogarbazza.violationbuilder;

import com.github.thiagogarbazza.simplemessage.SimpleMessage;
import com.github.thiagogarbazza.simplemessage.SimpleMessages;

import static com.github.thiagogarbazza.simplemessage.SimpleMessageType.ERROR;
import static com.github.thiagogarbazza.simplemessage.SimpleMessageType.WARNING;

public class ViolationBuilder {

  private final SimpleMessages violations;

  /**
   * <p>Constructor for ViolationBuilder.</p>
   * <p>Starts off assuming that no violations. Multiple calls are
   * then made to the various append methods, followed by a call to
   * {@link #build} to get the result.</p>
   */
  public ViolationBuilder() {
    this.violations = new SimpleMessages();
  }

  /**
   * @throws ViolationException when there is a violation.
   */
  public void build() throws ViolationException {
    build(false);
  }

  /**
   * @param ignoreWarnings Condition that defines if there is any violation of warning type, they will be ignored or not.
   *
   * @throws ViolationException when there is a violation.
   */
  public void build(boolean ignoreWarnings) throws ViolationException {
    SimpleMessages thatViolations = ignoreWarnings
      ? this.violations.extractByType(ERROR)
      : this.violations;

    if (!thatViolations.isEmpty()) {
      throw new ViolationException("There was some violation.", thatViolations);
    }
  }

  /**
   * @throws ViolationException when there is a violation of error type.
   * If there is any violation of warning type, they will be ignored.
   */
  public void buildIgnoreWarnings() throws ViolationException {
    build(true);
  }

  /**
   * <p>Appends to the builder a violation of error type.</p>
   *
   * @param condition Condition that defines if the violation should be added or not.
   * @param key Unique identifier of the violation.
   * @param text Description of the violation.
   *
   * @return this - used to chain append calls
   */
  public ViolationBuilder error(final boolean condition, final String key, final String text) {
    if (condition) {
      this.violations.add(new SimpleMessage(ERROR, key, text));
    }

    return this;
  }

  /**
   * <p>Appends to the builder a violation of error type.</p>
   *
   * @param condition Condition that defines if the violation should be added or not.
   * @param key Unique identifier of the violation.
   * @param violationTextBuilder Lazy description builder of the violation.
   *
   * @return this - used to chain append calls
   */
  public ViolationBuilder error(final boolean condition, final String key, final ViolationTextBuilder violationTextBuilder) {
    if (condition) {
      this.violations.add(new SimpleMessage(ERROR, key, violationTextBuilder.build()));
    }

    return this;
  }

  /**
   * <p>Appends to the builder a violation of error type.</p>
   *
   * @param key Unique identifier of the violation.
   * @param text Description of the violation.
   *
   * @return this - used to chain append calls
   */
  public ViolationBuilder error(final String key, final String text) {
    this.violations.add(new SimpleMessage(ERROR, key, text));

    return this;
  }

  /**
   * <p>Appends to the builder a violation of error type.</p>
   *
   * @param key Unique identifier of the violation.
   * @param violationTextBuilder Lazy description builder of the violation.
   *
   * @return this - used to chain append calls
   */
  public ViolationBuilder error(final String key, final ViolationTextBuilder violationTextBuilder) {
    this.violations.add(new SimpleMessage(ERROR, key, violationTextBuilder.build()));

    return this;
  }

  /**
   * <p>Appends to the builder a warning of error type.</p>
   *
   * @param condition Condition that defines if the violation should be added or not.
   * @param key Unique identifier of the violation.
   * @param violationTextBuilder Lazy description builder of the violation.
   *
   * @return this - used to chain append calls
   */
  public ViolationBuilder warning(final boolean condition, final String key, final ViolationTextBuilder violationTextBuilder) {
    if (condition) {
      this.violations.add(new SimpleMessage(WARNING, key, violationTextBuilder.build()));
    }

    return this;
  }

  /**
   * <p>Appends to the builder a violation of warning type.</p>
   *
   * @param condition Condition that defines if the violation should be added or not.
   * @param key Unique identifier of the violation.
   * @param text Description of the violation.
   *
   * @return this - used to chain append calls
   */
  public ViolationBuilder warning(final boolean condition, final String key, final String text) {
    if (condition) {
      this.violations.add(new SimpleMessage(WARNING, key, text));
    }

    return this;
  }

  /**
   * <p>Appends to the builder a violation of warning type.</p>
   *
   * @param key Unique identifier of the violation.
   * @param text Description of the violation.
   *
   * @return this - used to chain append calls
   */
  public ViolationBuilder warning(final String key, final String text) {
    this.violations.add(new SimpleMessage(WARNING, key, text));

    return this;
  }

  /**
   * <p>Appends to the builder a warning of error type.</p>
   *
   * @param key Unique identifier of the violation.
   * @param violationTextBuilder Lazy description builder of the violation.
   *
   * @return this - used to chain append calls
   */
  public ViolationBuilder warning(final String key, final ViolationTextBuilder violationTextBuilder) {
    this.violations.add(new SimpleMessage(WARNING, key, violationTextBuilder.build()));

    return this;
  }

  /**
   * <p>Constructor for ViolationBuilder.</p>
   * <p>Starts off assuming that no violations. Multiple calls are
   * then made to the various append methods, followed by a call to
   * {@link #build} to get the result.</p>
   */
  public static ViolationBuilder builder() {
    return new ViolationBuilder();
  }
}
