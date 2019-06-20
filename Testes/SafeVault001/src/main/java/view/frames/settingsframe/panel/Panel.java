package view.frames.settingsframe.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.file.drive.Drive;
import view.frames.settingsframe.SettingsFrame;
import view.frames.settingsframe.panel.cancelbutton.CancelButton;
import view.frames.settingsframe.panel.importcertificate.ImportCertificateButton;
import view.frames.settingsframe.panel.importprivatekey.ImportPrivateKeyButton;
import view.frames.settingsframe.panel.keytextfield.KeyTextField;
import view.frames.settingsframe.panel.savebutton.SaveButton;

@SuppressWarnings("serial")
public class Panel extends JPanel {

	public SettingsFrame settingsFrame;
	public Drive drive;
	
	public JTextField name;
	public JTextField startPath;
	
	public KeyTextField privateKeyTextField;
	public KeyTextField publicKeyTextField;
	
	public ImportPrivateKeyButton importPrivateKeyButton;
	public ImportCertificateButton importCertificateButton;

	public JTextField secretPhrase;
	
	public PrivateKey privateKey;
	public X509Certificate certificate;
	
	public Panel(SettingsFrame settingsFrame, Drive drive) {
		this.settingsFrame = settingsFrame;
		this.drive = drive;
		
		this.name = new JTextField(drive.getName());
		this.startPath = new JTextField(drive.getStartPath());
		
		this.privateKeyTextField = new KeyTextField(drive.getPrivateKey());
		this.publicKeyTextField = new KeyTextField(drive.getPublicKey());
		
		this.importPrivateKeyButton = new ImportPrivateKeyButton(this);
		this.importCertificateButton = new ImportCertificateButton(this);
		
		this.secretPhrase = new JTextField("frase secreta aqui");
		
		this.privateKey = drive.getPrivateKey();
		this.certificate = drive.getCertificate();
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.gridwidth = 4;
		constraints.insets = new Insets(10, 10, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JLabel("Drive name: " + drive.getName()), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JLabel("Plugin name:"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.gridwidth = 3;
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(name, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 10, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JLabel("Start path:"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.gridwidth = 3;
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(startPath, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new Insets(10, 10, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JLabel("Private key:"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.weightx = 1;
		constraints.insets = new Insets(10, 10, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(privateKeyTextField, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(importPrivateKeyButton, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.insets = new Insets(10, 10, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JLabel("Public Key:"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.weightx = 1;
		constraints.insets = new Insets(10, 10, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(publicKeyTextField, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(importCertificateButton, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.insets = new Insets(10, 10, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JLabel("Frase secreta:"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.weightx = 1;
		constraints.gridwidth = 3;
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(secretPhrase, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 6;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 10, 10, 0);
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		this.add(new SaveButton(this), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 3;
		constraints.gridy = 6;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		this.add(new CancelButton(this), constraints);
	}

}
