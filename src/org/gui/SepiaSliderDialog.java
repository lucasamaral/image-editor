package org.gui;

import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SepiaSliderDialog extends JOptionPane {

	private static final long serialVersionUID = 1L;

	public SepiaSliderDialog() {
		JSlider slider = putSlider(this);
		setMessage(new Object[] { "Selecione a intensidade:", slider });
		setMessageType(JOptionPane.QUESTION_MESSAGE);
	    setOptionType(JOptionPane.OK_CANCEL_OPTION);
	}

	private JSlider putSlider(final JOptionPane optionPane) {
		JSlider slider = new JSlider(0, 255);
		slider.setMajorTickSpacing(40);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				JSlider theSlider = (JSlider) changeEvent.getSource();
				if (!theSlider.getValueIsAdjusting()) {
					optionPane.setInputValue(new Integer(theSlider.getValue()));
				}
			}
		};
		slider.addChangeListener(changeListener);
		return slider;
	}

}
