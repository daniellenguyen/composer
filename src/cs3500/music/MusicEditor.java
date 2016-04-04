package cs3500.music;

import cs3500.music.controller.MusicEditorController;
import cs3500.music.model.NoteList;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConsoleViewImpl;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.ViewCreator;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException,
          InvalidMidiDataException, InterruptedException {

    MusicReader ReaderOfText = new MusicReader();


    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile(args[0]);


    NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mary-little-lamb.txt");
    /// /NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-1.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("ChromaticScale.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("BugTestSong.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-2.txt");
    NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-3.txt");

    //GuiViewFrame GuiView = (GuiViewFrame) ViewCreator.create
    // (ViewCreator.ViewType.GUI, inputSong);
    ///////GuiViewFrame GuiView = new GuiViewFrame(inputSong);
    //GuiView.initialize();

    ConsoleViewImpl consoleView = (ConsoleViewImpl) ViewCreator.create(ViewCreator.
            ViewType.CONSOLE, inputSong);
    MidiViewImpl midiView = (MidiViewImpl) ViewCreator.create(ViewCreator.
            ViewType.MIDI, inputSong);
    GuiViewFrame GuiView = (GuiViewFrame) ViewCreator.create(ViewCreator.
            ViewType.GUI, inputSong);

    MusicEditorController asd = new MusicEditorController(inputSong, GuiView, midiView, consoleView);
    
  }
}
