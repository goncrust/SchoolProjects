package prr;

import java.io.Serial;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import prr.util.NaturalLanguageTextComparator;
import prr.clients.Client;
import prr.terminals.FancyTerminal;
import prr.terminals.BasicTerminal;
import prr.terminals.Terminal;
import prr.exceptions.UnrecognizedEntryException;
import prr.exceptions.UnrecognizedTerminalTypeException;
import prr.exceptions.ClientDoesntExistException;
import prr.exceptions.ClientExistsException;
import prr.exceptions.IncorrectTerminalKeyException;
import prr.exceptions.InvalidEntryException;
import prr.exceptions.TerminalDoesntExistException;
import prr.exceptions.TerminalExistsException;

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

    /** Serial number for serialization. */
    @Serial
    private static final long serialVersionUID = 202208091753L;

    /** Clients list, sorted by key */
    private final Map<String, Client> clients = new TreeMap<>(new NaturalLanguageTextComparator());

    /** Terminals list, sorted by key */
    private final Map<String, Terminal> terminals = new TreeMap<>(new NaturalLanguageTextComparator());

    /** Communications counter, used for generating communication keys */
    private int communicationKey = 0;

    /** Something as changed since last save */
    private boolean unsaved = false;

    /**
     * Register that something changed
     */
    public void changed() {
        this.unsaved = true;
    }

    /**
     * Error saving, remains unsaved
     */
    public void failedSave() {
        this.unsaved = true;
    }

    /**
     * Register that all was saved
     */
    public void saved() {
        this.unsaved = false;
    }

    /**
     * Getter for unsaved variable
     */
    public boolean isUnsaved() {
        return this.unsaved;
    }

    /**
     * Get and increment the communication key tracker
     */
    public int getCommunicationKey() {
        return communicationKey++;
    }

    /**
     * Register a new client in the network
     * 
     * @param name new client's name
     * @param key  new client's key
     * @param nif  new client's tax id
     * @throws ClientExistsException if the given key is already in use
     */
    public void registerClient(String key, String name, int nif) throws ClientExistsException {
        if (clients.containsKey(key))
            throw new ClientExistsException(key);
        clients.put(key, new Client(name, key, nif));
        changed();
    }

    /**
     * Get all clients registered in the network
     * 
     * @return A {@link Collection} of clients, sorted by their key
     */
    public Collection<Client> getAllClients() {
        return clients.values();
    }

    /**
     * Get a client
     * 
     * @param key the key that identifies the client
     * @return The {@link Client} with the matching key
     * @throws ClientDoesntExistException if the given key can't be found
     */
    public Client getClient(String key) throws ClientDoesntExistException {
        if (!clients.containsKey(key))
            throw new ClientDoesntExistException(key);
        return clients.get(key);
    }

    /**
     * Get all terminals registered in the network
     * 
     * @return A {@link Collection} of terminals, sorted by their key
     */
    public Collection<Terminal> getAllTerminals() {
        return terminals.values();
    }

    /**
     * Get a terminal
     * 
     * @param key the key that identifies the terminal
     * @return The {@link Terminal} with the matching key
     * @throws TerminalDoesntExistException if the given key can't be found
     */
    public Terminal getTerminal(String key) throws TerminalDoesntExistException {
        if (!terminals.containsKey(key))
            throw new TerminalDoesntExistException(key);
        return terminals.get(key);
    }

    /**
     * Get all terminals with no communication history
     * 
     * @return A {@link Collection} of terminals, sorted by their key
     */
    public Collection<Terminal> getUnusedTerminals() {
        ArrayList<Terminal> unused = new ArrayList<Terminal>();

        for (Terminal t : terminals.values())
            if (t.getCommCount() == 0)
                unused.add(t);

        return unused;
    }

    /**
     * Register a new terminal in the network
     * 
     * @param key    new terminal's key
     * @param type   new terminal's type
     * @param client new terminal's owner
     * @throws TerminalExistsException    if the given key is already in use
     * @throws ClientDoenstExistException if the given client doesnt exist
     */
    public void registerTerminal(String key, String type, String client)
            throws TerminalExistsException, IncorrectTerminalKeyException, ClientDoesntExistException,
            UnrecognizedTerminalTypeException {
        registerTerminal(key, type, client, "IDLE");
    }

    /**
     * Register a new terminal in the network
     * 
     * @param key    new terminal's key
     * @param type   new terminal's type
     * @param client new terminal's owner
     * @param state  new terminal's initial state
     * @throws TerminalExistsException    if the given key is already in use
     * @throws ClientDoesntExistException if the given client doesnt exist
     */
    private void registerTerminal(String key, String type, String client, String state)
            throws TerminalExistsException, IncorrectTerminalKeyException, ClientDoesntExistException,
            UnrecognizedTerminalTypeException {

        if (terminals.containsKey(key))
            throw new TerminalExistsException(key);

        if (!key.matches("[0-9]{6}"))
            throw new IncorrectTerminalKeyException(key);

        Client owner = this.getClient(client);
        Terminal newTerminal;

        if (type.equals("FANCY"))
            newTerminal = new FancyTerminal(key, clients.get(client));
        else if (type.equals("BASIC"))
            newTerminal = new BasicTerminal(key, clients.get(client));
        else
            throw new UnrecognizedTerminalTypeException(type);

        switch (state) {
            case "ON":
                newTerminal.toIdle();
                break;
            case "OFF":
                newTerminal.toOff();
                break;
            case "SILENCE":
                newTerminal.toSilence();
                break;
        }

        owner.addTerminal(newTerminal);
        terminals.put(key, newTerminal);

        changed();
    }

    /**
     * Loads a client onto the network from an array of fields
     * 
     * @param fields the line fields
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws InvalidEntryException      if some entry doesn't respect the rules
     *                                    (repeated keys, invalid keys, etc.)
     */
    private void importClient(String[] fields) throws UnrecognizedEntryException, InvalidEntryException {
        if (fields.length != 4)
            throw new UnrecognizedEntryException(String.join("|", fields));
        try {
            registerClient(fields[1], fields[2], Integer.parseInt(fields[3]));
        } catch (ClientExistsException | NumberFormatException e) {
            throw new InvalidEntryException(String.join("|", fields), e);
        }
    }

    /**
     * Loads a terminal onto the network from an array of fields
     * 
     * @param fields the line fields
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws InvalidEntryException      if some entry doesn't respect the rules
     *                                    (repeated keys, invalid keys, etc.)
     */
    private void importTerminal(String[] fields) throws UnrecognizedEntryException, InvalidEntryException {
        if (fields.length != 4 | !fields[3].matches("^(ON|OFF|SILENCE)"))
            throw new UnrecognizedEntryException(String.join("|", fields));
        try {
            registerTerminal(fields[1], fields[0], fields[2], fields[3]);
        } catch (TerminalExistsException | IncorrectTerminalKeyException | ClientDoesntExistException
                | UnrecognizedTerminalTypeException e) {
            throw new InvalidEntryException(String.join("|", fields), e);
        }
    }

    /**
     * Loads a list of friends onto the network from an array of fields
     * 
     * @param fields the line fields
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws InvalidEntryException      if some entry doesn't respect the rules
     *                                    (repeated keys, invalid keys, etc.)
     */
    private void importFriends(String[] fields) throws UnrecognizedEntryException, InvalidEntryException {
        if (fields.length != 3)
            throw new UnrecognizedEntryException(String.join("|", fields));

        try {
            Terminal terminal = this.getTerminal(fields[1]);
            String[] friends = fields[2].split(",");
            for (String friend : friends) {
                this.getTerminal(friend);
                terminal.addFriend(friend);
            }

            changed();
        } catch (TerminalDoesntExistException e) {
            throw new InvalidEntryException(String.join("|", fields), e);
        }
    }

    /**
     * Read the first field of a line and check what object is supposed to be
     * imported
     * 
     * @param fields the line fields
     * @throws UnrecognizedEntryException if the first field isn't recognized
     */
    private void importObject(String[] fields) throws UnrecognizedEntryException, InvalidEntryException {
        if (fields[0].matches("^(CLIENT)"))
            importClient(fields);
        else if (fields[0].matches("^(BASIC|FANCY)"))
            importTerminal(fields);
        else if (fields[0].matches("^(FRIENDS)"))
            importFriends(fields);
        else
            throw new UnrecognizedEntryException(String.join("|", fields));
    }

    /**
     * Read text input file and create corresponding domain entities.
     * 
     * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws InvalidEntryException      if some entry doesn't respect the rules
     *                                    (repeated keys, invalid keys, etc.)
     * @throws IOException                if there is an IO error while processing
     *                                    the text file
     */
    void importFile(String filename) throws UnrecognizedEntryException, InvalidEntryException, IOException {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while ((line = reader.readLine()) != null)
                importObject(line.split("\\|"));
        }
    }
}