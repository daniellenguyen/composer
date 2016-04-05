package cs3500.music.view;

import cs3500.music.model.NoteList;
import cs3500.music.model.SoundUnitList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by daniellenguyen on 4/4/16.
 */
public class MockConsole {
  StringBuilder mockConsoleOutput;
  SoundUnitList list;

  public MockConsole() {
    mockConsoleOutput = new StringBuilder();
    list = new NoteList();
  }

  public void setStringBuilder(StringBuilder givenBuilder) {
    mockConsoleOutput = givenBuilder;
  }

  public StringBuilder getStringBuilder() {
    return mockConsoleOutput;
  }

  public void createSong(SoundUnitList list) {
    ConsoleViewImpl c = (ConsoleViewImpl)
            ViewCreator.create(ViewCreator.ViewType.CONSOLE, list);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);
    c.consoleRender(list);
    System.out.flush();
    System.setOut(old);
    mockConsoleOutput.append(baos.toString());
  }

  public void outputConsole() {
    System.out.println(mockConsoleOutput.toString());
  }

}
