package org.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.gui.AppScreen;

public class MenuUndoListener implements ActionListener {

	private AppScreen ap;

	public MenuUndoListener(AppScreen appScreen) {
		this.ap = appScreen;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Undoing");
		ap.undo();
	}

}
