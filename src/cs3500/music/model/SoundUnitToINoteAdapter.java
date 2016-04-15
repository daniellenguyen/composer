package cs3500.music.model;

import static cs3500.music.model.Note.*;
import cs3500.music.model2.INote;

/**
 * Created by Justin Hynes-Bruell on 4/14/2016.
 */
public class SoundUnitToINoteAdapter implements INote {

  Note ObjectAdaptorNote;

  public SoundUnitToINoteAdapter(int ioctave, cs3500.music.model2.Note.Pitch ipitch, int iduration, int istart, int instrumenti, int volumei) {
    ObjectAdaptorNote = new Note(convertPitchINoteToSoundUnit(ipitch), (Octave)intToOctave(ioctave), istart, istart+iduration);
    ObjectAdaptorNote.setVolume(volumei);
    ObjectAdaptorNote.setInstrument(instrumenti);
  }

  public SoundUnitToINoteAdapter(){

  }


  @Override
  public int getInstrument() {
    return ObjectAdaptorNote.getInstrument();
  }

  @Override
  public cs3500.music.model2.Note.Pitch getPitch() {
    return convertPitchSoundUnitToINote(ObjectAdaptorNote.getPitch());
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
  public void setPitch(cs3500.music.model2.Note.Pitch inputPitch) {
    ObjectAdaptorNote.setPitch(convertPitchINoteToSoundUnit(inputPitch));
  }

  @Override
  public int convertToMidiNumber() {
    return ObjectAdaptorNote.getMIDIPitch();
  }

  public SoundUnit ConvertINoteToSoundUnit(INote inputNote){
    SoundUnit newNote = new Note(convertPitchINoteToSoundUnit(inputNote.getPitch()),
            (Octave)intToOctave(inputNote.getOctave()), inputNote.getStart(),
            inputNote.getStart()+inputNote.getEnd());
    newNote.setVolume(inputNote.getVolume());
    newNote.setInstrument(inputNote.getInstrument());
    return newNote;
  }
  public INote ConvertSoundUnitToINote(SoundUnit inputNote){
    INote newNote = new SoundUnitToINoteAdapter(convertOctaveSoundUnitToINote(inputNote.getOctave()),
            convertPitchSoundUnitToINote(inputNote.getPitch()), (inputNote.getEnd()-inputNote.getStart()),
            inputNote.getStart(), inputNote.getVolume(), inputNote.getInstrument());
    return newNote;
  }


  /////////////////////CONVERTERS/////////////////////////////

  private SoundUnit.Pitch convertPitchINoteToSoundUnit(cs3500.music.model2.Note.Pitch inputPitch){
    SoundUnit.Pitch newPitch = Pitch.C;
    switch (inputPitch) {
      case C:
        newPitch = Pitch.C;
        break;
      case CS:
        newPitch = Pitch.C2;
        break;
      case D:
        newPitch = Pitch.D;
        break;
      case DS:
        newPitch = Pitch.D2;
        break;
      case E:
        newPitch = Pitch.E;
        break;
      case F:
        newPitch = Pitch.F;
        break;
      case FS:
        newPitch = Pitch.F2;
        break;
      case G:
        newPitch = Pitch.G;
        break;
      case GS:
        newPitch = Pitch.G2;
        break;
      case A:
        newPitch = Pitch.A;
        break;
      case AS:
        newPitch = Pitch.A2;
        break;
      case B:
        newPitch = Pitch.B;
        break;
    }
    return newPitch;
  }

  private cs3500.music.model2.Note.Pitch convertPitchSoundUnitToINote(SoundUnit.Pitch inputPitch){
    cs3500.music.model2.Note.Pitch newPitch;
    newPitch = cs3500.music.model2.Note.Pitch.C;
    switch (inputPitch) {
      case C:
        newPitch = cs3500.music.model2.Note.Pitch.C;
        break;
      case C2:
        newPitch = cs3500.music.model2.Note.Pitch.CS;
        break;
      case D:
        newPitch = cs3500.music.model2.Note.Pitch.D;
        break;
      case D2:
        newPitch = cs3500.music.model2.Note.Pitch.DS;
        break;
      case E:
        newPitch = cs3500.music.model2.Note.Pitch.E;
        break;
      case F:
        newPitch = cs3500.music.model2.Note.Pitch.F;
        break;
      case F2:
        newPitch = cs3500.music.model2.Note.Pitch.FS;
        break;
      case G:
        newPitch = cs3500.music.model2.Note.Pitch.G;
        break;
      case G2:
        newPitch = cs3500.music.model2.Note.Pitch.GS;
        break;
      case A:
        newPitch = cs3500.music.model2.Note.Pitch.A;
        break;
      case A2:
        newPitch = cs3500.music.model2.Note.Pitch.AS;
        break;
      case B:
        newPitch = cs3500.music.model2.Note.Pitch.B;
        break;
    }
    return newPitch;
  }

  private int convertOctaveSoundUnitToINote(SoundUnit.Octave inputOctave){
    int outputOctave = 4;
    switch (inputOctave) {
      case ONE:
        outputOctave = 1;
        break;
      case TWO:
        outputOctave = 2;
        break;
      case THREE:
        outputOctave = 3;
        break;
      case FOUR:
        outputOctave = 4;
        break;
      case FIVE:
        outputOctave = 5;
        break;
      case SIX:
        outputOctave = 6;
        break;
      case SEVEN:
        outputOctave = 7;
        break;
      case EIGHT:
        outputOctave = 8;
        break;
      case NINE:
        outputOctave = 9;
        break;
      case TEN:
        outputOctave = 10;
        break;
      case ELEVEN:
        outputOctave = 11;
        break;
    }
    return outputOctave;
  }


  private SoundUnit.Octave intToOctave(int ioctave){
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
}
