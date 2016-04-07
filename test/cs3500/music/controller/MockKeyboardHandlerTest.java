package cs3500.music.controller;

import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;
import static org.junit.Assert.*;

/**
 * Uses the MockKeyboardHandler to test the KeyboardHandler
 */
public class MockKeyboardHandlerTest {


  @Test
  public void BasicTest() {
    MockKeyboardHandler m = new MockKeyboardHandler();

    m.keyboardHandler.getKeyTypedMap().put('>', new Runnable() {
      public void run() {
        m.setStringBuilder("I ran!");
      }
    });
    assertEquals("", m.getStringBuilderAsString());
  }

  @Test
  public void KeyTypedTest() {
    MockKeyboardHandler m = new MockKeyboardHandler();

    Component asd = new Component() {
      @Override
      public String getName() {
        return "a";
      }
    };


    KeyEvent ke = new KeyEvent( asd, KeyEvent.KEY_PRESSED,
            0,                          // When timeStamp
            0,                          // Modifier
            KeyEvent.VK_UNDEFINED,      // Key Code
            KeyEvent.CHAR_UNDEFINED );  // Key Char

    m.keyPressed(ke);


    m.keyboardHandler.getKeyTypedMap().put('>', new Runnable() {
      public void run() {
        m.setStringBuilder("I ran!");
      }
    });
    assertEquals("", m.getStringBuilderAsString());
  }

  @Test
  public void keyPressedTest2() {
    MockKeyboardHandler m = new MockKeyboardHandler();

    Component asd = new Component() {
      @Override
      public String getName() {
        return "B";
      }
    };

    KeyEvent ke = new KeyEvent( asd, KeyEvent.KEY_PRESSED,
            0,                          // When timeStamp
            0,                          // Modifier
            KeyEvent.VK_A,      // Key Code
            KeyEvent.CHAR_UNDEFINED );  // Key Char

    m.keyPressed(ke);


    m.keyboardHandler.getKeyTypedMap().put('>', new Runnable() {
      public void run() {
        m.setStringBuilder("I ran!");
      }
    });
    assertEquals("", m.getStringBuilderAsString());
  }

  @Test
  public void keyReleasedTest() {
    MockKeyboardHandler m = new MockKeyboardHandler();

    Component asd = new Component() {
      @Override
      public String getName() {
        return "B";
      }
    };


    KeyEvent ke = new KeyEvent( asd, KeyEvent.KEY_PRESSED,
            0,                          // When timeStamp
            0,                          // Modifier
            KeyEvent.VK_A,      // Key Code
            KeyEvent.CHAR_UNDEFINED );  // Key Char

    m.keyReleased(ke);


    m.keyboardHandler.getKeyTypedMap().put('>', new Runnable() {
      public void run() {
        m.setStringBuilder("I ran!");
      }
    });
    assertEquals("", m.getStringBuilderAsString());
  }
}
