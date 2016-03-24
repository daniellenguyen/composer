package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;

/**
 * A dummy view that simply draws a string
 */
public class ConcreteGuiViewPanel extends JPanel {

  NoteList noteList;

  /**
   * Constructor Grabs data about Notelist from the constructor of th GuiViewFram. This is added
   * here to make it easier to paint when the time comes
   */
  public ConcreteGuiViewPanel(NoteList inputSong) {
    this.noteList = inputSong;
  }


  /**
   * Paints the GuiWithNotes similar to the way the console does. Has no action Functionality added
   * yet
   */
  @Override
  public void paint(Graphics g) {
    // Look for more documentation about the Graphics class, and methods on it that may be useful
    super.paintComponent(g);

    int separation = 15;

    int rangeOfSong = noteList.getHighestNote().getMIDIPitch() -
            noteList.getLowestNote().getMIDIPitch();

    //Iterate Through the Range to create Side Header with Pitch Values of Range
    for (int i = rangeOfSong; i >= 0; i--) {
      Note rangeNote = new Note(Note.Pitch.C, Note.Octave.FOUR, 0, 1);
      rangeNote.setPitchAndOctaveFromMIDI(noteList.getHighestNote().getMIDIPitch() - i);
      g.drawString(rangeNote.toString(), 15, separation * i + 30);
    }


    //For each Beat in the song Draws the Notes
    for (int BeatNumber = 0; BeatNumber < noteList.songLength(); BeatNumber++) {
      List<Note> ListOfNotesAtBeat = new ArrayList<>();
      ListOfNotesAtBeat.addAll(noteList.getAllAtTime(BeatNumber));

      //Create a Column of Notes
      for (int i = rangeOfSong; i >= 0; i--) {
        Note rangeNote = new Note(Note.Pitch.C, Note.Octave.FOUR, 0, 1);
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
          g.fillRect(40 + (25 * BeatNumber), ((separation * i)) + 15, 25, 15);
        } else if (noteContinues) {
          g.setColor(Color.GREEN);
          //g.drawString(rangeNote.toString(), 15*BeatNumber + 30, separation * i + 30);
          g.fillRect(40 + (25 * BeatNumber), ((separation * i)) + 15, 25, 15);
          g.setColor(Color.BLACK);
        }
      }
    }

    //Creates Columns and Headers (Every 16 Measures). This Overlays on Colored Notes
    for (Integer j = 0; j <= noteList.songLength(); j += 4) {
      //g.drawString(j.toString(), 40+(25*j), 15);
      //Iterate Through the Range to create Box for Beat of Notes for TESTING
      for (int i = 0; i <= rangeOfSong; i++) {
        g.drawRect(40 + (25 * j), ((separation * i)) + 15, 100, 15);

        Note rangeNote = new Note(Note.Pitch.C, Note.Octave.FOUR, 0, 1);
        rangeNote.setPitchAndOctaveFromMIDI(noteList.getHighestNote().getMIDIPitch() - i);
        if (rangeNote.getPitch() == Note.Pitch.B) {
          g.fillRect(40 + (25 * j), ((separation * i)) + 15, 100, 2);
        }
      }
    }
    for (Integer j = 0; j <= noteList.songLength(); j += 16) {
      g.drawString(j.toString(), 40 + (25 * j), 15);
    }
  }
}
