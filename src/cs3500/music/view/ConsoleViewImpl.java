package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;

import java.util.*;

/**
 * Returns in the console a view of how all the notes are played
 */
public class ConsoleViewImpl implements View {

  public ConsoleViewImpl(){}

  /**
   * Returns a list containing the range of notes to be played, including gaps
   * where there is no note in the song itself.
   *
   * @param list the list of notes to be played
   * @return a list representing the range of notes to be played.
   */
  public ArrayList<Note> makeTopRow(NoteList list) {

    int rangeOfSong = list.getHighestNote().getMIDIPitch()
            - list.getLowestNote().getMIDIPitch();
    ArrayList<Note> finalList = new ArrayList<>();
    //Iterate Through the Range to create Header
    for (int i = rangeOfSong; i >= 0; i--) {
      Note rangeNote = new Note(Note.Pitch.C, Note.Octave.FOUR, 0, 1);
      rangeNote.setPitchAndOctaveFromMIDI(list.getHighestNote().getMIDIPitch() - i);
      finalList.add(rangeNote);
    }
    return finalList;
  }

  /**
   * Renders the top row of the console output so that it lists
   * the entire range of pitches.
   *
   * @param list ArrayList
   * @return a string representing the range of pitches to be played
   */
  public String renderTopRow(ArrayList<Note> list) {
    String header = "        ";
    for (int i = 0; i < list.size(); i++) {
      if(list.get(i).toString().length() == 2) {
        header = header + list.get(i).toString() + "   ";
      }
      else {
        header = header + list.get(i).toString() + "  ";
      }
    }
    return header;
  }

  /**
   * Renders the console view of the given list of notes
   *
   * @param list the list of notes to be rendered
   * @throws IllegalArgumentException if the NoteList is empty
   */
  public void consoleRender(NoteList list) {
    if (list.size() == 0) {
      throw new IllegalArgumentException("Nothing to render");
    }
    ArrayList<Note> pitchRow = this.makeTopRow(list);
    HashMap<Note.Octave, HashSet<Note.Pitch>> onRightNow = new HashMap<>();
    String finalConsoleRender = this.renderTopRow(pitchRow) + "\n";
    for (int i = 0; i <= list.songLength(); i++) {
      String finalRow;
      if(i < 10) {
        finalRow = "   " + Integer.toString(i) + "  ";
      }
      else if (i < 100) {
        finalRow = "  " + Integer.toString(i) + "  ";
      }
      else if (i < 1000) {
        finalRow = " " + Integer.toString(i) + "  ";
      }
      else {
        finalRow = Integer.toString(i) + "  ";
      }
      if (list.hasNotesAtTime(i)) {
        for (int j = 0; j < pitchRow.size(); j++) {
          Iterator iterator = list.getAllAtTime(i).iterator();
          boolean wasAnythingAdded = false;
          while (iterator.hasNext()) {
            Note n = (Note) iterator.next();
            Note.Pitch nPitch = pitchRow.get(j).getPitch();
            Note.Octave nOctave = pitchRow.get(j).getOctave();
            if (nPitch.equals(n.getPitch()) && nOctave.equals(n.getOctave())) {
              if (!(onRightNow.containsKey(nOctave)) && n.getStart() == n.getEnd()) {
                finalRow = finalRow + "  X  ";
                wasAnythingAdded = true;
              }
              else if (!(onRightNow.containsKey(nOctave))) {
                HashSet<Note.Pitch> p = new HashSet<>();
                p.add(nPitch);
                onRightNow.put(nOctave, p);
                finalRow = finalRow + "  X  ";
                wasAnythingAdded = true;
              } else if (!(onRightNow.get(nOctave).contains(nPitch))) {
                onRightNow.get(nOctave).add(nPitch);
                finalRow = finalRow + "  X  ";
                wasAnythingAdded = true;
              }
              else if (onRightNow.get(nOctave).contains(nPitch)) {
                finalRow = finalRow + "  |  ";
                wasAnythingAdded = true;
                if (n.getEnd() == i + 1) {
                  onRightNow.get(nOctave).remove(nPitch);
                }
              }
            }
          }
          if (wasAnythingAdded == false) {
            finalRow = finalRow + "     ";
          }
        }
        finalConsoleRender = finalConsoleRender + finalRow + "\n";
      }
      else {
        finalConsoleRender = finalConsoleRender + finalRow + "\n";
      }
    }
    System.out.println(finalConsoleRender);
  }
}
