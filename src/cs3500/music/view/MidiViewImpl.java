package cs3500.music.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sound.midi.*;

import cs3500.music.model.Note;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements View {
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

  public void setMockReciever(Receiver inputReceiver) {
    this.receiver = inputReceiver;
  }

  public Receiver getMockReciever() { return receiver; }

  public void playBeat(SoundUnitList noteList, int BeatNumber) throws InvalidMidiDataException {

    Set<SoundUnit> Notes = noteList.getAllAtTime(BeatNumber);

    Iterator<SoundUnit> i = Notes.iterator();

    List<SoundUnit> alreadyPlayed = new ArrayList<>();

    while (i.hasNext()) {
      //Get the Note from the set
      SoundUnit n = i.next();

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
        myMsg.setMessage(ShortMessage.NOTE_ON, n.getInstrument() - 1,
                n.getMIDIPitch(), n.getVolume());

        receiver.send(myMsg, -1);
      }
      //Find Notes to End
      else if (n.getEnd() - 1 == BeatNumber && !dontStopNote) {

        ShortMessage myMsg = new ShortMessage();
        myMsg.setMessage(ShortMessage.NOTE_OFF, n.getInstrument() - 1,
                n.getMIDIPitch(), n.getVolume());

        receiver.send(myMsg, -1);
      }
    }
  }
  //this.receiver.close(); // Only call this once you're done playing *all* notes


  public void playSong(SoundUnitList inputSong) throws InvalidMidiDataException {
    for (int i = 0; i < inputSong.songLength(); i++) {

      try {
        playBeat(inputSong, i);
      } catch (IllegalArgumentException e) {
        continue;
      }

      try {
        Thread.sleep(inputSong.getTempo() / 1000);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
    }
  }

  public void fillMockReceiver(SoundUnitList inputSong) {
    for (int i = 0; i < inputSong.songLength(); i++) {
      try {
        playBeat(inputSong, i);
      } catch (IllegalArgumentException e) {
        continue;
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }
  }

}
