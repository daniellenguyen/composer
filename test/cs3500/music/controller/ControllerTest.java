package cs3500.music.controller;

import org.junit.Test;

import java.awt.*;

import cs3500.music.model.Note;
import cs3500.music.model.SoundUnitList;
import cs3500.music.util.MusicReader;
import cs3500.music.view.CompositeViewImpl;
import cs3500.music.view.ViewCreator;

/**
 * Controller Test Class
 */
public class ControllerTest {

  @Test
  public void playFromBeginningTest1() {
    MusicReader ReaderOfText = new MusicReader();

    SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mary-little-lamb.txt");

    CompositeViewImpl newCompositeView = (CompositeViewImpl)
            ViewCreator.create(ViewCreator.ViewType.COMPOSITE, inputSong);

    MusicEditorController asd = new MusicEditorController(inputSong, newCompositeView);
  }
  @Test
  public void playFromBeginningTest2() {
    MusicReader ReaderOfText = new MusicReader();


    SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-1.txt");

    CompositeViewImpl newCompositeView = (CompositeViewImpl)
            ViewCreator.create(ViewCreator.ViewType.COMPOSITE, inputSong);

    MusicEditorController asd = new MusicEditorController(inputSong, newCompositeView);
  }
  @Test
  public void playFromBeginningTest3() {
    MusicReader ReaderOfText = new MusicReader();


    SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("ChromaticScale.txt");

    CompositeViewImpl newCompositeView = (CompositeViewImpl)
            ViewCreator.create(ViewCreator.ViewType.COMPOSITE, inputSong);

    MusicEditorController asd = new MusicEditorController(inputSong, newCompositeView);
  }
  @Test
  public void playFromBeginningTest4() {
    MusicReader ReaderOfText = new MusicReader();

    SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("BugTestSong.txt");

    CompositeViewImpl newCompositeView = (CompositeViewImpl)
            ViewCreator.create(ViewCreator.ViewType.COMPOSITE, inputSong);

    MusicEditorController asd = new MusicEditorController(inputSong, newCompositeView);
  }

  @Test
  public void playFromBeginningTest5() {
    MusicReader ReaderOfText = new MusicReader();

    SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-2.txt");

    CompositeViewImpl newCompositeView = (CompositeViewImpl)
            ViewCreator.create(ViewCreator.ViewType.COMPOSITE, inputSong);

    MusicEditorController asd = new MusicEditorController(inputSong, newCompositeView);
  }

  @Test
  public void playFromBeginningTest6() {
    MusicReader ReaderOfText = new MusicReader();

    SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mystery-3.txt");

    CompositeViewImpl newCompositeView = (CompositeViewImpl)
            ViewCreator.create(ViewCreator.ViewType.COMPOSITE, inputSong);

    MusicEditorController asd = new MusicEditorController(inputSong, newCompositeView);
  }

  @Test
  public void AddNote() {
    MusicReader ReaderOfText = new MusicReader();

    SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mary-little-lamb.txt");

    CompositeViewImpl newCompositeView = (CompositeViewImpl)
            ViewCreator.create(ViewCreator.ViewType.COMPOSITE, inputSong);

    MusicEditorController asd = new MusicEditorController(inputSong, newCompositeView);

    asd.addNote(new Point(100, 100), 55);
  }

  @Test
  public void DeleteNote() {
    MusicReader ReaderOfText = new MusicReader();

    SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mary-little-lamb.txt");

    CompositeViewImpl newCompositeView = (CompositeViewImpl)
            ViewCreator.create(ViewCreator.ViewType.COMPOSITE, inputSong);

    MusicEditorController asd = new MusicEditorController(inputSong, newCompositeView);

    Point NotePoint = new Point(100, 100);

    asd.addNote(NotePoint, 55);

    Note NoteThatWasAdded = asd.NotePressed(NotePoint);

    asd.deleteNote(NotePoint);

    Note NoteThatWasPressed = asd.NotePressed(NotePoint);

    if(NoteThatWasAdded == NoteThatWasPressed){
      throw new IllegalArgumentException("Error!");
    }
  }

  @Test
  public void MoveNote() {
    MusicReader ReaderOfText = new MusicReader();

    SoundUnitList inputSong = ReaderOfText.ReturnNoteListFromFile("mary-little-lamb.txt");

    CompositeViewImpl newCompositeView = (CompositeViewImpl)
            ViewCreator.create(ViewCreator.ViewType.COMPOSITE, inputSong);

    MusicEditorController asd = new MusicEditorController(inputSong, newCompositeView);

    Point NotePoint = new Point(100, 100);

    asd.addNote(NotePoint, 55);

    Note NoteThatWasAdded = asd.NotePressed(NotePoint);

    Point MovePoint = new Point(200, 200);

    asd.moveNote(NotePoint, MovePoint, 100, 100);

    asd.deleteNote(NotePoint);

    Note NoteThatWasPressed = asd.NotePressed(NotePoint);

    if(NoteThatWasAdded == NoteThatWasPressed){
      throw new IllegalArgumentException("Error!");
    }
  }
}
