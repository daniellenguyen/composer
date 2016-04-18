package cs3500.music.controller;

import cs3500.music.model.Note;
import java.awt.*;
import java.awt.event.*;

/**
 * To create a controller for the Music Editor
 */
public interface Controller extends ActionListener {

  /**
   * Sets up the controller's Mouse Listeners
   */
  void configureMouseListener();

  /**
   * Configure the controller's Keyboard listeners
   */
  void configureKeyBoardListener();

  /**
   * Creates a new view for the user to add specific notes.
   * Allows for notes out of the range of the current gui
   * rendering to be created.
   */
  void noteAdderViewCreator();

  /**
   * Sets the beat of the piece to zero and initializes the timer
   * to begin playing the song
   */
  void playFromBeginning();

  /**
   * Starts the timer from the current beat
   */
  void playFromCurrentBeat();

  /**
   * Checks to see if the song is at it's last beat
   *
   * @return true if song is at it's last beat
   */
  boolean IsSongOver();

  /**
   * Updates the Gui and Midi View for a new beat
   */
  void RenderNewBeat();

  /**
   * Updates the Gui to the Right
   */
  void arrowRight();

  /**
   * Updates to Gui to the left
   */
  void arrowLeft();

  /**
   * Checks to ensure that the mousepoint pressed in within the range of the gui
   *
   * @param mousePoint the mousepoint pressed
   * @return true if in a viable area
   */
  boolean CheckForNote(Point mousePoint);

  /**
   * Deletes a Note from the model that is detected from the gui
   *
   * @param mousePoint the mousepoint pressed
   */
  void deleteNote(Point mousePoint);

  /**
   * Moves the Note from one Pitch or Starting point to another
   * Accounts for Note duration
   *
   * @param Start the Note's starting point
   * @param End the Note's ending point
   * @param xSeparation
   * @param ySeparation
   */
  void moveNote(Point Start, Point End, int xSeparation, int ySeparation);

  /**
   * Adds to the model the pitch of the note pressed at that starting point.
   * If dragged will create a longer note.
   * @param Begin the starting point to add at
   * @param separation the amount the point is dragged from the starting point
   */
  void addNote(Point Begin, int separation);

  /**
   * Determines what note was pressed based on it's area in the gui
   *
   * @param mousePoint where the note was pressed
   * @return the Note pressed
   */
  Note NotePressed(Point mousePoint);

  /**
   * Determines what space (note or Potential Note) was pressed based on
   * area pressed on the gui
   *
   * @param mousePoint where a mousepoint was recorded to be pressed
   * @return the Note or potential Note that was pressed
   */
  Note SpacePressed(Point mousePoint);

  /**
   * Closes the adder gui and returns to a refreshed gui view.
   */
  void exitFromNoteAdder();

  /**
   * Goes to End of Piece
   */
  void gotoEnd();

  /**
   * Goes to Start of Piece
   */
  void gotoHome();

}
