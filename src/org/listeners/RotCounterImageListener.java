package org.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gui.AppScreen;
import org.transform.ImageTransform;

public class RotCounterImageListener implements ActionListener {

	private AppScreen app;

	public RotCounterImageListener(AppScreen app) {
		super();
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ImageTransform transform = new ImageTransform();
		this.app.setImage(transform.getRotatedImage(app.getImage(), -Math.PI/2));
	}

}
