package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.swing.*;

/**
 * Created by Justin Hynes-Bruell on 4/25/2016.
 */
public class RepeatAdderView extends JFrame {
  private JPanel panelSouth, panelNorth, panelCenter;
  private JPanel panelType;

  ButtonGroup typeButton;

  private Container contents;

  private FlowLayout flowLayout = new FlowLayout();

  private JLabel displayLocation, displayAltEnding;
  private JLabel displayPitch, displayOctave;
  private JButton setRepeatButton, exitButton;
  private JTextField inputLocation, inputAltEnding;


  private JRadioButton backward = new JRadioButton("Backward");
  private JRadioButton forward = new JRadioButton("Forward");

  public RepeatAdderView() {
    super("Repeat Adder");
    setSize(500, 500);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setTitle("Repeat Adder");

    contents = getContentPane();

    this.setLayout(new BorderLayout());

    //North region
    panelNorth = new JPanel();
    panelNorth.setLayout(new BorderLayout());
    setTypeRadio();

    //Center Region
    panelCenter = new JPanel();
    panelCenter.setLayout(new GridLayout(4, 2));

    displayLocation = new JLabel("Location");
    panelCenter.add(displayLocation);
    inputLocation = new JTextField(10);
    panelCenter.add(inputLocation);

    displayAltEnding = new JLabel("Alt Ending Length");
    panelCenter.add(displayAltEnding);
    inputAltEnding = new JTextField(10);
    panelCenter.add(inputAltEnding);


    //South Region
    panelSouth = new JPanel();
    panelSouth.setLayout(new GridLayout(4, 2));
    //setNoteButton
    setRepeatButton = new JButton("Set Repeat");
    setRepeatButton.setActionCommand("Set Repeat Button");
    panelSouth.add(setRepeatButton);

    //exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    panelSouth.add(exitButton);


    this.add(panelNorth, BorderLayout.NORTH);
    this.add(panelCenter, BorderLayout.CENTER);
    this.add(panelSouth, BorderLayout.SOUTH);

    pack();
    setVisible(true);
  }
  public void addActionListener(ActionListener actionListener) {
    setRepeatButton.addActionListener(actionListener);
    exitButton.addActionListener(actionListener);
  }

  private void setTypeRadio() {
    panelType = new JPanel();

    //Create the radio buttons.
    backward.setMnemonic(KeyEvent.VK_B);
    backward.setActionCommand("Backward");

    forward.setMnemonic(KeyEvent.VK_B);
    forward.setActionCommand("Forward");

    forward.setSelected(true);

    //Group the radio buttons.
    ButtonGroup group = new ButtonGroup();
    group.add(backward);
    group.add(forward);

    typeButton = group;

    panelType.add(backward);
    panelType.add(forward);

    panelNorth.add(panelType, BorderLayout.SOUTH);

  }

  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  public String getInputType() {
    for (Enumeration<AbstractButton> buttons = typeButton.getElements();
         buttons.hasMoreElements(); ) {
      AbstractButton button = buttons.nextElement();

      if (button.isSelected()) {
        return button.getText();
      }
    }
    return "0";
  }

  public String getInputLocation() {
    return inputLocation.getText();
  }

  public String getInputAltEnding(){
    return inputAltEnding.getText();
  }
}
