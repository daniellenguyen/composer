package cs3500.music.controller;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import cs3500.music.model.Note;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;
import cs3500.music.view.CompositeView;
import cs3500.music.view.NoteAdderView;

public class MusicEditorController implements Controller {

  protected SoundUnitList model;
  protected CompositeView compositeView;
  private NoteAdderView noteAdderView;

  private Timer musicTimer;

  private boolean songPlaying;

  /**
   * Constructor for the controller. Does not begin playing.
   * Press "Space" to begin playing song.
   * @param model the input song
   * @param compositeView the input composite view
   */
  public MusicEditorController(SoundUnitList model, CompositeView compositeView) {
    this.model = model;
    this.compositeView = compositeView;
    configureKeyBoardListener();
    configureMouseListener();
    this.compositeView.addActionListener(this);
    this.compositeView.initialize();
    model.setCurrentBeat(0);
    musicTimer = new Timer();
    songPlaying = false;
  }

  /**
   * A TimerTask to synchronize playback of multiple views
   * Is called by {@code playFromCurrentBeat} or {@code playFromBeginning}
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

  @Override
  public void configureMouseListener() {
    MouseHandler listener = new MouseHandler(this);
    this.compositeView.addNewMouseListener(listener);
  }

  @Override
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

    //Home Key Pressed
    kbd.getKeyTypedMap().put((char)24, new Runnable() {
      public void run() {
        System.out.println("Goto Home\n");
        gotoEnd();
      }
    });

    //End Key Pressed
    kbd.getKeyTypedMap().put((char)23, new Runnable() {
      public void run() {
        System.out.println("Goto End\n");
        gotoHome();
      }
    });

    compositeView.addKeyListener(kbd);
  }

  @Override
  public void noteAdderViewCreator() {
    this.compositeView.setVisible(false);
    noteAdderView = new NoteAdderView(model.getLastNote());
    noteAdderView.resetFocus();
    noteAdderView.addActionListener(this);
  }

  @Override
  public void playFromBeginning() {
    this.model.setCurrentBeat(0);
    playFromCurrentBeat();
  }

  @Override
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

  @Override
  public boolean IsSongOver() {
    return this.model.getCurrentBeat() >= this.model.songLength();
  }

  @Override
  public void RenderNewBeat() {
    this.compositeView.playBeat(this.model.getCurrentBeat());
    arrowRight();
  }

  @Override
  public void arrowRight() {
    this.model.setCurrentBeat(this.model.getCurrentBeat() + 1);
    this.compositeView.render();
  }

  @Override
  public void arrowLeft() {
    this.model.setCurrentBeat(this.model.getCurrentBeat() - 1);
    this.compositeView.render();
  }

  @Override
  public boolean CheckForNote(Point mousePoint) {
    int separation = 15;
    int rangeOfSong = model.getHighestNote().getMIDIPitch() -
            model.getLowestNote().getMIDIPitch();
    // If before, after, above, or below the grid, return false
    return !(mousePoint.getX() < 40 ||
            mousePoint.getY() < 15);
  }

  @Override
  public void deleteNote(Point mousePoint) {
    //If there is in Note Space
    if (CheckForNote(mousePoint)) {
      //If note is Not Blank
      try {
        Note newNote = NotePressed(mousePoint);
        model.delete(newNote);
        System.out.println(newNote.toString() + " Deleted");
        System.out.println("Start: " + newNote.getStart() + " End: " + newNote.getEnd());
        this.compositeView.render();
      } catch (IllegalArgumentException e) {
        System.out.println(e.toString());
      }
    }
  }

  @Override
  public void moveNote(Point Start, Point End, int xSeparation, int ySeparation) {
    if (CheckForNote(Start) && CheckForNote(End)) {
        try {
          if (!NotePressed(Start).equals(new Note(SoundUnit.Pitch.C,
                  SoundUnit.Octave.FOUR, 999, 1000)) &&
                  !NotePressed(End).equals(new Note(SoundUnit.Pitch.C,
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

              System.out.println(copyNote.toString() + " Moved!");
              System.out.println("Start: " + copyNote.getStart()
                      + " End: " + copyNote.getEnd());
              this.compositeView.render();
            }
          }
         catch (IllegalArgumentException e) {
          System.out.println(e.toString());
        }
      }
    }

  @Override
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
          this.compositeView.render();
        }
      } catch (IllegalArgumentException e) {
        System.out.println(e.toString());
      }
    }
  }

  @Override
  public Note NotePressed(Point mousePoint) {
    return compositeView.NotePressed(mousePoint, model);
  }

  @Override
  public Note SpacePressed(Point mousePoint) {
    return compositeView.SpacePressed(mousePoint, model);
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

  @Override
  public void exitFromNoteAdder() {
    noteAdderView.setVisible(false);
    this.compositeView.refreshGuiViewFromModel(model);
    //this.compositeView.setGuiView(new GuiViewFrame(model));
    this.compositeView.initialize();
    this.compositeView.resetFocus();
    this.compositeView.addActionListener(this);
    configureKeyBoardListener();
    configureMouseListener();
  }

  @Override
  public void gotoEnd(){
    this.model.setCurrentBeat(this.model.songLength());
    this.compositeView.render();
  }

  @Override
  public void gotoHome(){
    this.model.setCurrentBeat(0);
    this.compositeView.render();
  }

}
