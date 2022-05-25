package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class QtdPlayerListener implements ActionListener{

	Component c;
	JRadioButton j;
	
	QtdPlayerListener(Component x, JRadioButton y){
		c=x;
		j=y;
	}
	
	public void actionPerformed(ActionEvent e) { //quando o botao é selecionado, troca o valor da quantidade de jogadores
		int qtdPlayer = Integer.parseInt(j.getText());
		((MainMenu)c).setQtdPlayer(qtdPlayer);
	}
}
