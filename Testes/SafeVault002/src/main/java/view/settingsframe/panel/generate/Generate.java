package view.settingsframe.panel.generate;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import _main.Main;
import view.settingsframe.panel.Panel;

@SuppressWarnings("serial")
public class Generate extends JButton {
	
	private Panel panel;
	
	public Generate(Panel panel) {
		this.panel = panel;
		
		this.setText("Generate pairs of keys");
		
		this.addActionListener((event) -> click());
	}
	
	private KeyPair generate() throws Exception {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		
		return generator.generateKeyPair();
	}
	
	private KeyPair pair() {
		try {
			return generate();
		} catch(Exception e) {
			String message = Main.view.utilities().strings().utf8("Falha ao gerar o par de chaves.");
	        JOptionPane.showMessageDialog(panel, message);
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void click() {
		KeyPair pair = pair();
		
		if(pair == null)
			return;
		
		panel.publickey_field().set_key(pair.getPublic());
		panel.privatekey_field().set_key(pair.getPrivate());
	}

}
