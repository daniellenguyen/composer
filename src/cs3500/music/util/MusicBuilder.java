package cs3500.music.util;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;

public class MusicBuilder implements CompositionBuilder<SoundUnitList> {

  private SoundUnitList listOfNotes = new NoteList();

  public MusicBuilder() {}

  @Override
  public SoundUnitList build() {
    return listOfNotes;
  }


  @Override
  public CompositionBuilder<SoundUnitList> setTempo(int tempo) {
    listOfNotes.setTempo(tempo);
    return null;
  }


  /**
   * This is used by the reader to build a composition. First general Note is created, then
   * parameters make it more specific
   *
   * @param start      The start time of the note, in beats
   * @param end        The end time of the note, in beats
   * @param instrument The instrument number (to be interpreted by MIDI)
   * @param pitch      The pitch (in the range [0, 127], where 60 represents C4, the middle-C on a
   *                   piano)
   * @param volume     The volume (in the range [0, 127])
   */
  @Override
  public CompositionBuilder<SoundUnitList> addNote(int start, int end,
                                              int instrument, int pitch, int volume) {
    Note newNote = new Note(SoundUnit.Pitch.C, SoundUnit.Octave.ONE, start, end);
    newNote.setPitchAndOctaveFromMIDI(pitch);
    newNote.setVolume(volume);
    newNote.setInstrument(instrument);
    listOfNotes.add(newNote);
    return null;
  }
}
