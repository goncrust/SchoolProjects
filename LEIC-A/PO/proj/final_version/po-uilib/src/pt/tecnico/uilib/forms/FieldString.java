package pt.tecnico.uilib.forms;

/** Request a String within a Form */
class FieldString extends Field<String> {

  /** @param prompt the description used in the request */
  FieldString(String prompt) {
    super(prompt);
  }

  /** @see pt.tecnico.uilib.forms.Field#parse(java.lang.String) */
  @Override
  public boolean parse(String in) {
    set(in);
    return true;
  }

}
