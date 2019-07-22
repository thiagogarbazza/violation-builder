package com.github.thiagogarbazza.violationbuilder.util;

import com.github.thiagogarbazza.simplemessage.SimpleMessage;
import com.github.thiagogarbazza.simplemessage.SimpleMessageType;
import lombok.experimental.UtilityClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

@UtilityClass
public class AssertMessageUtil {

  public static void assertMessage(final SimpleMessage simpleMessage, final SimpleMessageType type, final String key, final String content) {
    assertEquals(type, simpleMessage.getType());
    assertEquals(key, simpleMessage.getKey());
    assertEquals(content, simpleMessage.getContent());
  }
}
