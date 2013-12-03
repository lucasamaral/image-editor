package org.gui;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class LeftPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton grayButton;
	private JButton oldButton;
	private JButton negativeButton;
	private JButton frameButton;
	private JButton gradientFrameButton;
	private JButton rotateClockwise;
	private JButton rotateCounterClock;
	private JButton toggleCrop;
	private JSlider brightnessSlider;
	

	public LeftPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		grayButton = new JButton("Escala de Cinza");
		oldButton = new JButton("Envelhecer");
		negativeButton = new JButton("Negativo");
		frameButton = new JButton("Moldura");
		gradientFrameButton = new JButton("Moldura Gradiente");
		rotateClockwise = new JButton("Girar sentido horário");
		rotateCounterClock = new JButton("Girar sentido anti-horário");
		toggleCrop = new JButton("Iniciar cropping");
		
		
		
		add(grayButton);
		add(oldButton);
		add(negativeButton);
		add(frameButton);
		add(gradientFrameButton);
		add(rotateClockwise);
		add(rotateCounterClock);
		add(toggleCrop);
		
		brightnessSlider = new JSlider(1, 100);
		brightnessSlider.setOrientation(JSlider.VERTICAL);
		brightnessSlider.setValue(1);
		
		JPanel pan = new JPanel();
		pan.setLayout(new FlowLayout(FlowLayout.CENTER));
		pan.add(brightnessSlider);
		pan.add(new JLabel("Brilho"));
		add(pan);
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
	
	public JButton getGradientFrameButton() {
		return gradientFrameButton;
	}

	public JButton getRotateClockwise() {
		return rotateClockwise;
	}

	public JButton getRotateCounterClock() {
		return rotateCounterClock;
	}

	public JSlider getBrightnessSlider() {
		return brightnessSlider;
	}
	
	public JButton getToggleCrop(){
		return toggleCrop;
	}
	
}
