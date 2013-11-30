package org.transform;

import java.awt.image.BufferedImage;

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

	public BufferedImage oldImage() {

		return null;
	}

	public BufferedImage negativeImage() {

		return null;
	}

	// TODO: Criar três métodos de moldura

}
