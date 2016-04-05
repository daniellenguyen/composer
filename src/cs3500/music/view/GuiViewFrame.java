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
  NoteAdderView noteAdderView;

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

  public void paintComponent(Graphics g) {
    int separation = 15;

    int rangeOfSong = noteList.getHighestNote().getMIDIPitch() -
            noteList.getLowestNote().getMIDIPitch();

    //Iterate Through the Range to create Side Header with Pitch Values of Range
    for (int i = rangeOfSong; i >= 0; i--) {
      SoundUnit rangeNote = new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 0, 1) {
      };
      rangeNote.setPitchAndOctaveFromMIDI(noteList.getHighestNote().getMIDIPitch() - i);
      g.drawString(rangeNote.toString(), 15, separation * i + 30);
    }


    //For each Beat in the song Draws the Notes
    for (int BeatNumber = 0; BeatNumber < noteList.songLength(); BeatNumber++) {
      ArrayList<SoundUnit> ListOfNotesAtBeat = new ArrayList<>();
      ListOfNotesAtBeat.addAll(noteList.getAllAtTime(BeatNumber));

      //Create a Column of Notes
      for (int i = rangeOfSong; i >= 0; i--) {
        SoundUnit rangeNote = new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 0, 1);
        rangeNote.setPitchAndOctaveFromMIDI(noteList.getHighestNote().getMIDIPitch() - i);

        boolean noteStarts = false;
        boolean noteContinues = false;

        //Check if a note is Starting or Continuing
        for (int j = 0; j < ListOfNotesAtBeat.size(); j++) {
          if (rangeNote.getMIDIPitch() == ListOfNotesAtBeat.get(j).getMIDIPitch()) {
            if (ListOfNotesAtBeat.get(j).getStart() == BeatNumber) {
              noteStarts = true;
            } else {
              noteContinues = true;
            }
          }
        }

        //If a Note is Starting then Fill it! This takes Priority over the Continue
        if (noteStarts) {
          //g.drawString(rangeNote.toString(), 15*BeatNumber + 30, separation * i + 30);
          g.fillRect(40 + (25 * BeatNumber), ((separation * i)) + 15, 25, 15);
        } else if (noteContinues) {
          g.setColor(Color.GREEN);
          //g.drawString(rangeNote.toString(), 15*BeatNumber + 30, separation * i + 30);
          g.fillRect(40 + (25 * BeatNumber), ((separation * i)) + 15, 25, 15);
          g.setColor(Color.BLACK);
        }
      }
    }

    //Creates Columns and Headers (Every 16 Measures). This Overlays on Colored Notes
    for (Integer j = 0; j <= noteList.songLength(); j += 4) {
      //g.drawString(j.toString(), 40+(25*j), 15);
      //Iterate Through the Range to create Box for Beat of Notes for TESTING
      for (int i = 0; i <= rangeOfSong; i++) {
        g.drawRect(40 + (25 * j), ((separation * i)) + 15, 100, 15);

        SoundUnit rangeNote = new Note(SoundUnit.Pitch.C, SoundUnit.Octave.FOUR, 0, 1);
        rangeNote.setPitchAndOctaveFromMIDI(noteList.getHighestNote().getMIDIPitch() - i);
        if (rangeNote.getPitch() == SoundUnit.Pitch.B) {
          //Draws Bar for every Middle C
          g.fillRect(40 + (25 * j), ((separation * i)) + 15, 100, 2);
        }
      }
    }
    for (Integer j = 0; j <= noteList.songLength(); j += 16) {
      g.drawString(j.toString(), 40 + (25 * j), 15);
    }

    //Draw Measure of Current Beat Line
    for (Integer j = 0; j <= noteList.songLength(); j++) {
      if(j == noteList.getCurrentBeat()){
        g.setColor(Color.RED);
        // g.drawRect(40 + (25 * j), ((separation * i)) + 15, 100, 15);
        g.fillRect(40 + (25 * j), 15, 3, 15*(rangeOfSong+1));
        g.setColor(Color.BLACK);
      }
    }
  }
}
