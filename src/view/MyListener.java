package view;
import javax.swing.*;

import java.awt.Component;
import java.awt.event.*;

public class MyListener implements ActionListener{

	Component c;
	
	public MyListener(Component x) {
		c=x;
	}
	
	public void actionPerformed(ActionEvent e) {
		Board b = new Board();
	}
}
