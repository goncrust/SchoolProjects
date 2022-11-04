package pt.tecnico.uilib.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.menus.Menu;

/**
 * This class manages a menu window: it can be used to select an option
 * (Command) from a menu. The window menu has two parts: One contains the menu buttons
 * associated with the menu. The other part is used to print the results of the selected
 * command. There may be a bug if a command writes a result and it has the last command property.
 * In this case, the window with the written result is removed too fast.
 */
class SwingDialogMenu extends JDialog implements ActionListener{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202010150838L;

  /** The menu option selected */
  private int _option;

  // The menu
  private Menu _menu;

  // The output pane
  private JTextArea _out;
  private JLabel _outputTitleLabel;

  // The menu buttons
  private JButton[] _buttonMenu;

  // The selcted button
  private JButton _selectedButton;

  /**
   * Build a SwingPanel to display a Menu
   * 
   * @param menu the Menu to display
   */
  SwingDialogMenu(Menu menu, Frame frame, Component locationComp) {
    super(frame, true);

    _menu = menu;

    setTitle(menu.title());

    //Provide minimum sizes for the two components in the split pane.
    //Dimension minimumSize = new Dimension(500, 500);
    //menuPanel.setMinimumSize(minimumSize);
    //outputPanel.setMinimumSize(minimumSize);

    JPanel contentFrame = new JPanel(new FlowLayout());
    contentFrame.add(createMenuPanel());
    contentFrame.add(createOutputPane());

    //Make this dialog display it.
    setContentPane(contentFrame);

    //Handle window closing correctly.
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    pack();
    if (locationComp != null)
      setLocationRelativeTo(locationComp);
  }

  // build the output pane
  private JPanel createOutputPane() {
    JPanel outputPane = new JPanel(new BorderLayout());

    _outputTitleLabel = new JLabel("  ", SwingConstants.CENTER);
    _outputTitleLabel.setPreferredSize(new Dimension(40, 20));

    outputPane.add(_outputTitleLabel, BorderLayout.PAGE_START);

    _out = new JTextArea(20, 70);
    _out.setEditable(false);
    
    JScrollPane scrollPane = new JScrollPane(_out);

    outputPane.add(scrollPane, BorderLayout.CENTER);
    return outputPane;
  }

  // Renders the selected output. Title refers to the title of the command responsible for the output.
  public void updateOutput(String title, String output) {
    _outputTitleLabel.setText(title);
    _out.setText(output);
  }

  private JPanel createMenuPanel() {
    JPanel panel = new JPanel(new GridLayout(_menu.size() + 2, 1));
    JButton jb;
    int[] key = { //
        KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, //
        KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, //
        KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9 //
    };

    //panel.add(new JLabel(_menu.title(), SwingConstants.CENTER));
    panel.add(new JLabel(""));

    _buttonMenu = new JButton[_menu.size()];

    for (int i = 0; i < _menu.size(); i++) {
      panel.add(jb = new JButton((i + 1) + " - " + _menu.entry(i).title()));
      jb.setHorizontalAlignment(SwingConstants.LEFT);
      _buttonMenu[i] = jb;
      jb.addActionListener(this);
      jb.setActionCommand("" + (i + 1));
      if (i < 9)
        jb.setMnemonic(key[i]);
      if (!_menu.entry(i).isValid())
        jb.setEnabled(false);
    }
    panel.add(jb = new JButton(Prompt.exit()));
    jb.addActionListener(this);
    jb.setActionCommand("0");
    jb.setMnemonic(KeyEvent.VK_0);

    panel.setBorder(BorderFactory.createTitledBorder(_menu.title()));
    return panel;
  }

  /**
   * This is called when an interaction event occurs: a menu option is selected.
   * 
   * @param event the ActionEvent to be processed
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    int option = Integer.parseInt(event.getActionCommand());
    if (option != 0)
      _selectedButton = _buttonMenu[option -1];
    else
      _selectedButton = null;
    executeOption(option);
    updateValidButtons();
  }

  private void updateValidButtons() {
    for (int i = 0; i < _menu.size(); i++) {
      _buttonMenu[i].setEnabled(_menu.entry(i).isValid());
    }
  }

  JButton getSelectedButton() {
    return _selectedButton;
  }

  private void executeOption(int option) {
    if (option != 0) {
      try {
        _menu.entry(option - 1).performCommand();
      } catch (CommandException e) {
        //updateOutput(_menu.title(), _menu.entry(option - 1).title() + ": " + e); //$NON-NLS-1$
        showErrorMessage(_menu.title(), _menu.entry(option - 1).title() + ": " + e); //$NON-NLS-1$
      }
    }
      
    if (option == 0 || _menu.entry(option - 1).isLast()) {
      clearAndHide();
    } else
      setVisible(true);

  }

  /** @return the select option from a menu */
  int option() {
    return _option;
  }

  /** This method clears the dialog and hides it. */
  private void clearAndHide() {
    setVisible(false);
  }

  private void showErrorMessage(String title, String msg) {
    Frame parentFrame = (Frame) getOwner();
    JOptionPane.showMessageDialog(parentFrame,
                                  msg,
                                  title,
                                  JOptionPane.ERROR_MESSAGE);
  }
}
