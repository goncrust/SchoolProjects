public class App {
	public static void main(String[] args) {
		Page p = new Page();
		Paragraph p1 = new Paragraph();
		Paragraph p2 = new Paragraph();
		Span s1 = new Span();
		Span s2 = new Span();
		Image i = new Image();
		p2.addElement(i);
		p1.addElement(s1);
		p1.addElement(s2);
		p.addParagraph(p1);
		p.addParagraph(p2);
		System.out.println(p.render());
	}
}
