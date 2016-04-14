package cs3500.music.view2;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.model2.IPlayerModel;

/**
 * Represents the View created by combining the GuiVIew and the Midi
 */

public class CombinedView implements IGuiView{

  private IGuiView yourView;
  private MidiViewImpl yourMidi;

  public CombinedView (IGuiView yourViewi, MidiViewImpl yourMidii){
    this.yourView = yourViewi;
    this.yourMidi = yourMidii;

  }

  /**
   * Adds a KeyListener
   */

  @Override
  public void addKeyListener(KeyListener KeyListeneri) {
    this.yourView.addKeyListener(KeyListeneri);
  }


  /**
   * adds a MouseListener
   */

  @Override
  public void addMouseListener(MouseListener MouseListeneri) {
    this.yourView.getPanel().addMouseListener(MouseListeneri);
  }


  /**
   * gets the scroller of this
   */

  @Override
  public JScrollPane getScroller() {
    return this.yourView.getScroller();
  }


  /**
   * Updates the scroller
   */

  @Override
  public void updateScroll(String xory, int value) {
    if (xory.compareTo("x") == 0){
      this.getScroller().getHorizontalScrollBar().setValue
              (this.getScroller().getHorizontalScrollBar().getValue() + value);
    }
    if (xory.compareTo("y") == 0){
      this.getScroller().getVerticalScrollBar().setValue
              (this.getScroller().getVerticalScrollBar().getValue()+value);
    }
  }


  /**
   * gets the panel
   */

  @Override
  public JPanel getPanel() {
    return this.yourView.getPanel();
  }


  /**
   * gets the model
   */

  @Override
  public IPlayerModel getModel() {
    return this.yourView.getModel();
  }


  /**
   * outputs both the midi and the view
   */

  @Override
  public void outputView() {
    yourView.outputView();
    yourMidi.outputView();

  }
}
