package pt.tecnico.uilib.forms;

/** Request a boolean within a Form */
class FieldBoolean extends Field<Boolean> {

  /** How do you say "yes"? */
  private static final String BOOLEAN_WORD_YES = "sim";

  /** How do you say "yes"? */
  private static final String BOOLEAN_WORD_NO = "nao";

  /** How do you say "yes"? */
  private static final char BOOLEAN_CHAR_YES = 's';

  /** How do you say "yes"? */
  private static final char BOOLEAN_CHAR_NO = 'n';

  /** @param prompt the description used in the request */
  FieldBoolean(String prompt) {
    super(prompt);
  }

  /** @see pt.tecnico.uilib.forms.Field#parse(java.lang.String) */
  @Override
  public boolean parse(String in) {
    if (in.length() == 1 && (in.charAt(0) == BOOLEAN_CHAR_YES || in.charAt(0) == BOOLEAN_CHAR_NO)) {
      _value = in.charAt(0) == BOOLEAN_CHAR_YES;
      dirty();
      return true;
    }
    return false;
  }

  /** @see pt.tecnico.uilib.forms.Field#toString() */
  @Override
  public String toString() {
    return value() ? BOOLEAN_WORD_YES : BOOLEAN_WORD_NO;
  }

}
