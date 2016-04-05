package cs3500.music.view;
import java.util.Timer;

import cs3500.music.model.SoundUnitList;

/**
 * An interface to represent compositions in different forms.
 */
public interface View {

  /**
   * TODO:
   * Thoughts about view interface:
   * It is very hard to abstract anything here without overgeneralizing
   * method signatures for the most similar methods in each view.
   * Therefore maybe the view interface should accommodate what the
   * views explicitly share, like a timer (although a timer does
   * not exist in console).
   *
   * An alternative to the current hierarchy is to make an interface for
   * every view, and have them all those interfaces extend a single
   * general view interface. It's more cluttered this way but there is
   * definitely interface/class separation.
   *
   * The final way this is designed should reflect and be symmetrical with
   * the extension that the GUI interface must have in order to hold additional
   * methods irrelevant to the other two views.
   */
  void Render(SoundUnitList listOfNote);
}
