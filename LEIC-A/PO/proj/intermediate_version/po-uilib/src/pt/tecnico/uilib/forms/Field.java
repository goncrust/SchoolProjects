package pt.tecnico.uilib.forms;

/**
 * This class represents an input field in a form.
 * 
 * @param <Type> is the type of the data to be read in the field.
 */
public abstract class Field<Type> {

  /** the prompt used in the request */
  private String _prompt;

  /** a new value was read */
  private boolean _clear;

  /** value to be read */
  protected Type _value;

  /**
   * Build an Input without a title
   */
  protected Field() {
    this(null);
  }

  /**
   * @param prompt the prompt for the requested value
   * @param regex  the regular expression to be matched
   */
  protected Field(String prompt) {
    _prompt = prompt;
  }

  /** @return the description of the request */
  public String prompt() {
    return _prompt;
  }

  /** @param value the new value */
  public void set(Type value) {
    _value = value;
    dirty();
  }

  /** @return the value */
  public Type value() {
    return _value;
  }

  /** Mark the Input as unread */
  public void clear() {
    _clear = true;
  }

  /** Mark the Input as read */
  protected void dirty() {
    _clear = false;
  }

  /** @return true if the Input is cleared */
  public boolean cleared() {
    return _clear;
  }

  /** Is the input field read-only. */
  public boolean isReadOnly() {
    return false;
  }
  
  /**
   * Each Input must parse its own specific type of value, written in accordance
   * with the specific regular expression.
   * 
   * @param in the string to be parsed
   * @return the status of parsing success
   */
  public abstract boolean parse(String in);

  /** @see java.lang.Object#toString() */
  @Override
  public String toString() {
    return "" + _value;
  }

}
