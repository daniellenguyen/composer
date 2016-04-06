package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.music.controller.MusicEditorController;
import cs3500.music.model.Note;
import cs3500.music.model.NoteList;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends javax.swing.JFrame implements View {

  private JPanel displayPanel; // You may want to refine this to a subtype of JPanel

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(SoundUnitList inputSong) {
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    Render(inputSong);
  }

  //@Override
  public void initialize() {
    this.setVisible(true);
  }

  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1600, 800);
    //return new Dimension(displayPanel.getWidth(), displayPanel.getHeight());

  }

  public void addActionListener(ActionListener actionListener) {
  }

  @Override
  public void Render(SoundUnitList listOfNote) {
    displayPanel = new ConcreteGuiViewPanel(listOfNote);
    /*displayPanel.setPreferredSize(new Dimension(listOfNote.songLength() * 25 + 100,
            (listOfNote.getHighestNote().getMIDIPitch() -
                    listOfNote.getLowestNote().getMIDIPitch()) * 15 + 50));

    JScrollPane scroller = new JScrollPane(displayPanel);
    scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.add(scroller);
    */
    this.add(displayPanel);
    this.pack();

  }
}
