package cs3500.music.view;

import java.util.Iterator;
import java.util.Set;

import javax.sound.midi.*;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl /*implements YourViewInterfaceHere*/ {
  private Synthesizer synth;
  private Receiver receiver;

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

  //TODO This needs a ton of work
  public void playBeat(NoteList noteList, int BeatNumber) throws InvalidMidiDataException {

    Set<Note> Notes = noteList.getAllAtTime(BeatNumber);

    Iterator<Note> i = Notes.iterator();

    while (i.hasNext()) {
      //Get the Note from the set
      Note n = (Note) i.next();

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
      } else if (n.getEnd() == BeatNumber) {

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

  public void playSong(NoteList noteList) throws InvalidMidiDataException {

  }

}
