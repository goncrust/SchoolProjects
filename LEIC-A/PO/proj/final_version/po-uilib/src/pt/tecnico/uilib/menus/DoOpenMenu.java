package pt.tecnico.uilib.menus;

/**
 * Command to open a menu.
 */
public class DoOpenMenu extends Command<Menu> {

  /**
   * @param receiver
   */
  public DoOpenMenu(String label, Menu receiver) {
    super(label, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  protected final void execute() {
    _receiver.open();
  }

}