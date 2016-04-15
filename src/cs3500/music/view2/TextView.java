package cs3500.music.view2;

import java.io.IOException;
//import java.util.Scanner;

import cs3500.music.model2.IPlayerModel;

/**
 * Created by James on 3/22/16.
 */

/**
 * Class which represents a TextView
 */

public class TextView implements IView {

  private IPlayerModel inputModel;
  //Scanner theScanner = new Scanner(System.in);
  Appendable ap = System.out;

  /**
   * Constructor for TextView which takes in the model
   */

  public TextView(IPlayerModel inputModeli){
    inputModel = inputModeli;
  }


  /**
   * Method which outputs the View for the TextView
   * (essentially calls outputModel from IPlayerModel)
   */

  public void outputView() {
    try {
      ap.append(inputModel.outputModel());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
