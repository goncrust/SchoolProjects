import java.util.Map;
import java.util.TreeMap;

public class CatRegistry() implements Serializable {
	
	Map<String, Cat> cats = new TreeMap<String, Cat>();	

	public void put(Cat cat) {
		cats.put(cat.getName(), cat);
	}

	public Cat get(String name) {
		return cats.get(name);
	}
}
