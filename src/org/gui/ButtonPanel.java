package org.gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton grayButton;
	private JButton oldButton;
	private JButton negativeButton;
	private JButton frameButton;
	private JButton rotateClockwise;
	private JButton rotateCounterClock;
	

	public ButtonPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		grayButton = new JButton("Escala de Cinza");
		oldButton = new JButton("Envelhecer");
		negativeButton = new JButton("Negativo");
		frameButton = new JButton("Moldura");
		rotateClockwise = new JButton("Girar sentido horário");
		rotateCounterClock = new JButton("Girar sentido anti-horário");
		
		add(grayButton);
		add(oldButton);
		add(negativeButton);
		add(frameButton);
		add(rotateClockwise);
		add(rotateCounterClock);
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
	
	public JButton getFrameButton() {
		return frameButton;
	}

	public JButton getRotateClockwise() {
		return rotateClockwise;
	}

	public JButton getRotateCounterClock() {
		return rotateCounterClock;
	}

}
