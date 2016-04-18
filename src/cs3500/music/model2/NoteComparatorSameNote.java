package cs3500.music.model2;

import java.util.Comparator;

/**
 * Created by James on 4/5/16.
 */
public class NoteComparatorSameNote implements Comparator<INote> {

  /**
   * Checks to see if two notes are identical minus their instruments and pitches
   * 1) They are the same
   * 2) They are not the same
   *
   *
   * @param o1 -> A Note
   * @param o2 -> A Note
   * @return an integer representing which note starts earlier
   *
   */

  public int compare(INote o1, INote o2) {
    if ((o1.getOctave() == o2.getOctave()) && (o1.getPitch().convertPitchToString().
            compareTo(o2.getPitch().convertPitchToString()) == 0) &&
            (o1.getDuration() == o2.getDuration()) &&
            (o1.getStart() == o2.getStart()) &&
            (o1.getEnd() == o2.getEnd()) &&
            (o1.getInstrument() == o2.getInstrument()) &&
                    (o1.getVolume() == o2.getVolume()))
    {
      return 0;
    }
    else {
      return -1;
    }
  }
}
