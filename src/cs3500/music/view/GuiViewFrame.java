package cs3500.music.view;

import java.awt.*;

import javax.swing.*;

import cs3500.music.controller.MusicEditorController;
import cs3500.music.model.SoundUnitList;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends javax.swing.JFrame implements View {

  private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(SoundUnitList inputSong) {
    this.displayPanel = new ConcreteGuiViewPanel(inputSong);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(displayPanel);
    this.pack();
  }

  //@Override
  public void initialize(){
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize(){
    return new Dimension(1600, 800);
  }

  public void addActionListener(MusicEditorController musicEditorController) {
    //TODO add listeners Here
    /*
    echoButton.addActionListener(actionListener);
    exitButton.addActionListener(actionListener);
     */
  }
}
