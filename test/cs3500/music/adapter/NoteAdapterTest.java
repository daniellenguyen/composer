package cs3500.music.adapter;

import org.junit.Test;
import cs3500.music.model.SoundUnitToINoteAdapter;
import cs3500.music.model.SoundUnit;
import cs3500.music.model2.INote;
import cs3500.music.model2.Pitch;
import static org.junit.Assert.*;

/**
 * Tests the SoundUnit to INote adapter
 */
public class NoteAdapterTest {

  /**
   * Tests the method getInstrument
   */

  @Test
  public void getInstrumentTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.getInstrument(), 1);
  }

  /**
   * Tests the method getPitch
   */

  @Test
  public void getPitchTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.getPitch(), Pitch.C);
  }

  /**
   * Tests the method getOctave
   */

  @Test
  public void getOctaveTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.getOctave(), 4);
  }

  /**
   * Tests the method getDuration
   */

  @Test
  public void getDurationTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.getDuration(), 4);
  }

  /**
   * Tests the method getAudible
   */

  @Test
  public void getAudibleTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.getAudible(), true);
  }

  /**
   * Tests the method getStart
   */

  @Test
  public void getStartTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.getStart(), 5);
  }

  /**
   * Tests the method getEnd
   */

  @Test
  public void getEndTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.getEnd(), 9);
  }

  /**
   * Tests the method getVolume
   */

  @Test
  public void getVolumeTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.getVolume(), 70);
  }

  /**
   * Tests the method getNoteAsString
   */

  @Test
  public void getNoteAsStringTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.getNoteAsString(), "C4");
  }

  /**
   * Tests the method setInstrument
   */

  @Test
  public void setInstrumentTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.getInstrument(), 1);
    n.setInstrument(2);
    assertEquals(n.getInstrument(), 2);
  }

  /**
   * Tests the method setVolume
   */

  @Test
  public void setVolumeTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.getVolume(), 70);
    n.setVolume(71);
    assertEquals(n.getVolume(), 71);
  }

  /**
   * Tests the method setOctave
   */

  @Test
  public void setOctaveTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.getOctave(), 4);
    n.setOctave(3);
    assertEquals(n.getOctave(), 3);
    assertEquals(n.getAudible(), true);
  }

  /**
   * Tests the method setStart
   */

  @Test
  public void setStartTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.getStart(), 5);
    n.setStart(8);
    assertEquals(n.getStart(), 8);
  }

  /**
   * Tests the method setPitch
   */

  @Test
  public void setPitchTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.getPitch(), Pitch.C);
    n.setPitch(Pitch.A);
    assertEquals(n.getPitch(), Pitch.A);
  }

  /**
   * Tests the method convertToMidiNumber
   */

  @Test
  public void convertToMidiNumberTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    assertEquals(n.convertToMidiNumber(), 60);
  }

  /**
   * Tests the method pitchOctaveComparator
   */

  @Test
  public void pitchOctaveComparatorTest() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    INote n2 = new SoundUnitToINoteAdapter(4, Pitch.B, 4, 5, 1, 70);
    assertEquals(n.pitchOctaveComparator(n2), -1);
  }

  /**
   * Tests the method setDuration
   */

  @Test(expected = IllegalArgumentException.class)
  public void setDurationTestException() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    n.setDuration(-1);
  }

  @Test
  public void setDurationTestSame() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    n.setDuration(4);
    assertEquals(n.getDuration(), 4);
  }

  @Test
  public void setDurationTestDiff() {
    INote n = new SoundUnitToINoteAdapter(4, Pitch.C, 4, 5, 1, 70);
    n.setDuration(5);
    assertEquals(n.getDuration(), 5);
  }

  /**
   * Tests the method ConvertINoteToSoundUnit
   */

  @Test
  public void ConvertINoteToSoundUnitTest() {
    // Declared type as implementing class
    // in order to test methods not in interface
    SoundUnitToINoteAdapter n = new SoundUnitToINoteAdapter(4,
            Pitch.C, 4, 5, 1, 70);
    assertEquals(n.ConvertINoteToSoundUnit().getOctave(), SoundUnit.Octave.FOUR);
    assertEquals(n.ConvertINoteToSoundUnit().getPitch(), SoundUnit.Pitch.C);
    assertEquals(n.ConvertINoteToSoundUnit().getStart(), 5);
    assertEquals(n.ConvertINoteToSoundUnit().getEnd(), 9);
    assertEquals(n.ConvertINoteToSoundUnit().getVolume(), 70);
  }
}