package prr.app.lookups;

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

}
