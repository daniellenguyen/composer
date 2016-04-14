package cs3500.music.model2;

/**
 * Created by James on 3/22/16.
 */

public interface INote {


  /**
   * method which gets the name of the Instrument of the Note
   *
   * @return outputString -> name of the Instrument
   */

  int getInstrument();


  /**
   * method which returns the pitch of the Note
   *
   * @return outputPitch -> the pitch of the Note
   */

  Note.Pitch getPitch();


  /**
   * method which returns the Octave of the Note
   *
   * @return outputOctave -> the octave of the Note
   */

  int getOctave();


  /**
   * method which returns the duration of the Note
   *
   * @return outputDuration -> the duration of the Note
   */

  int getDuration();


  /**
   * method which returns whether the note is Audible or not()
   *
   * @return outputAudible -> boolean indicating whether the note is audible or not
   */

  boolean getAudible();


  /**
   * method which returns the start of the Note
   *
   * @return outputStart -> the start of the Note
   */

  int getStart();


  /**
   * method which returns the end of the Note
   *
   * @return outputEnd -> the end of the Note
   */

  int getEnd();


  /**
   * getVolume returns the Volume of a Note
   *
   * @return int -> the Volume of the Note
   */

  int getVolume();


  /**
   * method which a note as a String
   *
   * @return outputNote -> the Note as a string
   */

  String getNoteAsString();


  /**
   * method which sets the name of the Instrument of the Note
   *
   * @param newName -> name of the Instrument
   */

  void setInstrument(int newName);


  /**
   * setVolume returns the Volume of a Note
   *
   * @param inti -> the Volume of the Note to be set
   */

  void setVolume(int inti);


  /**
   * method which sets the Octave of the Note
   *
   * @param inputOctave -> int value of the Note
   */

  void setOctave(int inputOctave);


  /**
   * method which sets the Start of the note
   *
   * @param iStart -> start of the note
   */

  void setStart(int iStart);


  /**
   * method which sets the Pitch of the Note
   *
   * @param inputPitch -> value of the Pitch of the Note
   */

  void setPitch(Note.Pitch inputPitch);


  /**
   * converts a Note into a integer value indicative of it's midi number
   *
   * @return outputNumber -> the Value of the midi number
   */

  int convertToMidiNumber();


}

