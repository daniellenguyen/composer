package cs3500.music;
import cs3500.music.model.NoteList;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConsoleViewImpl;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.ViewCreator;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException, InterruptedException {

    MusicReader ReaderOfText = new MusicReader();

    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mary-little-lamb.txt");
    NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-1.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-2.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-3.txt");

    GuiViewFrame GuiView = (GuiViewFrame) ViewCreator.create(ViewCreator.ViewType.GUI, inputSong);
    ///////GuiViewFrame GuiView = new GuiViewFrame(inputSong);
    GuiView.initialize();
//
//    MidiViewImpl midiView = (MidiViewImpl) ViewCreator.create(ViewCreator.ViewType.MIDI, inputSong);
//    ///////MidiViewImpl midiView = new MidiViewImpl();
//    midiView.playSong(inputSong);

    ConsoleViewImpl consoleView = (ConsoleViewImpl) ViewCreator.create(ViewCreator.ViewType.CONSOLE, inputSong);
    consoleView.consoleRender(inputSong);

    Thread.sleep(3000);
    // You probably need to connect these views to your model, too...
  }
}
