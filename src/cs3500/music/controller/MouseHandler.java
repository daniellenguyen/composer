package cs3500.music.controller;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Justin Hynes-Bruell on 4/5/2016.
 */
public class MouseHandler implements java.awt.event.MouseListener {

  MusicEditorController controller;

  Point mousePoint;
  boolean noteFound;

  public MouseHandler(MusicEditorController controller){
    this.controller = controller;
    noteFound = false;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
/*
    //Delete Note on Right Click
    switch (e.getButton()) {
      case MouseEvent.BUTTON3:
        controller.deleteNote(e.getPoint());
        break;
    }*/
  }

  @Override
  public void mousePressed(MouseEvent e) {
    mousePoint = e.getPoint();
    noteFound = controller.CheckForNote(mousePoint);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    switch(e.getButton()) {


      case MouseEvent.BUTTON1:
        int separation = e.getX() - mousePoint.x;
        controller.addNote(mousePoint, separation);
        break;

      case MouseEvent.BUTTON2:
        int xSeparation = e.getX() - mousePoint.x;
        int ySeparation = e.getY() - mousePoint.y;
        controller.moveNote(mousePoint, e.getPoint(), xSeparation, ySeparation);
        break;

      case MouseEvent.BUTTON3:
        controller.deleteNote(e.getPoint());
        break;
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
