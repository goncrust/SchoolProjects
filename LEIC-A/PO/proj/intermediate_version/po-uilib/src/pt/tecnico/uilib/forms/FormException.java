package pt.tecnico.uilib.forms;

/**
 * A class for describing form-related problems.
 */
class FormException extends RuntimeException {

  /** Serial number for serialization. */
  static final long serialVersionUID = 202010272136L;

  /** @param msg message describing the exception. */
  public FormException(String msg) {
    super(msg);
  }

}