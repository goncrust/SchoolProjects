package pt.tecnico.uilib.swing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import pt.tecnico.uilib.menus.Menu;

/**
 * This class manages a menu window: it can be used to select an option
 * (Command) from a menu.
 */
class SwingMenu extends SwingPanel {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202010150838L;

  /** The menu option selected */
  private int _option;

  /**
   * Build a SwingPanel to display a Menu
   * 
   * @param menu the Menu to display
   */
  SwingMenu(Menu menu) {
    super(new GridLayout(menu.size() + 3, 1));

    // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    int i;
    JButton jb;
    int[] key = { //
        KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, //
        KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, //
        KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9 //
    };

    add(new JLabel(menu.title(), SwingConstants.CENTER));
    add(new JLabel(""));

    for (i = 0; i < menu.size(); i++) {
      if (menu.entry(i).isValid()) {
        add(jb = new JButton((i + 1) + " - " + menu.entry(i).title()));
        jb.addActionListener(this);
        jb.setActionCommand("" + (i + 1));
        if (i < 9)
          jb.setMnemonic(key[i]);
      } else {
        add(new JLabel((i + 1) + " - " + menu.entry(i).title(), SwingConstants.CENTER));
      }
    }
    add(jb = new JButton(Prompt.exit()));
    jb.addActionListener(this);
    jb.setActionCommand("0");
    jb.setMnemonic(KeyEvent.VK_0);
  }

  /**
   * This is called when an interaction event occurs: a menu option is selected.
   * 
   * @param event the ActionEvent to be processed
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    synchronized (_lock) {
      _option = Integer.parseInt(event.getActionCommand());
      _end = true;
      _lock.notifyAll();
    }
  }

  /** @return the select option from a menu */
  int option() {
    return _option;
  }

}
