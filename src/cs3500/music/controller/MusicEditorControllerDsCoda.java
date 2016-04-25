package cs3500.music.controller;

import java.util.List;

import cs3500.music.model.IRepeat;
import cs3500.music.model.SoundUnitList;
import cs3500.music.view.ICompositeView;

/**
 * Created by Justin Hynes-Bruell on 4/24/2016.
 */
public class MusicEditorControllerDsCoda extends MusicEditorController {

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
