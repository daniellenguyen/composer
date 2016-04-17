package cs3500.music.model;
import java.util.List;
import java.util.*;

/**
 * Created by James on 2/28/16.
 */


/**
 * Interface that represents a player model
 *
 */

public interface IPlayerModel {


  /**
   * method which adds a Note to the Model
   * @param inputNote  indicates the note that you want to add
   */

  void addNote(INote inputNote);


  /**
   * Outputs the model as a String
   * @return List of notes representing all of the notes in the song
   */

  String outputModel();


  /**
   * Outputs the model as a List
   * @return List of notes representing all of the notes in the song
   */

  List<INote> outputModelAsList();


  /**
   * Outputs the model as a Map
   * @return List of notes represented as a map
   */

  Map<Integer, List<INote>> outputModelAsMap();


  /**
   * removes a Note from the Model
   * @param inputNote  indicates the note that you want to have removed
   */

  void removeNote(INote inputNote);


  /**
   * finds the time of the last note
   * @return lastNote  indicates the final note to be played
   */

  int finalBeat();


  /**
   * finds all of the pitches used in song and returns them as strings
   * @return lastNote  indicates the final note to be played
   */

  List<String> outputPitchesOctaves();


  /**
   * returns the name of the song
   * @return Songname - name of the song
   */

  String getSongName();


  /**
   * returns the current beat of the song
   * @return Beat -> the current beat of hte
   */

  int getCurrentBeat();


  /**
   * Sets the current beat of the song
   * @param inputBeat -> what you want to current beat of the song to be
   */

  void setCurrentBeat(int inputBeat);


  /**
   * Sets the name of the Song
   * @param nameOfSong -> sets the name of the Song
   */

  void setSongName(String nameOfSong);


  /**
   * Sets the tempo of the song
   * @param inputTempo -> the new tempo of the song to be updated;
   *
   */

  void setTempo(int inputTempo);


  /**
   * Gets the Tempo of the Song
   * @return tempo -> gets the tempo of the song
   */

  int getTempo();



}