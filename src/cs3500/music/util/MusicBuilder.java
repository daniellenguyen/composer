package cs3500.music.util;


import cs3500.music.model.Note;
import cs3500.music.model.NoteList;

/**
 * Created by Justin Hynes-Bruell on 3/21/2016.
 */
public class MusicBuilder implements CompositionBuilder<NoteList> {

  private NoteList listOfNotes = new NoteList();


  public MusicBuilder(){

  }

  @Override
  public NoteList build() {
    return listOfNotes;
  }


  @Override
  public CompositionBuilder<NoteList> setTempo(int tempo) {
    listOfNotes.setTempo(tempo);
    return null;
  }


  @Override
  public CompositionBuilder<NoteList> addNote(int start, int end, int instrument, int pitch, int volume) {
    Note newNote = new Note(Note.Pitch.C, Note.Octave.ONE, start, end);
    newNote.setPitchAndOctaveFromMIDI(pitch);
    newNote.setVolume(volume);
    newNote.setInstrument(instrument);
    listOfNotes.add(newNote);
    return null;
  }
}
