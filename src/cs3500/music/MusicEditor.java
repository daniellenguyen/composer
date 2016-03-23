package cs3500.music;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.ViewCreator;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException, InterruptedException {

    GuiViewFrame view = new GuiViewFrame();
    view.DisplaySongFromFile("mary-little-lamb.txt");
    view.initialize();

    //MidiViewImpl midiView = new MidiViewImpl();
    MidiViewImpl midiView = (MidiViewImpl) ViewCreator.create(ViewCreator.ViewType.MIDI);
    //midiView.playNote();
    //midiView.playSong("mary-little-lamb.txt");
    //midiView.playSong("mystery-2.txt");
    //midiView.playSong("mystery-2.txt");
    //midiView.playSong("mystery-2.txt");
    //midiView.playSongFromText("mystery-3.txt");

    Thread.sleep(3000);
    // You probably need to connect these views to your model, too...
  }
}
