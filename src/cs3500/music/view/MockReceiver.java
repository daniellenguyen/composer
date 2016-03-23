package cs3500.music.view;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Created by Justin Hynes-Bruell on 3/23/2016.
 */
public class MockReceiver implements Receiver{

  StringBuilder ListOfNotesPlayed;

  public MockReceiver(){
    super();
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    byte[] myMsg = message.getMessage();
    ListOfNotesPlayed.append(myMsg.toString());
  }

  @Override
  public void close() {
    //Does Nothing
  }

  public String GetMockBuffer(){
    return ListOfNotesPlayed.toString();
  }
}
