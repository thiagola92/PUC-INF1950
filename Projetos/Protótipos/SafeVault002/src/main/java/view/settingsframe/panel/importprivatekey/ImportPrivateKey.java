package view.settingsframe.panel.importprivatekey;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import _main.Main;
import view.settingsframe.panel.Panel;

@SuppressWarnings("serial")
public class ImportPrivateKey extends JButton {

	private Panel panel;
	
	public ImportPrivateKey(Panel panel) {
		this.panel = panel;
		
		this.setText("Import Private Key");
		
		this.addActionListener((event) -> click());
	}
	
	private String file() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if(chooser.showOpenDialog(panel) != JFileChooser.APPROVE_OPTION)
			return null;
		
		return chooser.getSelectedFile().getPath();
	}
	
	private String password() {
		JLabel label = new JLabel("Senha: ");
		JPasswordField field = new JPasswordField(20);
		JPanel panel = new JPanel();
		
		panel.add(label);
		panel.add(field);
		
		String[] options = new String[] {"OK", "Cancel"};
		
		int answer = JOptionPane.showOptionDialog(this.panel, panel, "Input", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, 1);
		
		if(answer != 0)
			return null;
		
		return new String(field.getPassword());
	}
	
	private PrivateKey import_privatekey(String password, String file) throws Exception {
    	Path path = Paths.get(file);
    	
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes());

        KeyGenerator generator = KeyGenerator.getInstance("DES");
        generator.init(56, random);
        Key key = generator.generateKey();

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        
        byte[] file_bytes = Files.readAllBytes(path);
        byte[] pem_bytes = cipher.doFinal(file_bytes);
        String pem_string = new String(pem_bytes);
        
        pem_string = pem_string.replace("-----BEGIN PRIVATE KEY-----\n","");
        pem_string = pem_string.replace("-----END PRIVATE KEY-----\n","");

        byte[] privatekey = Base64.getMimeDecoder().decode(pem_string);
        PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(privatekey);

        return KeyFactory.getInstance("RSA").generatePrivate(pkcs8);
	}
	
	private PrivateKey privatekey(String password, String file) {
		try {
			return import_privatekey(password, file);
		} catch(Exception e) {
			String message = Main.view.utilities().strings().utf8("Falha durante a decrifragem da chave privada.");
	        JOptionPane.showMessageDialog(panel, message);
	        
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void click() {
		String file = file();
		
		if(file == null)
			return;
		
		String password = password();
		
		if(password == null)
			return;
		
		PrivateKey privatekey = privatekey(password, file);
		
		if(privatekey == null)
			return;
		
		panel.privatekey_field().set_key(privatekey);
	}
}
