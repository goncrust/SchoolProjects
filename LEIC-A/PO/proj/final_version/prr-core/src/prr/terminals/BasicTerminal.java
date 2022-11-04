package prr.terminals;

import prr.clients.Client;
import prr.communications.VideoCommunication;
import prr.Network;
import prr.exceptions.InvalidDestinationException;
import prr.exceptions.InvalidOriginException;
import prr.visitors.Printer;

public class BasicTerminal extends Terminal {

    public BasicTerminal(String key, Client owner) {
        super(key, owner);
    }

    @Override
    protected void startVideoCommunication(Terminal receiver, Network network) throws InvalidOriginException {
        throw new InvalidOriginException(getKey(), "VIDEO");
    }

    @Override
    public void receiveVideoCommunication(VideoCommunication communication) throws InvalidDestinationException {
        throw new InvalidDestinationException(getKey(), "VIDEO");
    }

    @Override
    public void accept(Printer visitor) {
        visitor.visit(this);
    }
}
