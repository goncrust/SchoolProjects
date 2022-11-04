package prr.terminals;

import prr.clients.Client;
import prr.visitors.Printer;
import prr.Network;
import prr.exceptions.BusyTerminalException;
import prr.exceptions.InvalidDestinationException;
import prr.exceptions.OffTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.communications.VideoCommunication;

public class FancyTerminal extends Terminal {

    public FancyTerminal(String key, Client owner) {
        super(key, owner);
    }

    @Override
    public void accept(Printer visitor) {
        visitor.visit(this);
    }

    @Override
    protected void startVideoCommunication(Terminal receiver, Network network) throws InvalidDestinationException,
            BusyTerminalException, OffTerminalException, SilencedTerminalException {
        VideoCommunication communication = network.newVideoCommunication(this, receiver);
        receiver.receiveVideoCommunication(communication);
        getState().startCommunication();
        setCurrentCommunication(communication);
        registerMadeCommunication(communication);
        getOwner().increaseVideoCount();

    }

    @Override
    public void receiveVideoCommunication(VideoCommunication communication)
            throws OffTerminalException, SilencedTerminalException, BusyTerminalException {
        try {
            getState().receiveCommunication();
        } catch (OffTerminalException | BusyTerminalException | SilencedTerminalException e) {
            registerInteractiveCommunicationObserver((communication.getSender()).getOwner());
            throw e;
        }
        setCurrentCommunication(communication);
        registerReceivedCommunication(communication);
    }
}
