package view.settingsframe.panel.cancel;

import javax.swing.JButton;

import view.settingsframe.panel.Panel;

@SuppressWarnings("serial")
public class Cancel extends JButton {
	
	private Panel panel;
	
	public Cancel(Panel panel) {
		this.panel = panel;
		
		this.setText("Cancel");
		
		this.addActionListener((event) -> click());
	}

	private void click() {
		panel.settings_frame().dispose();
	}
}
