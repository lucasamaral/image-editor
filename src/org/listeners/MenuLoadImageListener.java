package org.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.gui.AppScreen;

public class MenuLoadImageListener implements ActionListener {

	private AppScreen ap;

	public MenuLoadImageListener(AppScreen ap) {
		this.ap = ap;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(ap);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			ap.setImage(file);
		}
	}

}
