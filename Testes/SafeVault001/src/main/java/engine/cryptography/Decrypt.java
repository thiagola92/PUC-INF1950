package engine.cryptography;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class Decrypt {
	
	/*
	 * Por que a gente cifra a semente em vez da chave?
	 * Quais os benificios de recuperar a chave dessa maneira?
	 * Se não for bom usar, utilizar EncodedKeySpec + SecretKeyFactory?
	 */
	public static Key getSecretKey(byte[] encryptedSeed, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] seed = cipher.doFinal(encryptedSeed);
		
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(seed);
		
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(56, secureRandom);
		
		return keyGenerator.generateKey();
	}
	
	public static byte[] getDecryptedContent(byte[] encryptedContent, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		return cipher.doFinal(encryptedContent);
	}
	
	public static boolean isSignatureOK(byte[] currentContent, byte[] encryptedSignature, PublicKey publicKey) throws Exception {
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initVerify(publicKey);
		signature.update(currentContent);
		
		return signature.verify(encryptedSignature);
	}

}
