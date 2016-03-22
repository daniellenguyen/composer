package cs3500.music.model;

/**
 * Created by Justin Hynes-Bruell on 3/21/2016.
 */
public class MIDINote extends Note{
  private int instrument;
  private int volume;
  private int MIDIPitch;

  public MIDINote(Pitch pitch, Octave octave, int start, int end) {
    super(pitch, octave, start, end);
  }

  /**
   * Edits note's pitch and octave to match MIDI pitch
   * @param MIDIPitch
   */
  public void setPitchAndOctaveFromMIDI(int MIDIPitch){
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

  public void setInstrument(int instrument){
    this.instrument = instrument;
  }

  public void setVolume(int volume){
    this.volume = volume;
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
}
