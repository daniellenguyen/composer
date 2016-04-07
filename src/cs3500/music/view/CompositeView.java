package cs3500.music.view;

import cs3500.music.model.SoundUnitList;

/**
 * Created by Justin Hynes-Bruell on 4/6/2016.
 */
public class CompositeView implements View {
  SoundUnitList soundUnitList;

  public CompositeView(SoundUnitList soundUnitList){
    this.soundUnitList = soundUnitList;
  }

  @Override
  public void render() {

  }
}
