package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.Note;
import cs3500.music.model.SoundUnitList;
import cs3500.music.view.ConsoleViewImpl;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.NoteAdderView;

/**
 * Created by Justin Hynes-Bruell on 3/31/2016.
 */
public class MusicEditorController implements ActionListener {

  private SoundUnitList model;
  private GuiViewFrame guiView;
  private MidiViewImpl midiView;
  private ConsoleViewImpl consoleView;
  private NoteAdderView noteAdderView;

  public MusicEditorController(SoundUnitList model, GuiViewFrame guiView, MidiViewImpl midiView, ConsoleViewImpl consoleView) {
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

    keyTypes.put('a', new Runnable() {
      public void run() {
        System.out.println("Set Note Button Pressed:\n");
        noteAdderViewCreator();
      }
    });

    KeyboardListener kbd = new KeyboardListener();
    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    guiView.addKeyListener(kbd);
  }

  private void noteAdderViewCreator(){
    guiView.setVisible(false);
    noteAdderView = new NoteAdderView(model.getLastNote());
    noteAdderView.resetFocus();
    noteAdderView.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    switch (e.getActionCommand()) {
      //read from the input textfield
      case "Set Note Button":
        System.out.println("\n" + "Pitch: ");
        System.out.println(noteAdderView.getInputPitch());
        System.out.println("\n" + "Octave: ");
        System.out.println(noteAdderView.getInputOctave());
        System.out.println("\n" + "Start: ");
        System.out.println(noteAdderView.getInputStart().toString());
        System.out.println("\n" + "Duration: ");
        System.out.println(noteAdderView.getInputDuration());
        System.out.println("\n" + "Volume: ");
        System.out.println(noteAdderView.getInputVolume().toString());
        System.out.println("\n" + "Instrument: ");
        System.out.println(noteAdderView.getInputInstrument());
        System.out.println("\n\n");


        Note noteToAdd = new Note(noteAdderView.getInputPitchEnum(),
              noteAdderView.getInputOctaveEnum(), Integer.valueOf(noteAdderView.getInputStart()),
              Integer.valueOf(noteAdderView.getInputDuration()));
        noteToAdd.setVolume(Integer.valueOf(noteAdderView.getInputStart().toString()));
        noteToAdd.setInstrument(Integer.valueOf(noteAdderView.getInputVolume().toString()));
        model.add(noteToAdd);
        model.setLastNote(noteToAdd);
        break;

      case "Exit Button":
        noteAdderView.setVisible(false);
        guiView = new GuiViewFrame(model);
        guiView.initialize();
        guiView.resetFocus();
        guiView.addActionListener(this);
        configureKeyBoardListener();

        //System.exit(0);
        break;
    }
  }
}
