/*
** @author philippe.queinnec@enseeiht.fr
** Based on IBM TSpaces exemples.
**
**/

package applications.whiteboard;

import java.awt.*;

/**
 ** This class implements a 'shared' whiteboard to be used with Linda.
 ** All of the Linda related code is WhiteboardPanel.java
 ** 
*/
public class Whiteboard extends Panel {

    protected static final int WIDTH = 300;
    protected static final int HEIGHT = 350;
    public Frame appFrame;
        
    public Whiteboard(String serverURI) {
        appFrame = new Frame("Whiteboard");
        appFrame.add("Center", this);
        appFrame.setSize(WIDTH,HEIGHT);   
    
        setLayout(new BorderLayout());
        //WhiteboardPanel wp = new WhiteboardPanel(this, new server.LindaClient(serverURI));
        //add("Center", wp);
 
        appFrame.setVisible(true);

    }
        
    /*** main **
     ** Run the whiteboard as an application.
     **
     ** @param args - command line arguments
     */
    public static void main(String args[]) {
    	if (args.length != 1) {
    		System.err.println("Whiteboard serverURI.");
    		return;
    	}
        new Whiteboard(args[0]);
    }
}

