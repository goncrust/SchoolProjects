package pt.tecnico.uilib.forms;

/** Request no value (used as a separator within a form). */
public class FieldNone extends Field<Void> {

  /** @param prompt the description used in the separator */
  FieldNone(String prompt) {
    super(prompt);
  }

  /** @see pt.tecnico.uilib.forms.Field#parse(java.lang.String) */
  @Override
  public boolean parse(String in) {
    return true;
  }

  /** Is the input field read-only. */
  public boolean isReadOnly() {
    return true;
  }
  
}
