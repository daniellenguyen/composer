package cs3500.music.view2;

import cs3500.music.model2.IPlayerModel;



/**
 * Created by James on 3/23/16.
 */


/**
 * Class which creates a FactoryMethod which outputs an IView
 */

public class FactoryMethod implements IFactoryMethod {

  private IPlayerModel inputModel;


  /**
   * Constructor for FactoryMethod which takes in a model
   */

  public FactoryMethod(IPlayerModel inputModeli){
    inputModel = inputModeli;
  }


  /**
   * Takes in a string and outputs an instance of IView
   *
   * @param inputString -> string indicative of which view you want
   * @return IView -> the instance of IView you wish
   */

  public IView outputViewType(String inputString) throws IllegalArgumentException {
    if (inputString.compareTo("console") == 0){
      return new TextView(inputModel);
    }

    else if(inputString.compareTo("visual") == 0){
      return new GuiViewFrame(inputModel);
    }

    else if(inputString.compareTo("midi") == 0){
      return new MidiViewImpl(inputModel);
    }

    else {
      throw new IllegalArgumentException("This is not a valid input type");
    }
  }

}
