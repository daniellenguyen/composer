package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.Note;
import cs3500.music.model.SoundUnitList;
import cs3500.music.view.ConsoleViewImpl;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.NoteAdderView;

public class MusicEditorController implements ActionListener {

  private SoundUnitList model;
  private GuiViewFrame guiView;
  private MidiViewImpl midiView;
  private ConsoleViewImpl consoleView;
  private NoteAdderView noteAdderView;

  private Timer musicTimer;

  private boolean songPlaying;


  public MusicEditorController(SoundUnitList model, GuiViewFrame guiView,
                               MidiViewImpl midiView, ConsoleViewImpl consoleView) {
    this.model = model;
    this.guiView = guiView;
    this.midiView = midiView;
    this.consoleView = consoleView;
    configureKeyBoardListener();
    this.guiView.addActionListener(this);
    this.guiView.initialize();
    model.setCurrentBeat(0);
    musicTimer = new Timer();
    songPlaying = false;
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

    keyTypes.put('>', new Runnable() {
      public void run() {
        System.out.println("Right Arrow Key Pressed:\n");
        arrowRight();
      }
    });

    keyTypes.put('<', new Runnable() {
      public void run() {
        System.out.println("Left Arrow Key Pressed:\n");
        arrowLeft();
      }
    });

    keyTypes.put('p', new Runnable() {
      public void run() {
        System.out.println("Play Song From Begin\n");
        playFromBeginning();
      }
    });

    keyTypes.put(' ', new Runnable() {
      public void run() {
        System.out.println("Play Song From Current Beat / Stop Song\n");
        playFromCurrentBeat();
      }
    });

    KeyboardHandler kbd = new KeyboardHandler();
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

  private void playFromBeginning(){
    this.model.setCurrentBeat(0);
    playFromCurrentBeat();
  }

  private void playFromCurrentBeat(){
    if(songPlaying){
      musicTimer.cancel();
      songPlaying = false;
      musicTimer = new Timer();
    }
    else {
      songPlaying = true;
      int Delay = (this.model.getTempo()/1000);
      musicTimer.schedule(new timerTask(this), 0, Delay);
    }
  }

  class timerTask extends TimerTask {

    private MusicEditorController controller;
    public timerTask(MusicEditorController controller){
      this.controller = controller;
    }
    @Override
    public void run() {
      System.out.println("Playing a Beat\n");
      controller.RenderNewBeat();
      if(IsSongOver()){
        this.cancel();
      }
    }
  }

  public boolean IsSongOver(){
    if(this.model.getCurrentBeat() >= this.model.songLength()){
      return true;
    }
    else {
      return false;
    }
  }

  public void RenderNewBeat(){
    try {
      this.midiView.playBeat(this.model, this.model.getCurrentBeat());
      arrowRight();
    } catch (InvalidMidiDataException e) {
      //arrowRight();
    }
  }

  private void arrowRight(){
    this.model.setCurrentBeat(this.model.getCurrentBeat() + 1);
    guiView.Render(model);
  }

  private void arrowLeft(){
    this.model.setCurrentBeat(this.model.getCurrentBeat()-1);
    guiView.Render(model);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
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
              Integer.valueOf(noteAdderView.getInputDuration())
                      +Integer.valueOf(noteAdderView.getInputStart()));
        noteToAdd.setVolume(Integer.valueOf(noteAdderView.getInputVolume().toString()));
        noteToAdd.setInstrument(Integer.valueOf(noteAdderView.getInputInstrument().toString()));
        model.add(noteToAdd);
        model.setLastNote(noteToAdd);
        exitFromNoteAdder();
        break;

      case "Exit Button":
        exitFromNoteAdder();
        //System.exit(0);
        break;
    }
  }

  private void exitFromNoteAdder(){
    noteAdderView.setVisible(false);
    guiView = new GuiViewFrame(model);
    guiView.initialize();
    guiView.resetFocus();
    guiView.addActionListener(this);
    configureKeyBoardListener();
  }
}
