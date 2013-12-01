package org.listeners.effects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.gui.AppScreen;
import org.gui.SepiaSliderDialog;
import org.transform.Effects;

public class OldImageListener implements ActionListener {

	private AppScreen app;
	
	public OldImageListener(AppScreen app) {
		super();
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Effects effect = new Effects(app.getImage());
		SepiaSliderDialog sliderDialog = new SepiaSliderDialog();
		JDialog dialog = sliderDialog.createDialog(app, "Intesidade do Envelhecimento");
		dialog.setVisible(true);
		String value = sliderDialog.getInputValue().toString();
		if(sliderDialog.getInputValue() == JOptionPane.UNINITIALIZED_VALUE)
			this.app.setImage(effect.oldImage(5));
		else
			this.app.setImage(effect.oldImage(Integer.parseInt(value)));
	}

}
