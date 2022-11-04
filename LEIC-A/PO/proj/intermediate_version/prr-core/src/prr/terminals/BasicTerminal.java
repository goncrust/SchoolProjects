package prr.terminals;

import prr.clients.Client;

public class BasicTerminal extends Terminal {

    public BasicTerminal(String key, Client owner) {
        super(key, owner);
    }

    @Override
    public String toString() {
        return "BASIC|" + super.toString();
    }

}
