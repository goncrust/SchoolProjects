package pt.tecnico.uilib.menus;

import static pt.tecnico.uilib.Dialog.UI;

import pt.tecnico.uilib.Dialog;

/** Class Menu manages a list of commands. */
public class Menu {

  /** The graphical driver used to interact with the user */
  private Dialog _ui;

  /** Menu title. */
  private String _title;

  /** Commands available from the menu. */
  private Command<?>[] _commands;

  /**
   * @param ui       interaction for the menu.
   * @param title    menu title.
   * @param commands list of commands managed by the menu.
   */
  public Menu(Dialog ui, String title, Command<?>... commands) {
    _ui = ui;
    _title = title;
    _commands = commands;
  }

  /**
   * @param title    menu title.
   * @param commands list of commands managed by the menu.
   */
  public Menu(String title, Command<?>... commands) {
    this(UI, title, commands);
  }

  /**
   * @return the menu title.
   */
  public String title() {
    return _title;
  }

  /**
   * @return the number of commands.
   */
  public int size() {
    return _commands.length;
  }

  /**
   * @param n the command number (not the index) in the menu
   * @return the n-th command.
   */
  public Command<?> entry(int n) {
    return _commands[n];
  }

  /**
   * @return the commands (package).
   */
  public Command<?>[] entries() {
    return _commands;
  }

  /**
   * The menu interacts with the user and executes the appropriate commands.
   */
  public void open() {
    _ui.open(this);
  }

}
