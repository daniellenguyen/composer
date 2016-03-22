package cs3500.music.util;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;

/**
 * Created by Justin Hynes-Bruell on 3/21/2016.
 */
public class MusicBuilder implements CompositionBuilder<NoteList> {

  private NoteList listOfNotes = new NoteList();

  private int tempo;

  public MusicBuilder(){
    this.tempo = 0;
  }

  @Override
  public NoteList build() {
    return listOfNotes;
  }

  //TODO THIS SHOULD NOT RETURN NULL??? THE INTERFACE MAKES ME RETURN A BUILDER???
  @Override
  public CompositionBuilder<NoteList> setTempo(int tempo) {
    this.tempo = tempo;
    return null;
  }

  //TODO SAME PROBLEM AS ABOVE
  @Override
  public CompositionBuilder<NoteList> addNote(int start, int end, int instrument, int pitch, int volume) {
    listOfNotes.add(new Note(Note.Pitch.B, Note.Octave.TWO, start, end));
    return null;
  }
}
