package engine.file.cryptography;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class Decrypt {
	
	public static Key getSecretKey(byte[] seed) throws Exception {
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(seed);
		
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256, secureRandom);
		
		return keyGenerator.generateKey();
	}
	
	public static byte[] getDecryptedSeed(byte[] encryptedSeed, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		
		return cipher.doFinal(encryptedSeed);
	}
	
	public static byte[] getDecryptedContent(byte[] encryptedContent, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		return cipher.doFinal(encryptedContent);
	}
	
	public static boolean isSignatureOK(byte[] content, byte[] signature, PublicKey publicKey) throws Exception {
		Signature sign = Signature.getInstance("SHA256withRSA");
		sign.initVerify(publicKey);
		sign.update(content);
		
		return sign.verify(signature);
	}
	
	public static byte[] getDecryptedFile(byte[] container, PrivateKey privateKey, PublicKey publicKey) throws Exception {
		byte[] encryptedSeed = Arrays.copyOfRange(container, 0, 256);
		byte[] signature = Arrays.copyOfRange(container, 256, 512);
		byte[] encryptedContent = Arrays.copyOfRange(container, 512, container.length);

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
		
		byte[] seed = getDecryptedSeed(encryptedSeed, privateKey);
		Key key = getSecretKey(seed);
		
		byte[] content = getDecryptedContent(encryptedContent, key);
		if(isSignatureOK(content, signature, publicKey))
			return content;
		
		return null;
	}

}
