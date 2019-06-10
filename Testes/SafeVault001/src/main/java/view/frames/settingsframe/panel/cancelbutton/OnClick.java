package view.frames.settingsframe.panel.cancelbutton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.frames.settingsframe.panel.Panel;

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
