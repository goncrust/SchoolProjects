package prr.app.clients;

/**
 * Messages.
 */
interface Message {

    /** @return report message */
    static String clientNotificationsAlreadyEnabled() {
        return "A recepção de mensagens já está activa";
    }

    /** @return report message */
    static String clientNotificationsAlreadyDisabled() {
        return "A recepção de mensagens já está inactiva";
    }

    /**
     * @param key
     * @param balance
     * @return report message
     */
    static String clientPaymentsAndDebts(String key, long payments, long debts) {
        return "Valores para o cliente '" + key + "':" + payments + " (pagamentos),  " + debts + " (dívidas).";
    }
}
