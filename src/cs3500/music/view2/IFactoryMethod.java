package cs3500.music.view;

/**
 * Created by James on 3/23/16.
 */

/**
 * Interface for creating Factory Methods
 */

public interface IFactoryMethod {


  /**
   * method to output instances of IView
   *
   * @param inputString -> name of the class which implements IVIew you want
   */

  IView outputViewType(String inputString);
}
