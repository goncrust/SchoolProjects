package pt.tecnico.uilib.swing;

import javax.swing.JFrame;

import pt.tecnico.uilib.InteractionDriver;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.menus.Menu;

/**
 * Swing interaction back-end.
 */
public class SwingInteraction extends JFrame implements InteractionDriver {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202010150838L;

  /** Application title (for window titles). */
  private static final String MAIN_TITLE = "Programação com Objectos";

  /** Constructor. */
  public SwingInteraction() {
    super(MAIN_TITLE);
    setDefaultLookAndFeelDecorated(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);
    setVisible(true);
  }

  /** @see pt.tecnico.uilib.InteractionDriver#open(pt.tecnico.uilib.menus.Menu) */
  @Override
  public void open(Menu menu) {
    int option;
    
    do {
      SwingMenu panel = new SwingMenu(menu);
      add(panel);
      pack();
      panel.await();
      option = panel.option();
      remove(panel);
      
      if (option == 0)
        break;
      
      try {
        menu.entry(option - 1).performCommand();
      } catch (CommandException e) {
        message(menu.title(), menu.entry(option - 1).title() + ": " + e); //$NON-NLS-1$
      }
    } while (!menu.entry(option - 1).isLast());
  }

  /** @see pt.tecnico.uilib.InteractionDriver#fill(pt.tecnico.uilib.forms.Form) */
  @Override
  public void fill(Form form) {
    SwingForm panel = new SwingForm(form);
    add(panel);
    pack();
    do {
      panel.await();
    } while (!panel.parse());
    remove(panel);
  }

  /** @see pt.tecnico.uilib.InteractionDriver#render(String, String) */
  @Override
  public void render(String title, String text) {
    message(title, text);
  }

  /** @see pt.tecnico.uilib.InteractionDriver#close() */
  @Override
  public void close() {
    dispose();
  }

  /**
   * Display a Message.
   * 
   * @param msg
   * @param title
   */
  private void message(String title, String msg) {
    SwingMessage panel = new SwingMessage(title, msg);
    add(panel);
    pack();
    panel.await();
    remove(panel);
  }

}
