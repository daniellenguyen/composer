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

  SoundUnitList noteList;
  /**
   * Creates new GuiView
   */
  public GuiViewFrame(SoundUnitList inputSong) {
    this.displayPanel = new ConcreteGuiViewPanel(inputSong);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(displayPanel);
    this.pack();
    noteList = inputSong;
  }

  //@Override
  public void initialize(){
    this.setVisible(true);
  }

  public void refresh(SoundUnitList inputSong){
    //this.setVisible(false);
    //this.displayPanel = new ConcreteGuiViewPanel(inputSong);
    paintComponents(getGraphics());
    //  this.setVisible(true);
  }

  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public Dimension getPreferredSize(){
    return new Dimension(1600, 800);
  }

  public void addActionListener(ActionListener actionListener) {
  }

  @Override
  public void Render(SoundUnitList listOfNote) {
    this.displayPanel = new ConcreteGuiViewPanel(listOfNote);
    this.getContentPane().add(displayPanel);
    this.pack();
  }


}
