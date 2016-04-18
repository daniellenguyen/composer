package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.Note;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;
import cs3500.music.model.SoundUnitListToIPlayerModelAdapter;


public class CompositeViewAdapter implements cs3500.music.view.ICompositeView {

  cs3500.music.view2.IGuiView guiView;
  cs3500.music.view2.IMidiImpl midiView;

  public CompositeViewAdapter(cs3500.music.view2.IGuiView newGuiView, cs3500.music.view2.IMidiImpl newMidiView) {
    this.guiView = newGuiView;
    this.midiView = newMidiView;
  }

  //TODO This is gonna be tricky
  @Override
  public Note NotePressed(Point mousePoint, SoundUnitList model) {
    return new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 999, 1000);
  }

  //TODO This is gonna be tricky
  @Override
  public Note SpacePressed(Point mousePoint, SoundUnitList model) {
    return new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 999, 1000);
  }

  @Override
  public void addNewMouseListener(MouseListener listener) {
    guiView.addMouseListener(listener);
  }


  @Override
  public void addActionListener(ActionListener listener) {
    //TODO Consider Removing, does Nothing
  }

  @Override
  public void refreshGuiViewFromModel(SoundUnitList refreshedModel) {
    SoundUnitListToIPlayerModelAdapter PlayerModelAdapted = new SoundUnitListToIPlayerModelAdapter("SampleSong");
    PlayerModelAdapted.setPlayerModelFromSongList(refreshedModel);
    this.guiView = new cs3500.music.view2.GuiViewFrame(PlayerModelAdapted);
  }

  @Override
  public void initialize() {
    //TODO His model Does Not Require This
  }

  @Override
  public void resetFocus() {
    //TODO Methods are Private, thus his Model Does not Allow for this Ability
//    this.guiView.setFocusable(true);
//    this.guiView.requestFocus();
  }

  @Override
  public void addKeyListener(KeyboardHandler keyHandler) {
    this.guiView.addKeyListener(keyHandler);
  }

  @Override
  public void setVisible(boolean state) {
    //TODO this is somehow also not in his model
//    this.guiView.setVisible(state);
  }

  @Override
  public void playBeat(Integer BeatNumber) {
    this.guiView.updateScroll("x", BeatNumber);
    this.midiView.playNote();
    /*try {
      midiView.playBeat(BeatNumber);
    } catch (InvalidMidiDataException e) {
      //arrowRight();
    }*/
  }

  public void render(){

  }

}
