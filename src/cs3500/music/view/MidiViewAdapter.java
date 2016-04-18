package cs3500.music.view;

import cs3500.music.model.SoundUnitList;

/**
 * Created by Justin Hynes-Bruell on 4/17/2016.
 */
public class MidiViewAdapter extends MidiViewImpl{
  cs3500.music.view2.MidiViewImpl MidiView;

  /**
   * Creates MidiView
   * @param soundUnitList
   */
  public MidiViewAdapter(SoundUnitList soundUnitList) {
    super(soundUnitList);
  }

  public void playBeat(int beat){

  }

  public void setMidiView(cs3500.music.view2.MidiViewImpl inputMidiView){
    MidiView = inputMidiView;
  }
}
