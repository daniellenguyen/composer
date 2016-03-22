package cs3500.music.tests;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Set;


import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.util.MusicBuilder;
import cs3500.music.util.MusicReader;

import static org.junit.Assert.*;


/**
 * Created by Justin Hynes-Bruell on 3/22/2016.
 */
public class MusicReaderTest {


  /**
   * Things attempted before it worked:
   * This will be placed in the controller
   * CharBuffer FileName = CharBuffer.wrap("mary-little-lamb.txt");
   * Scanner fileIn = new Scanner(new File("mary-little-lamb.txt"));
   * Readable input = (Readable) new FileInputStream("mary-little-lamb.txt");
   */
  @Test
  public void MIDIReaderConstructorTest() {
    MusicReader ReaderOfText = new MusicReader();
    MusicBuilder Builder = new MusicBuilder();
    try {
      ReaderOfText.parseFile(new FileReader("mary-little-lamb.txt"), Builder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void MIDIReaderNoteCheckTest() {
    MusicReader ReaderOfText = new MusicReader();
    MusicBuilder Builder = new MusicBuilder();
    try {
      ReaderOfText.parseFile(new FileReader("mary-little-lamb.txt"), Builder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    NoteList MarysLamb = Builder.build();

    //At Beat 27, ONLY ONE NOTE IS PLAYING
    Set<Note> Notes = MarysLamb.getAllAtTime(27);

    Iterator<Note> i = Notes.iterator();

    while (i.hasNext()){
      Note n = (Note) i.next();
      assertEquals(n.getStart(), 26);
      assertEquals(n.getPitch(), Note.Pitch.G);
      assertEquals(n.getOctave(), Note.Octave.FOUR);
    }
  }
}
