package cs3500.music.view;

import cs3500.music.model.SoundUnitList;

/**
 * Created by Justin Hynes-Bruell on 4/6/2016.
 */
public class CompositeView implements View {
  GuiViewFrame guiViewFrame;
  MidiViewImpl midiView;

  public CompositeView(GuiViewFrame guiViewFrame, MidiViewImpl midiView){
    this.guiViewFrame = guiViewFrame;
    this.midiView = midiView;
  }

  public GuiViewFrame getGuiView() {
    return guiViewFrame;
  }

  public void setGuiView(GuiViewFrame guiView) {
    guiViewFrame = guiView;
  }

  public MidiViewImpl getMidiView() {
    return midiView;
  }

  @Override
  public void render() {

  }
}
