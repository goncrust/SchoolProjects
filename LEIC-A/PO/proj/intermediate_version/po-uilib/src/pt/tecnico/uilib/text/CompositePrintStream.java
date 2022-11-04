package pt.tecnico.uilib.text;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 * CompositePrintStream represents a print stream that encloses more than one
 * print stream. Each message sent to this print stream is also sent to its
 * nested print streams.
 */
class CompositePrintStream extends PrintStream {

  /** A stream collection. */
  private Collection<PrintStream> _streams = new ArrayList<>();

  /** PrintStreams convert exceptions into error status. */
  private boolean _error = false;

  /** Creates a new composite print stream. */
  CompositePrintStream() {
    super(System.out);
  }

  /** Creates a new composite print stream. */
  CompositePrintStream(PrintStream... printStreams) {
    super(System.out);
    for (PrintStream ps : printStreams)
      add(ps);
  }

  /**
   * Add a print stream to this composite print stream.
   * 
   * @param ps the new print stream to add.
   */
  final void add(PrintStream ps) {
    _streams.add(ps);
  }

  /** @see java.io.PrintStream#checkError() */
  @Override
  public boolean checkError() {
    boolean res = _error;
    for (PrintStream st : _streams)
      res = res || st.checkError();
    return res;
  }

  /** @see java.io.PrintStream#close() */
  @Override
  public void close() {
    _streams.stream().filter(s -> s != System.out).forEach(s -> s.close());
  }

  /** @see java.io.PrintStream#flush() */
  @Override
  public void flush() {
    _streams.forEach(s -> s.flush());
  }

  /** @see java.io.PrintStream#print(boolean) */
  @Override
  public void print(boolean b) {
    _streams.forEach(s -> s.print(b));
  }

  /** @see java.io.PrintStream#print(char) */
  @Override
  public void print(char c) {
    _streams.forEach(s -> s.print(c));
  }

  /** @see java.io.PrintStream#print(char[]) */
  @Override
  public void print(char[] c) {
    _streams.forEach(s -> s.print(c));
  }

  /** @see java.io.PrintStream#print(double d) */
  @Override
  public void print(double d) {
    _streams.forEach(s -> s.print(d));
  }

  /** @see java.io.PrintStream#print(float) */
  @Override
  public void print(float f) {
    _streams.forEach(s -> s.print(f));
  }

  /** @see java.io.PrintStream#print(int) */
  @Override
  public void print(int i) {
    _streams.forEach(s -> s.print(i));
  }

  /** @see java.io.PrintStream#print(long) */
  @Override
  public void print(long l) {
    _streams.forEach(s -> s.print(l));
  }

  /** @see java.io.PrintStream#print(Object) */
  @Override
  public void print(Object obj) {
    _streams.forEach(s -> s.print(obj));
  }

  /** @see java.io.PrintStream#print(String) */
  @Override
  public void print(String str) {
    _streams.forEach(s -> s.print(str));
  }

  /** @see java.io.PrintStream#println(boolean) */
  @Override
  public void println(boolean b) {
    _streams.forEach(s -> s.println(b));
  }

  /** @see java.io.PrintStream#println(char) */
  @Override
  public void println(char c) {
    _streams.forEach(s -> s.println(c));
  }

  /** @see java.io.PrintStream#println(char[]) */
  @Override
  public void println(char[] c) {
    _streams.forEach(s -> s.println(c));
  }

  /** @see java.io.PrintStream#println(double d) */
  @Override
  public void println(double d) {
    _streams.forEach(s -> s.println(d));
  }

  /** @see java.io.PrintStream#println(float) */
  @Override
  public void println(float f) {
    _streams.forEach(s -> s.println(f));
  }

  /** @see java.io.PrintStream#println(int) */
  @Override
  public void println(int i) {
    _streams.forEach(s -> s.println(i));
  }

  /** @see java.io.PrintStream#println(long) */
  @Override
  public void println(long l) {
    _streams.forEach(s -> s.println(l));
  }

  /** @see java.io.PrintStream#println(Object) */
  @Override
  public void println(Object obj) {
    _streams.forEach(s -> s.println(obj));
  }

  /** @see java.io.PrintStream#println(String) */
  @Override
  public void println(String str) {
    _streams.forEach(s -> s.println(str));
  }

  /** @see java.io.PrintStream#setError() */
  @Override
  protected void setError() {
    _error = true;
  }

  /** @see java.io.PrintStream#write(byte[], int, int) */
  @Override
  public void write(byte[] buf, int off, int len) {
    _streams.forEach(s -> s.write(buf, off, len));
  }

  /** @see java.io.PrintStream#write(int) */
  @Override
  public void write(int b) {
    _streams.forEach(s -> s.write(b));
  }

  /** @see java.io.FilterOutputStream#write(byte[]) */
  @Override
  public void write(byte[] b) throws IOException {
    for (PrintStream st : _streams)
      st.write(b);
  }

}
