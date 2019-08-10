package view.frames.settingsframe.panel.importprivatekey;

import javax.swing.JButton;

import view.frames.settingsframe.panel.Panel;

@SuppressWarnings("serial")
public class ImportPrivateKeyButton extends JButton {
	
	public Panel panel;
	
	public ImportPrivateKeyButton(Panel panel) {
		this.panel = panel;
		
		this.setText("Import Private Key");
		
		this.addActionListener(new OnClick(this));
	}

}
