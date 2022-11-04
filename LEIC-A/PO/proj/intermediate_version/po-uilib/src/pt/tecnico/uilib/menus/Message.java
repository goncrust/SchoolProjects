package pt.tecnico.uilib.menus;

/**
 * Messages in unsuccessful command executions.
 */
interface Message {

  /**
   * @param error error message.
   * @return message text.
   */
  static String operationFailed(String error) {
    return "Operação inválida: " + error;
  }

}
