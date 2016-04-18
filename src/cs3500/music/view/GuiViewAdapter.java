package cs3500.music.view;

import com.sun.istack.internal.Nullable;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.SoundUnitList;
import cs3500.music.model.SoundUnitListToIPlayerModelAdapter;

/**
 * Created by Justin Hynes-Bruell on 4/17/2016.
 */
public class GuiViewAdapter extends cs3500.music.view.GuiViewFrame {

  cs3500.music.view2.GuiViewFrame GuiView;
  /**
   * Creates new GuiView
   */
  public GuiViewAdapter(SoundUnitList soundUnitList) {
    super(soundUnitList);
  }

  public void setGuiView(cs3500.music.view2.GuiViewFrame inputGuiView){
    GuiView = inputGuiView;
  }

  public void addActionListener(ActionListener newActionListener){

  }

  public void resetFocus(){

  }

  public void addKeyListener(@Nullable java.awt.event.KeyListener keyHandler){

  }

  public void initialize(){

  }

  public void setVisible(boolean vis){

  }

  public void addNewMouseListener(MouseListener newMouseListener){

  }

  @Override
  public void render() {

  }
}
