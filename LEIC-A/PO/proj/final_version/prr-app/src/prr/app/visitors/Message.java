package prr.app.visitors;

/**
 * Messages.
 */
interface Message {

  /**
   * @return tag for clients
   */
  static String client() {
    return "CLIENT";
  }

  /**
   * @return tag for active notifications
   */
  static String activeNotifications() {
    return "YES";
  }

  /**
   * @return tag for inactive notifications
   */
  static String inactiveNotifications() {
    return "NO";
  }

  /**
   * @return tag for basic terminals
   */
  static String basicTerminal() {
    return "BASIC";
  }

  /**
   * @return tag for fancy terminals
   */
  static String fancyTerminal() {
    return "FANCY";
  }

  /**
   * @return tag for ongoing communications
   */
  static String ongoingCommunication() {
    return "ONGOING";
  }

  /**
   * @return tag for ongoing communications
   */
  static String finishedCommunication() {
    return "FINISHED";
  }

  /**
   * @return tag for text communications
   */
  static String textCommunication() {
    return "TEXT";
  }

  /**
   * @return tag for voice communications
   */
  static String voiceCommunication() {
    return "VOICE";
  }

  /**
   * @return tag for video communications
   */
  static String videoCommunication() {
    return "VIDEO";
  }

  /**
   * @return tag for off to silent notifications
   */
  static String offToSilent() {
    return "O2S";
  }

  /**
   * @return tag for off to idle notifications
   */
  static String offToIdle() {
    return "O2I";
  }

  /**
   * @return tag for busy to idle notifications
   */
  static String busyToIdle() {
    return "B2I";
  }

  /**
   * @return tag for silent to idle notifications
   */
  static String silentToIdle() {
    return "S2I";
  }

}
