package pt.tecnico.uilib.menus;

import java.util.function.Predicate;

import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.forms.Form;

/**
 * Command represents an operation that can be carried out by or over an entity.
 * 
 * @param <Receiver> the entity providing the command's context.
 */
public abstract class Command<Receiver> {

  /** Indicates whether, in a menu, this is the last command. */
  private boolean _last;

  /** The command label in a menu. */
  private String _title;

  /** The entity providing the command's context. */
  protected final Receiver _receiver;

  /** The validity predicate: is the command available/visible? */
  protected Predicate<Receiver> _valid = receiver -> true;

  /** This command's input form. */
  private final Form _form;

  /** This command's display. */
  protected final Display _display;

  /**
   * @param last  indicates whether, in a menu, this is the last command.
   * @param title the command label in a menu.
   */
  public Command(boolean last, String title) {
    this(last, title, null);
  }

  /**
   * @param last     indicates whether, in a menu, this is the last command.
   * @param title    the command label in a menu.
   * @param receiver the entity providing the command's context.
   */
  public Command(boolean last, String title, Receiver receiver) {
    this(last, title, receiver, r -> true);
  }

  /**
   * @param last     indicates whether, in a menu, this is the last command.
   * @param title    the command label in a menu.
   * @param receiver the entity providing the command's context.
   * @param valid    custom validity predicate
   */
  public Command(boolean last, String title, Receiver receiver, Predicate<Receiver> valid) {
    _last = last;
    _title = title;
    _receiver = receiver;
    _form = new Form(_title);
    _display = new Display(_title);
    _valid = valid;
  }

  /**
   * @param title    the command label in a menu.
   * @param receiver the entity providing the command's context.
   */
  public Command(String title, Receiver receiver) {
    this(false, title, receiver);
  }

  /**
   * @param title    the command label in a menu.
   * @param receiver the entity providing the command's context.
   * @param valid    custom validity predicate
   */
  public Command(String title, Receiver receiver, Predicate<Receiver> valid) {
    this(false, title, receiver, valid);
  }

  /**
   * @return the command's title.
   */
  public final String title() {
    return _title;
  }

  /**
   * Indicates whether, in a menu, this is the last command.
   * 
   * @return true if, in a menu, this is the last command.
   */
  public boolean isLast() {
    return _last;
  }

  /**
   * @return whether the command is valid for the given receiver.
   */
  public boolean isValid() {
    return _valid.test(_receiver);
  }

  public void addBooleanField(String key, String prompt) {
    _form.addBooleanField(key, prompt);
  }

  public void addRealField(String key, String prompt) {
    _form.addRealField(key, prompt);
  }

  public void addIntegerField(String key, String prompt) {
    _form.addIntegerField(key, prompt);
  }

  public void addStringField(String key, String prompt) {
    _form.addStringField(key, prompt);
  }

  public void addOptionField(String key, String prompt, String... options) {
    _form.addOptionField(key, prompt, options);
  }

  public Boolean booleanField(String key) {
    return _form.booleanField(key);
  }

  public Double realField(String key) {
    return _form.realField(key);
  }

  public Integer integerField(String key) {
    return _form.integerField(key);
  }

  public String stringField(String key) {
    return _form.stringField(key);
  }

  public String optionField(String key) {
    return _form.optionField(key);
  }

  /**
   * @throws CommandException
   */
  public final void performCommand() throws CommandException {
    _display.clear();
    _form.parse();
    execute();
  }

  /**
   * Executes the command: as defined in the Command pattern, the command's
   * execution should eventually invoke the receiver's action.
   * 
   * @throws CommandException if something wrong or unexpected occurs.
   */
  protected abstract void execute() throws CommandException;

}
