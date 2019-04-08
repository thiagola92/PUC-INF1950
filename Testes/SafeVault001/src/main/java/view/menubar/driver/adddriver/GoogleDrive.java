package view.menubar.driver.adddriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class GoogleDrive extends JMenuItem {

	public GoogleDrive() {
		this.setText("Google Drive");
		
		this.addActionListener(new OnClick());
	}
	
	private class OnClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Creating Google Drive plugin");
			
		}
		
	}
}
