package example;

import java.security.*;
import java.util.Arrays;

import javax.crypto.Cipher;

public class MySignature {
	
	private MessageDigest messageDigest;
	private Cipher cipher;
	
	protected MySignature(String algorithms) throws Exception {
		String[] algorithm = algorithms.split("with");
		
		if(algorithm.length != 2) {
			System.err.print("\nYou need to inform the algorithm from Digest and Asymetric Key Cypher, example: \"MD5WithRSA\"");
			System.err.print("\nThis way you use MD5 and RSA algorithm");
			System.exit(1);
		}
		
		System.out.print("\nAlgorithm 0: " + algorithm[0]);
		System.out.print("\nAlgorithm 1: " + algorithm[1]);
		
		messageDigest = MessageDigest.getInstance(algorithm[0]);
		System.out.print("\nProvider: " + messageDigest.getProvider().getInfo());
		
		cipher = Cipher.getInstance(algorithm[1] + "/ECB/PKCS1Padding");
	}
	
	public static MySignature getInstance(String algorithm) throws Exception {
		MySignature mySignature = new MySignature(algorithm);
		
		return mySignature;
	}

	public void initSign(PrivateKey privateKey) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
	}

	public void initVerify(PublicKey publicKey) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
	}

	public void update(byte[] data) {
		messageDigest.update(data);
	}

	public boolean verify(byte[] cipherSignature) throws Exception {
		byte[] digest = messageDigest.digest();
		byte[] signature = cipher.doFinal(cipherSignature);
		
		if(Arrays.equals(signature, digest))
			return true;
		
		return false;
	}

	public byte[] sign() throws Exception {
		byte[] digest = messageDigest.digest();
		
		System.out.print("\nDigest: ");
		for(int i = 0; i != digest.length; i++)
			System.out.print(String.format("%02X", digest[i]));
		
		byte[] cipherDigest = cipher.doFinal(digest);
		return cipherDigest;
	}
}
