package cs3500.music.tests;

import org.junit.Test;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.NoteList;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MockReceiver;
import cs3500.music.view.ViewCreator;

import static org.junit.Assert.assertEquals;

/**
 * Created by Justin Hynes-Bruell on 3/23/2016.
 */
public class MockRevieverTest {

  @Test
  public void test() throws InvalidMidiDataException {
    MusicReader ReaderOfText = new MusicReader();

    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mary-little-lamb.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-1.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-2.txt");
    NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-3.txt");

    MidiViewImpl midiView = (MidiViewImpl) ViewCreator.create(ViewCreator.ViewType.MIDI, inputSong);
    midiView.setMockReciever(new MockReceiver());
    //midiView.playSong(inputSong);

    //MockReceiver editedReceiver = (MockReceiver) midiView.getMockReciever();

    //assertEquals("", editedReceiver.GetMockBuffer());
  }
}
