package prr.app.clients;

import prr.Network;

import prr.exceptions.ClientDoesntExistException;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show the payments and debts of a client.
 */
class DoShowClientPaymentsAndDebts extends Command<Network> {

    DoShowClientPaymentsAndDebts(Network receiver) {
        super(Label.SHOW_CLIENT_BALANCE, receiver);
        addStringField("key", Prompt.key());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            String key = stringField("key");
            _display.popup(
                    Message.clientPaymentsAndDebts(key, Math.round(_receiver.getClientPayments(key)),
                            Math.round(_receiver.getClientDebt(key))));
        } catch (ClientDoesntExistException e) {
            throw new UnknownClientKeyException(e.getKey());
        }
    }
}
