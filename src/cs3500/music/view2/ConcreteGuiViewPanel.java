package cs3500.music.view;

import java.awt.*;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

<<<<<<< HEAD
import cs3500.music.model.INote;
import cs3500.music.model.IPlayerModel;
=======
import cs3500.music.model2.INote;
import cs3500.music.model2.IPlayerModel;
>>>>>>> d5b4df49ca8d85a5dc57316cd5138ae8e63c6dd4
import cs3500.music.model.Note;

/**
 * // keep track of reverselists -> lists for view for Pitches are inverted
 */


/**
 * A dummy view that simply draws a string
 */

public class ConcreteGuiViewPanel extends JPanel {

  private IPlayerModel inputModel;


  /**
   * Constructor for ViewPanel which takes in a model
   */

  public ConcreteGuiViewPanel(IPlayerModel inputModeli){
    inputModel = inputModeli;
  }

  /**
   * Draws onto GUI
   *
   */

  @Override
  public void paintComponent(Graphics g){
    // Handle the default painting
    super.paintComponent(g);
    // Look for more documentation about the Graphics class,
    // and methods on it that may be useful
    ((Graphics2D)g).setStroke(new BasicStroke(2f));
    List<String> allPitches = inputModel.outputPitchesOctaves();
    Collections.reverse(allPitches);
    int howManyColumns = allPitches.size();
    int howManyRows = inputModel.finalBeat();
    int width = (howManyRows * 20)+80; // + 40 on each side (first 40 is the notes)
    int height = (howManyColumns * 20)+40; // + 20 on each side
    this.setPreferredSize(new Dimension(width, height));


    List<INote> listOfNotes = inputModel.outputModelAsList();
    // gets each individual note
    for (INote nz: listOfNotes){

      int currspoty = 25;
      for (int j = 0; allPitches.get(j).compareTo(nz.getNoteAsString()) != 0;
           j+=1) {
        currspoty += 20;
      }

      int currspotx = nz.getStart();

      // adds the notes
      while (currspotx < nz.getEnd()){
        boolean black = false;
        if (currspotx == nz.getStart()) {
          black = true;
        }
        if (black){
          g.setColor(Color.BLACK);
        }
        else {
          g.setColor(Color.GREEN);
        }
        g.fillRect((currspotx*20)+40, currspoty, 20, 20);
        currspotx += 1;
      }
    }

    // adds a new box for all of the notes and the different pitches
    g.setColor(Color.BLACK);
    int y = 40;
    int x = 0;
    for (String a: allPitches){
      g.drawString(a, x, y);
      g.drawRect(40, y-15, 20*howManyRows, 20);
             // x,y,w,h
      y += 20;
    }

    // adds the note length you are at
    y = 20;
    x = 40;
    int count = 0;
    while (x < width - 40 ){
      g.drawString(Integer.toString(count), x, y);
      count += 16;
      x+= 16*20;
    }

    // adds the vertical lines
    x = 40;
    while (x < width - 40){
      g.drawLine(x,25,x,height-15);
             // x1,y1,x2,y2
      x += 80;
    }

    // adds the red line for the current beat
    g.setColor(Color.RED);
    x = 40 + (20*inputModel.getCurrentBeat());
    g.drawLine(x,25, x, 25+20*howManyColumns);
  }

}
