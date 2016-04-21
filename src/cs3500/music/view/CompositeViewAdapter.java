package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;
import cs3500.music.model.SoundUnitListToIPlayerModelAdapter;
import cs3500.music.model2.IPlayerModel;


public class CompositeViewAdapter implements cs3500.music.view.ICompositeView {

//  cs3500.music.view2.IGuiView guiView;
//  cs3500.music.view2.IMidiImpl midiView;
  cs3500.music.view2.GuiViewFrame guiView;
  cs3500.music.view2.MidiViewImpl midiView;

  SoundUnitList model;
  IPlayerModel adaptedModel;


  public CompositeViewAdapter(cs3500.music.view2.GuiViewFrame newGuiView, cs3500.music.view2.MidiViewImpl newMidiView) {
    this.guiView = newGuiView;
    this.midiView = newMidiView;
  }


  @Override
  public Note NotePressed(Point mousePoint, SoundUnitList model) {
    int moveOverForBeat = model.getCurrentBeat() * 25;

    //int scrollOffset = guiView.getScroller().getHorizontalScrollBar().getValue();
    int scrollOffset = model.getCurrentBeat() * 25;

    System.out.println("X: " + mousePoint.getX() + " Y: " + mousePoint.getY());




    int separation = 20;

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


        noteStarts = true;
        rangeNote.setEnd(BeatNumber + 1);
        rangeNote.setStart(BeatNumber);
        PossibleSaveNote.add(rangeNote);

        /*
        //rangeNote.setStart();*/

        //If a Note is Starting then Fill it! This takes Priority over the Continue
        if (noteStarts) {
          if (mousePoint.getX() > 40 + (20 * BeatNumber)
                  - moveOverForBeat && mousePoint.getX() - scrollOffset < 40
                  + (20 * BeatNumber) - moveOverForBeat + 20) {
            if (mousePoint.getY() > ((separation * i))
                    + 20 && mousePoint.getY() < ((separation * i)) + 20 + 20) {
              System.out.println("Note Attempted to Delete: " + PossibleSaveNote.getHighestNote().toString());
              return PossibleSaveNote.getHighestNote();
            }
          }
        } else if (noteContinues) {
          if (mousePoint.getX() > 40 + (20 * BeatNumber)
                  - moveOverForBeat && mousePoint.getX() - scrollOffset < 40 + (20 * BeatNumber)
                  - moveOverForBeat + 20) {
            if (mousePoint.getY() > ((separation * i))
                    + 20 && mousePoint.getY() < ((separation * i)) + 20 + 20) {
              System.out.println("Note Attempted to Delete: " + PossibleSaveNote.getHighestNote().toString());
              return PossibleSaveNote.getHighestNote();
            }
          }
        }
      }
    }
    return new Note(SoundUnit.Pitch.D, SoundUnit.Octave.FOUR, 999, 1001);
  }

  @Override
  public Note SpacePressed(Point mousePoint, SoundUnitList model) {
    int moveOverForBeat = model.getCurrentBeat() * 25;

    //int scrollOffset = guiView.getScroller().getHorizontalScrollBar().getValue();
    int scrollOffset = model.getCurrentBeat() * 25;

    System.out.println("X: " + mousePoint.getX() + " Y: " + mousePoint.getY());




    int separation = 20;

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
          if (mousePoint.getX() > 40 + (20 * BeatNumber)
                  - moveOverForBeat && mousePoint.getX() - scrollOffset < 40
                  + (20 * BeatNumber) - moveOverForBeat + 20) {
            if (mousePoint.getY() > ((separation * i))
                    + 20 && mousePoint.getY() < ((separation * i)) + 20 + 20) {
              return PossibleSaveNote.getHighestNote();
            }
          }
        } else if (noteContinues) {
          if (mousePoint.getX() > 40 + (20 * BeatNumber)
                  - moveOverForBeat && mousePoint.getX() - scrollOffset < 40 + (20 * BeatNumber)
                  - moveOverForBeat + 20) {
            if (mousePoint.getY() > ((separation * i))
                    + 20 && mousePoint.getY() < ((separation * i)) + 20 + 20) {
              return PossibleSaveNote.getHighestNote();
            }
          }
        }
      }
    }
    throw new IllegalArgumentException("Invalid Space");
  }

  @Override
  public void addNewMouseListener(MouseListener listener) {
    guiView.addMouseListener(listener);
  }


  @Override
  public void addActionListener(ActionListener listener) { }

  @Override
  public void refreshGuiViewFromModel(SoundUnitList refreshedModel) {
    SoundUnitListToIPlayerModelAdapter PlayerModelAdapted = new SoundUnitListToIPlayerModelAdapter("SampleSong");
    PlayerModelAdapted.setPlayerModelFromSongList(refreshedModel);
    this.guiView = new cs3500.music.view2.GuiViewFrame(PlayerModelAdapted);
    this.adaptedModel = PlayerModelAdapted;
  }

  @Override
  public void initialize() {
    this.guiView.setPreferredSize(new Dimension(800, 1600));
    this.guiView.initialize();
  }

  @Override
  public void resetFocus() {
    this.guiView.setFocusable(true);
    this.guiView.requestFocus();
  }

  @Override
  public void addKeyListener(KeyboardHandler keyHandler) {
    this.guiView.addKeyListener(keyHandler);
  }

  @Override
  public void setVisible(boolean state) {
    this.guiView.setVisible(state);
  }

  @Override
  public void playBeat(Integer BeatNumber) {
    guiView.getModel().setCurrentBeat(BeatNumber);
    guiView.outputView();
    guiView.getPanel().updateUI();
    this.guiView.updateScroll("x", BeatNumber);
    try {
      this.midiView.playBeat(BeatNumber);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    //this.midiView.outputView();
    System.out.println("PlayBeatCalled\n");
  }

  public void render(){
    guiView.outputView();
  }

}
