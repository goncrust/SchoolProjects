package pt.tecnico.uilib.swing;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JDialog;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.forms.Field;

/**
 * Form panel: it can be used to fill the input values of a form.
 */
class SwingDialogForm extends JDialog implements ActionListener {

  private final Border RED_BORDER = new LineBorder(Color.RED, 1);
  private final Border NO_BORDER = new LineBorder(Color.GRAY, 1);

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202010150838L;

  /** Association between user Input instances and Applet's JTextField values. */
  private Map<Field<?>, JTextField> _inputs;

  /** @param form the Form to display in the dialog. */
  SwingDialogForm(Form form, Frame frame) {
    super(frame, true);

    JPanel formPanel = createFormPanel(form);
    //Make this dialog display it.
    setContentPane(formPanel);
    pack();
    //Handle window closing correctly.
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
  }

  private JPanel createFormPanel(Form form) {
    GridLayout layout = new GridLayout(form.entries().size() + 1, 2);
    layout.setVgap(5);
    JPanel panel = new JPanel(layout);

    _inputs = new HashMap<Field<?>, JTextField>();
    boolean first = true;

    if (form.title() != null) {
      //panel.add(new JLabel(""));
      //panel.add(new JLabel(form.title()));
      setTitle(form.title());
    }

    for (Field<?> in : form.entries()) {
      JLabel l;
      panel.add(l = new JLabel(in.prompt(), SwingConstants.TRAILING));
      if (!in.isReadOnly()) {
        JTextField textField = in.cleared() ? new JTextField(10) : new JTextField(in.toString(), 10);
	textField.setBorder(NO_BORDER);
        panel.add(textField);
        l.setLabelFor(textField);
        _inputs.put(in, textField);
        if (first) {
          first = false;
          textField.setFocusAccelerator('1');
        }
      } else {
        panel.add(new JLabel(""));
      }
    }
    panel.add(new JLabel(""));

    JButton button = new JButton("OK");
    panel.add(button);
    button.addActionListener(this);
    button.setActionCommand("0");
    button.setMnemonic(KeyEvent.VK_ENTER);

    return panel;
  }

  /**
   * This is called when an interaction event occurs.
   * 
   * @param event the ActionEvent to be processed
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    if (parse())
      setVisible(false);  // leave this dialog
  }

  /**
   * Copy user input values to the corresponding input values.
   * 
   * @return parsing success condition
   */
  boolean parse() {
    boolean ret = true;
    for (Field<?> input : _inputs.keySet()) {
      if (!input.parse(_inputs.get(input).getText())) {
        _inputs.get(input).setText("");
        //_inputs.get(input).setBorder(new LineBorder(Color.RED, 2));
        _inputs.get(input).setBorder(RED_BORDER);
        ret = false;
      } else
	_inputs.get(input).setBorder(NO_BORDER);
    }
    return ret;
  }

}
