package pt.tecnico.uilib.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import pt.tecnico.uilib.InteractionDriver;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Menu;

/**
 * New Swing interaction back-end. Based on JDialog. Each menu is presented on
 * its own JDialog.
 */
public class NewSwingInteraction extends JFrame implements InteractionDriver {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202010150838L;

  /** Application title (for window titles). */
  private static final String MAIN_TITLE = "Programação com Objectos";

  /** Current menu */
  private SwingDialogMenu _currentDialogMenu = null;

  /** Constructor. */
  public NewSwingInteraction() {
    super(MAIN_TITLE);
    setDefaultLookAndFeelDecorated(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);
    setVisible(true);

    // change the look and feel of this application to Metal
    try {
      UIManager.setLookAndFeel(new MetalLookAndFeel());
    } catch (javax.swing.UnsupportedLookAndFeelException e) {
      System.err.println("Swing Metal Look & Feel not supported");
    }
  }

  /** @see pt.tecnico.uilib.InteractionDriver#open(pt.tecnico.uilib.menus.Menu) */
  @Override
  public void open(Menu menu) {
    JButton selectedButton = _currentDialogMenu != null ? _currentDialogMenu.getSelectedButton() : null;
    SwingDialogMenu dialogMenu = new SwingDialogMenu(menu, this, selectedButton);
    SwingDialogMenu previousMenu = _currentDialogMenu;
    _currentDialogMenu = dialogMenu;
    dialogMenu.pack();
    dialogMenu.setVisible(true);
    _currentDialogMenu = previousMenu;
  }

  /** @see pt.tecnico.uilib.InteractionDriver#fill(pt.tecnico.uilib.forms.Form) */
  @Override
  public void fill(Form form) {
    SwingDialogForm formDialog = new SwingDialogForm(form, this);
    formDialog.setVisible(true);
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
    // SwingMessage panel = new SwingMessage(title, msg);
    _currentDialogMenu.updateOutput(title, msg);
  }

}
