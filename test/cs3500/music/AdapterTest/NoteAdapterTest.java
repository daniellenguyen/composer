package cs3500.music.AdapterTest;


import org.junit.Test;

import cs3500.music.model.Note;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitToINoteAdapter;
import cs3500.music.model2.INote;
import cs3500.music.model2.Pitch;

import static org.junit.Assert.*;

/**
 * Created by Justin Hynes-Bruell on 4/17/2016.
 */
public class NoteAdapterTest {

  @Test
  public void TestAdapter(){
    SoundUnit newSoundUnit = new Note(SoundUnit.Pitch.C2, SoundUnit.Octave.FOUR, 0, 5);

    SoundUnitToINoteAdapter newINote = new SoundUnitToINoteAdapter(5, Pitch.DS, 3, 1, 1, 70);

    INote adaptedINote = newINote.ConvertSoundUnitToINote(newSoundUnit);

    assertEquals(adaptedINote.getOctave(), 4);
    assertEquals(adaptedINote.getPitch(), Pitch.CS);
    //assertEquals(adaptedINote.getOctave(), 4);
    //assertEquals(adaptedINote.getOctave(), 4);
  }


}
