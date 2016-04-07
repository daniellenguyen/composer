package cs3500.music.controller;

import cs3500.music.MusicEditor;
import cs3500.music.model.SoundUnitList;
import cs3500.music.view.CompositeView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;

import java.awt.event.*;

/**
 * Created by daniellenguyen on 4/6/16.
 */
public class MockController implements ActionListener {
  MusicEditorController controller;

  public MockController(SoundUnitList soundUnitList) {
    controller = new MusicEditorController(soundUnitList, new CompositeView(new
            GuiViewFrame(soundUnitList), new MidiViewImpl(soundUnitList)));
  }


  public void actionPerformed(ActionEvent e) {}

}
