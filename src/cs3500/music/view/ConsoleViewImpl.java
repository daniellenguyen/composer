package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Returns in the console a view of how all the notes are played
 */
public class ConsoleViewImpl implements View {

  /**
   * Returns a list containing the range of notes to be played, including gaps
   * where there is no note in the song itself.
   * @param list the list of notes to be played
   * @return a list representing the range of notes to be played.
   */
  public ArrayList<Note> makeTopRow(NoteList list) {
    Note highestNote = list.getHighestNote();
    Note lowestNote = list.getLowestNote();
    Note.Pitch currentPitch = lowestNote.getPitch();
    Note.Octave currentOctave = lowestNote.getOctave();
    Note.Pitch highestPitch = highestNote.getPitch();
    Note.Octave highestOctave = highestNote.getOctave();

    ArrayList<Note.Octave> allOctaves = new ArrayList<>();
    for (Note.Octave octave : Note.Octave.values()) {
      if (octave.compareTo(lowestNote.getOctave()) >= 0)
        allOctaves.add(octave);
    }

    ArrayList<Note.Pitch> allPitches = new ArrayList<>();
    for (Note.Pitch pitch : Note.Pitch.values()) {
      allPitches.add(pitch);
    }

    ArrayList<Note> pitchRow = new ArrayList<>();
    while (currentOctave.compareTo(highestOctave) < 0 &&
            currentPitch.compareTo(highestPitch) < 0) {
      Note n = new Note(currentPitch, currentOctave, 0, 1);
      pitchRow.add(n);
      // advance current octave and set pitch back to C
      if (currentPitch.equals((Note.Pitch.B))) {
        currentOctave = allOctaves.get(allOctaves.indexOf(currentOctave) + 1);
        currentPitch = Note.Pitch.C;
      } else {
        currentPitch = allPitches.get(allPitches.indexOf(currentPitch) + 1);
      }
    }
    return pitchRow;
  }

  /**
   * Renders the top row of the console output so that it lists
   * the entire range of pitches.
   * @param list
   * @return a string representing the range of pitches to be played
   */
  public String renderTopRow(ArrayList<Note> list) {
    String header = "";
    for(int i = 0; i < list.size(); i++) {
      header = header + list.get(i).toString();
    }
    return header;
  }

  /**
   * Renders the console view of the given list of notes
   * @param list the list of notes to be rendered
   * @return a string representing the list of notes as a timestamp grid
   */
  public String consoleRender(NoteList list) {
    ArrayList<Note> pitchRow = this.makeTopRow(list);
    HashMap<Note.Octave, HashSet<Note.Pitch>> onRightNow = new HashMap<>();
    String finalConsoleRender = "";    // final return value
    for (int i = 0; i <= list.songLength(); i++) {
      if (list.hasNotesAtTime(i)) {
        Iterator iterator = list.getAllAtTime(i).iterator();
        String finalRow = this.renderTopRow(pitchRow);
        for (int j = 0; j < pitchRow.size(); j++) {
          int wasAnythingAdded = 0;
          while (iterator.hasNext()) {
            Note n = (Note) iterator.next();
            Note.Pitch nPitch = pitchRow.get(j).getPitch();
            Note.Octave nOctave = pitchRow.get(j).getOctave();
            if (nPitch.equals(n.getPitch()) && nOctave.equals(n.getOctave())) {
              // "___X___"
              if (!(onRightNow.containsKey(nOctave))) {
                HashSet<Note.Pitch> p = new HashSet<>();
                p.add(nPitch);
                onRightNow.put(nOctave, p);
                finalRow = finalRow + "   X   ";
                wasAnythingAdded = 1;
              } else if (!(onRightNow.get(nOctave).contains(nPitch))) {
                onRightNow.get(nOctave).add(nPitch);
                finalRow = finalRow + "   X   ";
                wasAnythingAdded = 1;
              }
              // "___|___"
              else if (onRightNow.get(nOctave).contains(nPitch)) {
                finalRow = finalRow + "   |   ";
                wasAnythingAdded = 1;
                if (n.getEnd() == i) {
                  onRightNow.get(nOctave).remove(nPitch);
                }
              }
            }
          }
          if (wasAnythingAdded == 0) {
            finalRow = finalRow + "_______";
          }
        }
        finalConsoleRender = finalConsoleRender + finalRow + "/n";
      }

    }
    return finalConsoleRender;
  }
}
