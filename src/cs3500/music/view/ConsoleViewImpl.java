package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.model.SoundUnitList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Returns in the console a view of how all the notes are played
 */
public class ConsoleViewImpl implements View {


  public void consoleRender(NoteList list) {
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
    HashMap<Note.Octave, HashSet<Note.Pitch>> onRightNow = new HashMap<>();
    String finalConsoleRender = "";    // final return value
    for (int i = 0; i <= list.songLength(); i++) {
      if (list.hasNotesAtTime(i)) {
        Iterator iterator = list.getAllAtTime(i).iterator();
        String finalRow = "";
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
    System.out.println(finalConsoleRender);
  }
}
