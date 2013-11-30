import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;

import org.gui.AppScreen;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					Logger.getLogger(getClass().getName()).log(Level.INFO,
							"can not enable system look and feel", e);
				}

				AppScreen frame = new AppScreen();
				frame.setVisible(true);
			}
		});
	}
}
