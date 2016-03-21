package cs3500.music;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException, InterruptedException {
    GuiViewFrame view = new GuiViewFrame();
    view.initialize();
    MidiViewImpl midiView = new MidiViewImpl();

    midiView.playNote();

    /*
    NoteList l = new NoteList();
    l.add(new Note(Note.Pitch.C, Note.Octave.THREE, 0, 2));
    l.add(new Note(Note.Pitch.C, Note.Octave.FOUR, 1, 2));
    l.add(new Note(Note.Pitch.C, Note.Octave.FIVE, 2, 3));
    l.add(new Note(Note.Pitch.C, Note.Octave.SIX, 4, 5));
    midiView.playBeat(l, 0);
    midiView.playBeat(l, 1);
    midiView.playBeat(l, 2);
    midiView.playBeat(l, 3);
    midiView.playBeat(l, 4);
    */


    Thread.sleep(3000);
    // You probably need to connect these views to your model, too...
  }
}
