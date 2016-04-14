package cs3500.music.model;
import cs3500.music.model2.IPlayerModel;

import java.util.Set;

/**
 * To represent a collection of sound units ordered by timestamp
 */
public interface SoundUnitList<T> {

  /**
   * Adds a {@code SoundUnit} to this list
   *
   * @param note the {@code SoundUnit} to add
   * @throws IllegalArgumentException if the given {@code SoundUnit}
   * overlaps another {@code SoundUnit} in this list.
   */
  void add(SoundUnit note);


  /**
   * Deletes a {@code SoundUnit} from this list
   *
   * @param note the {@code SoundUnit} to delete
   * @throws IllegalArgumentException if the given {@code SoundUnit}
   * is not in this list
   */
  void delete(SoundUnit note);

  /**
   * Does this list contain the given {@code SoundUnit}?
   *
   * @param note the given {@code SoundUnit}
   * @return a boolean representing whether the given
   * {@code SoundUnit} is in this list
   */
  boolean contains(SoundUnit note);

  /**
   * Gets all {@code SoundUnit}s playing at the given
   * time (integral time)
   *
   * @param time the given time
   * @return all the {@code SoundUnit} playing at the given time
   * @throws IllegalArgumentException if {@code time} is negative
   * @throws IllegalArgumentException if there are no {@code SoundUnit}
   * at the given {@code time}
   */
  Set<T> getAllAtTime(int time);

  /**
   * Returns the number of distinct notes in this list
   *
   * @return an int representing the number of notes in this list
   */
  int size();

  /**
   * Gets the tempo of the SoundUnitList
   */
  int getTempo();

  /**
   * Gets the highest note in the SoundUnitList
   */
  Note getHighestNote();

  /**
   * Gets the lowest note in the SoundUnitList
   */
  Note getLowestNote();

  /**
   * Gets the length in beats of the song
   */
  int songLength();

  /**
   * Does the given timestamp have any notes?
   *
   * @param time the given timestamp
   * @return boolean representing if timestamp has notes
   */
  boolean hasNotesAtTime(int time);

  /**
   * Sets a new tempo for the SoundUnitList
   * @param tempo the new tempo
   */
  void setTempo(int tempo);

  void setLastNote(Note newLastNote);

  void setCurrentBeat(Integer newBeat);

  Integer getCurrentBeat();

  Note getLastNote();
}
