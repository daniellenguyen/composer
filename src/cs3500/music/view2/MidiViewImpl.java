<<<<<<< HEAD
package cs3500.music.view;
=======
package cs3500.music.view2;
>>>>>>> d5b4df49ca8d85a5dc57316cd5138ae8e63c6dd4

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sound.midi.*;

<<<<<<< HEAD
import cs3500.music.model.INote;
import cs3500.music.model.IPlayerModel;
import cs3500.music.model.Note;
import cs3500.music.model.NoteComparatorStartTime;
=======
import cs3500.music.model2.INote;
import cs3500.music.model2.IPlayerModel;
>>>>>>> d5b4df49ca8d85a5dc57316cd5138ae8e63c6dd4

/**
 * A skeleton for MIDI playback
 */

public class MidiViewImpl implements IMidiImpl {
  private MidiDevice synth; // creates the sounds
  private Receiver receiver; // gets the sounds
  private IPlayerModel inputModel;
  //private Map<Integer, List<INote>> theMap;// = inputModel.outputModelAsMap();


  /**
   * Constructor for Midi which takes in the model
   */

  public MidiViewImpl(IPlayerModel inputModeli) {
    try {
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.inputModel = inputModeli;
    //theMap = inputModel.outputModelAsMap();
  }

  /**
   * Constructor for Midi which takes in the model and the synthesizor (MidiDevice)
   */

  public MidiViewImpl(IPlayerModel inputModeli, MidiDevice synthi) {
    this.synth = synthi;
    try {
      this.receiver = synth.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    inputModel = inputModeli;
    //theMap = inputModel.outputModelAsMap();
  }

  /**
   * Method which returns the Receiver associated with this synthesizor
   *
   * @return reciever -> the receiver associated with the given synthesizor
   */
  public Receiver getReceiver() {
    return this.receiver;
  }

  //synthesizers (which generate sounds when triggered by MIDI messages)
  // input output ports which data comes to an out to MIDI devices

  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   *  <li>{@link MidiSystem#getSynthesizer()}</li>
   *  <li>{@link Synthesizer}
   *    <ul>
   *      <li>{@link Synthesizer#open()}</li>
   *      <li>{@link Synthesizer#getReceiver()}</li>
   *      <li>{@link Synthesizer#getChannels()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link Receiver}
   *    <ul>
   *      <li>{@link Receiver#send(MidiMessage, long)}</li>
   *      <li>{@link Receiver#close()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link MidiMessage}</li>
   *  <li>{@link ShortMessage}</li>
   *  <li>{@link MidiChannel}
   *    <ul>
   *      <li>{@link MidiChannel#getProgram()}</li>
   *      <li>{@link MidiChannel#programChange(int)}</li>
   *    </ul>
   *  </li>
   * </ul>
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   *   </a>
   */


  /**
   * A Method which plays a single beat in time
   */

  public void playNote() throws IllegalArgumentException {

    if (this.inputModel.getCurrentBeat() > inputModel.finalBeat()) {
      throw new IllegalArgumentException("you cannot play after the last beat. This should" +
              "not happen - outputView (midi) should prevent this");
    }


    List<INote> inputNotesAsList = inputModel.outputModelAsMap().get(inputModel.getCurrentBeat());
    for (INote notex : inputNotesAsList) {
      if (notex.getOctave() > 0 && notex.getOctave() <= 10) {
        if (notex.getStart() == inputModel.getCurrentBeat()) {
          MidiMessage start = null;
          try {
            start = new ShortMessage(ShortMessage.NOTE_ON, notex.getInstrument(),
                    notex.convertToMidiNumber(), notex.getVolume());
          } catch (InvalidMidiDataException e) {
            e.printStackTrace();
          }
          this.receiver.send(start, notex.getStart() * inputModel.getTempo());
        }

        // will need to turn notes off when home is pressed aswell
        if (notex.getEnd() == inputModel.getCurrentBeat()) {
          MidiMessage end = null;
          try {
            end = new ShortMessage(ShortMessage.NOTE_OFF, notex.getInstrument(),
                    notex.convertToMidiNumber(), notex.getVolume());
          } catch (InvalidMidiDataException e) {
            e.printStackTrace();
          }
          this.receiver.send(end, notex.getEnd() * inputModel.getTempo());
        }
      }
    }

    try {
      Thread.sleep(inputModel.getTempo() / 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    if (this.inputModel.getCurrentBeat() >= inputModel.finalBeat()) {
      System.out.println();
    } else {
      this.inputModel.setCurrentBeat(inputModel.getCurrentBeat() + 1);
    }

  }


  /**
   * Method which outputs the View for the IView
   */

  @Override
  public void outputView() {
    int currB = inputModel.getCurrentBeat();
    while (currB <= inputModel.finalBeat()) {
      playNote();
      currB = inputModel.getCurrentBeat();
    }
  }
}
