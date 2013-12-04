package org.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.gui.AppScreen;
import org.gui.ImagePanel;

public class CropListener implements MouseListener, MouseMotionListener {

	private AppScreen ap;
	private ImagePanel ip;
	private int x1, x2, y1, y2;

	// private BufferedImage tempImage;

	public CropListener(AppScreen ap, ImagePanel ip) {
		this.ap = ap;
		this.ip = ip;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		System.out.println(arg0.getX() + " - " + arg0.getY());
		x1 = arg0.getX();
		y1 = arg0.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		ip.getGraphics().drawImage(ip.getImage(), 0, 0, null);
		System.out.println(arg0.getX() + " - " + arg0.getY());
		x2 = arg0.getX();
		y2 = arg0.getY();
		correctCoordinates();
		if (ap.isCropping() && x1 != x2 && y1 != y2) {
			ap.crop(x1, y1, x2 - x1, y2 - y1);
		}
	}

	private void correctCoordinates() {
		if (x1 > x2) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}
		if (y1 > y2) {
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (ap.isCropping()) {
			ip.getGraphics().drawImage(ip.getImage(), 0, 0, null);
			x2 = arg0.getX();
			y2 = arg0.getY();
			if (insideImage()) {
				ip.getGraphics().drawRect(Math.min(x1, x2), Math.min(y1, y2),
						Math.abs(x2 - x1), Math.abs(y2 - y1));
			}
		}
	}

	private boolean insideImage() {
		int w = ap.getImage().getWidth();
		int h = ap.getImage().getHeight();
		if (x1 > 0 && x1 < w && x2 > 0 && x2 < w && y1 > 0 && y1 < h && y2 > 0
				&& y2 < h) {
			return true;
		}
		return false;
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
