package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Returns in the console a view of how all the notes are played
 */
public class ConsoleViewImpl implements View {

  /**
   * Returns a list containing the range of notes to be played, including gaps
   * where there is no note in the song itself.
   *
   * @param list the list of notes to be played
   * @return a list representing the range of notes to be played.
   */
  public ArrayList<Note> makeTopRow(NoteList list) {

    int rangeOfSong = list.getHighestNote().getMIDIPitch() - list.getLowestNote().getMIDIPitch();
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
   * @param list
   * @return a string representing the range of pitches to be played
   */
  public String renderTopRow(ArrayList<Note> list) {
    String header = "     ";
    for (int i = 0; i < list.size(); i++) {
      header = header + list.get(i).toString() + "   ";
    }
    return header;
  }

  /**
   * Renders the console view of the given list of notes
   *
   * @param list the list of notes to be rendered
   * @return a string representing the list of notes as a timestamp grid
   */
  public void consoleRender(NoteList list) {
//    String finalConsoleRender = "";
//    //For each Beat in the song
//    for (int BeatNumber = 0; BeatNumber < list.songLength(); BeatNumber++) {
//      List<Note> ListOfNotesAtBeat = new ArrayList<>();
//      ListOfNotesAtBeat.addAll(list.getAllAtTime(BeatNumber));
//      String finalRow = Integer.toString(BeatNumber) + "  ";
//      int rangeOfSong = list.getHighestNote().getMIDIPitch() - list.getLowestNote().getMIDIPitch();
//      //Create a Column of Notes
//      for (int i = rangeOfSong; i >= 0; i--) {
//        Note rangeNote = new Note(Note.Pitch.C, Note.Octave.FOUR, 0, 1);
//        rangeNote.setPitchAndOctaveFromMIDI(list.getHighestNote().getMIDIPitch() - i);
//
//        boolean noteStarts = false;
//        boolean noteContinues = false;
//
//        for (int j = 0; j < ListOfNotesAtBeat.size(); j++) {
//          if (rangeNote.getMIDIPitch() == ListOfNotesAtBeat.get(j).getMIDIPitch()) {
//            if (ListOfNotesAtBeat.get(j).getStart() == BeatNumber) {
//              noteStarts = true;
//            } else {
//              noteContinues = true;
//            }
//          }
//        }
//
//        //If a Note is Starting then Fill it! This takes Priority over the Continue
//        if (noteStarts) {
//          finalRow = finalRow + "  X  ";
//        } else if (noteContinues) {
//          finalRow = finalRow + "  |  ";
//        }
//
//      }
//      finalConsoleRender = finalConsoleRender + Integer.toString(BeatNumber) + "\n";
//    }
//    System.out.println(finalConsoleRender);
//  }
    ArrayList<Note> pitchRow = this.makeTopRow(list);
    HashMap<Note.Octave, HashSet<Note.Pitch>> onRightNow = new HashMap<>();
    String finalConsoleRender = this.renderTopRow(pitchRow) + "\n";    // final return value
    for (int i = 0; i <= list.songLength(); i++) {
      if (list.hasNotesAtTime(i)) {

        String finalRow = Integer.toString(i) + "  ";
        for (int j = 0; j < pitchRow.size(); j++) {
          Iterator iterator = list.getAllAtTime(i).iterator();
          boolean wasAnythingAdded = false;
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
                finalRow = finalRow + "  X  ";
                wasAnythingAdded = true;
              } else if (!(onRightNow.get(nOctave).contains(nPitch))) {
                onRightNow.get(nOctave).add(nPitch);
                finalRow = finalRow + "  X  ";
                wasAnythingAdded = true;
              }
              // "___|___"
              else if (onRightNow.get(nOctave).contains(nPitch)) {
                finalRow = finalRow + "  |  ";
                wasAnythingAdded = true;
                if (n.getEnd() == i) {
                  onRightNow.get(nOctave).remove(nPitch);
                }
              }
            }
          }
          if (wasAnythingAdded == false) {
            finalRow = finalRow + "  $  ";
          }
        }
        finalConsoleRender = finalConsoleRender + finalRow + "\n";
      }
      else {
        finalConsoleRender = finalConsoleRender + Integer.toString(i) + "\n";
      }

    }
    System.out.println(finalConsoleRender);
  }
}
