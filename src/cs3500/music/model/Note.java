package cs3500.music.model;

/**
 * Created by daniellenguyen on 2/28/16.
 */
public class Note implements SoundUnit, Comparable {

  protected Pitch pitch; //Pitch of the Note
  protected Octave octave; //Octave of the Note
  protected int start; //Start of the Note
  protected int end; //End of the Note. Can be used to find duration

  /**
   * Pitch of the current note
   * This also has a toString method built in
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
   * Octave of the current note
   * This also has a toString method built in
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
   * Note Constructor. This creates the note and sets all of the fields.
   * @param pitch
   * @param octave
   * @param start
   * @param end
   */
  public Note(Pitch pitch, Octave octave, int start, int end) {
    this.pitch = pitch;
    this.octave = octave;
    this.start = start;
    this.end = end;

    if (this.start < 0 || this.end < 1 || this.start >= this.end) {
      throw new IllegalArgumentException("Start and/or end time is invalid!");
    }
  }

  /**
   * toString method for the Note.
   * @return "String" + "Octave"
   */
  @Override
  public String toString() {
    return this.pitch.toString() + this.octave.toString();
  }


  @Override
  public boolean equals(Object object) {
    return object instanceof Note &&
            ((Note) object).pitch == this.pitch &&
            ((Note) object).octave == this.octave &&
            ((Note) object).start == this.start &&
            ((Note) object).end == this.end;
  }

  @Override
  public int hashCode() {
    return pitch.hashCode() + octave.hashCode() + this.start + this.end;
  }

  public void setPitch(Pitch newPitch) {
    this.pitch = newPitch;
  }

  public void setOctave(Octave newOctave) {
    this.octave = newOctave;
  }

  public void setStart(int newTime) {
    if (newTime >= this.end || newTime < 0) {
      throw new IllegalArgumentException("New start time is greater than or equal to end time.");
    } else {
      this.start = newTime;
    }
  }

  public void setEnd(int newTime) {
    if (newTime <= this.start || newTime < 1) {
      throw new IllegalArgumentException("New end time is less than or equal to start time.");
    } else {
      this.end = newTime;
    }
  }

  public Pitch getPitch() {
    return this.pitch;
  }

  public Octave getOctave() {
    return this.octave;
  }

  public int getStart() {
    return this.start;
  }

  public int getEnd() {
    return this.end;
  }

  /**
   * Copy Constructor for Note
   *
   * @param inputNote Note that you are copying
   */
  public Note(Note inputNote) {
    this.pitch = inputNote.getPitch();
    this.octave = inputNote.getOctave();
    this.start = inputNote.getStart();
    this.end = inputNote.getEnd();
  }



  @Override
  public int compareTo(Object o) throws ClassCastException, NullPointerException {
    if (o == null) {
      throw new NullPointerException("The given object is null.");
    }
    if (this.octave.compareTo(((Note) o).octave) == 0 &&
            this.pitch.compareTo(((Note) o).pitch) == 0) {
      return ((Note) o).start - this.start;
    }
    // if only same octave
    else if (this.octave.compareTo(((Note) o).octave) == 0) {
      return this.pitch.compareTo(((Note) o).pitch);
    }
    // if not same octave
    else {
      return this.octave.compareTo(((Note) o).octave);
    }
  }
}
