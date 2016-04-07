package cs3500.music.controller;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;
import cs3500.music.view.CompositeView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.NoteAdderView;

public class MusicEditorController implements ActionListener {

  private SoundUnitList model;
  private CompositeView compositeView;
  private NoteAdderView noteAdderView;

  private Timer musicTimer;

  private boolean songPlaying;

  /**
   * Constructor for the controller. Does not begin playing. Press "Space" to begin playing song.
   * @param model
   * @param compositeView
   */
  public MusicEditorController(SoundUnitList model, CompositeView compositeView) {
    this.model = model;
    this.compositeView = compositeView;
    configureKeyBoardListener();
    configureMouseListener();
    this.compositeView.getGuiView().addActionListener(this);
    this.compositeView.getGuiView().initialize();
    model.setCurrentBeat(0);
    musicTimer = new Timer();
    songPlaying = false;
  }

  /**
   * A TimerTask to synchronize playback of multiple views Is called by playFromCurrentBeat or
   * playFrom Beginning
   */
  class timerTask extends TimerTask {

    private MusicEditorController controller;

    public timerTask(MusicEditorController controller) {
      this.controller = controller;
    }

    @Override
    public void run() {
      System.out.println("Playing a Beat\n");
      controller.RenderNewBeat();
      if (IsSongOver()) {
        this.cancel();
      }
    }
  }

  /**
   * Sets up the Mouse Listeners
   */
  public void configureMouseListener() {
    MouseHandler listener = new MouseHandler(this);
    this.compositeView.getGuiView().addNewMouseListener(listener);
  }

  /**
   * Configure the Keyboard listeners
   */
  public void configureKeyBoardListener() {
    KeyboardHandler kbd = new KeyboardHandler();

    kbd.getKeyTypedMap().put('a', new Runnable() {
      public void run() {
        System.out.println("Set Note Button Pressed:\n");
        noteAdderViewCreator();
      }
    });

    kbd.getKeyTypedMap().put('>', new Runnable() {
      public void run() {
        System.out.println("Right Arrow Key Pressed:\n");
        arrowRight();
      }
    });

    kbd.getKeyTypedMap().put('<', new Runnable() {
      public void run() {
        System.out.println("Left Arrow Key Pressed:\n");
        arrowLeft();
      }
    });

    kbd.getKeyTypedMap().put('p', new Runnable() {
      public void run() {
        System.out.println("Play Song From Begin\n");
        playFromBeginning();
      }
    });

    kbd.getKeyTypedMap().put(' ', new Runnable() {
      public void run() {
        System.out.println("Play Song From Current Beat / Stop Song\n");
        playFromCurrentBeat();
      }
    });
    compositeView.getGuiView().addKeyListener(kbd);
  }

  /**
   * Creates a new view for adding very specific notes. This allows for notes out of the range of
   * the current gui pitch and length to be created. As well as specific notes of instrument and
   * volume
   */
  public void noteAdderViewCreator() {
    this.compositeView.getGuiView().setVisible(false);
    noteAdderView = new NoteAdderView(model.getLastNote());
    noteAdderView.resetFocus();
    noteAdderView.addActionListener(this);
  }

  /**
   * Sets Beat to zero and starts timer to Begin playing the song
   */
  public void playFromBeginning() {
    this.model.setCurrentBeat(0);
    playFromCurrentBeat();
  }

  /**
   * Starts timer from current beat
   */
  public void playFromCurrentBeat() {
    if (songPlaying) {
      musicTimer.cancel();
      songPlaying = false;
      musicTimer = new Timer();
    } else {
      songPlaying = true;
      int Delay = (this.model.getTempo() / 1000);
      musicTimer.schedule(new timerTask(this), 0, Delay);
    }
  }

  /**
   * Checks if the song is at it's last beat
   *
   * @return true if over
   */
  public boolean IsSongOver() {
    if (this.model.getCurrentBeat() >= this.model.songLength()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Updates the Gui and Midi View for a new beat
   */
  public void RenderNewBeat() {
    try {
      this.compositeView.getMidiView().playBeat(this.model.getCurrentBeat());
      arrowRight();
    } catch (InvalidMidiDataException e) {
      //arrowRight();
    }
  }

  /**
   * Updates the Gui to the Right
   */
  public void arrowRight() {
    this.model.setCurrentBeat(this.model.getCurrentBeat() + 1);
    this.compositeView.getGuiView().render();
  }

  /**
   * Updates to Gui to the left
   */
  public void arrowLeft() {
    this.model.setCurrentBeat(this.model.getCurrentBeat() - 1);
    this.compositeView.getGuiView().render();
  }

  /**
   * Checks to ensure that the area pressed in within the range of the gui
   *
   * @return true if in a viable area
   */
  public boolean CheckForNote(Point mousePoint) {
    int separation = 15;
    int rangeOfSong = model.getHighestNote().getMIDIPitch() -
            model.getLowestNote().getMIDIPitch();
    //If before the Grid
    if (mousePoint.getX() < 40) {
      return false;
    }
    //if After the Grid
    else if (mousePoint.getX() > 40 + (25 * model.songLength())
            - model.getCurrentBeat() * 25) {
      return false;
    }
    //if Above the Grid
    else if (mousePoint.getY() < 15) {
      return false;
    }
    //if Below the Grid
    else if (mousePoint.getY() > separation * rangeOfSong + 30) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Deletes a Note from the model that is detected from the gui
   */
  public void deleteNote(Point mousePoint) {
    //If there is in Note Space
    if (CheckForNote(mousePoint)) {
      //If note is Not Blank
      try {
        Note newNote = NotePressed(mousePoint);
        model.delete(newNote);
        System.out.println(newNote.toString() + " Deleted");
        System.out.println("Start: " + newNote.getStart() + " End: " + newNote.getEnd());
        this.compositeView.getGuiView().render();
      } catch (IllegalArgumentException e) {
        System.out.println(e.toString());
      }
    }
  }

  /**
   * Moves the Note from one Pitch or Starting point to another, accounting for Duration
   */
  public void moveNote(Point Start, Point End, int xSeparation, int ySeparation) {
    if (CheckForNote(Start)) {
      if (CheckForNote(End)) {
        try {
          if (!NotePressed(Start).equals(new Note(SoundUnit.Pitch.C,
                  SoundUnit.Octave.FOUR, 999, 1000))) {
            if (!NotePressed(End).equals(new Note(SoundUnit.Pitch.C,
                    SoundUnit.Octave.FOUR, 999, 1000))) {
              //Determine What note it is
              int MoveX = xSeparation / 25;
              int MoveY = ySeparation / 25;

              System.out.println("X: " + MoveX + " Y: " + MoveY + "\n");

              //Find Old Note
              Note OldNote = NotePressed(Start);

              //Find New Note
              Note newNote = NotePressed(End);

              //Copy Note
              Note copyNote = new Note(newNote.getPitch(), newNote.getOctave(),
                      OldNote.getStart() + MoveX, OldNote.getEnd() + MoveX);
              copyNote.setInstrument(OldNote.getInstrument());
              copyNote.setVolume(OldNote.getVolume());

              //Delete Old Note
              model.delete(OldNote);

              model.add(copyNote);

              System.out.println(copyNote.toString() + " Added!");
              System.out.println("Start: " + copyNote.getStart()
                      + " End: " + copyNote.getEnd());
              this.compositeView.getGuiView().render();
            }
          }
        } catch (IllegalArgumentException e) {
          System.out.println(e.toString());
        }
      }
    }
  }

  /**
   * Adds to the model the pitch of the note pressed at that starting point. If dragged will create
   * a longer note.
   */
  public void addNote(Point Begin, int separation) {
    //If there is in Note Space
    if (CheckForNote(Begin)) {

      try {
        if (!NotePressed(Begin).equals(new Note(SoundUnit.Pitch.C,
                SoundUnit.Octave.FOUR, 999, 1000))) {
          //Determine What note it is
          Note newNote = SpacePressed(Begin);

          int Duration = separation / 25;

          System.out.println(Duration + "\n");

          newNote.setEnd(SpacePressed(Begin).getStart() + Duration + 1);

          model.add(newNote);
          System.out.println(SpacePressed(Begin).toString() + " Added!");
          System.out.println("Start: " + newNote.getStart() + " End: " + newNote.getEnd());
          this.compositeView.getGuiView().render();
        }
      } catch (IllegalArgumentException e) {
        System.out.println(e.toString());
      }
    }
  }

  /**
   * Determines what note was pressed based on it's area in the gui
   * @param mousePoint
   * @return
   */
  public Note NotePressed(Point mousePoint) {
    return compositeView.NotePressed(mousePoint, model);
  }

  /**
   * Determines what space (note or Potential Note) was pressed based on area pressed on the gui
   * @param mousePoint
   * @return
   */
  public Note SpacePressed(Point mousePoint) {
    return compositeView.SpacePressed(mousePoint, model);
  }

  /**
   * Used for Buttons on the add Note Gui
   * @param e
   */
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
                noteAdderView.getInputOctaveEnum(),
                Integer.valueOf(noteAdderView.getInputStart()),
                Integer.valueOf(noteAdderView.getInputDuration())
                        + Integer.valueOf(noteAdderView.getInputStart()));
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

  /**
   * Closes the adder gui and returns to a refreshed gui view.
   */
  public void exitFromNoteAdder() {
    noteAdderView.setVisible(false);
    this.compositeView.setGuiView(new GuiViewFrame(model));
    this.compositeView.getGuiView().initialize();
    this.compositeView.getGuiView().resetFocus();
    this.compositeView.getGuiView().addActionListener(this);
    configureKeyBoardListener();
    configureMouseListener();
  }

  /**
   * Gives back composite view. (For testing)
   * @return composite view of the controller
   */
  public CompositeView getCompositeView() {
    return compositeView;
  }
}
