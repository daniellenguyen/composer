package cs3500.music.model;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;
import cs3500.music.model2.INote;
import cs3500.music.model2.IPlayerModel;
import cs3500.music.model2.NoteComparatorTextView;
import cs3500.music.view.ConsoleViewImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An adapter class from SoundUnitList to IPlayerModel
 */
public class SoundUnitListToIPlayerModelAdapter implements IPlayerModel {

  SoundUnitList ObjectAdaptorSongList;

  String songname;

  public SoundUnitListToIPlayerModelAdapter(String inputSongName) {
    songname = inputSongName;
    ObjectAdaptorSongList = new NoteList();
    ObjectAdaptorSongList.setTempo(0);
  }

  public SoundUnitListToIPlayerModelAdapter(String inputSongName, List<Note> inputNotes){
    songname = inputSongName;
    for(int i=0; i< inputNotes.size(); i++){
      ObjectAdaptorSongList.add(inputNotes.get(i));
    }
    ObjectAdaptorSongList.setCurrentBeat(0);
    ObjectAdaptorSongList.setTempo(0);
  }

  public SoundUnitListToIPlayerModelAdapter(String inputSongName, List<Note> inputNotes, int tempoi){
    songname = inputSongName;
    for(int i=0; i< inputNotes.size(); i++){
      ObjectAdaptorSongList.add(inputNotes.get(i));
    }
    ObjectAdaptorSongList.setCurrentBeat(0);
    ObjectAdaptorSongList.setTempo(tempoi);
  }

  /**
   * method which adds a Note to the Model
   * @param inputNote  indicates the note that you want to add
   */

  public void addNote(cs3500.music.model2.Note inputNote) {
    ObjectAdaptorSongList.add(new SoundUnitToINoteAdapter().ConvertINoteToSoundUnit(inputNote));
  }

  public void addNoteInterface(cs3500.music.model2.INote inputNote) {
    ObjectAdaptorSongList.add(new SoundUnitToINoteAdapter().ConvertINoteToSoundUnit(inputNote));
  }


  /**
   * Outputs the model as a String
   * @return List of notes representing all of the notes in the song
   */

  public String outputModel() {
    // Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print some output: goes to your special stream
    ConsoleViewImpl ShouldBeInModel = new ConsoleViewImpl(ObjectAdaptorSongList);

    ShouldBeInModel.render();

    // Put things back
    System.out.flush();
    System.setOut(old);
    // Show what happened
    //System.out.println("Here: " + baos.toString());
    return baos.toString();
  }

  public List<cs3500.music.model2.INote> SoundUnitListConverter2(SoundUnitList inputSongList){
    List<cs3500.music.model2.INote> ListOfINote = new ArrayList<>();

    //Add Tons of Notes to the song (there are Repeats)
    for(int beat = 0; beat < inputSongList.songLength(); beat++){
      //If there are notes at this beat
      if(inputSongList.hasNotesAtTime(beat)){
        ArrayList<SoundUnit> ListOfNotesAtBeat = new ArrayList<>();
        ListOfNotesAtBeat.addAll(inputSongList.getAllAtTime(beat));
        for(int i = 0; i < ListOfNotesAtBeat.size(); i++){
          ListOfINote.add(new SoundUnitToINoteAdapter().ConvertSoundUnitToINote(ListOfNotesAtBeat.get(i)));
        }
      }
    }

    //Remove Repeats
    for(int i = 0; i < ListOfINote.size(); i++){
      for(int j = 0; j < ListOfINote.size(); j++){
        //If Pitch is the Same
        if(ListOfINote.get(i).getPitch() == ListOfINote.get(j).getPitch()){
          if(ListOfINote.get(i).getOctave() == ListOfINote.get(j).getOctave()){
            if(ListOfINote.get(i).getStart() == ListOfINote.get(j).getStart()){
              if(ListOfINote.get(i).getEnd() == ListOfINote.get(j).getEnd()){
                if(ListOfINote.get(i).getVolume() == ListOfINote.get(j).getVolume()){
                  if(ListOfINote.get(i).getInstrument() == ListOfINote.get(j).getInstrument()){
                    //If Everything Matches Remove the Note
                    ListOfINote.remove(ListOfINote.get(i));
                  }
                }
              }
            }
          }
        }
      }
    }
    return ListOfINote;
  }

  public List<cs3500.music.model2.Note> SoundUnitListConverter(SoundUnitList inputSongList){
    List<cs3500.music.model2.Note> ListOfINote = new ArrayList<>();

    //Add Tons of Notes to the song (there are Repeats)
    for(int beat = 0; beat < inputSongList.songLength(); beat++){
      //If there are notes at this beat
      if(inputSongList.hasNotesAtTime(beat)){
        ArrayList<SoundUnit> ListOfNotesAtBeat = new ArrayList<>();
        ListOfNotesAtBeat.addAll(inputSongList.getAllAtTime(beat));
        for(int i = 0; i < ListOfNotesAtBeat.size(); i++){
          ListOfINote.add((cs3500.music.model2.Note) new SoundUnitToINoteAdapter().ConvertSoundUnitToINote(ListOfNotesAtBeat.get(i)));
        }
      }
    }

    //Remove Repeats
    for(int i = 0; i < ListOfINote.size(); i++){
      for(int j = 0; j < ListOfINote.size(); j++){
        //If Pitch is the Same
        if(ListOfINote.get(i).getPitch() == ListOfINote.get(j).getPitch()){
          if(ListOfINote.get(i).getOctave() == ListOfINote.get(j).getOctave()){
            if(ListOfINote.get(i).getStart() == ListOfINote.get(j).getStart()){
              if(ListOfINote.get(i).getEnd() == ListOfINote.get(j).getEnd()){
                if(ListOfINote.get(i).getVolume() == ListOfINote.get(j).getVolume()){
                  if(ListOfINote.get(i).getInstrument() == ListOfINote.get(j).getInstrument()){
                    //If Everything Matches Remove the Note
                    ListOfINote.remove(ListOfINote.get(i));
                  }
                }
              }
            }
          }
        }
      }
    }
    return ListOfINote;
  }

  /**
   * Outputs the model as a List
   * @return List of notes representing all of the notes in the song
   */
  //TODO THIS NEEDS TO BE REVIEWED AND MADE PERFECT
  public List<cs3500.music.model2.Note> outputModelAsList() {
    return SoundUnitListConverter(ObjectAdaptorSongList);
  }


  /**
   * Outputs the model as a Map
   * @return List of notes represented as a map
   */

  public Map<Integer, List<cs3500.music.model2.Note>> outputModelAsMap() {

    if (this.outputModelAsList().size() <= 0) {
      return Collections.emptyMap();
    }

    List<cs3500.music.model2.Note> theNotes = this.outputModelAsList();
    Collections.sort(theNotes, new NoteComparatorTextView());
    int endPoint = this.finalBeat();
    Map<Integer, List<cs3500.music.model2.Note>> dataMap = new HashMap<Integer, List<cs3500.music.model2.Note>>();

    for (int i = 0; i <= endPoint; i++) {
      dataMap.put(i, new ArrayList<cs3500.music.model2.Note>());
    }

    for (cs3500.music.model2.Note aNote : theNotes) {
      int curr = aNote.getStart();
      int end = aNote.getEnd();
      while (curr < end) {
        dataMap.get(curr).add(aNote);
        curr += 1;
      }
    }

    return dataMap;

  }


  /**
   * removes a Note from the Model
   * @param inputNote  indicates the note that you want to have removed
   */

  public void removeNote(INote inputNote) {
    ObjectAdaptorSongList.delete(new SoundUnitToINoteAdapter().ConvertINoteToSoundUnit(inputNote));
  }


  /**
   * finds the time of the last note
   * @return lastNote  indicates the final note to be played
   */

  public int finalBeat() {
    return ObjectAdaptorSongList.songLength();
  }


  /**
   * finds all of the pitches used in song and returns them as strings
   * @return lastNote  indicates the final note to be played
   */

  public List<String> outputPitchesOctaves() {

    List<String> ListOfNotes = new ArrayList<>();

    int rangeOfSong = ObjectAdaptorSongList.getHighestNote().getMIDIPitch() -
            ObjectAdaptorSongList.getLowestNote().getMIDIPitch();

    //Iterate Through the Range to create Side Header with Pitch Values of Range
    for (int i = 0; i < rangeOfSong; i++) {
      SoundUnit rangeNote = new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 0, 1) {};
      rangeNote.setPitchAndOctaveFromMIDI(ObjectAdaptorSongList.getLowestNote().getMIDIPitch() + i);
      ListOfNotes.add(rangeNote.toString());
    }

    return ListOfNotes;
  }


  /**
   * returns the name of the song
   * @return Songname - name of the song
   */

  public String getSongName() {
    return songname;
  }


  /**
   * returns the current beat of the song
   * @return Beat -> the current beat of hte
   */

  public int getCurrentBeat() {
    return ObjectAdaptorSongList.getCurrentBeat();
  }


  /**
   * Sets the current beat of the song
   * @param inputBeat -> what you want to current beat of the song to be
   */

  public void setCurrentBeat(int inputBeat) {
    ObjectAdaptorSongList.setCurrentBeat(inputBeat);
  }


  /**
   * Sets the name of the Song
   * @param nameOfSong -> sets the name of the Song
   */

  public void setSongName(String nameOfSong) {
    songname = nameOfSong;
  }


  /**
   * Sets the tempo of the song
   * @param inputTempo -> the new tempo of the song to be updated;
   *
   */

  public void setTempo(int inputTempo) {
    ObjectAdaptorSongList.setTempo(inputTempo);
  }


  /**
   * Gets the Tempo of the Song
   * @return tempo -> gets the tempo of the song
   */

  public int getTempo() {
    return ObjectAdaptorSongList.getTempo();
  }
}
