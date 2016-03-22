package cs3500.music;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.util.MusicBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConsoleViewImpl;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.ViewCreator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException, InterruptedException {
    GuiViewFrame view = new GuiViewFrame();
    view.initialize();

    //MidiViewImpl midiView = new MidiViewImpl();
    MidiViewImpl midiView = (MidiViewImpl) ViewCreator.create(ViewCreator.ViewType.MIDI);
    //midiView.playNote();
    //midiView.playSong("mary-little-lamb.txt");
    //midiView.playSong("mystery-2.txt");
    //midiView.playSong("mystery-2.txt");
    //midiView.playSong("mystery-2.txt");
    midiView.playSongFromText("mystery-2.txt");

    Thread.sleep(3000);
    // You probably need to connect these views to your model, too...
  }
}
