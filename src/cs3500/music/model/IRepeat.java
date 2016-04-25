package cs3500.music.model;

/**
 * Created by Justin Hynes-Bruell on 4/25/2016.
 */
public interface IRepeat {

  /**
   * To represent the pitch of the note.
   * Scales from C to B
   */
  public enum RepeatType {
    Forward("Forward"), Backward("Backward");

    private final String asString;

    RepeatType(String asString) {
      this.asString = asString;
    }

    @Override
    public String toString() {
      return this.asString;
    }
  }

  String toString();

  void setType(RepeatType inputType);

  void setLocation(int inputBeat);

  void setRepeatNumber(int inputNumber);

  void setAlternativeEndingLength(int inputLength);

  void setPlayed(boolean status);

  RepeatType getType();

  int getLocation();

  int getRepeatNumber();

  int getAlternativeEndingLength();

  boolean getPlayed();
}
