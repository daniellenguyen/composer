package cs3500.music.model;

/**
 * To represent a unit of sound.
 *
 * Implementations of this interface are recommended to
 * override equals and hashcode.
 */
public interface SoundUnit {

  /**
   * Returns the readable representation for this
   * {@code SoundUnit}.
   *
   * @return a String representing this {@code SoundUnit}.
   */
  String toString();

  /**
   * Edits the end time of this {@code SoundUnit}.
   *
   * @param newTime the new time this note will end at
   * @throws IllegalArgumentException if {@code newTime} is less than
   * or equal to the {@code Note}'s start time.
   */
  void changeEndTime(int newTime);

  /**
   * Edits the end time of this {@code SoundUnit}.
   *
   * @param newTime the new time this note will end at
   * @throws IllegalArgumentException if {@code newTime} is greater than
   * or equal to the {@code Note}'s end time.
   */
  void changeStartTime(int newTime);

}
