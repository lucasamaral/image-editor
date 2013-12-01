package org.listeners.effects;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.gui.AppScreen;

public class ToggleCropListener implements ActionListener {

	private AppScreen ap;
	private JButton jb;

	public ToggleCropListener(AppScreen appScreen, JButton jb) {
		this.ap = appScreen;
		this.jb = jb;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ap.toggleCropping();
		if(ap.isCropping()){
			jb.setText("Parar Cropping");
		}else{
			jb.setText("Iniciar Cropping");
		}
		jb.repaint();
	}

}
