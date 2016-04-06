package cs3500.music.controller;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Justin Hynes-Bruell on 4/5/2016.
 */
public class MouseListener implements java.awt.event.MouseListener {

  MusicEditorController controller;

  Point mousePoint;
  boolean noteFound;

  public MouseListener(MusicEditorController controller){
    this.controller = controller;
    noteFound = false;
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    //Delete Note on Right Click
    switch (e.getButton()) {
      case MouseEvent.BUTTON3:
        controller.deleteNote(e.getPoint());
        break;
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    mousePoint = e.getPoint();
    noteFound = controller.CheckForNote(mousePoint);
    if (noteFound == true){
      System.out.println("Note Pressed!\n");
    }
    else {
      System.out.println("Note NOT Pressed!\n");
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
