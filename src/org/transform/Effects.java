package org.transform;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Effects {

	private BufferedImage original;

	public Effects(BufferedImage original) {
		super();
		this.original = original;
	}

	public BufferedImage grayImage() {
		int w1 = this.original.getWidth();
		int h1 = this.original.getHeight();

		BufferedImage gray = new BufferedImage(w1, h1, 1);
		int value, alpha, r, g, b;
		for (int i = 0; i < w1; i++) {
			for (int j = 0; j < h1; j++) {
				value = this.original.getRGB(i, j);
				alpha = get_alpha(value);
				r = get_red(value);
				g = get_green(value);
				b = get_blue(value);

				value = (r + g + b) / 3;
				r = g = b = value;
				value = createRgb(alpha, r, g, b);
				gray.setRGB(i, j, value);
			}
		}
		return gray;
	}

	public int createRgb(int alpha, int r, int g, int b) {
		int rgb = (alpha << 24) + (r << 16) + (g << 8) + b;
		return rgb;
	}

	public int get_alpha(int rgb) {
		return (rgb >> 24) & 0xFF;
	}

	public int get_red(int rgb) {
		return (rgb >> 16) & 0xFF;
	}

	public int get_green(int rgb) {
		return (rgb >> 8) & 0xFF;
	}

	public int get_blue(int rgb) {
		return rgb & 0xFF;
	}

	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	public BufferedImage oldImage(int sepiaIntensity) {
		int sepiaDepth = 20;

		BufferedImage old = deepCopy(this.original);
		int w = old.getWidth();
		int h = old.getHeight();

		WritableRaster raster = old.getRaster();

		int[] pixels = new int[w * h * 3];
		raster.getPixels(0, 0, w, h, pixels);

		for (int i = 0; i < pixels.length; i += 3) {
			int r = pixels[i];
			int g = pixels[i + 1];
			int b = pixels[i + 2];

			int gry = (r + g + b) / 3;
			r = g = b = gry;
			r = r + (sepiaDepth * 2);
			g = g + sepiaDepth;

			if (r > 255)
				r = 255;
			if (g > 255)
				g = 255;
			if (b > 255)
				b = 255;

			// cor azul escuro para aumentar o efeito sepia
			b -= sepiaIntensity;

			// deixando as cores normalizadas
			if (b < 0)
				b = 0;
			if (b > 255)
				b = 255;

			pixels[i] = r;
			pixels[i + 1] = g;
			pixels[i + 2] = b;
		}
		raster.setPixels(0, 0, w, h, pixels);
		return old;
	}

	public BufferedImage negativeImage() {

		int w1 = this.original.getWidth();
		int h1 = this.original.getHeight();
		
		BufferedImage negative = new BufferedImage(w1, h1, 1);
		int value, alpha, r, g, b;
		for (int i = 0; i < w1; i++) {
			for (int j = 0; j < h1; j++) {
				value = this.original.getRGB(i, j);
				alpha = get_alpha(value);
				r = 255 - get_red(value);
				g = 255 - get_green(value);
				b = 255 - get_blue(value);

				value = createRgb(alpha, r, g, b);
				negative.setRGB(i, j, value);
			}
		}
		return negative;
	}

	public BufferedImage addFrame(int rgbValue, int thickness) {
		
		int w1 = this.original.getWidth();
		int h1 = this.original.getHeight();
		
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
					value = this.original.getRGB(i - thickness, j - thickness);
					framed.setRGB(i, j, value);
				}
			}
		}
		return framed;
	}
	// TODO: Criar três métodos de moldura

}
