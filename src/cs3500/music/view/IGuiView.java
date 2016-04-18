package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;

import cs3500.music.controller.MouseHandler;

/**
 * Created by Justin Hynes-Bruell on 4/18/2016.
 */
public interface IGuiView {

  void initialize();

  void resetFocus();

  Dimension getPreferredSize();

  void addActionListener(ActionListener actionListener);

  void render();

  void addNewMouseListener(MouseHandler listener);
}
