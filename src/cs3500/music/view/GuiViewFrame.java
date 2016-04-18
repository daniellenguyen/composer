package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs3500.music.controller.MouseHandler;
import cs3500.music.model.SoundUnit;
import cs3500.music.model.SoundUnitList;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends javax.swing.JFrame implements View, IGuiView {
  SoundUnitList soundUnitList;

  private JPanel displayPanel;

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(SoundUnitList soundUnitList) {
    this.soundUnitList = soundUnitList;
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    render();
  }

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
  }

  public void addActionListener(ActionListener actionListener) {

  }

  @Override
  public void render() {
    displayPanel = new ConcreteGuiViewPanel(soundUnitList);
    this.add(displayPanel);
    this.pack();
  }

  public void addNewMouseListener(MouseHandler listener) {
    displayPanel.addMouseListener(listener);
  }
}
