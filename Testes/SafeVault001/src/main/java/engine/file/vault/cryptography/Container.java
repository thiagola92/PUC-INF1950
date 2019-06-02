package engine.file.vault.cryptography;

public class Container {
	
	public static byte[] createContainer(byte[] encryptedSeed, byte[] signature, byte[] encryptedContent) {
		byte[] container = new byte[encryptedSeed.length + signature.length + encryptedContent.length];
		
		System.arraycopy(encryptedSeed, 0, container, 0, encryptedSeed.length);
		System.arraycopy(signature, 0, container, encryptedSeed.length, signature.length);
		System.arraycopy(encryptedContent, 0, container, encryptedSeed.length + signature.length, encryptedContent.length);
		
		return container;
	}

}
