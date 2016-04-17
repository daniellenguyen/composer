package cs3500.music.model;

import java.util.Comparator;

/**
 * Created by James on 3/24/16.
 */
public class NoteComparatorStartTime implements Comparator<INote> {

  /**
   * Returns how many players can still make a move
   *
   * @param o1 -> A Note
   * @param o2 -> A Note
   * @return an integer representing which note starts earlier
   */

  public int compare(INote o1, INote o2){
    if (o1.getStart() < o2.getStart()){
      return -1;
    }
    else if(o1.getStart() == o2.getStart()){
      return 0;
    }
    else {
      return 1;
    }
  }
}
