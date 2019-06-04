package view.frame.settingsframe.panel.cancelbutton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.frame.settingsframe.panel.Panel;

public class OnClick implements ActionListener {
	
	private Panel panel;
	
	public OnClick(Panel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.settingsFrame.dispose();
	}

}
