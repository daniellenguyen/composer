
package cs3500.music.model2;

/**
 * To Know: Start includes in duration
 * start 2: duration 1: 2
 * start 2: duration 5: 2,3,4,5,6
 */

/**
* Created by James on 2/28/16.
*/

/**
 * Class, made to represent a note that can be played on the music editor
 */

public class Note implements INote{ //implements Comparable<Note>{


  /**
   * Enum which represents the pitch of a note
   */

  // representative of the pitch the note has
  private int octave;
  // the specific
  private Pitch pitch;
  //private NoteLength duration;
  private int duration;
  // time representing the start of the note
  private int start;
  // whether or not people can hear the note
  private boolean audible;
  // intiger representative of the end of the note
  private int end;
  // the name of the song this note belongs to
  private int instrument;
  // the volume of the note
  private int volume;


  /**
   * Creates a PlayerModel with the octave, pitch, duration, and start passed in
   */

  public Note(int ioctave, Pitch ipitch, int iduration, int istart, int instrumenti, int volumei) {
    octave = ioctave; // set restraint for 0 to 11
    pitch = ipitch;
    duration = durationHelper(iduration);
    start = startHelper(istart);
    end = istart + iduration;
    instrument = instrumenti;
    volume = volumei;
  }

  /**
   * method which returns the name of the instrument used by the note
   *
   * @return instrument -> the code for the insturment being used
   */

  public int getInstrument() {
    return this.instrument;
  }

  /**
   * method which returns the name of the instrument used by the note
   * @param input -> integer of the instrument that it will be set to
   *
   */

  public void setInstrument(int input) {
    this.instrument = input;
  }



  /**
   * method which returns the octave of a note
   * @return octave of the note
   *
   */

  public int getOctave() {
    return this.octave;
  }


  /**
   * method which returns the pitch of a note
   * @return Pitch of the note
   */

  public Pitch getPitch() {
    return this.pitch;
  }


  /**
   * method which returns an entire Note as a String
   * @return String -> the note as a String
   */

  public String getNoteAsString() {
    return this.pitch.convertPitchToString() + Integer.toString(this.getOctave());
  }


  /**
   * method which returns the duration of a note
   * @return returns the duration of the note
   */

  public int getDuration() {
    return this.duration;
  }


  /**
   * Makes sure inputs into the constructor are valid
   * @return a valid duration
   */

  private int durationHelper(int iduration) throws IllegalArgumentException {
    if (iduration <= 0) {
      throw new IllegalArgumentException("Notes cannot be negative or 0 time");
    } else {
      return iduration;
    }
  }


  /**
   * Makes helper that makes sure the start is passed in correctly
   * @return a valid start time
   */

  private int startHelper(int istart) throws IllegalArgumentException {
    if (istart < 0) {
      throw new IllegalArgumentException("Notes cannot be negative at their start");
    } else {
      return istart;
    }
  }


  /**
   * method which returns the start of a note
   * @return the value of the start of the note
   */

  public int getStart() {
    return this.start;
  }


//  /**
//   * method which returns the start of a note
//   */
//
//  public String getSongName() { return this.songname; }


  /**
   * method which returns whether or not a note is audible
   * @return whether the note is audible or not
   */

  public boolean getAudible() {
    return this.audible;
  }


  /**
   * method which returns the beat of the end of a note
   * @return the end of the note as an integer
   */

  public int getEnd() {
    return this.end;
  }


  /**
   * method which sets the octave of a note
   * @param newPitch -> the enum Pitch you want the note to be set to
   */

  public void setPitch(Pitch newPitch) {
    this.pitch = newPitch;
  }


  /**
   * method which sets the octave of a note
   * @param ioctave -> the octave you want to set your note to
   */

  public void setOctave(int ioctave) {
    this.octave = ioctave;

    this.audible = (ioctave > 0 && ioctave <= 10);
  }


  /**
   * method which sets the duration of a note
   * @param iduration -> the duratoin of the note you want to set the note to
   */

  public void setDuration(int iduration) throws IllegalArgumentException {
    if (iduration <= 0) {
      throw new IllegalArgumentException("You must input a number greater than 0");
    }
    this.duration = iduration;
    this.end = this.start + iduration;
  }


  /**
   * method which sets the duration of a note
   * @param istart - the value of the start of the note you want
   */

  public void setStart(int istart) throws IllegalArgumentException {
    if (istart < 0) {
      throw new IllegalArgumentException("You must input a number greater than or equal to 0");
    }

    this.start = istart;
    this.end = istart + this.duration;
  }



  /**
   * method which finds if two Notes have the same pitch and Octave
   *
   * @return value -> indicative of whether they are the same
   */

  public int pitchOctaveComparator(INote o) {

    if (this.octave < o.getOctave()) {

      return -1;
    } else if (this.octave > o.getOctave()) {
      return 1;
    } else {
      if (this.pitch.compareTo(o.getPitch()) > 0) {
        return 1;
      } else if (this.pitch.compareTo(o.getPitch()) < 0) {
        return -1;
      }  else {
        return 0;
      }
    }
  }


  /**
   * method which converts a Note into a midiNumber
   *
   * @return outputNum -> the value of the midiNumber
   */

  public int convertToMidiNumber() {
    int count = 0;
    Pitch myPitch = this.getPitch();
    int octave = this.getOctave();

    count += octave * 12;
    if (myPitch.compareTo(Pitch.C) == 0) {
      count += 0;
    } else if (myPitch.compareTo(Pitch.CS) == 0) {
      count += 1;
    } else if (myPitch.compareTo(Pitch.D) == 0) {
      count += 2;
    } else if (myPitch.compareTo(Pitch.DS) == 0) {
      count += 3;
    } else if (myPitch.compareTo(Pitch.E) == 0) {
      count += 4;
    } else if (myPitch.compareTo(Pitch.F) == 0) {
      count += 5;
    } else if (myPitch.compareTo(Pitch.FS) == 0) {
      count += 6;
    } else if (myPitch.compareTo(Pitch.G) == 0) {
      count += 7;
    } else if (myPitch.compareTo(Pitch.GS) == 0) {
      count += 8;
    } else if (myPitch.compareTo(Pitch.A) == 0) {
      count += 9;
    } else if (myPitch.compareTo(Pitch.AS) == 0) {
      count += 10;
    } else {
      count += 11;
    }
    return count;
  }


  /**
   * method which returns the volume of a note
   * @return int -> the volume of the note
   */

  public int getVolume() {
    return this.volume;
  }


  /**
   * method which sets the volume of a note
   * @return newVolume -> the volume of the note
   */

  public void setVolume(int newVolume) {
    this.volume = newVolume;
  }
}