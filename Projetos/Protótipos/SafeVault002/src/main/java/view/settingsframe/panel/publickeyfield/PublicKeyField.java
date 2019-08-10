package view.settingsframe.panel.publickeyfield;

import java.security.PublicKey;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PublicKeyField extends JTextField {
	
	private PublicKey publickey;
	
	public PublicKeyField(PublicKey publickey) {
		this.setEditable(false);
		
		set_key(publickey);
	}
	
	public PublicKey publickey() {
		return publickey;
	}
	
	public void set_key(PublicKey publickey) {
		if(publickey == null)
			return;
		
		this.publickey = publickey;
		
		this.setText(to_hexadecimal(publickey.getEncoded()));
	}
	
	private String to_hexadecimal(byte[] bytes) {
		String text = "";
		
		for(int i = 0; i < bytes.length; i++)
			text += String.format("%02X", bytes[i]);
		
		return text;
	}

}
