/*
** @author philippe.queinnec@enseeiht.fr
** Based on IBM TSpaces exemples.
**
**/

package applications.whiteboard;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

import linda.*;
import linda.Linda.eventMode;
import linda.Linda.eventTiming;

/**
 ** The panel of the whiteboard.
 **
 ** This handles the panel where the lines and points are drawn.
 ** All of the linda handling is done inside this object.
 **
 ** [ KEY_WHITEBOARD, CMD_ERASE ]
 ** [ KEY_WHITEBOARD, CMD_RECTANGLE, Rectangle, Color ]
 **
 ** (where KEY_WHITEBOARD="Whiteboard", CMD_ERASE="Erase", CMD_RECTANGLE="Rectangle")
 **
 */
public class WhiteboardPanel extends Panel
    implements MouseListener, MouseMotionListener {

    public static final String KEY_WHITEBOARD = "Whiteboard";
    public static final String CMD_ERASE = "Erase";
    public static final String CMD_RECTANGLE = "Rectangle";

    public static final int LINES = 0;
    public static final int POINTS = 1;
    private int    mode = LINES;

    /** The lines and their respective colors that this client knows about. */
    private Vector<Rectangle> lines = new Vector<Rectangle>();
    private Vector<Color> colors = new Vector<Color>();

    protected static Color BACKGROUND_COLOR = Color.white;
    private int x1, y1;
    private int x2, y2;
    private int xl, yl;
    private WhiteboardControls Controls = null;
    /**
     ** This holds a reference to the current Linda.
     */
    protected Linda linda = null;

    private boolean eraseFlag = false;   // set true when erase command received

    private Tuple motifErase = new Tuple(KEY_WHITEBOARD, CMD_ERASE);
    private Tuple motifRectangle = new Tuple(KEY_WHITEBOARD, CMD_RECTANGLE,	Rectangle.class, Color.class);

    /** Initialization routine.
    */
    public WhiteboardPanel(Whiteboard caller, Linda linda) {
        // Place the controls at the top of the parent window
        //System.out.println("init entered");
        Controls = new WhiteboardControls(this);
        caller.add("North",Controls);

        setBackground(BACKGROUND_COLOR);
        addMouseMotionListener(this);
        addMouseListener(this);

        // Handle the user exiting/killing the application
        if ( caller.appFrame != null )
          caller.appFrame.addWindowListener( new WindowAdapter()  {
                  public void
                      windowClosing (WindowEvent e) {
                      System.out.println("Window Close event");
                      WhiteboardPanel.this.term();    // give it a chance to cleanup
                      //System.exit(0);
                  }
              } );
        
        this.linda = linda;

        // Create a template to indicate what we are interested in.
        linda.eventRegister(eventMode.READ, eventTiming.FUTURE, motifErase, new CallbackErase());
        linda.eventRegister(eventMode.READ, eventTiming.FUTURE, motifRectangle, new CallbackRectangle());


        System.out.println("Scan for current status");
        // During initialization, we need to read all the current line and color
        // Vectors stored at the lindaSpaces server.
        // create a template to indicate what we are interested in
        Tuple match = new Tuple(KEY_WHITEBOARD, CMD_RECTANGLE,	Rectangle.class, Color.class);
        Collection<Tuple> tupleSet = linda.readAll(match);
        for (Tuple t : tupleSet) {
        	System.out.println("Tuple " + t);
        	Rectangle line = (Rectangle)t.get(2);
        	Color color = (Color)t.get(3);
        	// add the line and color to the Vectors that we maintain.
        	lines.add(line);
        	colors.add(color);
        }

        //System.out.println("Now paint initial screen");
        Graphics g = this.getGraphics();
        if (g != null)
          this.paint(g);
        setDrawMode(LINES);
    } // init

    /**
     ** This is called when the windowClosing event arrives.
     *  We should tell the server that we are going away.
     **
     */
    public void term() {
        //System.out.println("term()");
    	System.exit(0);
    }

    /**
     ** Global Erase of the whiteboard.
     **
     ** Since we will be informed of this in the callback,
     ** we will let the callback update the set of all lines.
     **
     ** We won't worry about problems caused by requests arriving at the
     ** the server and clients in different orders.
     */
    public void eraseAll() {
        System.out.println("Erase all");
        // Delete all rectangle tuples,
        linda.takeAll(new Tuple(KEY_WHITEBOARD, CMD_RECTANGLE, Rectangle.class, Color.class));
        // Tell all clients that we did an erase by writing an erase tuple,
        linda.write(new Tuple(KEY_WHITEBOARD, CMD_ERASE));
        // and delete this tuple (potential synchronization problem !)
        linda.takeAll(new Tuple(KEY_WHITEBOARD, CMD_ERASE));
    }	

    public void exitcmd() {
        System.out.println("Exiting");
        term();
        //System.exit(0);
    }

    /**
     ** Add a new rectangle (line or point) to the tuple space.
     * @param rectangle - the coordinates of the rectangle
     * @param color - the color of the rectangle
     */
    private void addRectangle (Rectangle rectangle, Color color)
    {
        // Build a new rectangle tuple and write it into the tuple space.
    	Tuple publish = new Tuple(KEY_WHITEBOARD, CMD_RECTANGLE, rectangle, color);
    	linda.write(publish);
    }

    /**
     ** Paints the panel.
     ** The real paint method is called first for the objects that
     ** this user has generated, and then for a vector of objects
     ** that have been received form linda.
     **
     ** If an Erase request was received from linda then the
     ** EraseFlag was set and we will erase the window before
     ** proceeding.
     **
     ** @param g - the graphics context
     */
    public void paint(Graphics g) {
        //System.out.println("paint() ");
        if (eraseFlag) {
            g.setColor(BACKGROUND_COLOR);
            g.fillRect(0,0,getSize().width,getSize().height);
            eraseFlag = false;
        }
        paint(g,lines,colors);
    }

    /**
     ** Paints the panel based on the vectors of lines and colors.
     **
     ** @param g - the graphics context
     ** @param lines - A vector of Rectangle objects
     ** @param colors - A vector of color objects.
     */
    public void paint(Graphics g,Vector<Rectangle> lines, Vector<Color> colors) {
        int np = lines.size();
        /* draw the current lines */
        g.setColor(getForeground());
        g.setPaintMode();
        for (int i=0; i < np; i++) {
            Rectangle p = lines.elementAt(i);
            g.setColor(colors.elementAt(i));
            if (p.width != -1) {
                g.drawLine(p.x, p.y, p.width, p.height);
            } else {
                g.drawLine(p.x, p.y, p.x, p.y);
            } // if
        } // for
        if (mode == LINES) {
            g.setXORMode(getBackground());
            if (xl != -1) {
                /* erase the last line. */
                g.drawLine(x1, y1, xl, yl);
            } // if
            g.setColor(getForeground());
            g.setPaintMode();
            if (x2 != -1) {
                g.drawLine(x1, y1, x2, y2);
            } // if
        } // if
    } // paint

    /**
     ** Set the draw mode.
     ** @param mode - the draw mode
     */
    public void setDrawMode(int mode) {
        switch (mode) {
          case LINES:
          case POINTS:
            this.mode = mode;
            break;
          default:
            throw new IllegalArgumentException();
        } // switch
    } // setDrawMode

    /**
     ** The mouse was dragged.
     ** @param e - the mouse event
     */
    public void mouseDragged(MouseEvent e) {
        e.consume();
        switch (mode) {
          case LINES:
            xl = x2;
            yl = y2;
            x2 = e.getX();
            y2 = e.getY();
            break;
          case POINTS:
          default:
            addRectangle(new Rectangle(x1, y1, e.getX(), e.getY()), getForeground());
            x1 = e.getX();
            y1 = e.getY();
            break;
        } // switch
        repaint();
    } // mouseDragged

    /**
     ** The mouse was moved.
     ** @param e - the mouse event
     */
    public void mouseMoved(MouseEvent e) {
        // not much to do here
    } // mouseMoved

    /**
     ** The mouse button was pressed.
     ** @param e - the mouse event
     */
    public void mousePressed(MouseEvent e) {
        e.consume();
        switch (mode) {
          case LINES:
            x1 = e.getX();
            y1 = e.getY();
            x2 = -1;
            break;
          case POINTS:
          default:
            addRectangle(new Rectangle(e.getX(), e.getY(), -1, -1), getForeground());
            x1 = e.getX();
            y1 = e.getY();
            repaint();
            break;
        } // break
    } // mousePressed

    /**
     ** The mouse button was released.
     ** @param e - the mouse event
     */
    public void mouseReleased(MouseEvent e) {
        e.consume();
        switch (mode) {
          case LINES:
            addRectangle(new Rectangle(x1, y1, e.getX(), e.getY()), getForeground());
            x2 = xl = -1;
            break;
          case POINTS:
          default:
            break;
        } // switch
        repaint();
    } // mouseReleased

    /**
     ** The mouse entered this panel.
     ** @param e - the mouse event
     */
    public void mouseEntered(MouseEvent e) {
    }

    /**
     ** The mouse left this panel.
     ** @param e - the mouse event
     */
    public void mouseExited(MouseEvent e) {
    }

    /**
     ** The mouse button was clicked.
     ** @param e - the mouse event
     */
    public void mouseClicked(MouseEvent e) {
    }
    
    /***************************************************************/
    
    private class CallbackErase implements linda.Callback {
		public void call(Tuple t) {
			System.out.println("Erase Request received from server");
			lines.clear();
			colors.clear();
			eraseFlag = true;
	        // Now go repaint the screen.
	        int savemode = mode;
	        mode = -1;
	        paint(getGraphics());
	        setDrawMode(savemode);
            linda.eventRegister(eventMode.READ, eventTiming.FUTURE, motifErase, this);
		}	
    }
    
    private class CallbackRectangle implements linda.Callback {
		public void call(Tuple t) {
			System.out.println("Rectangle Request received from server");
            Rectangle line = (Rectangle)(t.get(2));
            Color color = (Color)(t.get(3));
            lines.add(line);
            colors.add(color);
            // Now go repaint the screen.
            int savemode = mode;
            mode = -1;
            paint(getGraphics());
            setDrawMode(savemode);
            linda.eventRegister(eventMode.READ, eventTiming.FUTURE, motifRectangle, this);
		}
    }

}

