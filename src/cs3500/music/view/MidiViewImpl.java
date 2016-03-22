package cs3500.music.view;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sound.midi.*;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.util.MusicBuilder;
import cs3500.music.util.MusicReader;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements View {
  private Synthesizer synth;
  private Receiver receiver;
  Instrument[] instr;

  public MidiViewImpl() {
    try {
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * Relevant classes and methods from the javax.sound.midi library: <ul> <li>{@link
   * MidiSystem#getSynthesizer()}</li> <li>{@link Synthesizer} <ul> <li>{@link
   * Synthesizer#open()}</li> <li>{@link Synthesizer#getReceiver()}</li> <li>{@link
   * Synthesizer#getChannels()}</li> </ul> </li> <li>{@link Receiver} <ul> <li>{@link
   * Receiver#send(MidiMessage, long)}</li> <li>{@link Receiver#close()}</li> </ul> </li> <li>{@link
   * MidiMessage}</li> <li>{@link ShortMessage}</li> <li>{@link MidiChannel} <ul> <li>{@link
   * MidiChannel#getProgram()}</li> <li>{@link MidiChannel#programChange(int)}</li> </ul> </li>
   * </ul>
   *
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI"> https://en.wikipedia.org/wiki/General_MIDI
   * </a>
   */

  public void playNote() throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 64);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, 60, 64);
    this.receiver.send(start, -1);
    this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);
    this.receiver.close(); // Only call this once you're done playing *all* notes
  }

  public void playBeat(NoteList noteList, int BeatNumber) throws InvalidMidiDataException {

    Set<Note> Notes = noteList.getAllAtTime(BeatNumber);

    Iterator<Note> i = Notes.iterator();

    List<Note> alreadyPlayed = new ArrayList<>();

    while (i.hasNext()) {
      //Get the Note from the set
      Note n = (Note) i.next();

      //this.synth.loadInstrument(this.synth.getAvailableInstruments()[n.getInstrument()]);
      //this.synth.loadInstrument(instr[n.getInstrument()]);

      //MidiEvent instrumentChange = new MidiEvent(ShortMessage.PROGRAM_CHANGE,drumPatch.getBank(),drumPatch.getProgram());


      //Determine if note has already been played at this beat
      boolean dontStopNote = false;
      for (int j = 0; j < alreadyPlayed.size(); j++) {
        if (alreadyPlayed.get(j).getMIDIPitch() == n.getMIDIPitch()) {
          dontStopNote = true;
        }
      }
      alreadyPlayed.add(n);


      //Find notes to Start
      if (n.getStart() == BeatNumber) {
        ShortMessage myMsg = new ShortMessage();
        myMsg.setMessage(ShortMessage.NOTE_ON, 0, n.getMIDIPitch(), n.getVolume());
        Receiver rcvr = null;
        try {
          rcvr = MidiSystem.getReceiver();
        } catch (MidiUnavailableException e) {
          e.printStackTrace();
        }
        rcvr.send(myMsg, -1);

        //Change Instrument To Suit Note
        ShortMessage instrumentChange = new ShortMessage();
        instrumentChange.setMessage(ShortMessage.PROGRAM_CHANGE, 0, n.getInstrument(),0);
        rcvr.send(instrumentChange, -1);

      }
      //Find Notes to End
      else if (n.getEnd() == BeatNumber && !dontStopNote) {

        ShortMessage myMsg = new ShortMessage();
        myMsg.setMessage(ShortMessage.NOTE_OFF, 0, n.getMIDIPitch(), n.getVolume());
        Receiver rcvr = null;
        try {
          rcvr = MidiSystem.getReceiver();
        } catch (MidiUnavailableException e) {
          e.printStackTrace();
        }
        rcvr.send(myMsg, -1);
      }
    }
  }
  //this.receiver.close(); // Only call this once you're done playing *all* notes

  public void playSong(String songName) throws InvalidMidiDataException {

    MidiViewImpl midiView = new MidiViewImpl();
    //midiView.playNote();

    MusicReader ReaderOfText = new MusicReader();

    MusicBuilder Builder = new MusicBuilder();
    try {
      //ReaderOfText.parseFile(new FileReader("mary-little-lamb.txt"), Builder);
      ReaderOfText.parseFile(new FileReader(songName), Builder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    NoteList MarysLamb = Builder.build();

    for (int i = 0; i < MarysLamb.songLength(); i++) {

      try {
        midiView.playBeat(MarysLamb, i);
      } catch (IllegalArgumentException e) {
        continue;
      }

      try {
        Thread.sleep(MarysLamb.getTempo()/1000);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
    }
  }

}
