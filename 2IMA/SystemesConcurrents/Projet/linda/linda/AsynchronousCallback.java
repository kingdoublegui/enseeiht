package linda;

/** The class helps to transform a callback to behave asynchronously.
 * The callback fires exactly once.
 * The callback fires asynchronously with other threads and may do whatever it wants (it may block).
 * @author philippe.queinnec@enseeiht.fr
 */
public class AsynchronousCallback implements Callback {
        
    private Callback cb;

    public AsynchronousCallback (Callback cb) { this.cb = cb; }
        
    /** Asynchronous call: the associated callback is concurrently run and this one immediately returns.
     * */
    public void call(final Tuple t) {
        new Thread() {
            public void run() {
                cb.call(t);
            }
        }.start();
    }
}
