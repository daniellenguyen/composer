package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

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
  }

  private void configureKeyBoardListener() {
    Map<Character, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();

    ////keyPresses.put(KeyEvent.VK_C, new MakeCaps());
    ////keyReleases.put(KeyEvent.VK_C, new MakeOriginalCase());
    // Another possible syntax: instead of defining a new class, just to make a single instance,
    // you can create an "anonymous class" that implements a particular interface, by writing
    // "new Interfacename() { all the methods you need to implement }"
    // Note that "view" is in scope inside this Runnable!  But, also note that within the Runnable,
    // "this" refers to the Runnable and not to the Controller, so we don't say "this.view".
    keyTypes.put('r', new Runnable() {
      public void run() {
        ///////////////////////     view.toggleColor();
      }
    });

    // Another possible syntax:
    // Instead of an anonymous class, you can (as of Java 8) use "lambda syntax",
    // as follows: if the interface you want to implement has only one single method,
    // then write the parenthesized argument list, followed by an arrow, followed by
    // the body of the method.  Java will infer that you mean to implement the particular
    // single method of that interface, and translate the code for you to resemble the
    // anonymous Runnable example above.
    // Again note all the names that are in scope.
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
