package cs3500.music.view;

import cs3500.music.model.NoteList;

/**
 * Created by Justin Hynes-Bruell on 3/22/2016.
 */
public class ViewCreator {

  public enum ViewType {GUI, MIDI, CONSOLE}

  public static View create(ViewType type, NoteList inputSong) {
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
