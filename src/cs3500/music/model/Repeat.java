package cs3500.music.model;

/**
 * Created by Justin Hynes-Bruell on 4/25/2016.
 */
public class Repeat implements IRepeat{

  RepeatType repeatType;
  Integer repeatBeat;
  Integer repeatNumber;
  Integer alternativeEnding;
  boolean played;

  public Repeat(RepeatType inputRepeatType, Integer inputRepeatBeat, Integer inputRepeatNumber, Integer inputAltEnding){
    this.repeatType = inputRepeatType;
    this.repeatBeat = inputRepeatBeat;
    this.repeatNumber = inputRepeatNumber;
    this.alternativeEnding = inputAltEnding;
    this.played = false;
  }

  @Override
  public void setType(RepeatType inputType) {
    this.repeatType = inputType;
  }

  @Override
  public void setLocation(int inputBeat) {
    this.repeatBeat = inputBeat;
  }

  @Override
  public void setRepeatNumber(int inputNumber) {
    this.repeatNumber = inputNumber;
  }

  @Override
  public void setAlternativeEndingLength(int inputLength) {
    this.alternativeEnding = inputLength;
  }

  @Override
  public void setPlayed(boolean status) {
    this.played = status;
  }

  @Override
  public RepeatType getType() {
    return this.repeatType;
  }

  @Override
  public int getLocation() {
    return this.repeatBeat;
  }

  @Override
  public int getRepeatNumber() {
    return this.repeatNumber;
  }

  @Override
  public int getAlternativeEndingLength() {
    return this.alternativeEnding;
  }

  @Override
  public boolean getPlayed() {
    return this.played;
  }

}
