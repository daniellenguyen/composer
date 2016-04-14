package cs3500.music.model;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;
import cs3500.music.model2.INote;
import cs3500.music.model2.IPlayerModel;

import java.util.List;
import java.util.Map;

/**
 * An adapter class from SoundUnitList to IPlayerModel
 */
public class SoundUnitListAdapter implements IPlayerModel {

  /**
   * method which adds a Note to the Model
   * @param inputNote  indicates the note that you want to add
   */

  public void addNote(cs3500.music.model2.Note inputNote) {
    this.add(inputNote);
  }


  /**
   * Outputs the model as a String
   * @return List of notes representing all of the notes in the song
   */

  public String outputModel() {

  }


  /**
   * Outputs the model as a List
   * @return List of notes representing all of the notes in the song
   */

  public List<cs3500.music.model2.Note> outputModelAsList() {

  }


  /**
   * Outputs the model as a Map
   * @return List of notes represented as a map
   */

  public Map<Integer, List<cs3500.music.model.Note>> outputModelAsMap() {

  }


  /**
   * removes a Note from the Model
   * @param inputNote  indicates the note that you want to have removed
   */

  public void removeNote(INote inputNote) {

  }


  /**
   * finds the time of the last note
   * @return lastNote  indicates the final note to be played
   */

  public int finalBeat() {

  }


  /**
   * finds all of the pitches used in song and returns them as strings
   * @return lastNote  indicates the final note to be played
   */

  public List<String> outputPitchesOctaves() {

  }


  /**
   * returns the name of the song
   * @return Songname - name of the song
   */

  public String getSongName() {

  }


  /**
   * returns the current beat of the song
   * @return Beat -> the current beat of hte
   */

  public int getCurrentBeat() {

  }


  /**
   * Sets the current beat of the song
   * @param inputBeat -> what you want to current beat of the song to be
   */

  public void setCurrentBeat(int inputBeat) {

  }


  /**
   * Sets the name of the Song
   * @param nameOfSong -> sets the name of the Song
   */

  public void setSongName(String nameOfSong) {

  }


  /**
   * Sets the tempo of the song
   * @param inputTempo -> the new tempo of the song to be updated;
   *
   */

  public void setTempo(int inputTempo) {

  }


  /**
   * Gets the Tempo of the Song
   * @return tempo -> gets the tempo of the song
   */

  public int getTempo() {

  }



}
