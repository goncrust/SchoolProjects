import java.util.ArrayList;

public class Paragraph extends Element {
	private ArrayList<Element> elements = new ArrayList<Element>();

	public void addElement(Element e) {
		elements.add(e);
	}

	public void remove(Element e) {
		elements.remove(e);
	}

	public String render() {
		String s = "<p>\n";
		for (Element e : elements) {
			s += e.render() + "\n";
		}
		return s += "</p>";
	}
}
