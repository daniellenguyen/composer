package cs3500.music.model;
;
import cs3500.music.model.Note.Pitch;
import cs3500.music.model.Note.Octave;

/**
 * To represent a unit of sound.
 *
 * Implementations of this interface are recommended to
 * override equals and hashcode.
 */
public interface SoundUnit {

  /**
   * Returns the readable representation for this SoundUnit
   *
   * @return a String representing this SoundUnit
   */
  String toString();

  /**
   * Sets a new pitch for the SoundUnit
   *
   * @param newPitch the new pitch for the SoundUnit
   */
  void setPitch(Pitch newPitch);

  /**
   * Sets a new octave for the SoundUnit
   *
   * @param newOctave the new octave for the SoundUnit
   */
  void setOctave(Octave newOctave);

  /**
   * Sets a new start time for the SoundUnit
   *
   * @param newTime the new start time for the SoundUnit
   * @throws IllegalArgumentException if new start time of the
   * note is greater than or equal to end time
   */
  void setStart(int newTime);

  /**
   * Sets a new end time for the SoundUnit
   *
   * @param newTime the new end time for the SoundUnit
   * @throws IllegalArgumentException if new end time
   * of the note is less than or equal to start time
   */
  void setEnd(int newTime);



}
