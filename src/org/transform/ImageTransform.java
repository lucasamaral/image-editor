package org.transform;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageTransform {

	public BufferedImage getRotatedImage(BufferedImage img, double angle) {
		return getRotatedImage(img, img.getWidth() / 2, img.getHeight() / 2,
				angle);
	}

	public BufferedImage getRotatedImage(BufferedImage img,
			int rotationCenterX, int rotationCenterY, double angle) {

		BufferedImage image = initRotatedImage(img, rotationCenterX,
				rotationCenterY, angle);
		int rotatedX;
		int rotatedY;
		int maxY = Integer.MIN_VALUE;
		int maxX = Integer.MIN_VALUE;
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int centerY = image.getHeight() / 2 + 1;
		int centerX = image.getWidth() / 2 + 1;

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				rotatedX = centerX
						+ calculateRotatedX(x - rotationCenterX, y
								- rotationCenterY, angle);
				rotatedY = centerY
						+ calculateRotatedY(x - rotationCenterX, y
								- rotationCenterY, angle);
				if (maxX < rotatedX) {
					maxX = rotatedX;
				}
				if (maxY < rotatedY) {
					maxY = rotatedY;
				}
				if (minX > rotatedX) {
					minX = rotatedX;
				}
				if (minY > rotatedY) {
					minY = rotatedY;
				}

				image.setRGB(rotatedX, rotatedY, img.getRGB(x, y));

			}
		}
		// image = fillBlacks(image);
		return image.getSubimage(minX, minY, maxX - minX, maxY - minY);
	}

	private BufferedImage fillBlacks(BufferedImage image) {
		BufferedImage res = new BufferedImage(image.getWidth(),
				image.getHeight(), 1);
		for (int i = 1; i < image.getWidth() - 1; i++) {
			for (int j = 1; j < image.getHeight() - 1; j++) {
				int c = image.getRGB(i, j);
				if (c == Color.BLACK.getRGB()) {
					int cc1 = image.getRGB(i + 1, j);
					Color c1 = new Color(cc1);
					int cc2 = image.getRGB(i - 1, j);
					Color c2 = new Color(cc2);
					int cc3 = image.getRGB(i, j + 1);
					Color c3 = new Color(cc3);
					int cc4 = image.getRGB(i, j - 1);
					Color c4 = new Color(cc4);
					int count = 0;
					List<Color> n = new ArrayList<>();
					if (!c1.equals(Color.BLACK)) {
						count++;
						n.add(c1);
					}
					if (!c2.equals(Color.BLACK)) {
						count++;
						n.add(c2);
					}
					if (!c3.equals(Color.BLACK)) {
						count++;
						n.add(c3);
					}
					if (!c1.equals(Color.BLACK)) {
						count++;
						n.add(c4);
					}
					if (count > 1) {
						int newColor = mixture(n);
						res.setRGB(i, j, newColor);
					}
				} else {
					res.setRGB(i, j, c);
				}
			}
		}
		return res;
	}

	private int mixture(List<Color> n) {
		int sred = 0;
		int sblue = 0;
		int sgreen = 0;
		int salpha = 0;
		for (Color c : n) {
			sred += c.getRed();
			sblue += c.getBlue();
			sgreen += c.getGreen();
			salpha += c.getAlpha();
		}
		Color no = new Color(sred / n.size(), sgreen / n.size(), sblue
				/ n.size(), salpha / n.size());
		return no.getRGB();
	}

	private int calculateRotatedX(int x, int y, double rotatingAngle) {
		double angleBeforeRotation = Math.atan2(y, x);
		double r = getRotatedLength(x, y);
		return (int) Math.round(r
				* Math.cos(angleBeforeRotation + rotatingAngle));
	}

	private int calculateRotatedY(int x, int y, double rotatingAngle) {
		double angleBeforeRotation = Math.atan2(y, x);
		double r = getRotatedLength(x, y);
		return (int) Math.round(r
				* Math.sin(angleBeforeRotation + rotatingAngle));
	}

	private double getR(int x0, int x1, int y0, int y1) {
		return Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2));
	}

	private double getRotatedLength(int x, int y) {
		return getR(x, 0, y, 0);
	}

	private BufferedImage initRotatedImage(BufferedImage givenImage,
			int rotationCenterX, int rotationCenterY, double rotatingAngle) {
		double maxR = Integer.MIN_VALUE;
		int maxX = givenImage.getWidth() - 1;
		int maxY = givenImage.getHeight() - 1;
		int minX = 0;
		int minY = 0;

		double temp = getR(minX, rotationCenterX, minY, rotationCenterY);
		if (temp > maxR) {
			maxR = temp;
		}
		temp = getR(minX, rotationCenterX, maxY, rotationCenterY);
		if (temp > maxR) {
			maxR = temp;
		}
		temp = getR(maxX, rotationCenterX, maxY, rotationCenterY);
		if (temp > maxR) {
			maxR = temp;
		}
		temp = getR(maxX, rotationCenterX, minY, rotationCenterY);
		if (temp > maxR) {
			maxR = temp;
		}

		int L = (int) Math.round(3 * maxR + 1);
		return new BufferedImage(L, L, givenImage.getType());
	}

	public static BufferedImage cropImage(BufferedImage origina, int sx,
			int sy, int width, int height) {
		int w = origina.getWidth();
		int h = origina.getHeight();
		if (width < 0 || height < 0 || sx < 0 || sy < 0) {
			return origina;
		}
		if (sx + width > w || sy + height > h) {
			return origina;
		}
		BufferedImage framed = new BufferedImage(width, height, 1);
		int value;
		for (int i = sx; i < sx + width; i++) {
			for (int j = sy; j < sy + height; j++) {
				value = origina.getRGB(i, j);
				framed.setRGB(i - sx, j - sy, value);
			}
		}
		return framed;
	}

	public BufferedImage addFrame(BufferedImage original, int rgbValue,
			int thickness) {

		int w1 = original.getWidth();
		int h1 = original.getHeight();

		BufferedImage framed = new BufferedImage(w1 + 2 * thickness, h1 + 2
				* thickness, 1);
		int value;
		for (int i = 0; i < w1 + 2 * thickness; i++) {
			for (int j = 0; j < h1 + 2 * thickness; j++) {
				boolean left = (i < thickness);
				boolean right = (i >= w1 + thickness);
				boolean top = (j < thickness);
				boolean bottom = (j >= h1 + thickness);
				if (left || right || top || bottom) {
					value = rgbValue;
					framed.setRGB(i, j, value);
				} else {
					value = original.getRGB(i - thickness, j - thickness);
					framed.setRGB(i, j, value);
				}
			}
		}
		return framed;
	}

	public BufferedImage polygonalCrop(BufferedImage image, List<Point> points) {
		Poligono p = new Poligono(points);
		int w = image.getWidth();
		int h = image.getHeight();
		for (Point poin : points) {
			if (poin.x < 0 || poin.x > w || poin.y < 0 || poin.y > h) {
				return image;
			}
		}
		BufferedImage res = p.process(image);
		points.clear();
		return res;
	}
}
