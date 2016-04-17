package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the MockConsole
 */
public class MockConsoleTest {

  /**
   * Test calls to the method consoleRender
   */
//
//  @Test (expected = IllegalArgumentException.class)
//  public void testEmptyListRender() {
//    NoteList l = new NoteList();
//    MockConsole m = new MockConsole();
//    m.createSong(l);
//    assertEquals(m.getStringBuilderAsString(), "");
//  }
//
//  @Test
//  public void testOneNoteOneBeatAtStartRender() {
//    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 0, 1);
//    NoteList l = new NoteList();
//    l.add(n1);
//    MockConsole m = new MockConsole();
//    m.createSong(l);
//    assertEquals(m.getStringBuilderAsString(), "        C4   \n" +
//            "   0    X  \n" +
//            "\n");
//  }
//
//  @Test
//  public void veryLongNoteRender() {
//    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 25);
//    NoteList l = new NoteList();
//    l.add(n1);
//    MockConsole m = new MockConsole();
//    m.createSong(l);
//    assertEquals(m.getStringBuilderAsString(), "        C4   \n" +
//            "   0  \n" +
//            "   1  \n" +
//            "   2  \n" +
//            "   3  \n" +
//            "   4  \n" +
//            "   5    X  \n" +
//            "   6    |  \n" +
//            "   7    |  \n" +
//            "   8    |  \n" +
//            "   9    |  \n" +
//            "  10    |  \n" +
//            "  11    |  \n" +
//            "  12    |  \n" +
//            "  13    |  \n" +
//            "  14    |  \n" +
//            "  15    |  \n" +
//            "  16    |  \n" +
//            "  17    |  \n" +
//            "  18    |  \n" +
//            "  19    |  \n" +
//            "  20    |  \n" +
//            "  21    |  \n" +
//            "  22    |  \n" +
//            "  23    |  \n" +
//            "  24    |  \n" + "\n");
//  }
//
//  @Test
//  public void chordRender() {
//    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 7);
//    Note n2 = new Note(Note.Pitch.E, Note.Octave.FOUR, 5, 7);
//    Note n3 = new Note(Note.Pitch.G, Note.Octave.FOUR, 5, 7);
//    NoteList l = new NoteList();
//    l.add(n1);
//    l.add(n2);
//    l.add(n3);
//    MockConsole m = new MockConsole();
//    m.createSong(l);
//    assertEquals(m.getStringBuilderAsString(),
//            "        C4   C#4  D4   D#4  E4   F4   F#4  G4   \n" +
//            "   0  \n" +
//            "   1  \n" +
//            "   2  \n" +
//            "   3  \n" +
//            "   4  \n" +
//            "   5    X                   X              X  \n" +
//            "   6    |                   |              |  \n" + "\n");
//  }
//
//  @Test
//  public void overlappingTimeDistinctNotesRender() {
//    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 7);
//    Note n2 = new Note(Note.Pitch.C2, Note.Octave.FOUR, 6, 8);
//    Note n3 = new Note(Note.Pitch.D, Note.Octave.FOUR, 7, 9);
//    NoteList l = new NoteList();
//    l.add(n1);
//    l.add(n2);
//    l.add(n3);
//    MockConsole m = new MockConsole();
//    m.createSong(l);
//    assertEquals(m.getStringBuilderAsString(),
//            "        C4   C#4  D4   \n" +
//            "   0  \n" +
//            "   1  \n" +
//            "   2  \n" +
//            "   3  \n" +
//            "   4  \n" +
//            "   5    X            \n" +
//            "   6    |    X       \n" +
//            "   7         |    X  \n" +
//            "   8              |  \n" + "\n");
//  }
//
//  @Test
//  public void consecutiveSamePitchOctaveRender() {
//    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 7);
//    Note n2 = new Note(Note.Pitch.C, Note.Octave.FOUR, 8, 9);
//    Note n3 = new Note(Note.Pitch.C, Note.Octave.FOUR, 10, 12);
//    NoteList l = new NoteList();
//    l.add(n1);
//    l.add(n2);
//    l.add(n3);
//    MockConsole m = new MockConsole();
//    m.createSong(l);
//    assertEquals(m.getStringBuilderAsString(),
//            "        C4   \n" +
//            "   0  \n" +
//            "   1  \n" +
//            "   2  \n" +
//            "   3  \n" +
//            "   4  \n" +
//            "   5    X  \n" +
//            "   6    |  \n" +
//            "   7  \n" +
//            "   8    X  \n" +
//            "   9  \n" +
//            "  10    X  \n" +
//            "  11    |  \n" + "\n");
//  }
//
//  @Test
//  public void crossOctaveRender() {
//    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 7);
//    Note n2 = new Note(Note.Pitch.C, Note.Octave.FOUR, 8, 9);
//    Note n3 = new Note(Note.Pitch.C, Note.Octave.FIVE, 10, 12);
//    NoteList l = new NoteList();
//    l.add(n1);
//    l.add(n2);
//    l.add(n3);
//    MockConsole m = new MockConsole();
//    m.createSong(l);
//    assertEquals(m.getStringBuilderAsString(),
//            "        C4   C#4  D4   D#4  E4   F4   F#4  G4   G#4  A4   A#4  B4   C5   \n" +
//            "   0  \n" +
//            "   1  \n" +
//            "   2  \n" +
//            "   3  \n" +
//            "   4  \n" +
//            "   5    X                                                              \n" +
//            "   6    |                                                              \n" +
//            "   7  \n" +
//            "   8    X                                                              \n" +
//            "   9  \n" +
//            "  10                                                                X  \n" +
//            "  11                                                                |  \n" + "\n");
//  }
}