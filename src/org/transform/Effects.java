package org.transform;

import java.awt.image.BufferedImage;
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

	public BufferedImage oldImage(BufferedImage img, int sepiaIntensity) {
		int sepiaDepth = 20;

	    int w = img.getWidth();
	    int h = img.getHeight();

	    WritableRaster raster = img.getRaster();
	    
	    int[] pixels = new int[w*h*3];
	    raster.getPixels(0, 0, w, h, pixels);
	    
	    for (int i=0;i<pixels.length; i+=3) {
	        int r = pixels[i];
	        int g = pixels[i+1];
	        int b = pixels[i+2];

	        int gry = (r + g + b) / 3;
	        r = g = b = gry;
	        r = r + (sepiaDepth * 2);
	        g = g + sepiaDepth;

	        if (r>255) r=255;
	        if (g>255) g=255;
	        if (b>255) b=255;

	        // cor azul escuro para aumentar o efeito sepia
	        b-= sepiaIntensity;

	        // deixando as cores normalizadas
	        if (b<0) b=0;
	        if (b>255) b=255;

	        pixels[i] = r;
	        pixels[i+1]= g;
	        pixels[i+2] = b;
	    }
	    raster.setPixels(0, 0, w, h, pixels);
		return img;
	}

	public BufferedImage negativeImage(BufferedImage img) {

		 int w1 = img.getWidth();
	        int h1 = img.getHeight();
	        // int value[][] = new int[w1][h1];
	        BufferedImage gray = new BufferedImage(w1, h1, 1);
	        int value, alpha, r, g, b;
	        for (int i = 0; i < w1; i++) {
	            for (int j = 0; j < h1; j++) {
	                value = img.getRGB(i, j); // store value
	                alpha = get_alpha(value);
	                r = 255 - get_red(value);
	                g = 255 - get_green(value);
	                b = 255 - get_blue(value);

	                value = createRgb(alpha, r, g, b);
	                gray.setRGB(i, j, value);
	            }
	        }
	        return gray;
	}

	// TODO: Criar três métodos de moldura

}
