package view.settingsframe.panel.save;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import _main.Main;
import view.settingsframe.panel.Panel;

@SuppressWarnings("serial")
public class Save extends JButton {
	
	private Panel panel;
	
	public Save(Panel panel) {
		this.panel = panel;
		
		this.setText("Save");
		
		this.addActionListener((event) -> click());
	}
	
	private boolean signature_ok(PrivateKey privatekey, PublicKey publickey) throws Exception {
        byte[] message = new byte[2048];
        (new SecureRandom()).nextBytes(message);

        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privatekey);
        signature.update(message);
        byte[] cipherMessage = signature.sign();

        signature.initVerify(publickey);
        signature.update(message);

        if(signature.verify(cipherMessage))
            return true;

        return false;
		
	}
	
	private boolean pairs(PrivateKey privatekey, PublicKey publickey) {
		try {
			if(signature_ok(privatekey, publickey))
				return true;

			String message = Main.view.utilities().strings().utf8("Chave privada e pública não são pares.");
	        JOptionPane.showMessageDialog(panel, message);
		} catch(Exception e) {
			String message = Main.view.utilities().strings().utf8("Falha na verificação das chaves.");
	        JOptionPane.showMessageDialog(panel, message);
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	private boolean valid_keys(PrivateKey privatekey, PublicKey publickey) {
		if(privatekey == null && publickey == null)
			return true;
		
		if(pairs(privatekey, publickey))
			return true;
		
		return false;
	}
	
	private boolean confirm_secret(String secret_phrase, String confirm_secret) {
		if(secret_phrase == null)
			return true;
		
		if(secret_phrase.equals(confirm_secret))
			return true;

		String message = Main.view.utilities().strings().utf8("Erro na confirmação da frase secreta.");
        JOptionPane.showMessageDialog(panel, message);
		
		return false;
	}
	
	private void click() {
		String name = panel.name().getText();
		String path = panel.path().getText();
		
		PrivateKey privatekey = panel.privatekey_field().privatekey();
		PublicKey publickey = panel.publickey_field().publickey();
		
		if(!valid_keys(privatekey, publickey))
			return;
		
		String secret_phrase = new String(panel.secret_phrase().getPassword());
		String confirm_secret = new String(panel.confirm_secret().getPassword());

		secret_phrase = secret_phrase.isEmpty() ? panel.drive().vault().secret_phrase() : secret_phrase;
		confirm_secret = confirm_secret.isEmpty() ? panel.drive().vault().secret_phrase() : confirm_secret;
		
		if(!confirm_secret(secret_phrase, confirm_secret))
			return;
		
		panel.drive().set_name(name);
		panel.drive().set_path(path);
		panel.drive().vault().set_privatekey(privatekey);
		panel.drive().vault().set_publickey(publickey);
		panel.drive().vault().set_secret_phrase(secret_phrase);
		
		panel.settings_frame().dispose();
	}

}
