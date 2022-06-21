package com.github.thiagogarbazza.violationbuilder.util;

import com.github.thiagogarbazza.simplemessage.SimpleMessage;
import com.github.thiagogarbazza.simplemessage.SimpleMessageType;
import lombok.experimental.UtilityClass;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@UtilityClass
public class AssertMessage {

  public static void assertMessage(final SimpleMessage simpleMessage, final SimpleMessageType type, final String key, final String content) {
    assertAll("Should all fields are correct",
      () -> assertEquals(type, simpleMessage.getType(), "The type of message is incorrect"),
      () -> assertEquals(key, simpleMessage.getKey(), "The key of message is incorrect"),
      () -> assertEquals(content, simpleMessage.getContent(), "The content of message is incorrect")
    );
  }
}
