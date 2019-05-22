package engine.file.cryptography;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import com.google.common.primitives.Bytes;

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

		System.out.println(encryptedSeed.length);
		for(int i = 0; i < encryptedSeed.length; i++)
			System.out.format("%X", encryptedSeed[i]);
		System.out.println();

		System.out.println(signature.length);
		for(int i = 0; i < signature.length; i++)
			System.out.format("%X", signature[i]);
		System.out.println();

		System.out.println(encryptedContent.length);
		for(int i = 0; i < encryptedContent.length; i++)
			System.out.format("%X", encryptedContent[i]);
		System.out.println();
		
		return Bytes.concat(encryptedSeed, signature, encryptedContent);
	}
}
