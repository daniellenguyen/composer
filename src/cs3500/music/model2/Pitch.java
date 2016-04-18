package cs3500.music.model2;

public enum Pitch {
  C, CS, D, DS, E, F, FS, G, GS, A, AS, B;
  private static Pitch[] vals = values();

  /**
   * method which converts a pitch into a String
   *
   * @return String indicative of the Pitch
   */

  public String convertPitchToString() {
    if (this == C) {
      return "C";
    } else if (this == CS) {
      return "C#";
    } else if (this == D) {
      return "D";
    } else if (this == DS) {
      return "D#";
    } else if (this == E) {
      return "E";
    } else if (this == F) {
      return "F";
    } else if (this == FS) {
      return "F#";
    } else if (this == G) {
      return "G";
    } else if (this == GS) {
      return "G#";
    } else if (this == A) {
      return "A";
    } else if (this == AS) {
      return "A#";
    } else if (this == B) {
      return "B";
    } else {
      return "error";
    }
  }

  public Pitch getNext() {
    return vals[(this.ordinal() + 1) % vals.length];
  }

  public Pitch getPrev() {
    return vals[(this.ordinal() + 11) % vals.length];
  }
}