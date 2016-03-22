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
   * Tests for the method makeTopRow
   */

  @Test
  public void makeTopRowWorks() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.A2, Note.Octave.TWO, 5, 7);
    Note n3 = new Note(Note.Pitch.B, Note.Octave.TWO, 5, 7);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
    l.add(n3);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), true);
    assertEquals(l.contains(n3), true);
    ConsoleViewImpl c = new ConsoleViewImpl();
    assertEquals(c.makeTopRow(l).get(1), n2);
    
  }

  /**
   * Tests for the method renderTopRow
   */
}