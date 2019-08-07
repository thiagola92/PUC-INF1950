package view.settingsframe.panel.privatekeyfield;

import java.security.PrivateKey;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PrivateKeyField extends JTextField {
	
	private PrivateKey privatekey;
	
	public PrivateKeyField(PrivateKey privatekey) {
		this.setEditable(false);
		
		set_key(privatekey);
	}
	
	public PrivateKey privatekey() {
		return privatekey;
	}
	
	public void set_key(PrivateKey privatekey) {
		if(privatekey == null)
			return;
		
		this.privatekey = privatekey;
		
		this.setText(to_hexadecimal(privatekey.getEncoded()));
	}
	
	private String to_hexadecimal(byte[] bytes) {
		String text = "";
		
		for(int i = 0; i < bytes.length; i++)
			text += String.format("%02X", bytes[i]);
		
		return text;
	}

}
