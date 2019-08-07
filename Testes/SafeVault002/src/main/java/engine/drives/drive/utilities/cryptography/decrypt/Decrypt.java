package engine.drives.drive.utilities.cryptography.decrypt;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import engine.drives.drive.utilities.cryptography.Cryptography;

public class Decrypt {
	
	private Cryptography cryptography;
	
	public Decrypt(Cryptography cryptography) {
		this.cryptography = cryptography;
	}
	
	private byte[] decrypted_seed(byte[] seed, PrivateKey privatekey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privatekey);
		
		return cipher.doFinal(seed);
	}
	
	private Key key(byte[] seed) throws Exception {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(seed);
		
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		generator.init(256, random);
		
		return generator.generateKey();
	}
	
	private byte[] decrypted_content(byte[] content, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		return cipher.doFinal(content);
	}
	
	private boolean is_signature_OK(byte[] content, byte[] signature, PublicKey publickey) throws Exception {
		Signature sign = Signature.getInstance("SHA256withRSA");
		sign.initVerify(publickey);
		sign.update(content);
		
		return sign.verify(signature);
	}
	
	public byte[] container(byte[] container, PrivateKey privatekey, PublicKey publickey) throws Exception {
		HashMap<String, byte[]> information = cryptography.container().open(container);
		
		byte[] encryptedseed = information.get("seed");
		byte[] signature = information.get("signature");
		byte[] encryptedcontent = information.get("content");
		
		byte[] seed = decrypted_seed(encryptedseed, privatekey);
		Key key = key(seed);
		
		byte[] content = decrypted_content(encryptedcontent, key);
		
		if(is_signature_OK(content, signature, publickey))
			return content;
		
		return null;
	}

}
