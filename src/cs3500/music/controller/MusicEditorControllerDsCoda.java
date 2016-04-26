package cs3500.music.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import cs3500.music.model.IRepeat;
import cs3500.music.model.Repeat;
import cs3500.music.model.SoundUnitList;
import cs3500.music.view.ICompositeView;
import cs3500.music.view.RepeatAdderView;

/**
 * Created by Justin Hynes-Bruell on 4/24/2016.
 */
public class MusicEditorControllerDsCoda extends MusicEditorController {

  private RepeatAdderView repeatAdderView;

  /**
   * For Editing Repeats in the .txt file:
   * int direction = scanner.nextInt();
   * int location = scanner.nextInt();
   * int RepeatID = scanner.nextInt();
   * int altEnding = scanner.nextInt();
   */

  /**
   * Constructor for the controller. Does not begin playing. Press "Space" to begin playing song.
   *
   * @param model         the input song
   * @param compositeView the input composite view
   */
  public MusicEditorControllerDsCoda(SoundUnitList model, ICompositeView compositeView) {
    super(model, compositeView);
    this.configureKeyBoardListenerWithRepeat();
  }


  public void configureKeyBoardListenerWithRepeat() {
    KeyboardHandler kbd = new KeyboardHandler();

    kbd.getKeyTypedMap().put('r', new Runnable() {
      public void run() {
        System.out.println("Add Repeat Window!\n");
        repeatAdderViewCreator();
      }
    });
    super.compositeView.addKeyListener(kbd);
  }


  public void exitFromRepeatAdder() {
    repeatAdderView.setVisible(false);
    super.compositeView.refreshGuiViewFromModel(model);
    //this.compositeView.setGuiView(new GuiViewFrame(model));
    super.compositeView.initialize();
    super.compositeView.resetFocus();
    super.compositeView.addActionListener(this);
    super.configureKeyBoardListener();
    super.configureMouseListener();
    this.configureKeyBoardListenerWithRepeat();
  }

  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      //read from the input textfield
      case "Set Repeat Button":
        System.out.println("Set Repeat Button Pressed");
        System.out.println("Type: " + repeatAdderView.getInputType());
        System.out.println("Location: " + repeatAdderView.getInputLocation());
        System.out.println("Alt Ending Length: " + repeatAdderView.getInputAltEnding());


        IRepeat.RepeatType inputType = IRepeat.RepeatType.Backward;
        if(repeatAdderView.getInputType().equals("Forward")){
          inputType = IRepeat.RepeatType.Forward;
        }

        //Add Repeat to Model
        super.model.addRepeat(new Repeat(inputType, Integer.parseInt(repeatAdderView.getInputLocation()), 1, Integer.parseInt(repeatAdderView.getInputAltEnding())));

        exitFromRepeatAdder();
        break;
      case "Exit Button":
        System.out.println("Exit Button Pressed");
        exitFromRepeatAdder();
        break;
    }
  }


  public void repeatAdderViewCreator(){
    super.compositeView.setVisible(false);
    repeatAdderView = new RepeatAdderView();
    repeatAdderView.resetFocus();
    repeatAdderView.addActionListener(this);
  }

  @Override
  public void RenderNewBeat() {
    super.RenderNewBeat();

    //Correct Beat for if it's at a repeat.
    List<IRepeat> repeats = super.model.getRepeatSet();
    for(int i = 0; i < repeats.size(); i++){

      if(super.model.getCurrentBeat() == repeats.get(i).getLocation() && !repeats.get(i).getPlayed()){
        int beatSet = 0;
        //Backwards Repeat was hit
        if(repeats.get(i).getType().equals(IRepeat.RepeatType.Backward)){
          //Search for a forward repeat that is before this repeat and not played
          for(int j = 0; j < repeats.size(); j++){
            if(super.model.getCurrentBeat() > repeats.get(j).getLocation() && !repeats.get(j).getPlayed() && repeats.get(j).getType().equals(IRepeat.RepeatType.Forward)){
              //Set To Forward Repeat
              beatSet = repeats.get(j).getLocation();
              repeats.get(j).setPlayed(true);
              break;
            }
          }

          super.model.setCurrentBeat(beatSet);

          //Set Repeat to Played
          repeats.get(i).setPlayed(true);
        }
      }
    }

    //Correct for alternative ending
    for(int i = 0; i < repeats.size(); i++){
      if(super.model.getCurrentBeat() == repeats.get(i).getLocation() - repeats.get(i).getAlternativeEndingLength() && repeats.get(i).getPlayed()){
        super.model.setCurrentBeat(repeats.get(i).getLocation());
      }
    }
  }

  @Override
  public void playFromBeginning() {
    List<IRepeat> repeats = super.model.getRepeatSet();
    for(int i = 0; i < repeats.size(); i++){
      repeats.get(i).setPlayed(false);
    }
    super.playFromBeginning();
  }
}
