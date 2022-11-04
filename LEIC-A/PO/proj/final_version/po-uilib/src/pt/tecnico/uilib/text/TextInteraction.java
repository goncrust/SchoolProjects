package pt.tecnico.uilib.text;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import pt.tecnico.uilib.InteractionDriver;
import pt.tecnico.uilib.forms.Field;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.menus.Menu;

/**
 * Text interaction back-end (either through the keyboard or files).
 */
public class TextInteraction implements InteractionDriver {

  /** Input channel. */
  private BufferedReader _in = new BufferedReader(new InputStreamReader(System.in));;

  /** Output channel. */
  private PrintStream _out = System.out;

  /** Log channel. */
  private PrintStream _log = null;

  /** Copy input to output? */
  private boolean _writeInput;

  /**
   * Constructor (package).
   */
  public TextInteraction() {
    String filename;

    filename = System.getProperty(Property.OUTPUT_CHANNEL);
    if (filename != null) {
      try {
        PrintStream pr = new PrintStream(new FileOutputStream(filename));
        if (Boolean.getBoolean(Property.BOTH_CHANNELS)) {
          _out = new CompositePrintStream(pr, System.out);
        } else {
          _out = pr;
        }
      } catch (FileNotFoundException e) {
        _out.println(Message.outputError(e));
      }
    }

    filename = System.getProperty(Property.INPUT_CHANNEL);
    if (filename != null) {
      try {
        _in = new BufferedReader(new FileReader(filename));
      } catch (FileNotFoundException e) {
        _out.println(Message.inputError(e));
      }
    }

    filename = System.getProperty(Property.LOG_CHANNEL);
    if (filename != null) {
      try {
        _log = new PrintStream(new FileOutputStream(filename));
      } catch (FileNotFoundException e) {
        _out.println(Message.logError(e));
      }
    }

    _writeInput = Boolean.getBoolean(Property.WRITE_INPUT);
  }

  /** @see pt.tecnico.uilib.InteractionDriver#close() */
  @Override
  public void close() {
    if (_out != System.out)
      _out.close();

    try {
      String filename = System.getProperty(Property.INPUT_CHANNEL);
      if (filename != null)
        _in.close();
    } catch (IOException e) {
      _out.println(Message.errorClosingInput(e));
    }

    if (_log != null)
      _log.close();
  }

  /** @see pt.tecnico.uilib.InteractionDriver#open(pt.tecnico.uilib.menus.Menu) */
  @Override
  public void open(Menu menu) {
    int option = 0, i;

    while (true) {
      _out.println(menu.title());
      for (i = 0; i < menu.size(); i++)
        if (menu.entry(i).isValid())
          _out.println((i + 1) + " - " + menu.entry(i).title()); //$NON-NLS-1$
      _out.println(Prompt.exit());

      try {
        option = readInteger(Prompt.option());
        if (option == 0)
          return;

        if (option < 0 || option > i || !menu.entry(option - 1).isValid()) {
          _out.println(Message.invalidOption());
        } else {
          menu.entry(option - 1).performCommand();
          if (menu.entry(option - 1).isLast())
            return;
        }
      } catch (CommandException e) {
        _out.println(menu.entry(option - 1).title() + ": " + e);
      } catch (EOFException e) {
        _out.println(Message.errorEOF(e));
        return;
      } catch (IOException e) {
        _out.println(Message.errorIO(e));
      } catch (NumberFormatException e) {
        _out.println(Message.errorInvalidNumber(e));
      } catch (RuntimeEOFException e) {
        _out.println(Message.errorREOF(e));
        return;
      }
    }
  }

  /** @see pt.tecnico.uilib.InteractionDriver#fill(pt.tecnico.uilib.forms.Form) */
  @Override
  public void fill(Form form) {
    try {
      for (Field<?> in : form.entries()) {
        if (!in.isReadOnly()) {
          while (!in.parse(readString(in.prompt())))
            ;
        } else {
          _out.println(in.prompt());
        }
      }
    } catch (EOFException e) {
      _out.println(Message.errorEOF(e));
    } catch (IOException e) {
      _out.println(Message.errorIO(e));
    } catch (NumberFormatException e) {
      _out.println(Message.errorInvalidNumber(e));
    } catch (RuntimeEOFException e) {
      _out.println(Message.errorREOF(e));
    }
  }

  /** @see pt.tecnico.uilib.InteractionDriver#render(String, String) */
  @Override
  public void render(String title, String text) {
    if (text.length() > 0)
      _out.println(text);
  }

  /**
   * @param prompt a prompt (may be null)
   * @return the string read from the input.
   * @throws IOException in case of read errors
   */
  private final String readString(String prompt) throws IOException {
    if (prompt != null)
      _out.print(prompt);

    String str = _in.readLine();
    if (str == null)
      throw new RuntimeEOFException(Message.endOfInput());

    if (_log != null)
      _log.println(str);

    if (_writeInput)
      _out.println(str);

    return str;
  }

  /**
   * Read an integer number from the input.
   * 
   * @param prompt a prompt (may be null)
   * @return the number read from the input.
   * @throws IOException in case of read errors
   */
  private final int readInteger(String prompt) throws IOException {
    while (true) {
      try {
        return Integer.parseInt(readString(prompt));
      } catch (NumberFormatException e) {
        _out.println(Message.errorInvalidNumber(e));
      }
    }
  }

}
