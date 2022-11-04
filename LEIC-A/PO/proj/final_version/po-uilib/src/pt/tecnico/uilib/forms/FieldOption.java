package pt.tecnico.uilib.forms;

/** Request a string of a list of valid strings within a Form */
class FieldOption extends Field<String> {
  private String[] _options;
  
  /** @param prompt the description used in the request */
  FieldOption(String prompt, String[] options) {
    super(prompt);
    _options = options;
  }

  /** @see pt.tecnico.uilib.forms.Field#parse(java.lang.String) */
  @Override
  public boolean parse(String in) {
    for(String option : _options) {
      if (option.equals(in)) {
        set(in);
        return true;
      }
    }

    return false;
  }

}
