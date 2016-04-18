package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.Note;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;
import cs3500.music.model.SoundUnitListToIPlayerModelAdapter;
import cs3500.music.model2.IPlayerModel;
import cs3500.music.view2.*;
import cs3500.music.view2.GuiViewFrame;


public class CompositeViewAdapter implements cs3500.music.view.ICompositeView {

//  cs3500.music.view2.IGuiView guiView;
//  cs3500.music.view2.IMidiImpl midiView;
  cs3500.music.view2.GuiViewFrame guiView;
  cs3500.music.view2.MidiViewImpl midiView;


  public CompositeViewAdapter(cs3500.music.view2.GuiViewFrame newGuiView, cs3500.music.view2.MidiViewImpl newMidiView) {
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
    this.guiView.initialize();
  }

  @Override
  public void resetFocus() {
    this.guiView.setFocusable(true);
    this.guiView.requestFocus();
  }

  @Override
  public void addKeyListener(KeyboardHandler keyHandler) {
    this.guiView.addKeyListener(keyHandler);
  }

  @Override
  public void setVisible(boolean state) {
    this.guiView.setVisible(state);
  }

  @Override
  public void playBeat(Integer BeatNumber) {
    guiView.getModel().setCurrentBeat(BeatNumber);
    guiView.initialize();
    /*
    IPlayerModel modelToUpdate = guiView.getModel();
    modelToUpdate.setCurrentBeat(BeatNumber);
    this.guiView = new GuiViewFrame(modelToUpdate);*/
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
