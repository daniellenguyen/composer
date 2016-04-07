package cs3500.music.view;

import cs3500.music.model.SoundUnitList;

/**
 * Created by Justin Hynes-Bruell on 4/6/2016.
 */
public class CompositeView implements View {

  private GuiViewFrame guiView;
  private MidiViewImpl midiView;

  public CompositeView(GuiViewFrame guiView, MidiViewImpl midiView){
    this.guiView = guiView;
    this.midiView = midiView;
  }

  public GuiViewFrame getGuiView(){
    return this.guiView;
  }

  public void setGuiView(GuiViewFrame guiView){
    this.guiView = guiView;
  }

  public MidiViewImpl getMidiView(){
    return this.midiView;
  }

  @Override
  public void Render(SoundUnitList listOfNote) {

  }
}
