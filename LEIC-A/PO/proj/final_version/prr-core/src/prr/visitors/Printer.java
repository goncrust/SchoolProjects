package prr.visitors;

import prr.clients.Client;
import prr.communications.TextCommunication;
import prr.communications.VideoCommunication;
import prr.communications.VoiceCommunication;
import prr.notifications.OffToIdleNotification;
import prr.notifications.OffToSilentNotification;
import prr.notifications.BusyToIdleNotification;
import prr.notifications.SilentToIdleNotification;
import prr.terminals.BasicTerminal;
import prr.terminals.FancyTerminal;

public interface Printer {

    public void visit(Client client);

    public void visit(FancyTerminal terminal);

    public void visit(BasicTerminal terminal);

    public void visit(TextCommunication communication);

    public void visit(VoiceCommunication communication);

    public void visit(VideoCommunication communication);

    public void visit(OffToIdleNotification notification);

    public void visit(OffToSilentNotification notification);

    public void visit(BusyToIdleNotification notification);

    public void visit(SilentToIdleNotification notification);
}