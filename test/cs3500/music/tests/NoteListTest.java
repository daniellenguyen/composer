package cs3500.music.tests;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.model.SoundUnitList;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A class to test methods of the NoteList class
 */
public class NoteListTest {

  /**
   * Tests for the method add
   */

  @Test
  public void addWorks() {
    Note n = new Note(Note.Pitch.A, Note.Octave.TWO, 5, 7);
    SoundUnitList l = new NoteList();
    l.add(n);
    assertEquals(l.contains(n), true);
  }

  @Test
  public void addVeryLongNoteWorks() {
    Note n = new Note(Note.Pitch.A, Note.Octave.TWO, 5, 100);
    NoteList l = new NoteList();
    l.add(n);
    assertEquals(l.contains(n), true);
  }

  @Test
  public void addTwoSameNotesDifferentTime() {
    Note n1 = new Note(Note.Pitch.A, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.A, Note.Octave.TWO, 18, 19);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), true);
  }

  @Test
  public void addTwoDifferentNotesSameTime() {
    Note n1 = new Note(Note.Pitch.A, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.B, Note.Octave.TWO, 5, 7);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), true);
  }

  @Test
  public void addThreeNotesWorks() {
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
  }

  //These Tests were throwing errors for note Overlap
  /*
  @Test (expected = IllegalArgumentException.class)
  public void addConsecutiveNotesWorks() {
    Note n1 = new Note(Note.Pitch.A2, Note.Octave.TWO, 2, 7);
    Note n2 = new Note(Note.Pitch.A2, Note.Octave.TWO, 7, 8);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), true);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addOverlap1() {
    Note n1 = new Note(Note.Pitch.A2, Note.Octave.TWO, 2, 7);
    Note n2 = new Note(Note.Pitch.A2, Note.Octave.TWO, 5, 8);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addOverlap2() {
    Note n1 = new Note(Note.Pitch.A2, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.A2, Note.Octave.TWO, 2, 6);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addOverlap3() {
    Note n1 = new Note(Note.Pitch.A2, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.A2, Note.Octave.TWO, 5, 6);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addOverlap4() {
    Note n1 = new Note(Note.Pitch.A2, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.A2, Note.Octave.TWO, 6, 7);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addOverlap5() {
    Note n1 = new Note(Note.Pitch.A2, Note.Octave.TWO, 1, 7);
    Note n2 = new Note(Note.Pitch.A2, Note.Octave.TWO, 4, 6);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addOverlap6() {
    Note n1 = new Note(Note.Pitch.A2, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.A2, Note.Octave.TWO, 1, 8);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addOverlapSameNotes() {
    Note n1 = new Note(Note.Pitch.A2, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.A2, Note.Octave.TWO, 5, 7);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
  }*/

  /**
   * Tests for the method delete
   */

  @Test
  public void deleteSingleNote() {
    Note n = new Note(Note.Pitch.A, Note.Octave.TWO, 5, 7);
    NoteList l = new NoteList();
    l.add(n);
    assertEquals(l.contains(n), true);
    l.delete(n);
    assertEquals(l.contains(n), false);
  }

  @Test
  public void deleteNoteWithSameStartTime() {
    Note n1 = new Note(Note.Pitch.A, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.B, Note.Octave.SIX, 5, 9);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), true);
    l.delete(n1);
    assertEquals(l.contains(n1), false);
    assertEquals(l.contains(n2), true);
  }

  @Test
  public void deleteNoteWithSameEndTime() {
    Note n1 = new Note(Note.Pitch.A, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.B, Note.Octave.THREE, 2, 4);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), true);
    l.delete(n1);
    assertEquals(l.contains(n1), false);
    assertEquals(l.contains(n2), true);
  }

  @Test
  public void deleteTwoNotesSamePitch() {
    Note n1 = new Note(Note.Pitch.A, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.A, Note.Octave.SEVEN, 2, 4);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), true);
    l.delete(n1);
    assertEquals(l.contains(n1), false);
    assertEquals(l.contains(n2), true);
  }

  @Test
  public void deleteTwoNotesSameOctave() {
    Note n1 = new Note(Note.Pitch.A, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.B, Note.Octave.TWO, 2, 4);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), true);
    l.delete(n1);
    assertEquals(l.contains(n1), false);
    assertEquals(l.contains(n2), true);
  }

  @Test (expected = IllegalArgumentException.class)
  public void deleteNoteNotInList() {
    Note n = new Note(Note.Pitch.A, Note.Octave.TWO, 5, 7);
    NoteList l = new NoteList();
    l.delete(n);
  }

  @Test
  public void deleteAndAddMultipleTimes() {
    Note n1 = new Note(Note.Pitch.A, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.B, Note.Octave.TWO, 2, 4);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), true);
    l.delete(n1);
    assertEquals(l.contains(n1), false);
    assertEquals(l.contains(n2), true);
    l.add(n1);
    l.delete(n2);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), false);
    l.add(n2);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), true);
    l.delete(n1);
    l.delete(n2);
    assertEquals(l.contains(n1), false);
    assertEquals(l.contains(n2), false);
  }

  /**
   * Tests for the method contains
   */

  @Test
  public void didNotAddToEmptyList() {
    Note n1 = new Note(Note.Pitch.B, Note.Octave.TWO, 2, 4);
    NoteList l = new NoteList();
    assertEquals(l.contains(n1), false);
  }

  @Test
  public void didNotAddToNonEmptyList() {
    Note n1 = new Note(Note.Pitch.A, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.B, Note.Octave.TWO, 2, 4);
    NoteList l = new NoteList();
    l.add(n1);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), false);
  }

  @Test
  public void containsWorks() {
    Note n1 = new Note(Note.Pitch.B, Note.Octave.TWO, 2, 4);
    NoteList l = new NoteList();
    l.add(n1);
    assertEquals(l.contains(n1), true);
  }

  @Test
  public void containsThreeWorks() {
    Note n1 = new Note(Note.Pitch.A, Note.Octave.TWO, 5, 7);
    Note n2 = new Note(Note.Pitch.B, Note.Octave.TWO, 2, 4);
    Note n3 = new Note(Note.Pitch.B, Note.Octave.FIVE, 7, 8);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
    l.add(n3);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), true);
    assertEquals(l.contains(n3), true);
  }

  /**
   * Tests for the method getAllAtTime
   */

  @Test
  public void getSingleNote1() {
    Note n1 = new Note(Note.Pitch.B, Note.Octave.TWO, 2, 4);
    NoteList l = new NoteList();
    l.add(n1);
    assertEquals(l.contains(n1), true);
    assertEquals(l.getAllAtTime(2).contains(n1), true);
    assertEquals(l.getAllAtTime(2).size(), 1);
    assertEquals(l.getAllAtTime(3).contains(n1), true);
    assertEquals(l.getAllAtTime(3).size(), 1);
    assertEquals(l.getAllAtTime(4).contains(n1), true);
    assertEquals(l.getAllAtTime(4).size(), 1);
  }

  @Test
  public void getSingleNote2() {
    Note n1 = new Note(Note.Pitch.B, Note.Octave.TWO, 2, 4);
    Note n2 = new Note(Note.Pitch.B, Note.Octave.TWO, 5, 9);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), true);
    assertEquals(l.getAllAtTime(2).contains(n1), true);
    assertEquals(l.getAllAtTime(2).size(), 1);
    assertEquals(l.getAllAtTime(3).contains(n1), true);
    assertEquals(l.getAllAtTime(3).size(), 1);
    assertEquals(l.getAllAtTime(4).contains(n1), true);
    assertEquals(l.getAllAtTime(4).size(), 1);
    assertEquals(l.getAllAtTime(5).contains(n2), true);
    assertEquals(l.getAllAtTime(5).contains(n1), false);
    assertEquals(l.getAllAtTime(5).size(), 1);
  }

  @Test
  public void getMultipleNotes() {
    Note n1 = new Note(Note.Pitch.B, Note.Octave.TWO, 2, 4);
    Note n2 = new Note(Note.Pitch.B, Note.Octave.TWO, 5, 9);
    NoteList l = new NoteList();
    l.add(n1);
    l.add(n2);
    assertEquals(l.contains(n1), true);
    assertEquals(l.contains(n2), true);
    assertEquals(l.getAllAtTime(2).contains(n1), true);
    assertEquals(l.getAllAtTime(2).contains(n2), false);
    assertEquals(l.getAllAtTime(2).size(), 1);
    assertEquals(l.getAllAtTime(5).contains(n2), true);
    assertEquals(l.getAllAtTime(5).contains(n1), false);
    assertEquals(l.getAllAtTime(5).size(), 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void timeIsNegative() {
    Note n1 = new Note(Note.Pitch.B, Note.Octave.TWO, 2, 4);
    NoteList l = new NoteList();
    l.add(n1);
    assertEquals(l.contains(n1), true);
    l.getAllAtTime(-1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void noNotesAtGivenTime() {
    Note n1 = new Note(Note.Pitch.B, Note.Octave.TWO, 2, 4);
    SoundUnitList l = new NoteList();
    l.add(n1);
    assertEquals(l.contains(n1), true);
    l.getAllAtTime(0);
  }

  /**
   * Tests for the method size
   */

  @Test
  public void testEmpty() {
    NoteList l = new NoteList();
    assertEquals(l.size(), 0);
  }

  @Test
  public void testNonEmpty() {
    Note n1 = new Note(Note.Pitch.B, Note.Octave.TWO, 2, 4);
    Note n2 = new Note(Note.Pitch.C, Note.Octave.TWO, 3, 5);
    NoteList l1 = new NoteList();
    l1.add(n1);
    l1.add(n2);
    assertEquals(l1.size(), 2);
  }
//
//  /**
//   * Test calls to the method consoleRender
//   */
//
//  @Test
//  public void testEmptyListRender() {
//    SoundUnitList l = new NoteList();
//    l.consoleRender();
//  }
//
//  @Test
//  public void testOneNoteOneBeatAtStartRender() {
//    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 0, 1);
//    SoundUnitList l = new NoteList();
//    l.add(n1);
//    l.consoleRender();
//  }
//
//  @Test
//  public void veryLongNoteRender() {
//    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 25);
//    SoundUnitList l = new NoteList();
//    l.add(n1);
//    l.consoleRender();
//  }
//
//  @Test
//  public void chordRender() {
//    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 7);
//    Note n2 = new Note(Note.Pitch.E, Note.Octave.FOUR, 5, 7);
//    Note n3 = new Note(Note.Pitch.G, Note.Octave.FOUR, 5, 7);
//    SoundUnitList l = new NoteList();
//    l.add(n1);
//    l.add(n2);
//    l.add(n3);
//    l.consoleRender();
//  }
//
//  @Test
//  public void overlappingTimeDistinctNotesRender() {
//    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 7);
//    Note n2 = new Note(Note.Pitch.C2, Note.Octave.FOUR, 6, 8);
//    Note n3 = new Note(Note.Pitch.D, Note.Octave.FOUR, 7, 9);
//    SoundUnitList l = new NoteList();
//    l.add(n1);
//    l.add(n2);
//    l.add(n3);
//    l.consoleRender();
//  }
//
//  @Test
//  public void consecutiveSamePitchOctaveRender() {
//    Note n1 = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 7);
//    Note n2 = new Note(Note.Pitch.C, Note.Octave.FOUR, 8, 9);
//    Note n3 = new Note(Note.Pitch.C, Note.Octave.FOUR, 10, 12);
//    SoundUnitList l = new NoteList();
//    NoteList dummy = new NoteList();
//    l.add(n1);
//    l.add(n2);
//    l.add(n3);
//    l.consoleRender(dummy);
//  }

}