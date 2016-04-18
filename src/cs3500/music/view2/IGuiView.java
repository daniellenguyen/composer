package cs3500.music.view2;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Position;

import cs3500.music.model2.IPlayerModel;

/**
 * Created by James on 4/1/16.
 */
public interface IGuiView extends IView {


  /**
   * Adds a keyListener
   *
   * @param KeyListeneri -> the key listener being added
   */

  void addKeyListener(KeyListener KeyListeneri);


  /**
   * Adds a mouseListener to the Panel
   *
   * @param MouseListeneri -> the Mouse listener being added
   */

  void addMouseListener(MouseListener MouseListeneri);


  /**
   * gets the scrollable of the view
   *
   * @return JScrollPane -> Scrollpane of the
   */

  JScrollPane getScroller();


//  /**
//   * returns the current beat of the song
//   *
//   * @return beat -> integer value of the current beat of the song (starts at 0)
//   */
//
//  int getBeat();
//
//
//  /**
//   * sets the current beat of the song
//   *
//   * @param beati -> integer value you want to set the beat to
//   */
//
//  void setBeat(int beati);


  /**
   * updates the scrollable
   *
   * @param value the integer value you want to increment the scroller by
   * @param xory -> which direction you want to update it in
   */

  void updateScroll(String xory, int value);

  JPanel getPanel();

  IPlayerModel getModel();


}
