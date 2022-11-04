package prr.terminals;

import prr.clients.Client;

public class FancyTerminal extends Terminal {

    public FancyTerminal(String key, Client owner) {
        super(key, owner);
    }

    @Override
    public String toString() {
        return "FANCY|" + super.toString();
    }

}
