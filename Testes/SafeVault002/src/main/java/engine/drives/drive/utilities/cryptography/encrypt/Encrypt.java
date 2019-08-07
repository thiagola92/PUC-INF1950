package engine.drives.drive.utilities.cryptography.encrypt;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import engine.drives.drive.utilities.cryptography.Cryptography;

public class Encrypt {
	
	private Cryptography cryptography;
	
	public Encrypt(Cryptography cryptography) {
		this.cryptography = cryptography;
	}
	
	private byte[] seed() {
		byte[] seed = new byte[20];
		
		SecureRandom random = new SecureRandom();
		random.nextBytes(seed);
		
		return seed;
	}
	
	private Key key(byte[] seed) throws Exception {
		SecureRandom random =  SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(seed);
		
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		generator.init(256, random);
		
		return generator.generateKey();
	}
	
	private byte[] encrypted_seed(byte[] seed, PublicKey publickey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publickey);
		
		return cipher.doFinal(seed);
	}
	
	private byte[] encrypted_content(byte[] content, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		return cipher.doFinal(content);
	}
	
	private byte[] signature(byte[] content, PrivateKey privatekey) throws Exception {
		Signature signature =  Signature.getInstance("SHA256withRSA");
		signature.initSign(privatekey);
		signature.update(content);
		
		return signature.sign();
	}
	
	public byte[] content(byte[] content, PrivateKey privatekey, PublicKey publickey) throws Exception {
		byte[] seed = seed();
		Key key = key(seed);
		
		byte[] encryptedseed = encrypted_seed(seed, publickey);
		byte[] signature = signature(content, privatekey);
		byte[] encryptedcontent = encrypted_content(content, key);
		
		return cryptography.container().create(encryptedseed, signature, encryptedcontent);
	}

}
