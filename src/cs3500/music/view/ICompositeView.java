package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.Note;
import cs3500.music.model.SoundUnitList;

/**
 * Created by Justin Hynes-Bruell on 4/18/2016.
 */
public interface ICompositeView {

  //GuiViewFrame getGuiView();

  //MidiViewImpl getMidiView();

  void setGuiView(View guiView);

  void setMidiView(View midiView);

  void addNewMouseListener(MouseListener listener);

  void addActionListener(ActionListener listener);

  void refreshGuiViewFromModel(SoundUnitList refreshedModel);

  void initialize();

  void resetFocus();

  void addKeyListener(KeyboardHandler keyHandler);

  void setVisible(boolean state);

  /**
   * Uses Midi View to Play an audible Beat
   * @param BeatNumber
   */
  void playBeat(Integer BeatNumber);

  void render();

  Note SpacePressed(Point mousePoint, SoundUnitList model);

  Note NotePressed(Point mousePoint, SoundUnitList model);
}
