package cs3500.music.view;


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.swing.*;

import cs3500.music.model.Note;
import cs3500.music.model.SoundUnit;

/**
 * Created by Justin Hynes-Bruell on 4/5/2016.
 */
public class NoteAdderView extends JFrame {

  private JPanel panelSouth, panelNorth, panelCenter;
  private JPanel panelPitch, panelOctave;

  ButtonGroup pitchButton;
  ButtonGroup octaveButton;

  private Container contents;

  private FlowLayout flowLayout = new FlowLayout();

  private JLabel displayStart, displayDuration, displayVolume, displayInstrument;
  private JLabel displayPitch, displayOctave;
  private JButton setNoteButton, exitButton;
  private JTextField inputStart, inputDuration, inputVolume, inputInstrument;


  private JRadioButton C = new JRadioButton("C");
  private JRadioButton C2 = new JRadioButton("C2");
  private JRadioButton D = new JRadioButton("D");
  private JRadioButton D2 = new JRadioButton("D2");
  private JRadioButton E = new JRadioButton("E");
  private JRadioButton F = new JRadioButton("F");
  private JRadioButton F2 = new JRadioButton("F2");
  private JRadioButton G = new JRadioButton("G");
  private JRadioButton G2 = new JRadioButton("G2");
  private JRadioButton A = new JRadioButton("A");
  private JRadioButton A2 = new JRadioButton("A2");
  private JRadioButton B = new JRadioButton("B");

  private JRadioButton oOne = new JRadioButton("1");
  private JRadioButton oTwo = new JRadioButton("2");
  private JRadioButton oThree = new JRadioButton("3");
  private JRadioButton oFour = new JRadioButton("4");
  private JRadioButton oFive = new JRadioButton("5");
  private JRadioButton oSix = new JRadioButton("6");
  private JRadioButton oSeven = new JRadioButton("7");
  private JRadioButton oEight = new JRadioButton("8");
  private JRadioButton oNine = new JRadioButton("9");
  private JRadioButton oTen = new JRadioButton("10");
  private JRadioButton oEleven = new JRadioButton("11");

  public NoteAdderView(Note oldNote) {
    super("Note Adder");
    setSize(500, 500);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setTitle("Note Adder");

    contents = getContentPane();

    this.setLayout(new BorderLayout());

    //North region
    panelNorth = new JPanel();
    panelNorth.setLayout(new BorderLayout());
    setPitchRadio();
    setOctaveRadio();

    //Center Region
    panelCenter = new JPanel();
    panelCenter.setLayout(new GridLayout(4, 2));

    displayStart = new JLabel("Start");
    panelCenter.add(displayStart);
    inputStart = new JTextField(10);
    panelCenter.add(inputStart);

    displayDuration = new JLabel("Duration");
    panelCenter.add(displayDuration);
    inputDuration = new JTextField(10);
    panelCenter.add(inputDuration);

    displayVolume = new JLabel("Volume");
    panelCenter.add(displayVolume);
    inputVolume = new JTextField(10);
    panelCenter.add(inputVolume);

    displayInstrument = new JLabel("Instrument");
    panelCenter.add(displayInstrument);
    inputInstrument = new JTextField(10);
    panelCenter.add(inputInstrument);


    //South Region
    panelSouth = new JPanel();
    panelSouth.setLayout(new GridLayout(4, 2));
    //setNoteButton
    setNoteButton = new JButton("Set Note");
    setNoteButton.setActionCommand("Set Note Button");
    panelSouth.add(setNoteButton);

    //exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    panelSouth.add(exitButton);


    this.add(panelNorth, BorderLayout.NORTH);
    this.add(panelCenter, BorderLayout.CENTER);
    this.add(panelSouth, BorderLayout.SOUTH);


    //Set text in Boxes
    Integer converter = oldNote.getStart();
    inputStart.setText(converter.toString());
    //converter = (oldNote.getEnd()-oldNote.getStart());
    //TODO determine End Vs Duration
    converter = oldNote.getEnd();
    inputDuration.setText(converter.toString());
    converter = oldNote.getVolume();
    inputVolume.setText(converter.toString());
    converter = oldNote.getInstrument();
    inputInstrument.setText(converter.toString());

    pack();
    setVisible(true);

  }

  private void setOctaveRadio() {
    panelOctave = new JPanel();
    panelOctave.setLayout(new FlowLayout());

    //Create the radio buttons.
    oOne.setMnemonic(KeyEvent.VK_B);
    oOne.setActionCommand("C");
    oOne.setSelected(true);

    oTwo.setMnemonic(KeyEvent.VK_C);
    oTwo.setActionCommand("C2");

    oThree.setMnemonic(KeyEvent.VK_D);
    oThree.setActionCommand("D");

    oFour.setMnemonic(KeyEvent.VK_R);
    oFour.setActionCommand("D2");

    oFive.setMnemonic(KeyEvent.VK_P);
    oFive.setActionCommand("E");

    oSix.setMnemonic(KeyEvent.VK_C);
    oSix.setActionCommand("F");

    oSeven.setMnemonic(KeyEvent.VK_D);
    oSeven.setActionCommand("F2");

    oEight.setMnemonic(KeyEvent.VK_R);
    oEight.setActionCommand("G");

    oNine.setMnemonic(KeyEvent.VK_P);
    oNine.setActionCommand("G2");

    oTen.setMnemonic(KeyEvent.VK_D);
    oTen.setActionCommand("A");

    oEleven.setMnemonic(KeyEvent.VK_R);
    oEleven.setActionCommand("A2");

    //Group the radio buttons.
    ButtonGroup group = new ButtonGroup();
    group.add(oOne);
    group.add(oTwo);
    group.add(oThree);
    group.add(oFour);
    group.add(oFive);
    group.add(oSix);
    group.add(oSeven);
    group.add(oEight);
    group.add(oNine);
    group.add(oTen);
    group.add(oEleven);

    octaveButton = group;

    panelOctave.add(oOne);
    panelOctave.add(oTwo);
    panelOctave.add(oThree);
    panelOctave.add(oFour);
    panelOctave.add(oFive);
    panelOctave.add(oSix);
    panelOctave.add(oSeven);
    panelOctave.add(oEight);
    panelOctave.add(oNine);
    panelOctave.add(oTen);
    panelOctave.add(oEleven);

    panelNorth.add(panelOctave, BorderLayout.SOUTH);
  }

  private void setPitchRadio() {
    panelPitch = new JPanel();
    panelPitch.setLayout(new FlowLayout());

    //Create the radio buttons.
    C.setMnemonic(KeyEvent.VK_B);
    C.setActionCommand("C");
    C.setSelected(true);

    C2.setMnemonic(KeyEvent.VK_C);
    C2.setActionCommand("C2");

    D.setMnemonic(KeyEvent.VK_D);
    D.setActionCommand("D");

    D2.setMnemonic(KeyEvent.VK_R);
    D2.setActionCommand("D2");

    E.setMnemonic(KeyEvent.VK_P);
    E.setActionCommand("E");

    F.setMnemonic(KeyEvent.VK_C);
    F.setActionCommand("F");

    F2.setMnemonic(KeyEvent.VK_D);
    F2.setActionCommand("F2");

    G.setMnemonic(KeyEvent.VK_R);
    G.setActionCommand("G");

    G2.setMnemonic(KeyEvent.VK_P);
    G2.setActionCommand("G2");

    A.setMnemonic(KeyEvent.VK_D);
    A.setActionCommand("A");

    A2.setMnemonic(KeyEvent.VK_R);
    A2.setActionCommand("A2");

    B.setMnemonic(KeyEvent.VK_P);
    B.setActionCommand("B");

    //Group the radio buttons.
    ButtonGroup group = new ButtonGroup();
    group.add(C);
    group.add(C2);
    group.add(D);
    group.add(D2);
    group.add(E);
    group.add(F);
    group.add(F2);
    group.add(G);
    group.add(G2);
    group.add(A);
    group.add(A2);
    group.add(B);

    pitchButton = group;

    panelPitch.add(C);
    panelPitch.add(C2);
    panelPitch.add(D);
    panelPitch.add(D2);
    panelPitch.add(E);
    panelPitch.add(F);
    panelPitch.add(F2);
    panelPitch.add(G);
    panelPitch.add(G2);
    panelPitch.add(A);
    panelPitch.add(A2);
    panelPitch.add(B);

    panelNorth.add(panelPitch, BorderLayout.NORTH);
  }


  public void addActionListener(ActionListener actionListener) {
    setNoteButton.addActionListener(actionListener);
    exitButton.addActionListener(actionListener);

    //Register a listener for the radio buttons.
    C.addActionListener(actionListener);
    C2.addActionListener(actionListener);
    D.addActionListener(actionListener);
    D2.addActionListener(actionListener);
    E.addActionListener(actionListener);
    F.addActionListener(actionListener);
    F2.addActionListener(actionListener);
    G.addActionListener(actionListener);
    G2.addActionListener(actionListener);
    A.addActionListener(actionListener);
    A2.addActionListener(actionListener);
    B.addActionListener(actionListener);

    //Register a listener for the radio buttons.
    oOne.addActionListener(actionListener);
    oTwo.addActionListener(actionListener);
    oThree.addActionListener(actionListener);
    oFour.addActionListener(actionListener);
    oFive.addActionListener(actionListener);
    oSix.addActionListener(actionListener);
    oSeven.addActionListener(actionListener);
    oEight.addActionListener(actionListener);
    oNine.addActionListener(actionListener);
    oTen.addActionListener(actionListener);
    oEleven.addActionListener(actionListener);
  }

  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  public void toggleColor() {
    if (this.displayVolume.getForeground().equals(Color.RED))
      this.displayVolume.setForeground(Color.BLACK);
    else
      this.displayVolume.setForeground(Color.RED);
  }

  public String getInputStart() {
    return inputStart.getText();
  }

  public String getInputDuration() {
    return inputDuration.getText();
  }

  public String getInputVolume() {
    return inputVolume.getText();
  }

  public String getInputInstrument() {
    return inputInstrument.getText();
  }

  public String getInputPitch() {
    for (Enumeration<AbstractButton> buttons = pitchButton.getElements(); buttons.hasMoreElements(); ) {
      AbstractButton button = buttons.nextElement();

      if (button.isSelected()) {
        return button.getText();
      }
    }
    return "C";
  }

  public String getInputOctave() {
    for (Enumeration<AbstractButton> buttons = octaveButton.getElements(); buttons.hasMoreElements(); ) {
      AbstractButton button = buttons.nextElement();

      if (button.isSelected()) {
        return button.getText();
      }
    }
    return "4";
  }

  public SoundUnit.Pitch getInputPitchEnum() {
    for (Enumeration<AbstractButton> buttons = pitchButton.getElements(); buttons.hasMoreElements(); ) {
      AbstractButton button = buttons.nextElement();

      if (button.isSelected()) {
        switch (button.getText()) {
          case "C":
            return(SoundUnit.Pitch.C);
          case "C2":
            return(SoundUnit.Pitch.C2);

          case "D":
            return(SoundUnit.Pitch.D);

          case "D2":
            return(SoundUnit.Pitch.D2);

          case "E":
            return(SoundUnit.Pitch.E);

          case "F":
            return(SoundUnit.Pitch.F);

          case "F2":
            return(SoundUnit.Pitch.F2);

          case "G":
            return(SoundUnit.Pitch.G);

          case "G2":
            return(SoundUnit.Pitch.G2);

          case "A":
            return(SoundUnit.Pitch.A);

          case "A2":
            return(SoundUnit.Pitch.A2);

          case "B":
            return(SoundUnit.Pitch.B);

        }
      }
    }
    return SoundUnit.Pitch.C;
  }

  public SoundUnit.Octave getInputOctaveEnum() {
    for (Enumeration<AbstractButton> buttons = octaveButton.getElements(); buttons.hasMoreElements(); ) {
      AbstractButton button = buttons.nextElement();

      if (button.isSelected()) {
        switch (button.getText()) {
          case "1":
            return(SoundUnit.Octave.ONE);

          case "2":
            return(SoundUnit.Octave.TWO);

          case "3":
            return(SoundUnit.Octave.THREE);

          case "4":
            return(SoundUnit.Octave.FOUR);

          case "5":
            return(SoundUnit.Octave.FIVE);

          case "6":
            return(SoundUnit.Octave.SIX);

          case "7":
            return(SoundUnit.Octave.SEVEN);

          case "8":
            return(SoundUnit.Octave.EIGHT);

          case "9":
            return(SoundUnit.Octave.NINE);

          case "10":
            return(SoundUnit.Octave.TEN);

          case "11":
            return(SoundUnit.Octave.ELEVEN);

        }
      }
    }
    return SoundUnit.Octave.FOUR;
  }
}
