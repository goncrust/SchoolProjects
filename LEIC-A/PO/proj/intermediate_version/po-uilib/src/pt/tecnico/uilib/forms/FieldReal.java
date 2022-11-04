package pt.tecnico.uilib.forms;

/** Request a real number in a Form */
class FieldReal extends Field<Double> {

  /** @param prompt the description used in the request */
  FieldReal(String prompt) {
    super(prompt);
  }

  /** @see pt.tecnico.uilib.forms.Field#parse(java.lang.String) */
  @Override
  public boolean parse(String in) {
    try {
      set(Double.parseDouble(in));
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
