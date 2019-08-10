package view.settingsframe.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import engine.drives.drive.Drive;
import view.settingsframe.SettingsFrame;
import view.settingsframe.panel.cancel.Cancel;
import view.settingsframe.panel.generate.Generate;
import view.settingsframe.panel.importprivatekey.ImportPrivateKey;
import view.settingsframe.panel.importpublickey.ImportPublicKey;
import view.settingsframe.panel.privatekeyfield.PrivateKeyField;
import view.settingsframe.panel.publickeyfield.PublicKeyField;
import view.settingsframe.panel.save.Save;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	
	private SettingsFrame settings_frame;
	private Drive drive;
	
	private JTextField name;
	private JTextField path;
	
	private PrivateKeyField privatekey_field;
	private PublicKeyField publickey_field;
	
	// import
	
	private JPasswordField secret_phrase;
	private JPasswordField confirm_secret;
	
	public Panel(SettingsFrame settings_frame, Drive drive) {
		this.settings_frame = settings_frame;
		this.drive = drive;
		
		this.name = new JTextField(drive.name());
		this.path = new JTextField(drive.path());
		
		this.privatekey_field = new PrivateKeyField(drive.vault().privatekey());
		this.publickey_field = new PublicKeyField(drive.vault().publickey());
		
		this.secret_phrase = new JPasswordField("");
		this.confirm_secret = new JPasswordField("");
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JLabel("Plugin: "), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.gridwidth = 3;
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JLabel(drive.plugin().name()), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JLabel("Drive name:"), constraints);

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
		this.add(path, constraints);

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
		this.add(privatekey_field, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(new ImportPrivateKey(this), constraints);

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
		this.add(publickey_field, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(new ImportPublicKey(this), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(new Generate(this), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.insets = new Insets(10, 10, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JLabel("Frase secreta:"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.weightx = 1;
		constraints.gridwidth = 3;
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(secret_phrase, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.insets = new Insets(10, 10, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JLabel("Confirme frase:"), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.weightx = 1;
		constraints.gridwidth = 3;
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(confirm_secret, constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 8;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 10, 10, 0);
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		this.add(new Save(this), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 3;
		constraints.gridy = 8;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.anchor = GridBagConstraints.LAST_LINE_START;
		this.add(new Cancel(this), constraints);
	}
	
	public SettingsFrame settings_frame() {
		return settings_frame;
	}
	
	public Drive drive() {
		return drive;
	}
	
	public JTextField name() {
		return name;
	}
	
	public JTextField path() {
		return path;
	}
	
	public PrivateKeyField privatekey_field() {
		return privatekey_field;
	}
	
	public PublicKeyField publickey_field() {
		return publickey_field;
	}
	
	public JPasswordField secret_phrase() {
		return secret_phrase;
	}
	
	public JPasswordField confirm_secret() {
		return confirm_secret;
	}

}
