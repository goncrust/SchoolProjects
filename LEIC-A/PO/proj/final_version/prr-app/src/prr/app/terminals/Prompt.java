package prr.app.terminals;

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
     * @return string prompting for terminal type
     */
    static String terminalType() {
        return "Tipo de terminal (BASIC ou FANCY): ";
    }
}
