package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import cs3500.music.model.NoteList;

/**
 * A dummy view that simply draws a string 
 */
public class ConcreteGuiViewPanel extends JPanel {

  NoteList inputSong;

  public ConcreteGuiViewPanel(NoteList inputSong){
    this.inputSong =  inputSong;
  }


  @Override
  public void paint(Graphics g){
    // Look for more documentation about the Graphics class, and methods on it that may be useful
    super.paintComponent(g);
    g.drawString("Hello World", 10, 10);

    inputSong.getAllAtTime(0);
  }

}
