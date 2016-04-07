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

  //TODO allow controller to do previous functionality
  public MusicEditorController(SoundUnitList model, CompositeView compositeView)   {
    this.model = model;
    this.compositeView = compositeView;
    configureKeyBoardListener();
    configureMouseListener();
    this.compositeView.getGuiView().addActionListener(this);
    this.compositeView.getGuiView().initialize();
    model.setCurrentBeat(0);
    musicTimer = new Timer();
    songPlaying = false;
    playFromBeginning();
  }

  public CompositeView getCompositeView() {
    return compositeView;
  }

  /**
   * A TimerTask to synchronize playback of multiple views
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

  public void configureMouseListener() {
    MouseHandler listener = new MouseHandler(this);
    this.compositeView.getGuiView().addNewMouseListener(listener);
  }

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

  public void noteAdderViewCreator() {
    this.compositeView.getGuiView().setVisible(false);
    noteAdderView = new NoteAdderView(model.getLastNote());
    noteAdderView.resetFocus();
    noteAdderView.addActionListener(this);
  }

  public void playFromBeginning() {
    this.model.setCurrentBeat(0);
    playFromCurrentBeat();
  }

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

  public boolean IsSongOver() {
    if (this.model.getCurrentBeat() >= this.model.songLength()) {
      return true;
    } else {
      return false;
    }
  }

  public void RenderNewBeat() {
    try {
      this.compositeView.getMidiView().playBeat(this.model.getCurrentBeat());
      arrowRight();
    } catch (InvalidMidiDataException e) {
      //arrowRight();
    }
  }

  public void arrowRight() {
    this.model.setCurrentBeat(this.model.getCurrentBeat() + 1);
    this.compositeView.getGuiView().render();
  }

  public void arrowLeft() {
    this.model.setCurrentBeat(this.model.getCurrentBeat() - 1);
    this.compositeView.getGuiView().render();
  }

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

  public Note NotePressed(Point mousePoint) {
    int moveOverForBeat = model.getCurrentBeat() * 25;

    int separation = 15;

    int rangeOfSong = model.getHighestNote().getMIDIPitch() -
            model.getLowestNote().getMIDIPitch();
    //For each Beat in the song Draws the Notes
    for (int BeatNumber = 0; BeatNumber < model.songLength(); BeatNumber++) {
      ArrayList<SoundUnit> ListOfNotesAtBeat = new ArrayList<>();
      ListOfNotesAtBeat.addAll(model.getAllAtTime(BeatNumber));

      //Create a Column of Notes
      for (int i = rangeOfSong; i >= 0; i--) {
        SoundUnit rangeNote = new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 0, 1);
        rangeNote.setPitchAndOctaveFromMIDI(model.getHighestNote().getMIDIPitch() - i);

        boolean noteStarts = false;
        boolean noteContinues = false;

        SoundUnitList PossibleSaveNote = new NoteList();


        //Check if a note is Starting or Continuing
        for (int j = 0; j < ListOfNotesAtBeat.size(); j++) {
          if (rangeNote.getMIDIPitch() == ListOfNotesAtBeat.get(j).getMIDIPitch()) {

            if (ListOfNotesAtBeat.get(j).getStart() == BeatNumber) {
              noteStarts = true;
              PossibleSaveNote.add(ListOfNotesAtBeat.get(j));
            } else {
              noteContinues = true;
              PossibleSaveNote.add(ListOfNotesAtBeat.get(j));
            }
          }
        }

        if (!noteStarts && !noteContinues) {
          noteStarts = true;
          rangeNote.setEnd(BeatNumber + 1);
          rangeNote.setStart(BeatNumber);
          PossibleSaveNote.add(rangeNote);
        }

        /*
        //rangeNote.setStart();*/

        //If a Note is Starting then Fill it! This takes Priority over the Continue
        if (noteStarts) {
          if (mousePoint.getX() > 40 + (25 * BeatNumber)
                  - moveOverForBeat && mousePoint.getX() < 40 + (25 * BeatNumber)
                  - moveOverForBeat + 25) {
            if (mousePoint.getY() > ((separation * i)) + 15 && mousePoint.getY()
                    < ((separation * i)) + 15 + 15) {
              return PossibleSaveNote.getHighestNote();
            }
          }
        } else if (noteContinues) {
          if (mousePoint.getX() > 40 + (25 * BeatNumber)
                  - moveOverForBeat && mousePoint.getX()
                  < 40 + (25 * BeatNumber) - moveOverForBeat + 25) {
            if (mousePoint.getY() > ((separation * i))
                    + 15 && mousePoint.getY() < ((separation * i)) + 15 + 15) {
              return PossibleSaveNote.getHighestNote();
            }
          }
        }
      }
    }
    return new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 999, 1000);
  }

  public Note SpacePressed(Point mousePoint) {
    int moveOverForBeat = model.getCurrentBeat() * 25;

    int separation = 15;

    int rangeOfSong = model.getHighestNote().getMIDIPitch() -
            model.getLowestNote().getMIDIPitch();
    //For each Beat in the song Draws the Notes
    for (int BeatNumber = 0; BeatNumber < model.songLength(); BeatNumber++) {
      ArrayList<SoundUnit> ListOfNotesAtBeat = new ArrayList<>();
      ListOfNotesAtBeat.addAll(model.getAllAtTime(BeatNumber));

      //Create a Column of Notes
      for (int i = rangeOfSong; i >= 0; i--) {
        SoundUnit rangeNote = new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 0, 1);
        rangeNote.setPitchAndOctaveFromMIDI(model.getHighestNote().getMIDIPitch() - i);

        boolean noteStarts = false;
        boolean noteContinues = false;

        SoundUnitList PossibleSaveNote = new NoteList();



          noteStarts = true;
          rangeNote.setEnd(BeatNumber + 1);
          rangeNote.setStart(BeatNumber);
          PossibleSaveNote.add(rangeNote);

        /*
        //rangeNote.setStart();*/

        //If a Note is Starting then Fill it! This takes Priority over the Continue
        if (noteStarts) {
          if (mousePoint.getX() > 40 + (25 * BeatNumber)
                  - moveOverForBeat && mousePoint.getX() < 40
                  + (25 * BeatNumber) - moveOverForBeat + 25) {
            if (mousePoint.getY() > ((separation * i))
                    + 15 && mousePoint.getY() < ((separation * i)) + 15 + 15) {
              return PossibleSaveNote.getHighestNote();
            }
          }
        } else if (noteContinues) {
          if (mousePoint.getX() > 40 + (25 * BeatNumber)
                  - moveOverForBeat && mousePoint.getX() < 40 + (25 * BeatNumber)
                  - moveOverForBeat + 25) {
            if (mousePoint.getY() > ((separation * i))
                    + 15 && mousePoint.getY() < ((separation * i)) + 15 + 15) {
              return PossibleSaveNote.getHighestNote();
            }
          }
        }
      }
    }
    return new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 999, 1000);
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

  public void exitFromNoteAdder() {
    noteAdderView.setVisible(false);
    this.compositeView.setGuiView(new GuiViewFrame(model));
    this.compositeView.getGuiView().initialize();
    this.compositeView.getGuiView().resetFocus();
    this.compositeView.getGuiView().addActionListener(this);
    configureKeyBoardListener();
    configureMouseListener();
  }
}
