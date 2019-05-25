package engine.file.vault.cryptography;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import engine.file.Utility;

public class Encrypt {
	
	public static byte[] createRandomSeed() {
		byte[] seed = new byte[20];
		
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(seed);
		
		return seed;
	}
	
	public static Key getSecretKey(byte[] seed) throws Exception {
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(seed);
		
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256, secureRandom);
		
		return keyGenerator.generateKey();
	}
	
	public static byte[] getEncryptedSeed(byte[] seed, PublicKey publicKey) throws Exception {		
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encryptedKey = cipher.doFinal(seed);
		
		return encryptedKey;
	}

	public static byte[] getEncryptedContent(byte[] content, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		return cipher.doFinal(content);
	}
	
	public static byte[] getSignature(byte[] content, PrivateKey privateKey) throws Exception {
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(privateKey);
		signature.update(content);
		
		return signature.sign();
	}
	
	public static byte[] getEncryptedFile(byte[] content, PrivateKey privateKey, PublicKey publicKey) throws Exception {
		byte[] seed = createRandomSeed();
		Key key = getSecretKey(seed);
		
		byte[] encryptedSeed = getEncryptedSeed(seed, publicKey);
		byte[] signature = getSignature(content, privateKey);
		byte[] encryptedContent = getEncryptedContent(content, key);
		
		return Utility.createContainer(encryptedSeed, signature, encryptedContent);
	}
}
