package cs3500.music.model;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * To sort individual notes by time.
 */
public class NoteList implements SoundUnitList<Note> {
  private TreeMap<Integer, HashSet<Note>> map;
  private TreeSet<Note> set;

  public NoteList() {
    this.map = new TreeMap<>();
    this.set = new TreeSet<>();
  }

  @Override
  public void add(Note note) {
    for (int i = note.getStart(); i <= note.getEnd(); i++) {
      if (map.containsKey(i)) {
        Iterator iterator = map.get(i).iterator();
        while (iterator.hasNext()) {
          Note n = (Note) iterator.next();
          if (n.getPitch().toString() == note.getPitch().toString() &&
                  n.getOctave().toString() == note.getOctave().toString()) {
            throw new IllegalArgumentException("Note with same pitch and octave " +
                    "already exists at this time interval.");
          }
        }
      }
    }
    for (int i = note.getStart(); i <= note.getEnd(); i++) {
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
    for (int i = note.getStart(); i <= note.getEnd(); i++) {
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
    for (int i = note.getStart(); i <= note.getEnd(); i++) {
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
    if (!map.containsKey(time)) {
      throw new IllegalArgumentException("No notes at this time" +
              "stamp.");
    } else {
      return map.get(time);
    }
  }

  @Override
  public int size() {
    HashSet<Note> finalSet = new HashSet<>();
    for (Map.Entry<Integer, HashSet<Note>> entry : map.entrySet()) {
      HashSet value = entry.getValue();
      Iterator iterator = value.iterator();
      for (int i = 0; i < value.size(); i++) {
        Note n = (Note) iterator.next();
        finalSet.add(n); // this works only if add doesn't add duplicates
      }
    }
    return finalSet.size();
  }

  @Override
  public void consoleRender() {
    Note highestNote = set.last();
    Note lowestNote = set.first();
    SoundUnitList on = new NoteList();
    String finalString = "";
    for(int i = 0; i <= map.lastKey(); i++) { // for every timestamp starting at 0
      String thisRow = "";
      Iterator iterator = map.get(i).iterator();
      while(iterator.hasNext()) {
        Note n = (Note) iterator.next();
      }
    }

    /**
     * for (int i = note.start; i <= note.end; i++) {
     if (map.containsKey(i)) {
     Iterator iterator = map.get(i).iterator();
     while (iterator.hasNext()) {
     Note n = (Note) iterator.next();
     if (n.pitch.toString() == note.pitch.toString() &&
     n.octave.toString() == note.octave.toString()) {
     throw new IllegalArgumentException("Note with same pitch and octave " +
     "already exists at this time interval.");
     }
     }
     }
     }
     */

    /**
     * Have an "on" pile of notes and an "off" pile of notes.
     * Between these two piles is all the notes we can represent,
     * i.e. C1 through B8. If a note is in the "on" list it is currently
     * playing. If a note is in the "off" list it is currently not playing.
     * These two lists are attached to a for loop that iterates
     * through every timestamp and renders row by row; as the
     * for loop moves from timestamp to timestamp, the "on" list
     * and the "off" list get mutated to represent where each note starts and
     * ends.
     *
     * Need a way to find the distinction between 'X' and "|"
     * If a note exists at timestamp 6 but does not exist at timestamp 5,
     * represent the note using 'X'.
     * If a note exists at timestamp 6 and also exists at timestamp 5,
     * represent the note using '|'.
     * If a note exists at timestamp 6 but does not exist at timestamp 7,
     * this case should be covered directly below.
     *
     * Need a way to render "_____" <--- five spaces, or the nonexistence
     * of a note at a certain timestamp.
     * Make five spaces the default for every note in the row. If one of two
     * of the special cases above does not happen, do five spaces.
     *
     * Problems:
     *
     * Need a way to find the note with the highest pitch and octave and the
     * note with the lowest pitch and octave in this list because
     * rendering constraints specify not to show pitches and octaves outside
     * this range.
     * - As notes are added, keep track of the highest note in this list, and
     *  the lowest note in this list. Make it private.
     *  - This is currently being done with a TreeSet. Add every note to this
     *    structure, and return the highest and lowest note when necessary.
     *    Deletion happens to notes in this structure too.
     *
     * What happens when there are no notes in this list?
     * - When there are no notes in the list render an empty grid.
     * - What is an "empty" grid? Maybe we should just throw an error.
     *   in GUI if there are no notes we still render a default grid.
     *   Lets set the default grid to octave 4, C through B.
     *
     */
  }
}
