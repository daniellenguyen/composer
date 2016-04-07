package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;

/**
 * Created by Justin Hynes-Bruell on 4/6/2016.
 */
public class CompositeView implements View {
  GuiViewFrame guiViewFrame;
  MidiViewImpl midiView;

  public CompositeView(GuiViewFrame guiViewFrame, MidiViewImpl midiView){
    this.guiViewFrame = guiViewFrame;
    this.midiView = midiView;
  }

  public GuiViewFrame getGuiView() {
    return guiViewFrame;
  }

  public void setGuiView(GuiViewFrame guiView) {
    guiViewFrame = guiView;
  }

  public MidiViewImpl getMidiView() {
    return midiView;
  }

  public Note NotePressed(Point mousePoint, SoundUnitList model) {
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

  public Note SpacePressed(Point mousePoint, SoundUnitList model) {
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
  public void render() {

  }
}
