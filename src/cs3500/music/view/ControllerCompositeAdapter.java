package cs3500.music.view;

import java.awt.*;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;

/**
 * To adapt the Controller to new Views
 */
public class ControllerCompositeAdapter extends CompositeView {

  GuiViewAdapter GuiAdapter;
  MidiViewAdapter MidiAdapter;

  public ControllerCompositeAdapter(GuiViewFrame guiViewFrame, MidiViewImpl midiView) {
    super(guiViewFrame, midiView);

    GuiAdapter = new GuiViewAdapter(new NoteList());
    MidiAdapter = new MidiViewAdapter(new NoteList());
  }

  public GuiViewFrame getGuiView() {
    return GuiAdapter;
  }

  public MidiViewImpl getMidiView() {
    return midiView;
  }

  //May want to add Somthing here
  public void setGuiView(GuiViewFrame guiView) {
  }

  public void setMidiView(GuiViewFrame guiView) {
    guiViewFrame = guiView;
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
  public void render(){

  }

}
