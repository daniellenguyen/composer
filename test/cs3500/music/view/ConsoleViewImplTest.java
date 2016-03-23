package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by daniellenguyen on 3/22/16.
 */
public class ConsoleViewImplTest {


  /**
   * Test calls to the method consoleRender
   */

  @Test //(expected = IllegalArgumentException.class)
  public void testEmptyListRender() {
    NoteList l = new NoteList();
    ConsoleViewImpl c = new ConsoleViewImpl();
    c.consoleRender(l);
  }

  @Test
  public void testOneNoteOneBeatAtStartRender() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 0, 1);
    NoteList l = new NoteList();
    l.add(n1);
    l.consoleRender(l);
  }

  @Test
  public void veryLongNoteRender() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 25);
    NoteList l = new NoteList();
    ConsoleViewImpl c = new ConsoleViewImpl();
    l.add(n1);
    c.consoleRender(l);
  }

  @Test
  public void chordRender() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 7);
    Note n2 = new Note(Note.Pitch.E, Note.Octave.FOUR, 5, 7);
    Note n3 = new Note(Note.Pitch.G, Note.Octave.FOUR, 5, 7);
    NoteList l = new NoteList();
    ConsoleViewImpl c = new ConsoleViewImpl();
    l.add(n1);
    l.add(n2);
    l.add(n3);
    c.consoleRender(l);
  }

  @Test
  public void overlappingTimeDistinctNotesRender() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 7);
    Note n2 = new Note(Note.Pitch.C2, Note.Octave.FOUR, 6, 8);
    Note n3 = new Note(Note.Pitch.D, Note.Octave.FOUR, 7, 9);
    NoteList l = new NoteList();
    ConsoleViewImpl c = new ConsoleViewImpl();
    l.add(n1);
    l.add(n2);
    l.add(n3);
    c.consoleRender(l);
  }

  @Test
  public void consecutiveSamePitchOctaveRender() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 7);
    Note n2 = new Note(Note.Pitch.C, Note.Octave.FOUR, 8, 9);
    Note n3 = new Note(Note.Pitch.C, Note.Octave.FOUR, 10, 12);
    NoteList l = new NoteList();
    ConsoleViewImpl c = new ConsoleViewImpl();
    l.add(n1);
    l.add(n2);
    l.add(n3);
    c.consoleRender(l);
  }

  @Test
  public void crossOctaveRender() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 7);
    Note n2 = new Note(Note.Pitch.C, Note.Octave.FOUR, 8, 9);
    Note n3 = new Note(Note.Pitch.C, Note.Octave.FIVE, 10, 12);
    NoteList l = new NoteList();
    ConsoleViewImpl c = new ConsoleViewImpl();
    l.add(n1);
    l.add(n2);
    l.add(n3);
    c.consoleRender(l);
  }
}