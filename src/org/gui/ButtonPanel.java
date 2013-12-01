package org.gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton grayButton;
	private JButton oldButton;
	private JButton negativeButton;
	
	public ButtonPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		grayButton = new JButton("Escala de Cinza");
		
		oldButton = new JButton("Envelhecer");
		
		negativeButton = new JButton("Negativo");
		
		add(grayButton);
		add(oldButton);
		add(negativeButton);
	}

	public JButton getGrayButton() {
		return grayButton;
	}

	public JButton getOldButton() {
		return oldButton;
	}

	public JButton getNegativeButton() {
		return negativeButton;
	}
	

}
