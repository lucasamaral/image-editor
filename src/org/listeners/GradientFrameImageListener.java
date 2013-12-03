package org.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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
		BufferedImage current = app.getImage();
		Color innerBorderColor = JColorChooser.showDialog(app,
				"Escolha a cor interna da borda", null);
		Color outerBorderColor = JColorChooser.showDialog(app,
				"Escolha a cor externa da borda", null);
		if (innerBorderColor != null && outerBorderColor != null) {
			for (float t = 0.0f; t <= 1; t += 0.05) {
				current = transform.addFrame(current,
						mixture(innerBorderColor, outerBorderColor, t), 1);
			}
			app.setImage(current);
		}
	}

	private int mixture(Color c1, Color c2, float t) {
		int sred = (int) ((1 - t) * c1.getRed() + t * c2.getRed());
		int sblue = (int) ((1 - t) * c1.getBlue() + t * c2.getBlue());
		int sgreen = (int) ((1 - t) * c1.getGreen() + t * c2.getGreen());
		int salpha = (int) ((1 - t) * c1.getAlpha() + t * c2.getAlpha());
		Color no = new Color(sred, sgreen, sblue, salpha);
		return no.getRGB();
	}

}
