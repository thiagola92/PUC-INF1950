package engine.file;

import java.security.SecureRandom;

public class Utility {
	
	// "src" + "main" = "src/main"
	// "" + "main" = "main" 
	public static String concatPath(String parentPath, String childName) {
		if(parentPath.isEmpty())
			return childName;
		else
			return parentPath + java.io.File.separator + childName;
	}
	
	public static byte[] createContainer(byte[] encryptedSeed, byte[] signature, byte[] encryptedContent) {
		byte[] container = new byte[encryptedSeed.length + signature.length + encryptedContent.length];
		
		System.arraycopy(encryptedSeed, 0, container, 0, encryptedSeed.length);
		System.arraycopy(signature, 0, container, encryptedSeed.length, signature.length);
		System.arraycopy(encryptedContent, 0, container, encryptedSeed.length + signature.length, encryptedContent.length);
		
		return container;
	}
	
	public static String createRandomName() {
		String validChars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom secureRandom = new SecureRandom();
        byte[] name = new byte[8];

        for(int i = 0; i < name.length; i++)
            name[i] = (byte)validChars.charAt(secureRandom.nextInt(validChars.length()));
        
        return new String(name);
	}

}
