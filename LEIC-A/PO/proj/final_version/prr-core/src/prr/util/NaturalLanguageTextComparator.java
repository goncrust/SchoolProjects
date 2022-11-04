package prr.util;

import java.text.Collator;
import java.util.Locale;
import java.util.Comparator;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.IOException;
import java.io.Serializable;

public class NaturalLanguageTextComparator implements Comparator<String>, Serializable {

  @Serial
  private static final long serialVersionUID = 202218101128L;

  private transient Collator collator;

  public NaturalLanguageTextComparator() {
    this.mount();
  }

  private void mount() {
    collator = Collator.getInstance(Locale.getDefault());
    collator.setStrength(Collator.PRIMARY); // Ignoring case and accentuation
  }

  @Serial
  private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
    ois.defaultReadObject();
    this.mount();
  }

  @Override
  public int compare(String s1, String s2) {
    return collator.compare(s1, s2);
  }
}
