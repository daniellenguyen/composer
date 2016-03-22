package cs3500.music;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.util.MusicBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException, InterruptedException {
    GuiViewFrame view = new GuiViewFrame();
    //view.initialize();


    MidiViewImpl midiView = new MidiViewImpl();
    //midiView.playNote();

    MusicReader ReaderOfText = new MusicReader();
    MusicBuilder Builder = new MusicBuilder();
    try {
      ReaderOfText.parseFile(new FileReader("mary-little-lamb.txt"), Builder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    NoteList MarysLamb = Builder.build();

    for(int i = 0; i < MarysLamb.songLength(); i++) {
      midiView.playBeat(MarysLamb, 0);
      try {
        Thread.sleep(1000);                 //1000 milliseconds is one second.
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
    }



    Thread.sleep(3000);
    // You probably need to connect these views to your model, too...
  }
}
