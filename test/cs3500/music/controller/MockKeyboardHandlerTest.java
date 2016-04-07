package cs3500.music.controller;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Uses the MockKeyboardHandler to test the KeyboardHandler
 */
public class MockKeyboardHandlerTest {

  @Test
  public void keyTypedTest() {
    MockKeyboardHandler m = new MockKeyboardHandler();
    m.keyboardHandler.getKeyTypedMap().put('>', new Runnable() {
      public void run() {
      }
    });
    assertEquals(">", m.getStringBuilderAsString());
  }
}
