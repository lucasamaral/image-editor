package org.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

import org.listeners.MenuLoadImageListener;
import org.listeners.MenuSaveImageListener;
import org.transform.Effects;

public class AppScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	private Effects effect;
	private ImagePanel imagePanel;

	public AppScreen() {
		initEverything();
		setTitle("Image Editor CCI36");

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});
	}

	private void initEverything() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 600));
		BufferedImage original = readImage("tnt.jpg");
		this.effect = new Effects(original);
		this.imagePanel = new ImagePanel(original);

		getContentPane().add(this.imagePanel);

		JMenuBar blueMenuBar = new JMenuBar();
		JMenu loadImage = new JMenu("Carregar Imagem");
		loadImage.addMenuListener(new MenuLoadImageListener(this));
		JMenu saveImage = new JMenu("Salvar Imagem");
		saveImage.addMenuListener(new MenuSaveImageListener(this));
		blueMenuBar.add(loadImage);
		blueMenuBar.add(saveImage);
		blueMenuBar.setOpaque(true);
		blueMenuBar.setBackground(new Color(43, 43, 43));
		blueMenuBar.setPreferredSize(new Dimension(200, 20));
		setJMenuBar(blueMenuBar);

		ButtonPanel buttonPanel = new ButtonPanel();
		add(buttonPanel, BorderLayout.WEST);

		pack();
		setVisible(true);
	}

	private BufferedImage readImage(String name) {
		File input = new File(name);
		try {
			return imageFromFile(input);
		} catch (IOException e) {
			System.out.println("Não foi possível ler a imagem com nome: "
					+ name);
			e.printStackTrace();
		}
		return null;
	}

	private BufferedImage imageFromFile(File f) throws IOException {
		return ImageIO.read(f);
	}

	public void setImage(File f) {
		try {
			this.imagePanel.setImage(imageFromFile(f));
			this.imagePanel.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage(){
		return imagePanel.getImage();
	}

}
