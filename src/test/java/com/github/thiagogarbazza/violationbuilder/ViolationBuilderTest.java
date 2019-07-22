package com.github.thiagogarbazza.violationbuilder;

import com.github.thiagogarbazza.simplemessage.SimpleMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.thiagogarbazza.simplemessage.SimpleMessageType.ERROR;
import static com.github.thiagogarbazza.simplemessage.SimpleMessageType.WARNING;
import static com.github.thiagogarbazza.violationbuilder.util.AssertMessageUtil.assertMessage;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ViolationBuilderTest {

  private ViolationBuilder violationBuilder;

  @BeforeEach
  void before() {
    violationBuilder = new ViolationBuilder();
  }

  @Test
  void verifyDoNotThrowExceptioNoViolations() {
    violationBuilder.build();
  }

  @Test
  void verifyDoNotThrowExceptionIgnoringWarningViolations() {
    violationBuilder.warning("warning-key", "some warning message");

    violationBuilder.build(true);
  }
  
  @Test
  void verifyThrowExceptionWhenOwningViolationError() {
    violationBuilder.error("error-key", "some error message");

    final ViolationException violationException = assertThrows(ViolationException.class, () -> violationBuilder.build());

    final SimpleMessage simpleMessage = violationException.getViolations().iterator().next();
    assertMessage(simpleMessage, ERROR, "error-key", "some error message");
  }

  @Test
  void verifyThrowExceptionWhenOwningViolationErrorConditionFalse() {
    violationBuilder.error(false, "error-key", "some error message");

    violationBuilder.build();
  }

  @Test
  void verifyThrowExceptionWhenOwningViolationErrorConditionTrue() {
    violationBuilder.error(true, "error-key", "some error message");

    final ViolationException violationException = assertThrows(ViolationException.class, () -> violationBuilder.build());

    final SimpleMessage simpleMessage = violationException.getViolations().iterator().next();
    assertMessage(simpleMessage, ERROR, "error-key", "some error message");
  }

  @Test
  void verifyThrowExceptionWhenOwningViolationErrorWithArgs() {
    violationBuilder.error("error-key", () -> "some error message");

    final ViolationException violationException = assertThrows(ViolationException.class, () -> violationBuilder.build());

    final SimpleMessage simpleMessage = violationException.getViolations().iterator().next();
    assertMessage(simpleMessage, ERROR, "error-key", "some error message");
  }

  @Test
  void verifyThrowExceptionWhenOwningViolationErrorWithArgsConditionFalse() {
    violationBuilder.error(false, "error-key", () -> { throw new UnsupportedOperationException(); });

    violationBuilder.build();
  }

  @Test
  void verifyThrowExceptionWhenOwningViolationErrorWithArgsConditionTrue() {
    violationBuilder.error(true, "error-key", () -> "some error message");

    final ViolationException violationException = assertThrows(ViolationException.class, () -> violationBuilder.build());

    final SimpleMessage simpleMessage = violationException.getViolations().iterator().next();
    assertMessage(simpleMessage, ERROR, "error-key", "some error message");
  }

  @Test
  void verifyThrowExceptionWhenOwningViolationWarnning() {
    violationBuilder.warning("warning-key", "some warning message");

    final ViolationException violationException = assertThrows(ViolationException.class, () -> violationBuilder.build());

    final SimpleMessage simpleMessage = violationException.getViolations().iterator().next();
    assertMessage(simpleMessage, WARNING, "warning-key", "some warning message");
  }

  @Test
  void verifyThrowExceptionWhenOwningViolationWarnningConditionFalse() {
    violationBuilder.warning(false, "warning-key", "some warning message");

    violationBuilder.build();
  }

  @Test
  void verifyThrowExceptionWhenOwningViolationWarnningConditionTrue() {
    violationBuilder.warning(true, "warning-key", "some warning message");

    final ViolationException violationException = assertThrows(ViolationException.class, () -> violationBuilder.build());

    final SimpleMessage simpleMessage = violationException.getViolations().iterator().next();
    assertMessage(simpleMessage, WARNING, "warning-key", "some warning message");
  }

  @Test
  void verifyThrowExceptionWhenOwningViolationWarnningWithArgs() {
    violationBuilder.warning("warning-key", () -> "some warning message");

    final ViolationException violationException = assertThrows(ViolationException.class, () -> violationBuilder.build());

    final SimpleMessage simpleMessage = violationException.getViolations().iterator().next();
    assertMessage(simpleMessage, WARNING, "warning-key", "some warning message");
  }

  @Test
  void verifyThrowExceptionWhenOwningViolationWarnningWithArgsConditionFalse() {
    violationBuilder.warning(false, "warning-key", () -> { throw new UnsupportedOperationException(); });

    violationBuilder.build();
  }

  @Test
  void verifyThrowExceptionWhenOwningViolationWarnningWithArgsConditionTrue() {
    violationBuilder.warning(true, "warning-key", () -> "some warning message");

    final ViolationException violationException = assertThrows(ViolationException.class, () -> violationBuilder.build());

    final SimpleMessage simpleMessage = violationException.getViolations().iterator().next();
    assertMessage(simpleMessage, WARNING, "warning-key", "some warning message");
  }
}
