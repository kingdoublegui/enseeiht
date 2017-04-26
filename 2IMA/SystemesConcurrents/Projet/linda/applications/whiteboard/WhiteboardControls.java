/*
** @author philippe.queinnec@enseeiht.fr
** Based on IBM TSpaces exemples.
**
**/

package applications.whiteboard;

import java.awt.event.*;
import java.awt.*;

/**
 ** The controls for the whiteboard.
 ** 
 */
public class WhiteboardControls extends Panel implements ItemListener {

    private WhiteboardPanel target;
    private static String ERASEALL_LABEL = "Erase All";
    private static String EXIT_LABEL = "Exit";
    
    private Button eraseAllButton = null;
    private Button exitButton = null;
    
    /**
     ** The constructor.
     **
     ** @param target - the whiteboard panel
     */
    public WhiteboardControls(Panel _target) {
        
        this.target = (WhiteboardPanel)_target;
        Panel pLine1 = new Panel();
        Panel pLine2 = new Panel();
        setLayout(new GridLayout(2,1));
        add(pLine1);
        add(pLine2);
        
        pLine1.setLayout(new FlowLayout());
        
        eraseAllButton = new Button();
        eraseAllButton.setLabel(ERASEALL_LABEL);
        eraseAllButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        target.eraseAll();
                    }
                });
        pLine1.add(eraseAllButton);
        
        exitButton = new Button();
        exitButton.setLabel(EXIT_LABEL);
        exitButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        target.exitcmd();
                    }
                });
        pLine1.add(exitButton);

        pLine1.setBackground(Color.lightGray);
        pLine2.setBackground(Color.lightGray);
                
        target.setForeground(Color.red);
        CheckboxGroup group = new CheckboxGroup();
        Checkbox b;
        pLine2.add(b = new Checkbox(null, group, false));
        b.addItemListener(this);
        b.setBackground(Color.red);
        pLine2.add(b = new Checkbox(null, group, false));
        b.addItemListener(this);
        b.setBackground(Color.green);
        pLine2.add(b = new Checkbox(null, group, false));
        b.addItemListener(this);
        b.setBackground(Color.blue);
        pLine2.add(b = new Checkbox(null, group, false));
        b.addItemListener(this);
        b.setBackground(Color.pink);
        pLine2.add(b = new Checkbox(null, group, false));
        b.addItemListener(this);
        b.setBackground(Color.orange);
        pLine2.add(b = new Checkbox(null, group, true));
        b.addItemListener(this);
        b.setBackground(Color.black);
        target.setForeground(b.getForeground());
        
        Choice shapes = new Choice();
        shapes.addItemListener(this);
        shapes.addItem("Lines");
        shapes.addItem("Points");
        shapes.setBackground(Color.lightGray);
        pLine2.add(shapes);
    } // WhiteboardControls

    /**
     ** Paints me.
     **
     ** @param g - a graphics context
     */
    public void paint(Graphics g) {
        Rectangle r = getBounds();
        g.setColor(Color.lightGray);
        g.draw3DRect(0, 0, r.width, r.height, false);
    }

    /**
     ** The state of the item was changed.
     **
     ** @param e - the item event
     */
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof Checkbox) {
            target.setForeground(((Component)e.getSource()).getBackground());
        } else if (e.getSource() instanceof Choice) {
            String choice = (String) e.getItem();
            if (choice.equals("Lines")) {
                target.setDrawMode(WhiteboardPanel.LINES);
            } else if (choice.equals("Points")) {
                target.setDrawMode(WhiteboardPanel.POINTS);
            } 
        }
    }

} // WhiteboardControls

