package cs3500.music.model;

/**
 * To represent a unit of sound.
 *
 * Implementations of this interface are recommended to
 * override equals and hashcode.
 */
public interface SoundUnit {

  /**
   * To represent the pitch of the note.
   * Scales from C to B
   */
  public enum Pitch {
    C("C"), C2("C#"), D("D"), D2("D#"), E("E"), F("F"), F2("F#"),
    G("G"), G2("G#"), A("A"), A2("A#"), B("B");

    private final String asString;

    Pitch(String asString) {
      this.asString = asString;
    }

    @Override
    public String toString() {
      return this.asString;
    }

  }

  /**
   * To represent the octave of the note.
   * Scales from 1 to 11.
   */
  public enum Octave {
    ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
    SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), ELEVEN("11");


    private final String asString;

    Octave(String asString) {
      this.asString = asString;
    }

    @Override
    public String toString() {
      return this.asString;
    }
  }

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

  /**
   * Sets a new instrument number for the SoundUnit
   * according to MIDI standard numbers
   *
   * @param instrument the new MIDI instrument number
   * @throws IllegalArgumentException if new MIDI number is
   * not between 0 and 128
   */
  void setInstrument(int instrument);

  /**
   * Sets a new volume for the SoundUnit
   *
   * @param volume the new volume
   */
  void setVolume(int volume);

  /**
   * Gets the pitch of the SoundUnit
   */
  Pitch getPitch();

  /**
   * Gets the octave of the SoundUnit
   */
  Octave getOctave();

  /**
   * Gets the start time of the SoundUnit
   */
  int getStart();

  /**
   * Gets the end time of the SoundUnit
   */
  int getEnd();

  /**
   * Gets the instrument number of the SoundUnit
   */
  int getInstrument();

  /**
   * Gets the volume number of the SoundUnit
   */
  int getVolume();

  /**
   * Gets the MIDI pitch of SoundUnit
   */
  int getMIDIPitch();

  /**
   * Sets a MIDI pitch number using a given pitch and octave
   *
   * @param newPitch the new pitch
   * @param newOctave the new octave
   */
  void setMIDIFromPitchAndOctave(Pitch newPitch, Octave newOctave);

  /**
   * Edits a note's pitch and octave to match the MIDI pitch
   *
   * @param MIDIPitch the given MIDI pitch
   */
  void setPitchAndOctaveFromMIDI(int MIDIPitch);
}
