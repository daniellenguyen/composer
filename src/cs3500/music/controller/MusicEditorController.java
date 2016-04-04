package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.NoteList;
import cs3500.music.view.ConsoleViewImpl;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;

/**
 * Created by Justin Hynes-Bruell on 3/31/2016.
 */
public class MusicEditorController implements ActionListener {

  private NoteList model;
  private GuiViewFrame guiView;
  private MidiViewImpl midiView;
  private ConsoleViewImpl consoleView;

  public MusicEditorController(NoteList model, GuiViewFrame guiView, MidiViewImpl midiView, ConsoleViewImpl consoleView) {
    this.model = model;
    this.guiView = guiView;
    this.midiView = midiView;
    this.consoleView = consoleView;
    configureKeyBoardListener();
    this.guiView.addActionListener(this);

    try {
      this.guiView.initialize();
      this.midiView.playSong(this.model);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  private void configureKeyBoardListener() {
    Map<Character, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();

    keyTypes.put('r', new Runnable() {
      public void run() {
        ///////////////////////     view.toggleColor();
      }
    });

    keyTypes.put('x', () -> {
      // exchange the hotkeys C and U:
      // Take the event handlers from VK_C and VK_U
      Runnable oldCHandler = keyPresses.get(KeyEvent.VK_C);
      Runnable oldUHandler = keyPresses.get(KeyEvent.VK_U);
      // Update the C handler
      if (oldCHandler != null)
        keyPresses.put(KeyEvent.VK_U, oldCHandler);
      else
        keyPresses.remove(KeyEvent.VK_U);
      // Update the U handler
      if (oldUHandler != null)
        keyPresses.put(KeyEvent.VK_U, oldCHandler);
      else
        keyPresses.remove(KeyEvent.VK_U);
    });

    KeyboardListener kbd = new KeyboardListener();
    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    guiView.addKeyListener(kbd);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    switch (e.getActionCommand()) {
      /*
      //read from the input textfield
      case "Echo Button":
        String text = guiView.getInputString();
        //send text to the model
        model.setString(text);

        //clear input textfield
        guiView.clearInputString();
        //finally echo the string in view
        text = model.getString();
        guiView.setEchoOutput(text);

        //set focus back to main frame so that keyboard events work
        guiView.resetFocus();

        break;
      case "Exit Button":
        System.exit(0);
        break;*/
    }
  }
}
