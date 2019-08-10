import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;

import javax.crypto.Cipher;

public class CipherFile {
	
	public static final String CIPHER_FOLDER = "temp/cipher/";
	public static final String PLAINTEXT_FOLDER = "temp/plaintext/";
	
	public static void cipherFile(String filePath, Cipher cipher, Key key) throws Exception {
		Path path = Paths.get(filePath);
		String plainTextName = path.getFileName().toString();

		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] plainTextContent = Files.readAllBytes(path);
		byte[] cipherTextContentByte = cipher.doFinal(plainTextContent);
		
		FileOutputStream file = new FileOutputStream(CIPHER_FOLDER + plainTextName);
		file.write(cipherTextContentByte);
		file.flush();
		file.close();
	}
	
	public static void decipherFile(String filePath, Cipher cipher, Key key) throws Exception {
		Path path = Paths.get(filePath);
		String plainTextName = path.getFileName().toString();

		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] cipherTextContent = Files.readAllBytes(path);
		byte[] plainTextContent = cipher.doFinal(cipherTextContent);
		
		FileOutputStream file = new FileOutputStream(PLAINTEXT_FOLDER + plainTextName);
		file.write(plainTextContent);
		file.flush();
		file.close();
	}

}
