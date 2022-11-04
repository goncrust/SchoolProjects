package pt.tecnico.uilib;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Menu;

/**
 * Generic interaction driver (back-end).
 */
public interface InteractionDriver {

  /** @param menu menu to open. */
  void open(Menu menu);

  /** @param form form to be filled. */
  void fill(Form form);

  /**
   * @param title message title.
   * @param text  message text.
   */
  void render(String title, String text);

  /** Close all I/O channels. */
  void close();

}
