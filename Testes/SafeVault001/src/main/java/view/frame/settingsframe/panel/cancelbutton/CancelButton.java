package view.frame.settingsframe.panel.cancelbutton;

import javax.swing.JButton;

import view.frame.settingsframe.panel.Panel;

@SuppressWarnings("serial")
public class CancelButton extends JButton {
	
	public CancelButton(Panel panel) {
		this.setText("Cancel");
		
		this.addActionListener(new OnClick(panel));
	}

}
