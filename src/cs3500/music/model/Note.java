package cs3500.music.model;

/**
 * To represent a Note
 */
public class Note implements SoundUnit, Comparable {
  protected Pitch pitch; //Pitch of the Note
  protected Octave octave; //Octave of the Note
  protected int start; //Start of the Note
  protected int end; //End of the Note. Can be used to find duration
  private int instrument;
  private int volume;
  private int MIDIPitch;

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
   * The default constructor for a note.
   *
   * INVARIANT: A note is allowed to start and end on the same beat.
   *
   * @param pitch the pitch of the note on the scale of C to B
   * @param octave the octave of the note on the scale of 1 to 11
   * @param start the start time of the note
   * @param end the end time of the note
   * @throws IllegalArgumentException if start time is less than 0,
   * if end time is less than 0, or if start time is greater than end time.
   */
  public Note(Pitch pitch, Octave octave, int start, int end) {
    if (start < 0 || end < 0 || start > end) {
      throw new IllegalArgumentException("Start and/or end time is invalid!");
    }
    this.pitch = pitch;
    this.octave = octave;
    this.start = start;
    this.end = end;
    this.volume = 70;
    this.instrument = 1;

    setMIDIFromPitchAndOctave(pitch, octave);
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
  public void setPitch(Pitch newPitch) {
    this.pitch = newPitch;
  }

  @Override
  public void setOctave(Octave newOctave) {
    this.octave = newOctave;
  }

  @Override
  public void setStart(int newTime) {
    if (newTime >= this.end || newTime < 0) {
      throw new IllegalArgumentException("New start time is greater than or equal to end time.");
    } else {
      this.start = newTime;
    }
  }

  @Override
  public void setEnd(int newTime) {
    if (newTime <= this.start || newTime < 1) {
      throw new IllegalArgumentException("New end time is less than or equal to start time.");
    } else {
      this.end = newTime;
    }
  }


  public void setInstrument(int instrument){
    if(instrument < 0 || instrument > 128) {
      throw new IllegalArgumentException("Invalid MIDI instrument number. " +
              "Should be between 0 and 128.");
    }
    else {
      this.instrument = instrument;
    }
  }

  public void setVolume(int volume){
    this.volume = volume;
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

  public int getInstrument(){
    return instrument;
  }

  public int getVolume(){
    return volume;
  }

  public int getMIDIPitch(){
    return MIDIPitch;
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

  public void setMIDIFromPitchAndOctave(Pitch newPitch, Octave newOctave){
    int newMIDIPitch = 0;
    switch (newOctave) {
      case ONE:
        newMIDIPitch = 1;
        break;
      case TWO:
        newMIDIPitch = 2;
        break;
      case THREE:
        newMIDIPitch = 3;
        break;
      case FOUR:
        newMIDIPitch = 4;
        break;
      case FIVE:
        newMIDIPitch = 5;
        break;
      case SIX:
        newMIDIPitch = 6;
        break;
      case SEVEN:
        newMIDIPitch = 7;
        break;
      case EIGHT:
        newMIDIPitch = 8;
        break;
      case NINE:
        newMIDIPitch = 9;
        break;
      case TEN:
        newMIDIPitch = 10;
        break;
      case ELEVEN:
        newMIDIPitch = 11;
        break;
    }

    newMIDIPitch = (newMIDIPitch* 12)+12;

    switch (newPitch) {
      case C:
        newMIDIPitch += 0;
        break;
      case C2:
        newMIDIPitch += 1;
        break;
      case D:
        newMIDIPitch += 2;
        break;
      case D2:
        newMIDIPitch += 3;
        break;
      case E:
        newMIDIPitch += 4;
        break;
      case F:
        newMIDIPitch += 5;
        break;
      case F2:
        newMIDIPitch += 6;
        break;
      case G:
        newMIDIPitch += 7;
        break;
      case G2:
        newMIDIPitch += 8;
        break;
      case A:
        newMIDIPitch += 9;
        break;
      case A2:
        newMIDIPitch += 10;
        break;
      case B:
        newMIDIPitch += 11;
        break;
    }
    this.MIDIPitch = newMIDIPitch;
  }

  //Edits note's pitch and octave to match MIDI pith
  public void setPitchAndOctaveFromMIDI(int MIDIPitch){
    if(MIDIPitch < 24){
      throw new IllegalArgumentException("Note is Too Low: " + MIDIPitch + " MIDI Value");
    }
    if(MIDIPitch > 108){
      throw new IllegalArgumentException("Note is Too High: " + MIDIPitch + " MIDI Value");
    }

    //Determine ENUM for Pitch
    switch ((MIDIPitch-24)%12) {
      case 0:
        this.pitch = Pitch.C;
        break;
      case 1:
        this.pitch = Pitch.C2;
        break;
      case 2:
        this.pitch = Pitch.D;
        break;
      case 3:
        this.pitch = Pitch.D2;
        break;
      case 4:
        this.pitch = Pitch.E;
        break;
      case 5:
        this.pitch = Pitch.F;
        break;
      case 6:
        this.pitch = Pitch.F2;
        break;
      case 7:
        this.pitch = Pitch.G;
        break;
      case 8:
        this.pitch = Pitch.G2;
        break;
      case 9:
        this.pitch = Pitch.A;
        break;
      case 10:
        this.pitch = Pitch.A2;
        break;
      case 11:
        this.pitch = Pitch.B;
        break;
    }

    //Determine ENUM for Octave
    switch ((MIDIPitch-12)/12) {
      case 1:
        this.octave = Octave.ONE;
        break;
      case 2:
        this.octave = Octave.TWO;
        break;
      case 3:
        this.octave = Octave.THREE;
        break;
      case 4:
        this.octave = Octave.FOUR;
        break;
      case 5:
        this.octave = Octave.FIVE;
        break;
      case 6:
        this.octave = Octave.SIX;
        break;
      case 7:
        this.octave = Octave.SEVEN;
        break;
      case 8:
        this.octave = Octave.EIGHT;
        break;
      case 9:
        this.octave = Octave.NINE;
        break;
      case 10:
        this.octave = Octave.TEN;
        break;
      case 11:
        this.octave = Octave.ELEVEN;
        break;
    }

    this.MIDIPitch = MIDIPitch;
  }
}
