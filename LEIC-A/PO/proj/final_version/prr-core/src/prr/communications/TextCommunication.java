package prr.communications;

import prr.clients.Client;
import prr.terminals.Terminal;
import prr.visitors.Printer;

public class TextCommunication extends Communication {

    private final String message;

    public TextCommunication(int key, Terminal sender, Terminal receiver, String message, boolean isFriend) {
        super(key, sender, receiver, message.length());
        this.message = message;
        updatePrice(isFriend);
        finish();
    }

    @Override
    public void accept(Printer visitor) {
        visitor.visit(this);
    }

    @Override
    public void updatePrice(boolean isFriend) {
        Client sender = getSender().getOwner();
        setPrice(sender.getType().accept(sender.getCalculator(), this, isFriend));
    }
}
