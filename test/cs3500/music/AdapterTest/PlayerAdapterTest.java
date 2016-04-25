package cs3500.music.AdapterTest;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.model.SoundUnitList;
import cs3500.music.model.SoundUnitListToIPlayerModelAdapter;
import cs3500.music.model2.INote;
import cs3500.music.util.MusicReader;

import static org.junit.Assert.*;

/**
 * Created by Justin Hynes-Bruell on 4/17/2016.
 */
public class PlayerAdapterTest {

  @Test
  public void PlayerAdapterTestModelSize(){
    //Get Song Using my Util
    MusicReader ReaderOfText = new MusicReader();
    SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mary-little-lamb.txt");

    //Create Adapter
    SoundUnitListToIPlayerModelAdapter PlayerModelAdapted =
            new SoundUnitListToIPlayerModelAdapter("SampleSong");

    //Add Notes from Sound Unit to a List of INotes
    List<INote> inputListOfINote = new ArrayList<>();
    inputListOfINote = PlayerModelAdapted.SoundUnitListConverter(inputSong);

    //Add the List of INotes to AdaptedPlayerModel
    for(int i = 0; i < inputListOfINote.size(); i++){
      PlayerModelAdapted.addNote(inputListOfINote.get(i));
    }

    List<cs3500.music.model2.INote> outputListOfINote = PlayerModelAdapted.outputModelAsList();

    assertEquals(inputSong.size(), inputListOfINote.size());
    assertEquals(inputSong.size(), outputListOfINote.size());
  }

  @Test
  public void PlayerAdapterTestRange(){
    //Get Song Using my Util
    MusicReader ReaderOfText = new MusicReader();
    SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mary-little-lamb.txt");

    //Create Adapter
    SoundUnitListToIPlayerModelAdapter PlayerModelAdapted =
            new SoundUnitListToIPlayerModelAdapter("SampleSong");

    PlayerModelAdapted.setPlayerModelFromSongList(inputSong);

    int counter = 0;
    List<String> RangeOfSong = PlayerModelAdapted.outputPitchesOctaves();
    for(int i = 0; i < RangeOfSong.size(); i++){
      counter++;
    }

    assertEquals(16, counter);
  }
}
