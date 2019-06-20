package view.frames.settingsframe.panel.importprivatekey;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import view.stringformat.StringFormat;

public class OnClick implements ActionListener {
	
	public ImportPrivateKeyButton importPrivateKeyButton;
	
	public OnClick(ImportPrivateKeyButton importPrivateKeyButton) {
		this.importPrivateKeyButton = importPrivateKeyButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if(fileChooser.showOpenDialog(importPrivateKeyButton.panel) != JFileChooser.APPROVE_OPTION)
			return;
		
		String password = getPassword();
		if(password == null)
			return;

		try {
			String cipherPemPath = fileChooser.getSelectedFile().getPath();
			PrivateKey privateKey = getPrivateKey(password, cipherPemPath);
			
			importPrivateKeyButton.panel.privateKey = privateKey;
			importPrivateKeyButton.panel.privateKeyTextField.setKey(privateKey);
		} catch(Exception e) {
			StringFormat message = new StringFormat("Chave privada inválida ou senha incorreta.");
			JOptionPane.showMessageDialog(importPrivateKeyButton.panel, message);
		}

	}
	
	public String getPassword() {
		JLabel passwordLabel = new JLabel("Senha: ");
		JPasswordField passworldField = new JPasswordField(20);
		JPanel panel = new JPanel();
		String[] options = new String[] {"OK", "Cancel"};
		
		panel.add(passwordLabel);
		panel.add(passworldField);
		
		int answer = JOptionPane.showOptionDialog(importPrivateKeyButton.panel, panel, "Input", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, 1);
		String password = new String(passworldField.getPassword());
		
		System.out.println(answer);
		System.out.println(password);
		
		if(answer != 0)
			return null;
		
		return password;
	}

    public PrivateKey getPrivateKey(String password, String cipherPemPathString) throws Exception {
    	Path cipherPemPath = Paths.get(cipherPemPathString);
    	
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());

        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56, secureRandom);
        Key key = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherPemBytes = Files.readAllBytes(cipherPemPath);
        byte[] pemBytes = cipher.doFinal(cipherPemBytes);

        String pemString = new String(pemBytes);
        pemString = pemString.replace("-----BEGIN PRIVATE KEY-----\n","");
        pemString = pemString.replace("-----END PRIVATE KEY-----\n","");

        byte[] privateKeyBytes = Base64.getMimeDecoder().decode(pemString);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

}
