package cs3500.music.model;

import java.util.*;

/**
 * To sort individual notes by time.
 */
public class NoteList implements SoundUnitList<Note> {
  private TreeMap<Integer, HashSet<Note>> map;
  private TreeSet<Note> set;
  private int tempo;

  private Note lastNote;

  private Integer currentBeat;

  public NoteList() {
    this.map = new TreeMap<>();
    this.set = new TreeSet<>();
    this.tempo = 0;

    lastNote = new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR,0,1);
    lastNote.setVolume(100);
    lastNote.setInstrument(1);
    currentBeat = 0;
  }

  @Override
  public void setTempo(int tempo){
    this.tempo = tempo;
  }

  @Override
  public int getTempo(){
    return tempo;
  }

  @Override
  public int songLength(){
    return map.lastKey();
  }

  @Override
  public Note getHighestNote() {
    return set.last();
  }

  @Override
  public Note getLowestNote() {
    return set.first();
  }

  @Override
  public boolean hasNotesAtTime(int time) {
    return map.containsKey(time);
  }

  @Override
  public void add(SoundUnit note) {
    for (int i = note.getStart(); i < note.getEnd(); i++) {
      if (!map.containsKey(i)) {
        map.put(i, new HashSet());
        map.get(i).add((Note) note);
        set.add((Note) note); // adds if not already present
      } else {
        map.get(i).add((Note) note);
        set.add((Note) note);
      }
    }
  }

  @Override
  public void delete(SoundUnit note) {
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
  public boolean contains(SoundUnit note) {
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
  public void setLastNote(Note newLastNote){
    this.lastNote = newLastNote;
  }

  @Override
  public Note getLastNote(){
    return lastNote;
  }

  @Override
  public void setCurrentBeat(Integer newBeat){
    if(newBeat<0){
      newBeat = 0;
    }
    if(newBeat>songLength()){
      newBeat = songLength();
    }
    this.currentBeat = newBeat;
  }

  @Override
  public Integer getCurrentBeat(){
    return this.currentBeat;
  }

}
