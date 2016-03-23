package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.swing.*;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;

/**
 * A dummy view that simply draws a string 
 */
public class ConcreteGuiViewPanel extends JPanel {

  NoteList noteList;

  public ConcreteGuiViewPanel(NoteList inputSong){
    this.noteList =  inputSong;
  }


  @Override
  public void paint(Graphics g){
    // Look for more documentation about the Graphics class, and methods on it that may be useful
    super.paintComponent(g);
    //g.drawRect(0,0,100, 100);

    //g.drawString(noteList.getLowestNote().toString(), 50, 50);

    //g.drawString(noteList.getHighestNote().toString(), 50, 100);

    int separation = 15;

    int rangeOfSong = noteList.getHighestNote().getMIDIPitch() - noteList.getLowestNote().getMIDIPitch();

    //Iterate Through the Range to create Header
    for(int i = rangeOfSong; i >= 0; i--){
      Note rangeNote = new Note(Note.Pitch.C, Note.Octave.FOUR, 0, 1);
      rangeNote.setPitchAndOctaveFromMIDI(noteList.getHighestNote().getMIDIPitch()-i);
      g.drawString(rangeNote.toString(), 15, separation*i +30);
    }

    for(Integer j = 0; j <= noteList.songLength(); j+=4) {
      g.drawString(j.toString(), 40+(25*j), 15);
      //Iterate Through the Range to create Box for Beat of Notes for TESTING
      for (int i = 0; i < rangeOfSong; i++) {
        g.drawRect(40+(25*j), ((separation * i)) + 15, 100, 30);
      }
    }


    /*
    //For each Beat in the song
    for (int BeatNumber = 0; BeatNumber < noteList.songLength(); BeatNumber++) {
      Set<Note> Notes = noteList.getAllAtTime(BeatNumber);

      Iterator<Note> i = Notes.iterator();

      List<Note> alreadyPlayed = new ArrayList<>();

      while (i.hasNext()) {
        //Get the Note from the set
        Note n = (Note) i.next();

        //Determine if note has already been played at this beat
        boolean dontStopNote = false;
        for (int j = 0; j < alreadyPlayed.size(); j++) {
          if (alreadyPlayed.get(j).getMIDIPitch() == n.getMIDIPitch()) {
            dontStopNote = true;
          }
        }
        alreadyPlayed.add(n);

        //Find notes to Start
        if (n.getStart() == BeatNumber) {

        }
        //Find Notes to End
        else if (n.getEnd() == BeatNumber) {
        }
      }
    }*/
  }

}
