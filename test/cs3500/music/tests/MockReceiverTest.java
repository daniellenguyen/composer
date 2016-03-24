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
 * ListOfNotesPlayed.append("Command, Channel, Data1, Data2"); So Data come in in that order for
 * each message.
 * 144 = Note On
 * 128 = Note Off
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

/*
  @Test
  public void OneNoteMockTest() throws InvalidMidiDataException {
    MusicReader ReaderOfText = new MusicReader();

    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mary-little-lamb.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-1.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("ChromaticScale.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("BugTestSong.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-2.txt");
    //NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-3.txt");

    NoteList inputSong = new NoteList();
    inputSong.add(new Note(Note.Pitch.C, Note.Octave.FOUR, 0, 1));
    inputSong.add(new Note(Note.Pitch.C, Note.Octave.FOUR, 0, 1));
    inputSong.add(new Note(Note.Pitch.C2, Note.Octave.FOUR, 0, 1));


    MidiViewImpl midiView = (MidiViewImpl) ViewCreator.create(ViewCreator.ViewType.MIDI, inputSong);
    midiView.playSong(inputSong);
  }*/


  @Test
  public void OneNoteMockTest() throws InvalidMidiDataException {
    NoteList inputSong = new NoteList();
    Note newNote = new Note(Note.Pitch.C, Note.Octave.ONE, 1, 3);
    newNote.setPitchAndOctaveFromMIDI(60);
    newNote.setVolume(99);
    newNote.setInstrument(1);
    inputSong.add(newNote);

    MidiViewImpl midiView = (MidiViewImpl) ViewCreator.create(ViewCreator.ViewType.MIDI, inputSong);
    midiView.setMockReciever(new MockReceiver());
    midiView.fillMockReceiver(inputSong);

    MockReceiver editedReceiver = (MockReceiver) midiView.getMockReciever();

    assertEquals("144 0 60 99\n", editedReceiver.GetMockBuffer());
  }


  @Test
  public void TwoNoteMockTest() throws InvalidMidiDataException {
    NoteList inputSong = new NoteList();
    Note newNote = new Note(Note.Pitch.C, Note.Octave.ONE, 1, 3);
    newNote.setPitchAndOctaveFromMIDI(60);
    newNote.setVolume(99);
    newNote.setInstrument(1);
    inputSong.add(newNote);

    Note newNote2 = new Note(Note.Pitch.C, Note.Octave.ONE, 1, 3);
    newNote.setPitchAndOctaveFromMIDI(65);
    newNote.setVolume(85);
    newNote.setInstrument(2);
    inputSong.add(newNote2);


    MidiViewImpl midiView = (MidiViewImpl) ViewCreator.create(ViewCreator.ViewType.MIDI, inputSong);
    midiView.setMockReciever(new MockReceiver());
    midiView.fillMockReceiver(inputSong);

    MockReceiver editedReceiver = (MockReceiver) midiView.getMockReciever();

    //144 = Note On
    //128 = Note Off
    assertEquals("144 1 65 85\n" +
            "144 0 24 70\n", editedReceiver.GetMockBuffer());
  }

  @Test
  public void ThreeNoteMockTest() throws InvalidMidiDataException {
    NoteList inputSong = new NoteList();
    Note newNote = new Note(Note.Pitch.C, Note.Octave.ONE, 1, 2);
    newNote.setPitchAndOctaveFromMIDI(60);
    newNote.setVolume(99);
    newNote.setInstrument(1);
    inputSong.add(newNote);

    Note newNote2 = new Note(Note.Pitch.C, Note.Octave.ONE, 1, 5);
    newNote.setPitchAndOctaveFromMIDI(65);
    newNote.setVolume(85);
    newNote.setInstrument(2);
    inputSong.add(newNote2);

    Note newNote3 = new Note(Note.Pitch.C, Note.Octave.ONE, 1, 4);
    newNote.setPitchAndOctaveFromMIDI(70);
    newNote.setVolume(50);
    newNote.setInstrument(1);
    inputSong.add(newNote3);


    MidiViewImpl midiView = (MidiViewImpl) ViewCreator.create(ViewCreator.ViewType.MIDI, inputSong);
    midiView.setMockReciever(new MockReceiver());
    midiView.fillMockReceiver(inputSong);

    MockReceiver editedReceiver = (MockReceiver) midiView.getMockReciever();

    //144 = Note On
    //128 = Note Off
    assertEquals("144 0 70 50\n" +
                    "144 0 24 70\n" +
                    "144 0 24 70\n",
            editedReceiver.GetMockBuffer());
  }

  @Test
  public void ChromaticScale() throws InvalidMidiDataException {
    MusicReader ReaderOfText = new MusicReader();

    NoteList inputSong = ReaderOfText.ReturnNoteListFromFile("ChromaticScale.txt");

    MidiViewImpl midiView = (MidiViewImpl) ViewCreator.create(ViewCreator.ViewType.MIDI, inputSong);
    midiView.setMockReciever(new MockReceiver());
    midiView.fillMockReceiver(inputSong);

    MockReceiver editedReceiver = (MockReceiver) midiView.getMockReciever();

    //144 = Note On
    //128 = Note Off
    assertEquals("144 0 51 72\n" +
                    "144 0 52 72\n" +
                    "144 0 53 72\n" +
                    "144 0 54 72\n" +
                    "144 0 55 72\n" +
                    "144 0 56 72\n" +
                    "144 0 57 72\n" +
                    "144 0 58 72\n" +
                    "144 0 59 72\n" +
                    "144 0 60 72\n" +
                    "144 0 61 72\n" +
                    "144 0 62 72\n" +
                    "144 0 63 72\n" +
                    "144 0 64 72\n" +
                    "144 0 65 72\n" +
                    "144 0 66 72\n" +
                    "144 0 67 72\n" +
                    "144 0 68 72\n" +
                    "144 0 69 72\n" +
                    "144 0 70 72\n" +
                    "144 0 71 72\n" +
                    "144 0 72 72\n" +
                    "144 0 73 72\n" +
                    "144 0 74 72\n" +
                    "144 0 75 72\n" +
                    "144 0 76 72\n" +
                    "144 0 77 72\n" +
                    "144 0 78 72\n" +
                    "144 0 79 72\n",
            editedReceiver.GetMockBuffer());
  }
}
