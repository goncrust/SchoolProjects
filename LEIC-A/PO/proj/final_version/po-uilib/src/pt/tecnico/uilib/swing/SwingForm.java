package pt.tecnico.uilib.swing;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.forms.Field;

/**
 * Form panel: it can be used to fill the input values of a form.
 */
class SwingForm extends SwingPanel {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202010150838L;

  /** Association between user Input instances and Applet's JTextField values. */
  private Map<Field<?>, JTextField> _inputs;

  /** @param form the Form to display in the panel. */
  SwingForm(Form form) {
    super(new GridLayout(form.entries().size() + 2, 2));

    _inputs = new HashMap<Field<?>, JTextField>();
    boolean first = true;

    if (form.title() != null) {
      add(new JLabel(""));
      add(new JLabel(form.title()));
    }

    for (Field<?> in : form.entries()) {
      JLabel l;
      add(l = new JLabel(in.prompt(), SwingConstants.TRAILING));
      if (!in.isReadOnly()) {
        JTextField textField = in.cleared() ? new JTextField(10) : new JTextField(in.toString(), 10);
        add(textField);
        l.setLabelFor(textField);
        _inputs.put(in, textField);
        if (first) {
          first = false;
          textField.setFocusAccelerator('1');
        }
      } else {
        add(new JLabel(""));
      }
    }
    add(new JLabel(""));

    JButton button = new JButton("OK");
    add(button);
    button.addActionListener(this);
    button.setActionCommand("0");
    button.setMnemonic(KeyEvent.VK_ENTER);
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
        ret = false;
      }
    }
    return ret;
  }

}
