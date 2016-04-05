package cs3500.music.view;

import cs3500.music.model.SoundUnitList;

/**
 * Creator to Make Views. Be sure to call create!
 */
public class ViewCreator {

  public enum ViewType {GUI, MIDI, CONSOLE}

  public ViewCreator(){
  }

  public static View create(ViewType type, SoundUnitList inputSong) {
    if (type == ViewType.GUI) {
      return new GuiViewFrame(inputSong);
    }else if (type == ViewType.MIDI) {
      return new MidiViewImpl();
    }
    else {
      return new ConsoleViewImpl();
    }
  }

}
