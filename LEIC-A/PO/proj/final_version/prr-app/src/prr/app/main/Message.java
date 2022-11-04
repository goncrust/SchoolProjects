package prr.app.main;

/**
 * Messages.
 */
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
     * @param payments
     * @param debts
     * @return string presenting message
     */
    static String globalPaymentsAndDebts(long payments, long debts) {
        return "Valores globais: " + payments + " (pagamentos),  " + debts + " (dívidas).";
    }
}
