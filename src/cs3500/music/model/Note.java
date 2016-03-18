package cs3500.music.model;

/**
 * Created by daniellenguyen on 2/28/16.
 */
public final class Note implements SoundUnit, Comparable {
  public final Pitch pitch;   // start and end are dangerous because
  public final Octave octave; // anyone can get their values and
  private int start;           // manipulate them.
  public int end;

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

  public enum Octave {
    ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
    SIX("6"),  SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), ELEVEN("11");


    private final String asString;

    Octave(String asString) {
      this.asString = asString;
    }

    @Override
    public String toString() {
      return this.asString;
    }
  }

  public Note(Pitch pitch, Octave octave, int start, int end) {
    this.pitch = pitch;
    this.octave = octave;
    this.start = start;
    this.end = end;

    if (this.start < 0 || this.end < 1 || this.start >= this.end) {
      throw new IllegalArgumentException("Start and/or end time is invalid!");
    }
  }

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

  @Override
  public void changeStartTime(int newTime) {
    if (newTime >= this.end) {
      throw new IllegalArgumentException("New start time is greater than or equal to end time.");
    } else {
      this.start = newTime;
    }
  }

  @Override
  public void changeEndTime(int newTime) {
    if (newTime <= this.start) {
      throw new IllegalArgumentException("New end time is less than or equal to start time.");
    } else {
      this.end = newTime;
    }
  }

  @Override
  public int compareTo(Object o) throws ClassCastException, NullPointerException {
    if (o == null) {
      throw new NullPointerException("The given object is null.");
    }
    if(this.octave.compareTo(((Note) o).octave) == 0 &&
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


  public int getStart() {
    return start;
  }

}
