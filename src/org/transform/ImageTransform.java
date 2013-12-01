package org.transform;

import java.awt.image.BufferedImage;

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
		int maxY = -99999;
		int maxX = -99999;
		int minX = 99999;
		int minY = 99999;
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

		return image.getSubimage(minX, minY, maxX - minX, maxY - minY);
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
		double maxR = -99999;
		double minR = 99999;
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
} // x, height-y-1,

