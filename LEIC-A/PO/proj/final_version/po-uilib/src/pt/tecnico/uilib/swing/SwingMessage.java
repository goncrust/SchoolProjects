package pt.tecnico.uilib.swing;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * This class manages a text window: it can be used to display a text message.
 */
class SwingMessage extends SwingPanel {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202010150838L;

  /**
   * Build a panel to display a message.
   * 
   * @param title the panel title
   * @param msg   the message to display
   */
  SwingMessage(String title, String msg) {
    super(new BorderLayout());

    if (title != null)
      add(new JLabel(title, SwingConstants.CENTER));

    JTextArea textArea = new JTextArea(5, 20);
    textArea.setEditable(false);
    textArea.append(msg);

    JScrollPane scrollPane = new JScrollPane(textArea);
    add(scrollPane, BorderLayout.CENTER);

    JButton button = new JButton("OK");
    add(button, BorderLayout.SOUTH);
    button.addActionListener(this);
    button.setActionCommand("0");
    button.setMnemonic(KeyEvent.VK_ENTER);
  }

}
