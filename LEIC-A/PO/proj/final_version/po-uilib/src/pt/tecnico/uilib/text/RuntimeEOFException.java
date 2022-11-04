package pt.tecnico.uilib.text;

/**
 * Class RuntimeEOFException
 */
class RuntimeEOFException extends RuntimeException {

  /** Serial number for serialization. */
  static final long serialVersionUID = 200610291655L;

  /** Default constructor. */
  RuntimeEOFException() {
    // intentionally left empty
  }

  /**
   * @param msg message describing the exception.
   */
  RuntimeEOFException(String msg) {
    super(msg);
  }

}