package prr.app.exceptions;

/** Messages for error reporting. */
interface Message {

    /**
     * @return string with "file not found" message.
     */
    static String fileNotFound() {
        return "O ficheiro não existe.";
    }

    /**
     * @param filename
     * @return string with "file not found" message (more elaborate).
     */
    static String fileNotFound(String filename) {
        return "O ficheiro '" + filename + "' não existe.";
    }

    /**
     * @param filename
     * @return string with problem description.
     */
    static String problemOpeningFile(Exception cause) {
        return "Problema ao abrir ficheiro: " + cause.getMessage();
    }

    /**
     * @param key
     * @return string with problem description.
     */
    static String unknownClientKey(String key) {
        return "O cliente '" + key + "' não existe.";
    }

    /**
     * @param key
     * @return string reporting a duplicate client
     */
    static String duplicateClientKey(String key) {
        return "O cliente '" + key + "' já existe.";
    }

    /**
     * @param key
     * @return string with problem description.
     */
    static String unknownTerminalKey(String key) {
        return "O terminal '" + key + "' não existe.";
    }

    /**
     * @param key
     * @return string reporting duplicate terminals
     */
    public static String duplicateTerminalKey(String key) {
        return "O terminal '" + key + "' já existe.";
    }

    /**
     * @param key
     * @return string reporting invalid keys
     */
    public static String invalidTerminalKey(String key) {
        return "A chave '" + key + "' não é válida para terminais.";
    }

    /**
     * @param key
     * @return string with problem description.
     */
    static String unknownCommunicationKey(int key) {
        return "A comunicação '" + key + "' não existe.";
    }

    /**
     * @param key
     * @return string with problem description.
     */
    public static String duplicateCommunicationKey(int key) {
        return "A comunicação '" + key + "' já existe.";
    }
}
