package cs3500.music;

import cs3500.music.controller.MusicEditorController;
import cs3500.music.model.NoteList;
import cs3500.music.model.SoundUnitList;
import cs3500.music.model.SoundUnitListToIPlayerModelAdapter;
import cs3500.music.model2.INote;
import cs3500.music.util.MusicReader;
import cs3500.music.view.CompositeView;
import cs3500.music.view.ConsoleViewImpl;
import cs3500.music.view.ControllerCompositeAdapter;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.View;
import cs3500.music.view.ViewCreator;
import cs3500.music.view2.CombinedView;
import cs3500.music.view2.ICompositeView;
import cs3500.music.view2.IGuiView;
import cs3500.music.view2.IMidiImpl;
import cs3500.music.view2.IView;
import cs3500.music.view2.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException,
          InvalidMidiDataException, InterruptedException {

    MusicReader ReaderOfText = new MusicReader();

    //args = new String[2];
    //args[0] = "mary-little-lamb.txt";
    //args[1] = "console";

    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile(args[0]);

    SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mary-little-lamb.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-1.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("ChromaticScale.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("BugTestSong.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-2.txt");
    //SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-3.txt");

    /*

    //Create Adapter
    SoundUnitListToIPlayerModelAdapter PlayerModelAdapted = new SoundUnitListToIPlayerModelAdapter("SampleSong");

    PlayerModelAdapted.setPlayerModelFromSongList(inputSong);


    //CONSOLE VIEW
    TextView newTextView = new TextView(PlayerModelAdapted);
    //newTextView.outputView();

    //GUI VIEW
    //IGuiView newGuiView = new cs3500.music.view2.GuiViewFrame(PlayerModelAdapted);

    //MIDI VIEW
    //IMidiImpl newMidiView = new cs3500.music.view2.MidiViewImpl(PlayerModelAdapted);
    //newMidiView.outputView();



    //TODO Delete this after the update and put as a interface, also chang combined view to COmposite
    cs3500.music.view2.MidiViewImpl newMidiView2 = new cs3500.music.view2.MidiViewImpl(PlayerModelAdapted);

    //COMPOSITE VIEW
    //IGuiView newCompositeView = new CombinedView(newGuiView, newMidiView2);


    GuiViewFrame mockGuiView = new GuiViewFrame(inputSong);
    MidiViewImpl mockMidiView = new MidiViewImpl(inputSong);
    CompositeView newControllerCompositeAdapter = new ControllerCompositeAdapter(mockGuiView, mockMidiView);


    //Controller
    //MusicEditorController controller = new MusicEditorController(PlayerModelAdapted.getPlayerModelFromSongList(),
    //        newControllerCompositeAdapter);

    CompositeView newCompositeView = (CompositeView) ViewCreator.create(ViewCreator.ViewType.COMPOSITE, inputSong);
    MusicEditorController asd = new MusicEditorController(inputSong, newCompositeView);
    */

    /*
    if(Objects.equals(args[1], "composite")){
      CompositeView newCompositeView = (CompositeView)
              ViewCreator.create(ViewCreator.ViewType.COMPOSITE, inputSong);
      MusicEditorController asd = new MusicEditorController(inputSong, newCompositeView);
    }
    else if(Objects.equals(args[1], "gui")){
      GuiViewFrame newGuiView = new GuiViewFrame(inputSong);
      newGuiView.initialize();
    }
    else if(Objects.equals(args[1], "midi")){
      MidiViewImpl newMidiView = new MidiViewImpl(inputSong);
      newMidiView.playSong();
    }
    else if(Objects.equals(args[1], "console")){
      ConsoleViewImpl newConsoleView = new ConsoleViewImpl(inputSong);
      newConsoleView.render();
    }
    */

    CompositeView newCompositeView = (CompositeView)
            ViewCreator.create(ViewCreator.ViewType.COMPOSITE, inputSong);

     MusicEditorController asd = new MusicEditorController(inputSong, newCompositeView);

  }
}
