package linda;

/** Callback when a tuple appears.
 * @author philippe.queinnec@enseeiht.fr
*/
public interface Callback {

    /** Callback when a tuple appears. 
     * See Linda.eventRegister for details.
     * 
     * @param t the new tuple
     */
    void call(Tuple t);
}
