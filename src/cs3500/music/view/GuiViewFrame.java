package cs3500.music.view;

import java.awt.*;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events
import java.util.Iterator;
import java.util.Set;

import javax.swing.*;

import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.util.MusicReader;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends javax.swing.JFrame implements View {

  private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel

  /**
   * Creates new GuiView
   */
  public GuiViewFrame() {
    this.displayPanel = new ConcreteGuiViewPanel();
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(displayPanel);
    this.pack();
  }

  //@Override
  public void initialize(){
    this.setVisible(true);
  }

  public void DisplaySongFromFile(String FileName){
    MusicReader ReaderOfText = new MusicReader();

    NoteList inputSong = ReaderOfText.ReturnNoteListFromFile(FileName);

    DisplaySong(inputSong);
  }

  public void DisplaySong(NoteList noteList) {
    getGraphics().drawString("BannaGramM!!", 50, 50);
    int BeatNumber = 0;

    Set<Note> Notes = noteList.getAllAtTime(BeatNumber);
    Iterator<Note> i = Notes.iterator();
    while (i.hasNext()) {
      Note n = (Note) i.next();
      //Find notes to Start
      if (n.getStart() == BeatNumber) {
        getGraphics().drawString(n.toString(), 100, 200);
      }
      //Find Notes to End
      else if (n.getEnd() == BeatNumber) {
      }
    }
    displayPanel.paint(getGraphics());
  }




  @Override
  public Dimension getPreferredSize(){
    return new Dimension(1500, 600);
  }

}
