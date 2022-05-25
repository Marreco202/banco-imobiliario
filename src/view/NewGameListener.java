package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NewGameListener implements ActionListener{

	Component c;
	
	
	public NewGameListener(Component x) {
		c=x;
	}
	
	public void actionPerformed(ActionEvent e) {
		//JRadioButton j = (JRadioButton) c;
		//int qtdPlayers = Integer.parseInt(j.getText());
		int qtdPlayer = ((MainMenu)c).getQtdPlayer();
		Board b = new Board(qtdPlayer);
		c.setVisible(false);
		System.out.println(qtdPlayer);
	}

	
	
}
