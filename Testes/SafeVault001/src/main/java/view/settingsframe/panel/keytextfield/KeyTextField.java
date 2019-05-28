package view.settingsframe.panel.keytextfield;

import java.security.Key;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class KeyTextField extends JTextField {
	
	public KeyTextField(Key key) {
		this.setEditable(false);
		
		setKey(key);
	}
	
	public void setKey(Key key) {
		if(key == null)
			return;
		
		this.setText(ByteToHex(key.getEncoded()));
	}
	
	private String ByteToHex(byte[] bytes) {		
		String text = "";
		for(int i = 0; i < bytes.length; i++)
			text += String.format("%02X", bytes[i]);
		
		return text;
	}

}
