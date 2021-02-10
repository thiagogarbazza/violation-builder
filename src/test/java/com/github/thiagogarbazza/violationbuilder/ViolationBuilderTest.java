package com.github.thiagogarbazza.violationbuilder;

import com.github.thiagogarbazza.simplemessage.SimpleMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.thiagogarbazza.simplemessage.SimpleMessageType.ERROR;
import static com.github.thiagogarbazza.simplemessage.SimpleMessageType.WARNING;
import static com.github.thiagogarbazza.violationbuilder.util.AssertMessageUtil.assertMessage;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ViolationBuilderTest {

  private ViolationBuilder violationBuilder;

  @BeforeEach
  void beforeEach() {
    violationBuilder = ViolationBuilder.builder();
  }

  @Test
  void verifyChainOfCalls() {
    final ViolationBuilder violationBuilder = ViolationBuilder.builder()
      .error("key-01", "msg-01")
      .error("key-02", () -> "msg-02")
      .error(true, "key-03", "msg-03")
      .error(true, "key-04", () -> "msg-04")
      .warning("key-01", "msg-01")
      .warning("key-02", () -> "msg-02")
      .warning(true, "key-03", "msg-03")
      .warning(true, "key-04", () -> "msg-04");

    final ViolationException violationException = assertThrows(ViolationException.class, violationBuilder::build);

    assertEquals(8, violationException.getViolations().size());
  }

  @Test
  void verifyDoNotThrowExceptioNoViolations() {
    assertDoesNotThrow(() -> violationBuilder.build());
  }

  @Test
  void verifyDoNotThrowExceptionIgnoringWarningViolations() {
    violationBuilder.warning("warning-key", "some warning message");

    assertDoesNotThrow(() -> violationBuilder.buildIgnoreWarnings());
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

    assertDoesNotThrow(() -> violationBuilder.build());
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

    assertDoesNotThrow(() -> violationBuilder.build());
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

    assertDoesNotThrow(() -> violationBuilder.build());
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

    assertDoesNotThrow(() -> violationBuilder.build());
  }

  @Test
  void verifyThrowExceptionWhenOwningViolationWarnningWithArgsConditionTrue() {
    violationBuilder.warning(true, "warning-key", () -> "some warning message");

    final ViolationException violationException = assertThrows(ViolationException.class, () -> violationBuilder.build());

    final SimpleMessage simpleMessage = violationException.getViolations().iterator().next();
    assertMessage(simpleMessage, WARNING, "warning-key", "some warning message");
  }
}
