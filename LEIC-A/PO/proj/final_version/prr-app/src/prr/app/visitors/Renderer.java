package prr.app.visitors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import prr.clients.Client;
import prr.communications.TextCommunication;
import prr.communications.VideoCommunication;
import prr.communications.VoiceCommunication;
import prr.notifications.BusyToIdleNotification;
import prr.notifications.OffToIdleNotification;
import prr.notifications.OffToSilentNotification;
import prr.notifications.SilentToIdleNotification;
import prr.terminals.BasicTerminal;
import prr.terminals.FancyTerminal;
import prr.terminals.Terminal;
import prr.visitors.Printer;

public class Renderer implements Printer {

    Collection<String> screen = new ArrayList<>();

    @Override
    public void visit(Client client) {
        screen.add(Message.client() + "|" + client.getKey() + "|" + client.getName() + "|" + client.getNif() + "|"
                + client.getTypeLabel() + "|"
                + (client.getActiveNotifications() ? Message.activeNotifications() : Message.inactiveNotifications())
                + "|" + client.getTerminalCount() + "|" + Math.round(client.getPaid()) + "|"
                + Math.round(client.getDebt()));
    }

    @Override
    public void visit(OffToSilentNotification notification) {
        screen.add(Message.offToSilent() + "|" + notification.getId());
    }

    @Override
    public void visit(OffToIdleNotification notification) {
        screen.add(Message.offToIdle() + "|" + notification.getId());
    }

    @Override
    public void visit(BusyToIdleNotification notification) {
        screen.add(Message.busyToIdle() + "|" + notification.getId());
    }

    @Override
    public void visit(SilentToIdleNotification notification) {
        screen.add(Message.silentToIdle() + "|" + notification.getId());
    }

    private String getTerminalFriends(Terminal terminal) {
        ArrayList<String> terminalFriends = new ArrayList<>(terminal.getFriends());
        Collections.sort(terminalFriends);
        String friends = "";
        if (!terminalFriends.isEmpty()) {
            friends += "|";
            for (String friend : terminalFriends)
                friends += friend + ",";
            friends = friends.substring(0, friends.length() - 1);
        }
        return friends;
    }

    @Override
    public void visit(FancyTerminal terminal) {
        screen.add(Message.fancyTerminal() + "|" + terminal.getKey() + "|" + terminal.getOwnerKey() + "|"
                + terminal.getStateLabel() + "|"
                + Math.round(terminal.getPaid()) + "|" + Math.round(terminal.getDebt()) + getTerminalFriends(terminal));
    }

    @Override
    public void visit(BasicTerminal terminal) {
        screen.add(Message.basicTerminal() + "|" + terminal.getKey() + "|" + terminal.getOwnerKey() + "|"
                + terminal.getStateLabel() + "|"
                + Math.round(terminal.getPaid()) + "|" + Math.round(terminal.getDebt()) + getTerminalFriends(terminal));
    }

    @Override
    public void visit(TextCommunication communication) {
        screen.add(Message.textCommunication() + "|" + communication.getKey() + "|" + communication.getSenderKey() + "|"
                + communication.getReceiverKey() + "|" + communication.getUnits() + "|"
                + Math.round(communication.getPrice()) + "|"
                + (communication.isFinished() ? Message.finishedCommunication() : Message.ongoingCommunication()));
    }

    @Override
    public void visit(VideoCommunication communication) {
        screen.add(Message.videoCommunication() + "|" + communication.getKey() + "|" + communication.getSenderKey()
                + "|"
                + communication.getReceiverKey() + "|" + communication.getUnits() + "|"
                + Math.round(communication.getPrice()) + "|"
                + (communication.isFinished() ? Message.finishedCommunication() : Message.ongoingCommunication()));
    }

    @Override
    public void visit(VoiceCommunication communication) {
        screen.add(Message.voiceCommunication() + "|" + communication.getKey() + "|" + communication.getSenderKey()
                + "|"
                + communication.getReceiverKey() + "|" + communication.getUnits() + "|"
                + Math.round(communication.getPrice()) + "|"
                + (communication.isFinished() ? Message.finishedCommunication() : Message.ongoingCommunication()));
    }

    public Collection<String> render() {
        return screen;
    }
}
