package org.listeners.effects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gui.AppScreen;
import org.transform.Effects;

public class FrameImageListener implements ActionListener {

	private AppScreen app;
	
	public FrameImageListener(AppScreen app) {
		super();
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Effects effect = new Effects(app.getImage());
		
		this.app.setImage(effect.addFrame(200, 5));
	}

}
