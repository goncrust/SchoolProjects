package prr.app.clients;

interface Prompt {

    /** @return prompt for a client identifier */
    static String key() {
        return "Identificador do cliente: ";
    }

    /** @return prompt for a client name */
    static String name() {
        return "Nome do cliente: ";
    }

    /** @return prompt for the client's tax id */
    static String taxId() {
        return "NIF do cliente: ";
    }
}
