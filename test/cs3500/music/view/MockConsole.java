package cs3500.music.view;

import cs3500.music.model.NoteList;
import cs3500.music.model.SoundUnitList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * To test the console output
 */
public class MockConsole implements View {
  private StringBuilder mockConsoleOutput;
  private SoundUnitList list;

  public MockConsole() {
    mockConsoleOutput = new StringBuilder();
    list = new NoteList();
  }

  public String getStringBuilderAsString() {
    return mockConsoleOutput.toString();
  }

  public void createSong(SoundUnitList list) {
    ConsoleViewImpl c = (ConsoleViewImpl)
            ViewCreator.create(ViewCreator.ViewType.CONSOLE, list);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);
    c.consoleRender();
    System.out.flush();
    System.setOut(old);
    mockConsoleOutput.append(baos.toString());
  }

  @Override
  public void render() {
    createSong(list);
  }
}
