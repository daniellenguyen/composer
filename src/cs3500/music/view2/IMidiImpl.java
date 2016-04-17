package cs3500.music.view;

/**
 * Created by James on 4/13/16.
 */

/**
 * Interface for Midi as it has more functionality than is needed for IView
 */

public interface IMidiImpl extends IView {

  /**
   * void method which plays a specific note
   */

  public void playNote();
}
