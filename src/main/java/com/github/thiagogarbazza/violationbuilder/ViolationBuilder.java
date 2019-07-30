package com.github.thiagogarbazza.violationbuilder;

import com.github.thiagogarbazza.simplemessage.SimpleMessage;
import com.github.thiagogarbazza.simplemessage.SimpleMessages;

import static com.github.thiagogarbazza.simplemessage.SimpleMessageType.ERROR;
import static com.github.thiagogarbazza.simplemessage.SimpleMessageType.WARNING;

public class ViolationBuilder {

  private final SimpleMessages violations;

  public ViolationBuilder() {
    this.violations = new SimpleMessages();
  }

  public void build() {
    build(false);
  }

  public void build(boolean ignoreWarnings) {
    SimpleMessages thatViolations = ignoreWarnings
      ? this.violations.extractByType(ERROR)
      : this.violations;

    if (!thatViolations.isEmpty()) {
      throw new ViolationException("There was some violation.", thatViolations);
    }
  }

  public void buildIgnoreWarnings() {
    build(true);
  }

  public ViolationBuilder error(final boolean condition, final String key, final String text) {
    if (condition) {
      this.violations.add(new SimpleMessage(ERROR, key, text));
    }

    return this;
  }

  public ViolationBuilder error(final boolean condition, final String key, final ViolationTextBuilder violationTextBuilder) {
    if (condition) {
      this.violations.add(new SimpleMessage(ERROR, key, violationTextBuilder.build()));
    }

    return this;
  }

  public ViolationBuilder error(final String key, final String text) {
    this.violations.add(new SimpleMessage(ERROR, key, text));

    return this;
  }

  public ViolationBuilder error(final String key, final ViolationTextBuilder violationTextBuilder) {
    this.violations.add(new SimpleMessage(ERROR, key, violationTextBuilder.build()));

    return this;
  }

  public ViolationBuilder warning(final boolean condition, final String key, final ViolationTextBuilder violationTextBuilder) {
    if (condition) {
      this.violations.add(new SimpleMessage(WARNING, key, violationTextBuilder.build()));
    }

    return this;
  }

  public ViolationBuilder warning(final boolean condition, final String key, final String text) {
    if (condition) {
      this.violations.add(new SimpleMessage(WARNING, key, text));
    }

    return this;
  }

  public ViolationBuilder warning(final String key, final String text) {
    this.violations.add(new SimpleMessage(WARNING, key, text));

    return this;
  }

  public ViolationBuilder warning(final String key, final ViolationTextBuilder violationTextBuilder) {
    this.violations.add(new SimpleMessage(WARNING, key, violationTextBuilder.build()));

    return this;
  }

  public static ViolationBuilder builder() {
    return new ViolationBuilder();
  }
}
