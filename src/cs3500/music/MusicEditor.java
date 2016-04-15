package cs3500.music;

import cs3500.music.controller.MusicEditorController;
import cs3500.music.model.SoundUnitList;
import cs3500.music.model.SoundUnitListToIPlayerModelAdapter;
import cs3500.music.model2.INote;
import cs3500.music.model2.Note;
import cs3500.music.util.MusicReader;
import cs3500.music.view.CompositeView;
import cs3500.music.view.ConsoleViewImpl;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.ViewCreator;
import cs3500.music.view2.IGuiView;

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

    SoundUnitListToIPlayerModelAdapter convertedInputSong = new SoundUnitListToIPlayerModelAdapter("SampleSong");

    List<cs3500.music.model2.INote> ListOfINote = new ArrayList<>();
    ListOfINote = convertedInputSong.SoundUnitListConverter2(inputSong);
    for(int i = 0; i < ListOfINote.size(); i++){
      System.out.println(ListOfINote.get(i).getNoteAsString() + " " + ListOfINote.get(i).getStart() + " " + ListOfINote.get(i).getEnd());
      convertedInputSong.addNoteInterface(ListOfINote.get(i));
    }

    System.out.println(ListOfINote.size() + "\n");

    System.out.println(convertedInputSong.outputModel());

    //IGuiView newGuiView = new cs3500.music.view2.GuiViewFrame(convertedInputSong);

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
    /*
    CompositeView newCompositeView = (CompositeView)
            ViewCreator.create(ViewCreator.ViewType.COMPOSITE, inputSong);

     MusicEditorController asd = new MusicEditorController(inputSong, newCompositeView);
     */

  }
}
