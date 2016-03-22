package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.MIDINote;
import cs3500.music.model.Note;

import static org.junit.Assert.*;

/**
 * Created by Justin Hynes-Bruell on 3/21/2016.
 */
public class MIDINoteTest {

  @Test
  public void MIDInoteConstructorWorks() {
    MIDINote n = new MIDINote(Note.Pitch.C, Note.Octave.FIVE, 5, 7);
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
    MIDINote n = new MIDINote(Note.Pitch.C, Note.Octave.FIVE, 5, 7);
    n.setPitchAndOctaveFromMIDI(60);
    assertEquals(n.getPitch(), Note.Pitch.C);
    assertEquals(n.getOctave(), Note.Octave.FOUR);
  }

  @Test
  public void SetMIDIPitchTest2() {
    MIDINote n = new MIDINote(Note.Pitch.C, Note.Octave.FIVE, 5, 7);
    n.setPitchAndOctaveFromMIDI(72);
    assertEquals(n.getPitch(), Note.Pitch.C);
    assertEquals(n.getOctave(), Note.Octave.FIVE);
  }

  @Test
  public void SetMIDIPitchTest3() {
    MIDINote n = new MIDINote(Note.Pitch.C, Note.Octave.FIVE, 5, 7);
    n.setPitchAndOctaveFromMIDI(41);
    assertEquals(n.getPitch(), Note.Pitch.F);
    assertEquals(n.getOctave(), Note.Octave.TWO);
  }

}
