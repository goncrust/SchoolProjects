package prr.app.terminal;

/**
 * Prompts.
 */
interface Prompt {

    /**
     * @return string prompting for client key
     */
    static String clientKey() {
        return "Identificador do cliente: ";
    }

    /**
     * @return string prompting for terminal key
     */
    static String terminalKey() {
        return "Número do terminal: ";
    }

    /**
     * @return string prompting for a the payment value.
     */
    static String paymentValue() {
        return "Qual o valor pretendido? (em cêntimos): ";
    }

    /**
     * @return string prompting for the communication key.
    */
    static String commKey() {
        return "Número da comunicação: ";
    }

    /**
     * @return string prompting for the communication type.
     */
    static String commType() {
        return "Tipo de comunicação a iniciar (VOICE, VIDEO): ";
    }

    /**
     * @return string prompting for the communication duration.
     */
    static String duration() {
        return "Duração da comunicação (em minutos): ";
    }

    /**
     * @return string prompting for the number of characters in text communication.
     */
    static String characters() {
        return "Número de caracteres da comunicação: ";
    }
    
    /**
    * @return string prompting for the text message.
    */
    static String textMessage() {
        return "Mensagem de texto a enviar: ";
    }
}
