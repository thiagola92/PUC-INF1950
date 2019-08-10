package view.frames.settingsframe.panel.savebutton;

import javax.swing.JButton;

import view.frames.settingsframe.panel.Panel;

@SuppressWarnings("serial")
public class SaveButton extends JButton {
	
	public SaveButton(Panel panel) {
		this.setText("Save");
		this.addActionListener(new OnClick(panel));
	}

}
