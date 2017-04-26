package linda;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/** Representation of a tuple.
 * @author philippe.queinnec@enseeiht.fr
 */
public class Tuple extends LinkedList<Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Creates a new tuple.
     * Example :
     * new Tuple(4, 5, "foo", true)  -> [ 4 5 "foo" true ]
     * new Tuple(4, Integer.class, "foo".getclass(), Boolean.class)  -> [ 4 ?Integer ?String ?Boolean ]
     */
    public Tuple(Serializable... components) {
        for (Serializable c : components) {
            this.add(c);
        }
    }

    private static boolean matches(Serializable thiscomponent, Serializable templatecomponent) {
        if (templatecomponent instanceof Tuple) {
            if (! (thiscomponent instanceof Tuple))
              return false;
            else
              return ((Tuple)thiscomponent).matches((Tuple)templatecomponent);
        } else if (templatecomponent instanceof Class) {
            if (thiscomponent instanceof Class)
              return ((Class<?>)templatecomponent).isAssignableFrom((Class<?>)thiscomponent);
            else
              return ((Class<?>) templatecomponent).isInstance(thiscomponent);
        } else {
            return thiscomponent.equals(templatecomponent);
        }

    }

    /** Returns true if this tuple matches the given template.
     * Matching rules : a tuple matches a template if all their components match two by two.
     * Two components match :
     *  - if they are both values and are equals;
     *  - if the template component is a class/interface, and the tuple component is an instance/implementation of this class/interface (Class.isInstance);
     *  - if the template component is a class/interface, and the tuple component is a subclass/subinterface of this class/interface (Class.isAsssignableFrom);
     *  - recursively if both are tuples.
     *
     *  Examples:
     *   [ 3 5 "foo" ] matches [ 3 5 "foo" ], [ ?Integer 5 "foo" ], [ ?Integer ?Integer ?String ]
     *   [ 3 ?Integer [ 6 7 ] [ 7 8 ] ] matches [ ?Integer ?Integer [ ?Integer 7 ] ?Tuple ], [3 ?Integer ?Tuple ?Tuple ]
     *
     *   @param template the template which this tuple is compared to.
     */
    public boolean matches(Tuple template) {
        if (this.size() != template.size())
          return false;
        Iterator<Serializable> itthis = this.iterator();
        Iterator<Serializable> itmotif = template.iterator();
        while (itthis.hasNext()) {
            Serializable othis = itthis.next();
            Serializable omotif = itmotif.next();
            if (! matches(othis, omotif))
              return false;
        }
        return true;
    }

    /** Returns true if this tuple (seen as a template) contains <code>t</code>.
     * This is the reverse of {@link #matches(Tuple)}. */
    public boolean contains(Tuple t) {
        return t.matches(this);
    }

    /**
     * Returns a deep copy of the tuple.
     * @return a deep copy of this object
     */
    /* Les éléments types sont représentés par des instances de Class, qui n'est pas cloneable.
     * Le plus simple de passer par une sérialisation/desérialisation ce qui marche pour toutes les classes qui implantent serializable.
     */
    public Tuple deepclone() {
        Tuple copy = null;
        try {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream (buf);
            out.writeObject (this);
            ObjectInputStream in = new ObjectInputStream (new ByteArrayInputStream (buf.toByteArray()));
            copy = (Tuple) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return copy;
    }

    /** Returns a string representation of this tuple.
     * @return a string representation of this tuple.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object o : this) {
            if (o instanceof Class) {
                sb.append(" ?" + ((Class<?>)o).getName());
            } else if (o instanceof String) {
                sb.append(" \"" + o + "\"");
            } else if (o instanceof Character) {
                sb.append(" '" + o + "'");
            } else {
                sb.append(" " + o.toString());
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    /** Parses a sequence of words to form a tuple.
     * The initial [ must be missing.
     * @param stk
     * @return
     * @throws TupleFormatException
     */
    private static Tuple valueOf(StringTokenizer stk) throws TupleFormatException {
        Tuple res = new Tuple();
        while (stk.hasMoreTokens()) {
            String token = stk.nextToken();
            if (token.equals("]"))
              return res; // BEWARE
            if (token.startsWith("\"") && token.endsWith("\"")) {
                String val = token.substring(1, token.length()-1);
                res.add(val);
            } else if (token.startsWith("'") && token.endsWith("'") && (token.length() > 2)) {
                res.add(new Character(token.charAt(1)));
            } else if (token.startsWith("?")) {
                String classname = token.substring(1);
                Class<?> c = null;
                final String[] prefixes = { "", "java.lang.", "linda." };
                for (String prefix : prefixes) {
                    try {
                        c = Class.forName(prefix + classname);
                        break; // oh !
                    } catch (ClassNotFoundException e) {
                        // ignore and try next prefix
                    }
                }
                if (c != null)
                  res.add(c);
                else
                  throw new TupleFormatException("Unknown class ?"+classname);
            } else if ("-0123456789".indexOf(token.charAt(0)) != -1) {
                int val;
                try {
                    val = Integer.valueOf(token);
                } catch (NumberFormatException e) {
                    throw new TupleFormatException("NumberFormatException on '"+token+"'");
                }
                res.add(val);
            } else if (token.equals("true")) {
                res.add(true);
            } else if (token.equals("false")) {
                res.add(false);
            } else if (token.equals("[")) {
                Tuple val = valueOf(stk); // yeepi!
                res.add(val);
            } else {
                throw new TupleFormatException("Unhandled chars: '"+token+"'");
            }
        }
        throw new TupleFormatException("Missing closing ']'");
    }

    /** Returns a Tuple with a value represented by the specified String.
     * Known values: integer (45, -67), boolean (true, false), string ("toto"), classname (?Integer), recursive tuple.
     * Examples: [ 3 4 ], [ ?Integer "toto" true 78 ?Boolean ], [ ?Integer ?Tuple ], [ [ true 78 ]  [ 3 4 [ 5 6 ] 7 ] ]
     * For these components, the parsable strings are identical to the printed strings.
     * Note: do not expect the parser to be resilient to arbitrary strings.
     *
     * @param s the string to be parsed.
     * @return a Tuple object holding the value represented by the string argument.
     * @throws TupleFormatException
     */
    public static Tuple valueOf(String s) throws TupleFormatException {
        StringTokenizer stk = new StringTokenizer(s);
        if (! stk.hasMoreTokens() || !stk.nextToken().equals("["))
          throw new TupleFormatException("Missing initial '['");
        Tuple res = valueOf(stk);
        if (stk.hasMoreTokens())
          throw new TupleFormatException("Trailing chars after ']'");
        return res;
    }

}
