package engine.cryptography;

import java.security.Key;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class Encrypt {
	
	public static Key createSecretKey(byte[] seed) throws Exception {
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.nextBytes(seed);
		
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(56, secureRandom);
		
		return keyGenerator.generateKey();
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
}
