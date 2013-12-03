package org.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import org.gui.AppScreen;
import org.transform.ImageTransform;

public class GradientFrameImageListener implements ActionListener {

	private AppScreen app;

	public GradientFrameImageListener(AppScreen app) {
		super();
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ImageTransform transform = new ImageTransform();
		Color innerBorderColor = JColorChooser.showDialog(app,
				"Escolha a cor interna da borda", null);
		Color outerBorderColor = JColorChooser.showDialog(app,
				"Escolha a cor externa da borda", null);
		if (innerBorderColor != null && outerBorderColor != null) {
			for (float t = 0.0f; t <= 1; t += 0.05) {
				this.app.setImage(transform.addFrame(app.getImage(),
						mixture(innerBorderColor, outerBorderColor, t), 1));
			}
		}
	}

	private int mixture(Color c1, Color c2, float t) {
		int sred = 0;
		int sblue = 0;
		int sgreen = 0;
		int salpha = 0;
		sred = (int) ((1 - t) * c1.getRed() + t * c2.getRed());
		sblue = (int) ((1 - t) * c1.getBlue() + t * c2.getBlue());
		sgreen = (int) ((1 - t) * c1.getGreen() + t * c2.getGreen());
		salpha = (int) ((1 - t) * c1.getAlpha() + t * c2.getAlpha());
		Color no = new Color(sred, sgreen, sblue, salpha);
		return no.getRGB();
	}

}
