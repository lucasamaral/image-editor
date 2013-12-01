package org.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage imageToShow;

	public ImagePanel(BufferedImage imageToShow) {
		super();
		this.imageToShow = imageToShow;
		setPreferredSize(new Dimension(this.imageToShow.getWidth(), this.imageToShow.getHeight()));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imageToShow, 0, 0, null);
	}
	
	public void setImage(BufferedImage bi){
		imageToShow = bi;
	}

	public BufferedImage getImage() {
		return imageToShow;
	}
	
	
}
