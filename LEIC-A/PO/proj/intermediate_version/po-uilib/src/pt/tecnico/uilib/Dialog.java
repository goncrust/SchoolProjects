package pt.tecnico.uilib;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Menu;
import pt.tecnico.uilib.swing.SwingInteraction;
import pt.tecnico.uilib.swing.NewSwingInteraction;
import pt.tecnico.uilib.text.TextInteraction;

/**
 * Dialog with the user.
 */
public class Dialog implements AutoCloseable {

  /** Property name: user interface property name. */
  private final static String ACTION_CHANNEL = "ui";

  private final static String CHANNEL_SWING = "swing";
  private final static String CHANNEL_NEW_SWING = "newswing";
  private final static String CHANNEL_TEXT = "text";

  /** Single instance of this class. */
  public static/* final */Dialog UI = new Dialog();

  /** Interaction with subsystem (text, swing, ...). */
  private InteractionDriver _backend;

  /** Singleton constructor (private). */
  private Dialog() {
    try {
      _backend = switch (System.getProperty(ACTION_CHANNEL, CHANNEL_TEXT)) {
      case CHANNEL_SWING -> new SwingInteraction();
      case CHANNEL_NEW_SWING -> new NewSwingInteraction();
      case CHANNEL_TEXT -> new TextInteraction();
      default -> new TextInteraction();
      };
    } catch (SecurityException e) {
      System.err.println(e);
    }
  }

  /** @param backend interaction back-end. */
  public Dialog(InteractionDriver backend) {
    _backend = backend;
  }

  /** @param menu menu to open. */
  public void open(Menu menu) {
    _backend.open(menu);
  }

  /** @param form form to fill. */
  public void fill(Form form) {
    _backend.fill(form);
  }

  /**
   * @param title message title.
   * @param text  message text.
   */
  public void render(String title, String text) {
    _backend.render(title, text);
  }

  /** Close the interaction. */
  public void close() {
    _backend.close();
  }

}
