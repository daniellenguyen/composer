package cs3500.music;

import cs3500.music.controller.MusicEditorController;
import cs3500.music.model.SoundUnitList;
import cs3500.music.model.SoundUnitListToIPlayerModelAdapter;
import cs3500.music.util.MusicReader;

import java.io.IOException;
import java.util.Objects;

import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException,
          InvalidMidiDataException, InterruptedException {

    MusicReader ReaderOfText = new MusicReader();

    //args = new String[2];
    //args[0] = "mary-little-lamb.txt";
    //args[1] = "console";

    SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile(args[0]);

    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mary-little-lamb.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-1.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("ChromaticScale.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("BugTestSong.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-2.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-3.txt");

    //Create Sound Unit Adapter
    SoundUnitListToIPlayerModelAdapter PlayerModelAdapted = new SoundUnitListToIPlayerModelAdapter("SampleSong");
    PlayerModelAdapted.setPlayerModelFromSongList(inputSong);

    //Old Views and Controller
    if(Objects.equals(args[1], "composite2")){
      cs3500.music.view.CompositeView newCompositeView = (cs3500.music.view.CompositeView)
              cs3500.music.view.ViewCreator.create(cs3500.music.view.ViewCreator.ViewType.COMPOSITE, inputSong);
      MusicEditorController asd = new MusicEditorController(inputSong, newCompositeView);
    }
    else if(Objects.equals(args[1], "gui2")){
      cs3500.music.view.GuiViewFrame newGuiView = new cs3500.music.view.GuiViewFrame(inputSong);
      newGuiView.initialize();
    }
    else if(Objects.equals(args[1], "midi2")){
      cs3500.music.view.MidiViewImpl newMidiView = new cs3500.music.view.MidiViewImpl(inputSong);
      newMidiView.playSong();
    }
    else if(Objects.equals(args[1], "console2")){
      cs3500.music.view.ConsoleViewImpl newConsoleView = new cs3500.music.view.ConsoleViewImpl(inputSong);
      newConsoleView.render();
    }
    else if(Objects.equals(args[1], "controller2")){
      cs3500.music.view.CompositeView newCompositeView2 = (cs3500.music.view.CompositeView)
              cs3500.music.view.ViewCreator.create(cs3500.music.view.ViewCreator.ViewType.COMPOSITE, inputSong);
      MusicEditorController asddd = new MusicEditorController(inputSong, newCompositeView2);
    }

    //New Views and Controller
    else if(Objects.equals(args[1], "console")) {
      //CONSOLE VIEW
      cs3500.music.view2.TextView newTextView = new cs3500.music.view2.TextView(PlayerModelAdapted);
      newTextView.outputView();
    }

    else if(Objects.equals(args[1], "gui")) {
      //GUI VIEW
      cs3500.music.view2.GuiViewFrame newGuiView = new cs3500.music.view2.GuiViewFrame(PlayerModelAdapted);
    }
    else if(Objects.equals(args[1], "midi")) {
      //MIDI VIEW
      cs3500.music.view2.MidiViewImpl newMidiView = new cs3500.music.view2.MidiViewImpl(PlayerModelAdapted);
      newMidiView.outputView();
    }
    else if(Objects.equals(args[1], "composite")) {
      //COMPOSITE VIEW
      cs3500.music.view2.GuiViewFrame newGuiView = new cs3500.music.view2.GuiViewFrame(PlayerModelAdapted);
      cs3500.music.view2.MidiViewImpl newMidiView = new cs3500.music.view2.MidiViewImpl(PlayerModelAdapted);
      cs3500.music.view.ICompositeView newControllerCompositeAdapter = new cs3500.music.view.CompositeViewAdapter(newGuiView, newMidiView);
    }

    else if(Objects.equals(args[1], "controller")) {
      //Begin Controller with Adapted Composite View
      cs3500.music.view2.GuiViewFrame newGuiView = new cs3500.music.view2.GuiViewFrame(PlayerModelAdapted);
      cs3500.music.view2.MidiViewImpl newMidiView = new cs3500.music.view2.MidiViewImpl(PlayerModelAdapted);
      cs3500.music.view.ICompositeView newControllerCompositeAdapter = new cs3500.music.view.CompositeViewAdapter(newGuiView, newMidiView);
      MusicEditorController AdaotedInternalsController = new MusicEditorController(inputSong, newControllerCompositeAdapter);
    }
  }
}
