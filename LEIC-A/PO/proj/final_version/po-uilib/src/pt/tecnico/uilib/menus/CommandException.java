package pt.tecnico.uilib.menus;

/**
 * This class represents events in unsuccessful command executions.
 */
public abstract class CommandException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202010150838L;

  /** @param message */
  public CommandException(String message) {
    super(message);
  }

  /**
   * @param message
   * @param throwable
   */
  public CommandException(String message, Throwable throwable) {
    super(message, throwable);
  }

  /** @see java.lang.Throwable#toString() */
  @Override
  public final String toString() {
    return Message.operationFailed(getMessage());
  }

}