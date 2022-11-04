package prr.app.terminal;

/**
 * Messages.
 */
interface Message {

    /** @return status message. */
    static String originIsOff(String key) {
        return "O terminal de origem ('" + key + "') está desligado.";
    }

    /** @return status message. */
    static String destinationIsOff(String key) {
        return "O terminal de destino ('" + key + "') está desligado.";
    }

    /** @return status message. */
    static String originIsBusy(String key) {
        return "O terminal de origem ('" + key + "') está ocupado.";
    }

    /** @return status message. */
    static String destinationIsBusy(String key) {
        return "O terminal de destino ('" + key + "') está ocupado.";
    }

    /** @return status message. */
    static String destinationIsSilent(String key) {
        return "O terminal de destino ('" + key + "') está em silêncio.";
    }

    /** @return status message. */
    static String unsupportedAtOrigin(String key, String type) {
        return "O terminal '" + key + "' não pode iniciar comunicações " + type;
    }

    /** @return status message. */
    static String unsupportedAtDestination(String key, String type) {
        return "O terminal '" + key + "' não pode receber comunicações " + type;
    }

    /**
     * @param cost the cost of the communication
     * @return string reporting the cost of the communication.
     */
    static String communicationCost(long cost) {
        return "Custo da comunicação: " + cost + " unidades.";
    }

    /** @return status message. */
    static String alreadyOn() {
        return "O terminal já estava ligado.";
    }

    /** @return status message. */
    static String alreadyOff() {
        return "O terminal já estava desligado.";
    }

    /** @return status message. */
    static String alreadySilent() {
        return "O terminal já estava em silêncio.";
    }

    /**
     * @param idTerminal terminal that has been turned on
     * @param idCaller   terminal that tried to communicate before
     * @return string with informative message.
     */
    static String isAvailable(int idTerminal, int idCaller) {
        return "O terminal '" + idTerminal + "' já está disponível para o terminal '" + idCaller + "'.";
    }

    /**
     * @param idTerminal terminal that has been turned on
     * @param idCaller   terminal that tried to communicate before
     * @return string with informative message.
     */
    static String isAvailableForTextCommunications(int idTerminal, int idCaller) {
        return "O terminal '" + idTerminal + "' já está disponível para receber comunicações de texto de '" + idCaller
                + "'.";
    }

    /**
     * @param key
     * @param payments
     * @param debts
     * @return report message.
     */
    static String terminalPaymentsAndDebts(String key, long payments, long debts) {
        return "Valores para o terminal '\" + key + \"':" + payments + " (pagamentos),  " + debts + " (dívidas).";
    }

    /** @return status message. */
    static String noOngoingCommunication() {
        return "Não existe nenhuma comunicação em curso.";
    }

    /** @return status message */
    static String invalidCommunication() {
        return "O identificador da comunicação é inválido.";
    }
}
