package view.frames.settingsframe.panel.importcertificate;

import javax.swing.JButton;

import view.frames.settingsframe.panel.Panel;
import view.frames.settingsframe.panel.importcertificate.OnClick;

@SuppressWarnings("serial")
public class ImportCertificateButton extends JButton {
	
	public Panel panel;
	
	public ImportCertificateButton(Panel panel) {
		this.panel = panel;
		
		this.setText("Import");
		
		this.addActionListener(new OnClick(this));
	}

}
