package cs3500.music.view;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.*;

<<<<<<< HEAD
import cs3500.music.controller.KeyBoardHandler;
import cs3500.music.model.IPlayerModel;
=======
import cs3500.music.model2.IPlayerModel;
>>>>>>> d5b4df49ca8d85a5dc57316cd5138ae8e63c6dd4

/**
 * A skeleton Frame (i.e., a window) in Swing
 */


public class GuiViewFrame extends javax.swing.JFrame implements IGuiView {//} implements IView {

  private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel
  private IPlayerModel inputModel;
  private JScrollPane scrollable;

  /**
   * Constructor which creates new GuiView with the model passed in
   */

  public GuiViewFrame(IPlayerModel theModeli) {
    inputModel = theModeli;
    this.displayPanel = new ConcreteGuiViewPanel(theModeli);
    scrollable = new JScrollPane(displayPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollable.setViewportView(displayPanel);
    this.add(scrollable);//.setBounds(10,30,150,250);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.pack();
    initialize();
  }


  /**
   * sets the visibility of the GUI to true
   */

  public void initialize() {
    this.setVisible(true);
  }


  /**
   * creates the Dimension for a GUI
   *
   * @return Dimension of the GUI
   */

  @Override
  public Dimension getPreferredSize() { // this is for the size of the frame

    List<String> allPitches = inputModel.outputPitchesOctaves();
    return new Dimension(1000,300);

  }


  /**
   * Outputs the GUI
   */

  public void outputView() {
    this.revalidate();
    this.repaint();
  }


  @Override
  public void updateScroll(String xory, int value) {
    if (xory.compareTo("x") == 0){
      this.scrollable.getHorizontalScrollBar().setValue
              (this.scrollable.getHorizontalScrollBar().getValue()+value);
    }
    if (xory.compareTo("y") == 0){
      this.scrollable.getVerticalScrollBar().setValue
              (this.scrollable.getVerticalScrollBar().getValue()+value);
    }
  }

  @Override
  public JPanel getPanel() {
    return this.displayPanel;
  }

  @Override
  public IPlayerModel getModel() {
    return this.inputModel;
  }

  @Override
  public JScrollPane getScroller() {
    return this.scrollable;
  }

  @Override
  public void addMouseListener(MouseListener ml) {
    this.displayPanel.addMouseListener(ml);
  }

}
