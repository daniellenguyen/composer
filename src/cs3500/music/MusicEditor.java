package cs3500.music;

import cs3500.music.controller.MusicEditorController;
import cs3500.music.model.SoundUnitList;
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


    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile(args[0]);

    SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mary-little-lamb.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-1.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("ChromaticScale.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("BugTestSong.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-2.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-3.txt");

    GuiViewFrame GuiView = (GuiViewFrame) ViewCreator.create(ViewCreator.
            ViewType.GUI, inputSong);
    MidiViewImpl midiView = (MidiViewImpl) ViewCreator.create(ViewCreator.
            ViewType.MIDI, inputSong);

   MusicEditorController asd = new MusicEditorController(inputSong, GuiView, midiView, null);

  }
}
