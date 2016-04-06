package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.music.model.Note;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;

/**
 * A dummy view that simply draws a string
 */
public class ConcreteGuiViewPanel extends JPanel {

  SoundUnitList noteList;

  /**
   * Constructor Grabs data about Notelist from the constructor of th GuiViewFram. This is added
   * here to make it easier to paint when the time comes
   */
  public ConcreteGuiViewPanel(SoundUnitList inputSong) {
    this.noteList = inputSong;
  }


  /**
   * Paints the GuiWithNotes similar to the way the console does. Has no action Functionality added
   * yet
   * @param g Graphics
   */
  @Override
  public void paint(Graphics g) {
    paintComponent(g);
  }

  @Override public void paintComponent(Graphics g) {
    super.paintComponent(g);

    int moveOverForBeat = noteList.getCurrentBeat()*25;

    int separation = 15;

    int rangeOfSong = noteList.getHighestNote().getMIDIPitch() -
            noteList.getLowestNote().getMIDIPitch();

    //Iterate Through the Range to create Side Header with Pitch Values of Range
    for (int i = rangeOfSong; i >= 0; i--) {
      SoundUnit rangeNote = new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 0, 1) {
      };
      rangeNote.setPitchAndOctaveFromMIDI(noteList.getHighestNote().getMIDIPitch() - i);
      g.drawString(rangeNote.toString(), 15 - moveOverForBeat, separation * i + 30);
    }


    //For each Beat in the song Draws the Notes
    for (int BeatNumber = 0; BeatNumber < noteList.songLength(); BeatNumber++) {
      ArrayList<SoundUnit> ListOfNotesAtBeat = new ArrayList<>();
      ListOfNotesAtBeat.addAll(noteList.getAllAtTime(BeatNumber));

      //Create a Column of Notes
      for (int i = rangeOfSong; i >= 0; i--) {
        SoundUnit rangeNote = new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 0, 1);
        rangeNote.setPitchAndOctaveFromMIDI(noteList.getHighestNote().getMIDIPitch() - i);

        boolean noteStarts = false;
        boolean noteContinues = false;

        //Check if a note is Starting or Continuing
        for (int j = 0; j < ListOfNotesAtBeat.size(); j++) {
          if (rangeNote.getMIDIPitch() == ListOfNotesAtBeat.get(j).getMIDIPitch()) {
            if (ListOfNotesAtBeat.get(j).getStart() == BeatNumber) {
              noteStarts = true;
            } else {
              noteContinues = true;
            }
          }
        }

        //If a Note is Starting then Fill it! This takes Priority over the Continue
        if (noteStarts) {
          //g.drawString(rangeNote.toString(), 15*BeatNumber + 30, separation * i + 30);
          g.fillRect(40 + (25 * BeatNumber) - moveOverForBeat, ((separation * i)) + 15, 25, 15);
        } else if (noteContinues) {
          g.setColor(Color.GREEN);
          //g.drawString(rangeNote.toString(), 15*BeatNumber + 30, separation * i + 30);
          g.fillRect(40 + (25 * BeatNumber) - moveOverForBeat, ((separation * i)) + 15, 25, 15);
          g.setColor(Color.BLACK);
        }
      }
    }

    //Creates Columns and Headers (Every 16 Measures). This Overlays on Colored Notes
    for (Integer j = 0; j <= noteList.songLength(); j += 4) {
      //g.drawString(j.toString(), 40+(25*j), 15);
      //Iterate Through the Range to create Box for Beat of Notes for TESTING
      for (int i = 0; i <= rangeOfSong; i++) {
        g.drawRect(40 + (25 * j) - moveOverForBeat, ((separation * i)) + 15, 100, 15);

        SoundUnit rangeNote = new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 0, 1);
        rangeNote.setPitchAndOctaveFromMIDI(noteList.getHighestNote().getMIDIPitch() - i);
        if (rangeNote.getPitch() == SoundUnit.Pitch.B) {
          //Draws Bar for every Middle C
          g.fillRect(40 + (25 * j) - moveOverForBeat, ((separation * i)) + 15, 100, 2);
        }
      }
    }
    for (Integer j = 0; j <= noteList.songLength(); j += 16) {
      g.drawString(j.toString(), 40 + (25 * j) - moveOverForBeat, 15);
    }

    //Draw Measure of Current Beat Line
    for (Integer j = 0; j <= noteList.songLength(); j++) {
      if(j == noteList.getCurrentBeat()){
        g.setColor(Color.RED);
        // g.drawRect(40 + (25 * j), ((separation * i)) + 15, 100, 15);
        g.fillRect(40 + (25 * j) - moveOverForBeat, 15, 3, 15*(rangeOfSong+1));
        g.setColor(Color.BLACK);
      }
    }
  }
}
