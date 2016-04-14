package cs3500.music.model2;

import java.util.Comparator;

/**
 * Created by James on 3/24/16.
 */
public class NoteComparatorTextView implements Comparator<INote> {

  /**
   * Returns how many players can still make a move
   *
   * @param o1 -> A Note
   * @param o2 -> A Note
   * @return an integer representing which notes (based on octave -> pitch -> starttime) come first
   */

  public int compare(INote o1, INote o2) {

    if (o1.getOctave() < o2.getOctave()) {
      return -1;
    } else if (o1.getOctave() > o2.getOctave()) {
      return 1;
    } else {
      if (o1.getPitch().compareTo(o2.getPitch()) > 0) {
        return 1;
      } else if (o1.getPitch().compareTo(o2.getPitch()) < 0) {
        return -1;
      } else {
        if (o1.getStart() < o2.getStart()) {
          return -1;
        } else if (o1.getStart() > o2.getStart()) {
          return 1;
        } else {
          return 0;
        }
      }
    }
  }

}




