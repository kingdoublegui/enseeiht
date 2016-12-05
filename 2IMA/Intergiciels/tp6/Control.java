import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;


public class Control extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton gobutton;
	private JButton printbutton;
	
	public Control() {
		super(); 
		this.gobutton=new JButton("Go");
		this.gobutton.addActionListener(this);
		this.printbutton=new JButton("Print");
		this.printbutton.addActionListener(this);
		this.getContentPane().setLayout(new FlowLayout());
		this.getContentPane().add(this.gobutton);
		this.getContentPane().add(this.printbutton);
		this.setSize(350, 200);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		//
		//System.out.println(arg0.getActionCommand());
		String cmd = arg0.getActionCommand();
		if (cmd.equals("Go")) LauncherImpl.go();
		if (cmd.equals("Print")) LauncherImpl.print();
		//this.dispose();
	}
	
	public static void main(String args[]) {
		new Control();
	}
}
