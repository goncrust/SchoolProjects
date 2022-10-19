import java.util.ArrayList;

public class Page extends Element {

	private ArrayList<Element> elements = new ArrayList<Element>();

	public void addPage(Page p) {
		elements.add(p);
	}

	public void addParagraph(Paragraph p) {
		elements.add(p);
	}

	public void remove(Element e) {
		elements.remove(e);	
	}

	@Override
	public String render() {
		String s = "<page>\n";
		for (Element e : elements) {
			s += e.render() + "\n";
		}
		return s + "</page>";
	}
}
