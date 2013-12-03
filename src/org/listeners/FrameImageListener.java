package org.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import org.gui.AppScreen;
import org.transform.ImageTransform;

public class FrameImageListener implements ActionListener {

	private AppScreen app;

	public FrameImageListener(AppScreen app) {
		super();
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ImageTransform transform = new ImageTransform();
		Color borderColor = JColorChooser.showDialog(app,
				"Escolha a cor da borda", null);
		if (borderColor != null) {
			this.app.setImage(transform.addFrame(app.getImage(),
					borderColor.getRGB(), 5));
		}
	}

}
