package pt.tecnico.uilib.swing;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * This class manages a window. It provides behavior common to all panels.
 */
abstract class SwingPanel extends JPanel implements ActionListener {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202010150838L;

  /** End of the interaction with the panel reached. */
  protected boolean _end;

  /** Object lock used to synchronize the interface and application threads. */
  protected final Object _lock = new Object();

  SwingPanel(LayoutManager layout) {
    super(layout);
  }

  /**
   * This is called when an interaction event occurs.
   * 
   * @param event the ActionEvent to be processed
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    synchronized (_lock) {
      _end = true;
      _lock.notifyAll();
    }
  }

  /**
   * The current thread is put to sleep.
   * 
   * @param millis time to wait (milliseconds)
   */
  synchronized void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }

  /**
   * Wait until interaction ends.
   */
  synchronized void await() {
    _end = false;
    // while (!_end) { sleep(1); }
    synchronized (_lock) {
      while (!_end)
        try {
          _lock.wait();
        } catch (InterruptedException e) {
          System.out.println(e);
        }
    }
  }

}
