package view;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import engine.cryptography.Decrypt;
import engine.cryptography.Encrypt;
import view.frame.Frame;

public class View {
	
	public static Frame frame;// = new Frame();

	public static void main(String[] args) {
		try {			
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			
			byte[] encryptedFile = Encrypt.getEncryptedFile("asdf".getBytes(), keyPair.getPrivate(), keyPair.getPublic());
			byte[] file = Decrypt.getDecryptedFile(encryptedFile, keyPair.getPrivate(), keyPair.getPublic());
			
			System.out.println(new String(file));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
