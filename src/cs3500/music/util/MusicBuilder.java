package cs3500.music.util;

import cs3500.music.model.MIDINote;
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

  //TODO THIS SHOULD NOT RETURN NULL??? THE INTERFACE MAKES ME RETURN A BUILDER???
  @Override
  public CompositionBuilder<NoteList> setTempo(int tempo) {
    listOfNotes.setTempo(tempo);
    return null;
  }

  //TODO SAME PROBLEM AS ABOVE???
  @Override
  public CompositionBuilder<NoteList> addNote(int start, int end, int instrument, int pitch, int volume) {
    MIDINote newNote = new MIDINote(Note.Pitch.C, Note.Octave.ONE, start, end);
    newNote.setPitchAndOctaveFromMIDI(pitch);
    newNote.setVolume(volume);
    newNote.setInstrument(instrument);
    listOfNotes.add(newNote);
    return null;
  }
}
