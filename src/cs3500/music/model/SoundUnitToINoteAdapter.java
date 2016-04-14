package cs3500.music.model;

import static cs3500.music.model.Note.*;
import cs3500.music.model2.INote;

/**
 * Created by Justin Hynes-Bruell on 4/14/2016.
 */
public class SoundUnitToINoteAdapter implements INote {

  Note ObjectAdaptorNote;

  public SoundUnitToINoteAdapter(int ioctave, SoundUnit.Pitch ipitch, int iduration, int istart, int instrumenti, int volumei) {
    ObjectAdaptorNote = new Note(ipitch, (Octave)intToOctave(ioctave), istart, istart+iduration);
    ObjectAdaptorNote.setVolume(volumei);
    ObjectAdaptorNote.setInstrument(instrumenti);
  }

  protected Octave intToOctave(int ioctave){
    //Determine ENUM for Octave
    Octave a = Octave.FOUR;
    switch (ioctave) {
      case 1:
        a = Octave.ONE;
        break;
      case 2:
        a = Octave.TWO;
        break;
      case 3:
        a = Octave.THREE;
        break;
      case 4:
        a = Octave.FOUR;
        break;
      case 5:
        a = Octave.FIVE;
        break;
      case 6:
        a = Octave.SIX;
        break;
      case 7:
        a = Octave.SEVEN;
        break;
      case 8:
        a = Octave.EIGHT;
        break;
      case 9:
        a = Octave.NINE;
        break;
      case 10:
        a = Octave.TEN;
        break;
      case 11:
        a = Octave.ELEVEN;
        break;
    }
    return a;
  }

  @Override
  public int getInstrument() {
    return ObjectAdaptorNote.getInstrument();
  }

  @Override
  public Pitch getPitch() {
    return ObjectAdaptorNote.getPitch();
  }

  @Override
  public int getOctave() {
    return 0;
  }

  @Override
  public int getDuration() {
    return (ObjectAdaptorNote.getEnd() - ObjectAdaptorNote.getStart());
  }

  //TODO may want to review this
  @Override
  public boolean getAudible() {
    return true;
  }

  @Override
  public int getStart() {
    return ObjectAdaptorNote.getStart();
  }

  @Override
  public int getEnd() {
    return ObjectAdaptorNote.getEnd();
  }

  @Override
  public int getVolume() {
    return ObjectAdaptorNote.getVolume();
  }

  @Override
  public String getNoteAsString() {
    return ObjectAdaptorNote.toString();
  }

  @Override
  public void setInstrument(int newName) {
    ObjectAdaptorNote.setInstrument(newName);
  }

  @Override
  public void setVolume(int inti) {
    ObjectAdaptorNote.setVolume(inti);
  }

  @Override
  public void setOctave(int inputOctave) {
    ObjectAdaptorNote.setOctave(intToOctave(inputOctave));
  }

  @Override
  public void setStart(int iStart) {
    ObjectAdaptorNote.setStart(iStart);
  }

  @Override
  public void setPitch(Pitch inputPitch) {
    ObjectAdaptorNote.setPitch(inputPitch);
  }

  @Override
  public int convertToMidiNumber() {
    return ObjectAdaptorNote.getMIDIPitch();
  }
}
