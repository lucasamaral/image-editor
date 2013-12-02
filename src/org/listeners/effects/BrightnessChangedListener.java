package org.listeners.effects;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.gui.AppScreen;

public class BrightnessChangedListener implements ChangeListener {

	private AppScreen app;
	
	
	public BrightnessChangedListener(AppScreen app) {
		super();
		this.app = app;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			int bright = (int)source.getValue();
			float factor = 1.0f + bright/100.0f; 
			RescaleOp op = new RescaleOp(factor, 0, null);
			BufferedImage image = app.getImage();
			image = op.filter(image, null);
			app.setImage(image);
		}
	}

}
