package pt.tecnico.uilib.text;

/** 
 * Interaction messages. 
 */
interface Prompt {

  /** @return prompt prompt text. */
  static String option() {
    return "Escolha uma opção: ";
  }

  /**
   * Exit option for all menus.
   * 
   * @return message message text.
   */
  static String exit() {
    return "0 - Sair";
  }

}
