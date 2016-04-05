package cs3500.music.view;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Mock object to test MIDI
 */
public class MockReceiver implements Receiver{
  StringBuilder ListOfNotesPlayed;

  public MockReceiver(){
    super();
    ListOfNotesPlayed = new StringBuilder();
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    ShortMessage myMsg = (ShortMessage) message;
    ListOfNotesPlayed.append(myMsg.getCommand()
            + " " + myMsg.getChannel() + " " + myMsg.getData1() + " " + myMsg.getData2() + "\n");
  }

  @Override
  public void close() {
    //Does Nothing
  }

  public String GetMockBuffer(){
    return ListOfNotesPlayed.toString();
  }
}
