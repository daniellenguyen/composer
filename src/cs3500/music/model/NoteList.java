package cs3500.music.model;

import java.util.*;

import cs3500.music.util.CompositionBuilder;

/**
 * To sort individual notes by time.
 */
public class NoteList implements SoundUnitList<Note> {
  private TreeMap<Integer, HashSet<Note>> map;
  private TreeSet<Note> set;
  private int tempo;

  public NoteList() {
    this.map = new TreeMap<>();
    this.set = new TreeSet<>();
    this.tempo = 0;
  }

  public void setTempo(int tempo){
    this.tempo = tempo;
  }

  public int getTempo(){
    return tempo;
  }

  public int songLength(){
    return map.lastKey();
  }

  public Note getHighestNote() {
    return set.last();
  }

  public Note getLowestNote() {
    return set.first();
  }

  public boolean hasNotesAtTime(int time) {
    return map.containsKey(time);
  }

  @Override
  public void add(Note note) {
//    for (int i = note.getStart(); i <= note.getEnd(); i++) {
//      if (map.containsKey(i)) {
//        Iterator iterator = map.get(i).iterator();
//        while (iterator.hasNext()) {
//          Note n = (Note) iterator.next();
//          if (n.getPitch().toString() == note.getPitch().toString() &&
//                  n.getOctave().toString() == note.getOctave().toString()) {
//            /*throw new IllegalArgumentException("Note with same pitch and octave " +
//                    "already exists at this time interval. " + n.getPitch().toString() +
//                    n.getOctave().toString() + " and " +  note.getPitch().toString() +
//                    note.getOctave().toString());*/
//          }
//        }
//      }
//    }
    for (int i = note.getStart(); i < note.getEnd(); i++) {
      if (!map.containsKey(i)) {
        map.put(i, new HashSet());
        map.get(i).add(note);
        set.add(note); // adds if not already present
      } else {
        map.get(i).add(note);
        set.add(note);
      }
    }
  }

  @Override
  public void delete(Note note) {
    for (int i = note.getStart(); i < note.getEnd(); i++) {
      if (map.containsKey(i) && !map.get(i).contains(note) && !set.contains(note)
              || !map.containsKey(i) && !set.contains(note)) {
        throw new IllegalArgumentException("The given note is not in this list");
      } else {
        map.get(i).remove(note);
        set.remove(note);
      }
    }
  }

  @Override
  public boolean contains(Note note) {
    for (int i = note.getStart(); i < note.getEnd(); i++) {
      if (map.containsKey(i) && !map.get(i).contains(note) && !set.contains(note)
              || !map.containsKey(i) && !set.contains(note)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Set<Note> getAllAtTime(int time) {
    if (time < 0) {
      throw new IllegalArgumentException("Time cannot be negative.");
    }
    if (this.hasNotesAtTime(time)) {
      return map.get(time);
    }
    return new HashSet<>();
  }
  //    if (!map.containsKey(time)) {
//      throw new IllegalArgumentException("No notes at this time" +
//              "stamp.");
//    } else {
//      return map.get(time);
//    }

  @Override
  public int size() {
    HashSet<Note> finalSet = new HashSet<>();
    for (Map.Entry<Integer, HashSet<Note>> entry : map.entrySet()) {
      HashSet value = entry.getValue();
      Iterator iterator = value.iterator();
      for (int i = 0; i < value.size(); i++) {
        Note n = (Note) iterator.next();
        finalSet.add(n); // works because HashSet does not add duplicates
      }
    }
    return finalSet.size();
  }

  @Override
  public String consoleRender(NoteList list) {
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

    String header = "";
    for(int i = 0; i < pitchRow.size(); i++) {
      header = header + pitchRow.get(i).toString();
    }
    HashMap<Note.Octave, HashSet<Note.Pitch>> onRightNow = new HashMap<>();
    String finalConsoleRender = "";    // final return value
    for (int i = 0; i <= list.songLength(); i++) {
      if (list.hasNotesAtTime(i)) {
        Iterator iterator = list.getAllAtTime(i).iterator();
        String finalRow = header;
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
