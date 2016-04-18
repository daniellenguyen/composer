package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;
import cs3500.music.view2.IGuiView;


public class ControllerCompositeAdapter implements cs3500.music.view.ICompositeView {

  GuiViewAdapter GuiAdapter;
  MidiViewAdapter MidiAdapter;

  public ControllerCompositeAdapter(GuiViewFrame guiViewFrame, MidiViewImpl midiView) {
    GuiAdapter = new GuiViewAdapter(new NoteList());
    MidiAdapter = new MidiViewAdapter(new NoteList());
  }

  public GuiViewFrame getGuiView() {
    return GuiAdapter;
  }

  public MidiViewImpl getMidiView(){
    return MidiAdapter;
  }

  //May want to add Somthing here
  public void setGuiView(GuiViewFrame guiView) {
  }

  public void setMidiView(GuiViewFrame guiView){

  }

  public void RenderNewBeat(){

  }


  public Note NotePressed(Point mousePoint, SoundUnitList model) {
    return new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 999, 1000);
  }


  public Note SpacePressed(Point mousePoint, SoundUnitList model) {
    return new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 999, 1000);
  }

  @Override
  public void addNewMouseListener(MouseListener listener) {

  }

  @Override
  public void addActionListener(ActionListener listener) {

  }

  @Override
  public void refreshGuiViewFromModel(SoundUnitList refreshedModel) {

  }

  @Override
  public void initialize() {

  }

  @Override
  public void resetFocus() {

  }

  @Override
  public void addKeyListener(KeyboardHandler keyHandler) {

  }

  @Override
  public void setVisible(boolean state) {

  }

  @Override
  public void playBeat(Integer BeatNumber) {

  }

  public void render(){

  }

}
