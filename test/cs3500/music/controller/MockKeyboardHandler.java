package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by daniellenguyen on 4/6/16.
 */
public class MockKeyboardHandler implements KeyListener {
  private StringBuilder mockKeyboardLog;
  public KeyboardHandler keyboardHandler;

  public MockKeyboardHandler() {
    mockKeyboardLog = new StringBuilder();
    keyboardHandler = new KeyboardHandler();
  }

  /**
   * Returns the internal StringBuilder as a string
   */
  public String getStringBuilderAsString() {
    return mockKeyboardLog.toString();
  }

  /**
   * This is called when the view detects that a key has been typed. Find if anything has been
   * mapped to this key character and if so, execute it
   */
  @Override
  public void keyTyped(KeyEvent e) {
    if (keyboardHandler.getKeyTypedMap().containsKey(e.getKeyChar()))
      mockKeyboardLog.append(keyboardHandler.getKeyTypedMap().get(e.getKeyChar()));
  }

  /**
   * This is called when the view detects that a key has been pressed. Find if anything has been
   * mapped to this key code and if so, execute it
   */
  @Override
  public void keyPressed(KeyEvent e) {
    if (keyboardHandler.getKeyPressedMap().containsKey(e.getKeyCode()))
      mockKeyboardLog.append(keyboardHandler.getKeyPressedMap().get(e.getKeyCode()));
  }

  /**
   * This is called when the view detects that a key has been released. Find if anything has been
   * mapped to this key code and if so, execute it
   */
  @Override
  public void keyReleased(KeyEvent e) {
    if (keyboardHandler.getKeyReleasedMap().containsKey(e.getKeyCode()))
      mockKeyboardLog.append(keyboardHandler.getKeyReleasedMap().get(e.getKeyCode()));
  }

}
