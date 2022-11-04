package pt.tecnico.uilib.forms;

import static pt.tecnico.uilib.Dialog.UI;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import pt.tecnico.uilib.Dialog;

/**
 * A Form has a set of input fields.
 */
public class Form {

	/** The graphical driver used to interact with the user */
	private Dialog _ui;

	/** The Form's title (optional) */
	private String _title;

	/** A list of Input requests */
	private Map<String, Field<?>> _fields = new LinkedHashMap<>();

	/** use default values: static UI and no title */
	public Form() {
		this(null);
	}

	/**
	 * @param title the title
	 */
	public Form(String title) {
		this(UI, title);
	}

	/**
	 * @param ui    the dialog driver
	 * @param title the title
	 */
	public Form(Dialog ui, String title) {
		_ui = ui;
		_title = title;
	}

	/** @return the title of the Form (can be null) */
	public String title() {
		return _title;
	}

	/** @return a copy of the Form's fields */
	public Collection<Field<?>> entries() {
		return Collections.unmodifiableCollection(_fields.values());
	}

	/**
	 * @param key
	 * @return a copy of the Input requests of the Form
	 */
	public Field<?> field(String key) {
		return _fields.get(key);
	}

	/**
	 * Append an Input to a Form
	 * 
	 * @param key
	 * @param in
	 */
	private void add(String key, Field<?> in) {
		if (_fields.containsKey(key))
			throw new FormException(Message.keyAlreadyExists(_title, key));
		_fields.put(key, in);
	}

	/**
	 * @param key
	 * @param label
	 */
	public void addBooleanField(String key, String label) {
		add(key, new FieldBoolean(label));
	}

	/**
	 * @param key
	 * @param label
	 */
	public void addStringField(String key, String label) {
		add(key, new FieldString(label));
	}

	/**
	 * @param key
	 * @param label
	 */
	public void addRealField(String key, String label) {
		add(key, new FieldReal(label));
	}

	/**
	 * @param key
	 * @param label
	 */
	public void addIntegerField(String key, String label) {
		add(key, new FieldInteger(label));
	}

//  /**
//   * @param key
//   * @param label
//   * @param options
//   */
//  public void addOptionField(String key, String label, String[] options) {
//    add(key, new FieldOption(label, options));
//  }

	/**
	 * @param key
	 * @param label
	 * @param options
	 */
	public void addOptionField(String key, String label, String... options) {
		add(key, new FieldOption(label, options));
	}

	/**
	 * Get value of form field.
	 * 
	 * @param key
	 * @param type
	 * @return field value
	 */
	private Object get(String key, String type) {
		Field<?> input = _fields.get(key);
		if (input == null)
			throw new FormException(Message.keyNotFound(_title, key));
		Object value = input.value();
		if (value == null)
			throw new FormException(Message.formNotFilled(_title));
		String actualType = value.getClass().getCanonicalName();
		if (!type.equals(actualType))
			throw new FormException(Message.typeMismatch(_title, key, actualType, type));
		return value;
	}

	/**
	 * @param key
	 * @return field value
	 */
	public Boolean booleanField(String key) {
		return (Boolean) get(key, "java.lang.Boolean");
	}

	/**
	 * @param key
	 * @return field value
	 */
	public String stringField(String key) {
		return (String) get(key, "java.lang.String");
	}

	/**
	 * @param key
	 * @return field value
	 */
	public String optionField(String key) {
		return (String) get(key, "java.lang.String");
	}

	/**
	 * @param key
	 * @return field value
	 */
	public Double realField(String key) {
		return (Double) get(key, "java.lang.Double");
	}

	/**
	 * @param key
	 * @return field value
	 */
	public Integer integerField(String key) {
		return (Integer) get(key, "java.lang.Integer");
	}

	/**
	 * Request the parsing of all the form's fields, clearing previous field values.
	 * 
	 * @return the form
	 */
	public Form parse() {
		return parse(true);
	}

	/**
	 * Request the parsing of all the form's fields.
	 * 
	 * @param clear if true, field values are first cleared; otherwise, previous
	 *              field values are used as default values.
	 * @return the form
	 */
	public Form parse(boolean clear) {
		if (_fields.isEmpty())
			return this;

		if (clear)
			_fields.values().forEach(field -> field.clear());
		_ui.fill(this);
		return this;
	}

	/**
	 * Clear the form.
	 */
	public void clear() {
		_fields.clear();
	}

	public static Boolean confirm(String prompt) {
		Form confirm = new Form();
		confirm.addBooleanField("answer", prompt);
		return confirm.parse().booleanField("answer");
	}

	public static Integer requestInteger(String prompt) {
		Form request = new Form();
		request.addIntegerField("answer", prompt);
		return request.parse().integerField("answer");
	}

	public static Double requestReal(String prompt) {
		Form request = new Form();
		request.addRealField("answer", prompt);
		return request.parse().realField("answer");
	}

	public static String requestString(String prompt) {
		Form request = new Form();
		request.addStringField("answer", prompt);
		return request.parse().stringField("answer");
	}

	public static String requestOption(String prompt, String... options) {
		Form request = new Form();
		request.addOptionField("answer", prompt, options);
		return request.parse().optionField("answer");
	}

}
