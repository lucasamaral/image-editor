package org.transform;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Poligono {

	private class Segmento {
		public Point p1, p2;

		public Segmento(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
		}

		public String toString() {
			return "(" + p1 + "-" + p2 + ")";
		}

		public int getMinY() {
			if (p1.y < p2.y)
				return p1.y;
			return p2.y;
		}

		public int getMaxY() {
			if (p1.y < p2.y)
				return p2.y;
			return p1.y;
		}

		public float evaluateX(int y) {
			return (p2.x - p1.x) * (y - p1.y) / (p2.y - p1.y);
		}
	}

	private int minY = Integer.MAX_VALUE;
	private int maxY = Integer.MIN_VALUE;
	private int maxX = Integer.MIN_VALUE;
	private int minX = Integer.MAX_VALUE;
	private Polygon poly = new Polygon();

	public Poligono(List<Point> points) {
		System.out.println("Starting");
		for (Point p : points) {
			poly.addPoint(p.x, p.y);
		}
		for (Point p : points) {
			if (p.y < minY) {
				minY = p.y;
			}
			if (p.y > maxY) {
				maxY = p.y;
			}
			if (p.x < minX) {
				minX = p.x;
			}
			if (p.x > maxX) {
				maxX = p.x;
			}
		}
	}

	public BufferedImage process(BufferedImage original) {
		BufferedImage result = new BufferedImage(maxX - minX + 1, maxY - minY
				+ 1, 1);
		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j < maxY; j++) {
				if (poly.contains(i, j)) {
					result.setRGB(i - minX, j - minY, original.getRGB(i, j));
				} else {
					result.setRGB(i - minX, j - minY, Color.WHITE.getRGB());
				}
			}
		}
		return result;
	}
}
