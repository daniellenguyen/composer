package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by daniellenguyen on 3/2/16.
 */
public class NoteTest {

  /**
   * Tests for the Note constructor
   */

  @Test
  public void noteConstructorWorks() {
    Note n = new Note(Note.Pitch.C, Note.Octave.FIVE, 5, 7);
    assertEquals(n.getPitch(), Note.Pitch.C);
    assertEquals(n.getOctave(), Note.Octave.FIVE);
    assertEquals(n.getStart(), 5);
    assertEquals(n.getEnd(), 7);
  }

  @Test (expected = IllegalArgumentException.class)
  public void startTimeLessThan0() {
    Note n = new Note(Note.Pitch.C, Note.Octave.FIVE, -1, 7);
  }

  @Test (expected = IllegalArgumentException.class)
  public void endTimeLessThan1() {
    Note n = new Note(Note.Pitch.C, Note.Octave.FIVE, 1, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void endTimeLessThanStartTime() {
    Note n = new Note(Note.Pitch.C, Note.Octave.FIVE, 7, 2);
  }

  /**
   * Tests for the equals method
   */

  @Test
  public void equalsWorksTrue() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 7);
    Note n2 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 7);
    assertEquals(n1.equals(n2), true);
    assertEquals(n1, n2);
    assertEquals(n1.hashCode(), n2.hashCode());
  }

  @Test
  public void equalsWorksFalse1() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    Note n2 = new Note(Note.Pitch.C, Note.Octave.SIX, 1, 3);
    assertEquals(n1.equals(n2), false);
    assertEquals(n1.hashCode() == n2.hashCode(), false);
  }

  @Test
  public void equalsWorksFalse2() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    Note n2 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 5);
    assertEquals(n1.equals(n2), false);
    assertEquals(n1.hashCode() == n2.hashCode(), false);
  }

  @Test
  public void equalsWorksFalse3() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    Note n2 = new Note(Note.Pitch.C, Note.Octave.FOUR, 2, 3);
    assertEquals(n1.equals(n2), false);
    assertEquals(n1.hashCode() == n2.hashCode(), false);
  }

  @Test
  public void equalsWorksFalse4() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    Note n2 = new Note(Note.Pitch.B, Note.Octave.SIX, 2, 3);
    assertEquals(n1.equals(n2), false);
    assertEquals(n1.hashCode() == n2.hashCode(), false);
  }

  @Test
  public void equalsWorksFalse5() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    Note n2 = new Note(Note.Pitch.B, Note.Octave.SEVEN, 2, 3);
    assertEquals(n1.equals(n2), false);
    assertEquals(n1.hashCode() == n2.hashCode(), false);
  }

  /**
   * The method comparing two notes places emphasis on pitch and octave
   * and assigns lesser comparison value to start and end time.
   * Start and end time do not matter until there are two notes of same
   * pitch and octave.
   */

  /**
   * Tests for the compareTo method
   */

  @Test
  public void compareToWorksGreaterThanOctave() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SEVEN, 2, 3);
    Note n2 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.compareTo(n2) > 0, true);
  }

  @Test
  public void compareToWorksLessThanOctave() {
    Note n1 = new Note(Note.Pitch.B, Note.Octave.ONE, 2, 3);
    Note n2 = new Note(Note.Pitch.B, Note.Octave.SEVEN, 2, 3);
    assertEquals(n1.compareTo(n2) < 0, true);
  }

  @Test
  public void compareToWorksGreaterThanPitch() {
    Note n1 = new Note(Note.Pitch.B, Note.Octave.SEVEN, 2, 3); // lesser
    Note n2 = new Note(Note.Pitch.C, Note.Octave.SEVEN, 2, 3); // greater
    assertEquals(n1.compareTo(n2) > 0, true);
  }

  @Test
  public void compareToWorksLessThanPitch() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SEVEN, 2, 3); // lesser
    Note n2 = new Note(Note.Pitch.B, Note.Octave.SEVEN, 2, 3); // greater
    assertEquals(n1.compareTo(n2) < 0, true);
  }

  @Test
  public void compareToWorksGreaterThanPitchOctave() {
    Note n1 = new Note(Note.Pitch.B, Note.Octave.SEVEN, 2, 3); // lesser
    Note n2 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3); // greater
    assertEquals(n1.compareTo(n2) > 0, true);
  }

  @Test
  public void compareToWorksLessThanPitchOctave() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3); // lesser
    Note n2 = new Note(Note.Pitch.B, Note.Octave.SEVEN, 2, 3); // greater
    assertEquals(n1.compareTo(n2) < 0, true);
  }

  @Test
  public void compareToWorksEquals() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    Note n2 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.compareTo(n2) == 0, true);
  }

  @Test
  public void compareToWorksEqualsTimeLessThan() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 1, 2);
    Note n2 = new Note(Note.Pitch.C, Note.Octave.SIX, 3, 4);
    assertEquals(n1.compareTo(n2) > 0, true);
  }

  @Test
  public void compareToWorksEqualsTimeGreaterThan() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 18, 19);
    Note n2 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.compareTo(n2) < 0, true);
  }

  /**
   * Tests for the toString method
   */
  @Test
  public void toStringWorks() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    Note n2 = new Note(Note.Pitch.C, Note.Octave.SIX, 1, 4);
    assertEquals(n1.toString(), "C6");
    assertEquals(n2.toString(), "C6");
    assertEquals(n1.toString(), n2.toString());
  }

  /**
   * Tests for the set/get Start Time
   */
  @Test
  public void changeStartTimeWorks() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.getStart(), 2);
    n1.setStart(1);
    assertEquals(n1.getStart(), 1);
  }

  @Test
  public void changeStartTimeToSameNumber() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.getStart(), 2);
    n1.setStart(2);
    assertEquals(n1.getStart(), 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void illegalStartTime() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.getStart(), 2);
    n1.setStart(4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void illegalStartTimeSameNumber() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.getStart(), 2);
    n1.setStart(3);
  }


  /**
   * Tests for the set/get end time
   */
  @Test
  public void changeEndTimeWorks() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.getEnd(), 3);
    n1.setEnd(5);
    assertEquals(n1.getEnd(), 5);
  }

  @Test
  public void changeEndTimeToSameNumber() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.getEnd(), 3);
    n1.setEnd(3);
    assertEquals(n1.getEnd(), 3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void illegalEndTime() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.getEnd(), 3);
    n1.setEnd(1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void illegalEndTimeSameNumber() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.getEnd(), 3);
    n1.setEnd(2);
  }

  /**
   * Tests for the set/get Pitch
   * Please note that it is impossible to give an invalid value without a compiler error
  */
  @Test
  public void setPitchWorks() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.getPitch(), Note.Pitch.C);
    n1.setPitch(Note.Pitch.D2);
    assertEquals(n1.getPitch(), Note.Pitch.D2);
  }

  @Test
  public void setPitchToSameNumber() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.getPitch(), Note.Pitch.C);
    n1.setPitch(Note.Pitch.C);
    assertEquals(n1.getPitch(), Note.Pitch.C);
  }

  /**
   * Tests for the set/get Octave
   */

  @Test
  public void setOctaveWorks() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.getOctave(), Note.Octave.SIX);
    n1.setOctave(Note.Octave.ELEVEN);
    assertEquals(n1.getOctave(),  Note.Octave.ELEVEN);
  }

  @Test
  public void setOctaveToSameNumber() {
    Note n1 = new Note(Note.Pitch.C, Note.Octave.SIX, 2, 3);
    assertEquals(n1.getOctave(), Note.Octave.SIX);
    n1.setOctave(Note.Octave.SIX);
    assertEquals(n1.getOctave(), Note.Octave.SIX);
  }

  @Test
  public void MIDInoteConstructorWorks() {
    Note n = new Note(Note.Pitch.C, Note.Octave.FIVE, 5, 7);
    assertEquals(n.getPitch(), Note.Pitch.C);
    assertEquals(n.getOctave(), Note.Octave.FIVE);
    assertEquals(n.getStart(), 5);
    assertEquals(n.getEnd(), 7);

    n.setPitchAndOctaveFromMIDI(60);
    assertEquals(n.getPitch(), Note.Pitch.C);
    assertEquals(n.getOctave(), Note.Octave.FOUR);
  }

  @Test
  public void SetMIDIPitchTest1() {
    Note n = new Note(Note.Pitch.C, Note.Octave.FIVE, 5, 7);
    n.setPitchAndOctaveFromMIDI(60);
    assertEquals(n.getPitch(), Note.Pitch.C);
    assertEquals(n.getOctave(), Note.Octave.FOUR);
  }

  @Test
  public void SetMIDIPitchTest2() {
    Note n = new Note(Note.Pitch.C, Note.Octave.FIVE, 5, 7);
    n.setPitchAndOctaveFromMIDI(72);
    assertEquals(n.getPitch(), Note.Pitch.C);
    assertEquals(n.getOctave(), Note.Octave.FIVE);
  }

  @Test
  public void SetMIDIPitchTest3() {
    Note n = new Note(Note.Pitch.C, Note.Octave.FIVE, 5, 7);
    n.setPitchAndOctaveFromMIDI(41);
    assertEquals(n.getPitch(), Note.Pitch.F);
    assertEquals(n.getOctave(), Note.Octave.TWO);
  }

  @Test
  public void UseNormalConstructorToSetMIDI() {
    Note n = new Note(Note.Pitch.C, Note.Octave.FOUR, 5, 7);
    assertEquals(60, n.getMIDIPitch());
    Note n2 = new Note(Note.Pitch.C2, Note.Octave.FOUR, 5, 7);
    assertEquals(61, n2.getMIDIPitch());
  }
}