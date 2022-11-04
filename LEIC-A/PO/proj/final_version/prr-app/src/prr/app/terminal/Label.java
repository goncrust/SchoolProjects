package prr.app.terminal;

/**
 * Menu entries.
 */
interface Label {

    /** Menu title. */
    String TITLE = "OPERAÇÕES DO TERMINAL";

    /** Power on the mobile. */
    String POWER_ON = "Ligar";

    /** Power off the mobile. */
    String POWER_OFF = "Desligar";

    /** Mute the mobile. */
    String MUTE_TERMINAL = "Colocar no silêncio";

    /** Register a new friend */
    String ADD_FRIEND = "Adicionar amigo";

    /** Remove an old friend. */
    String REMOVE_FRIEND = "Retirar amigo";

    /** Pay. */
    String PERFORM_PAYMENT = "Pagamento";

    /** Show balance. */
    String SHOW_BALANCE = "Consultar saldo";

    /** Start communication with other terminal. */
    String SEND_TEXT_COMMUNICATION = "Enviar comunicação de texto";

    /** Start communication with other terminal. */
    String START_INTERACTIVE_COMMUNICATION = "Iniciar comunicação";

    /** End communication with other terminal. */
    String END_INTERACTIVE_COMMUNICATION = "Terminar comunicação activa";

    /** End communication with other terminal. */
    String SHOW_ONGOING_COMMUNICATION = "Mostrar comunicação em curso";
}
