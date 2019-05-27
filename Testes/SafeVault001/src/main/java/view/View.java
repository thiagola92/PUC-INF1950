package view;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import view.frame.Frame;

public class View {
	
	public static Frame frame = new Frame();
	
	public static PublicKey publicKey;
	public static PrivateKey privateKey;

	public static void main(String[] args) {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
