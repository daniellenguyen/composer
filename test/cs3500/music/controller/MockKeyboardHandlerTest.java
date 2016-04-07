package cs3500.music.controller;

import org.junit.Test;

import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

/**
 * Uses the MockKeyboardHandler to test the KeyboardHandler
 */
public class MockKeyboardHandlerTest {



  /**
   * The three methods being tested below are stubs.
   */

  @Test
  public void keyTypedTest() {
    MockKeyboardHandler m = new MockKeyboardHandler();
    m.keyboardHandler.getKeyTypedMap().put('>', new Runnable() {
      public void run() {
        m.setStringBuilder("I ran!");
      }
    });
    assertEquals("", m.getStringBuilderAsString());
  }
//
//  @Test
//  public void keyPressedTest() {
//    MockKeyboardHandler m = new MockKeyboardHandler();
//    m.keyboardHandler.getKeyPressedMap().put(1, new Runnable() {
//      public void run() {
//      }
//    });
//    assertEquals("", m.getStringBuilderAsString());
//  }
//
//  @Test
//  public void keyReleasedTest() {
//    MockKeyboardHandler m = new MockKeyboardHandler();
//    m.keyboardHandler.getKeyReleasedMap().put(2, new Runnable() {
//      public void run() {
//      }
//    });
//    assertEquals("", m.getStringBuilderAsString());
//  }
}
