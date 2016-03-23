package cs3500.music.tests;

import org.junit.Test;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MockReceiver;
import cs3500.music.view.ViewCreator;

import static org.junit.Assert.assertEquals;

/**
 * Created by Justin Hynes-Bruell on 3/23/2016.
 */
public class MockReceiverTest {

  @Test
  public void testForFullSongs() throws InvalidMidiDataException {
    MusicReader ReaderOfText = new MusicReader();

    NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mary-little-lamb.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-1.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-2.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-3.txt");

    MidiViewImpl midiView = (MidiViewImpl) ViewCreator.create(ViewCreator.ViewType.MIDI, inputSong);
    midiView.setMockReciever(new MockReceiver());
    midiView.fillMockReceiver(inputSong);

    MockReceiver editedReceiver = (MockReceiver) midiView.getMockReciever();

    //144 = Note On
    //128 = Note Off
    //assertEquals("", editedReceiver.GetMockBuffer());
  }

  /**
   * ListOfNotesPlayed.append("Command, Channel, Data1, Data2");
   * @throws InvalidMidiDataException
   */
  @Test
  public void BasicFunctionalityTest() throws InvalidMidiDataException {
    MusicReader ReaderOfText = new MusicReader();

    NoteList inputSong = new NoteList();
    inputSong.add(new Note(Note.Pitch.C, Note.Octave.FOUR, 0, 1));

    MidiViewImpl midiView = (MidiViewImpl) ViewCreator.create(ViewCreator.ViewType.MIDI, inputSong);
    midiView.setMockReciever(new MockReceiver());
    midiView.fillMockReceiver(inputSong);

    MockReceiver editedReceiver = (MockReceiver) midiView.getMockReciever();

    //144 = Note On
    //128 = Note Off
    assertEquals("144 0 60 70\n", editedReceiver.GetMockBuffer());
  }
}
