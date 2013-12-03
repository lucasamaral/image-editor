package org.listeners.effects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.gui.AppScreen;

public class TogglePolygonalCropListener implements ActionListener {

	private AppScreen ap;
	private JButton jb;

	public TogglePolygonalCropListener(AppScreen appScreen, JButton jb) {
		this.ap = appScreen;
		this.jb = jb;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("AAA");
		ap.togglePolygonalCropping();
		if(ap.isPolygonalCropping()){
			jb.setText("Parar Cropping poligonal");
		}else{
			jb.setText("Iniciar Cropping poligonal");
		}
		jb.repaint();
	}

}
