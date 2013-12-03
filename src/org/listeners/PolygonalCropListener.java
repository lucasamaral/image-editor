package org.listeners;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import org.gui.AppScreen;
import org.gui.ImagePanel;
import org.transform.ImageTransform;

public class PolygonalCropListener implements MouseListener,
		MouseMotionListener {

	private AppScreen ap;
	private ImagePanel ip;
	Graphics2D g;
	private List<Point> points = new ArrayList<>();

	// private BufferedImage tempImage;

	public PolygonalCropListener(AppScreen ap, ImagePanel ip) {
		this.ap = ap;
		this.ip = ip;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (ap.isPolygonalCropping()) {
			if (arg0.getButton() == MouseEvent.BUTTON1) {
				Point pn = arg0.getPoint();
				points.add(pn);
				g = (Graphics2D) ip.getGraphics();
				for (Point p : points) {
					g.drawOval(p.x - 1, p.y - 1, 2, 2);
				}
			} else if (arg0.getButton() == MouseEvent.BUTTON3) {
				ImageTransform it = new ImageTransform();
				ap.setImage(it.polygonalCrop(ap.getImage(), points));
				ap.finishPolygonalCropping();
			}
		}

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
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}
