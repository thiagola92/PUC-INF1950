/*
 * Para rodar esse exemplo:
 * 
 * Baixe sua credencial do Google Drive em 
 * https://console.cloud.google.com
 * Renomei ela para credentials.json
 * Bote na pasta bin (mesma pasta onde se encontra UploadExample.class)
 * 
 * Esse programa faz o seguinte:
 * 
 * Cifra "TEST/test" e salva em "temp/cipher/test"
 * Upload no google drive do "temp/cipher/test"
 * Download do google drive o arquivo "test"
 * Decifra "test" e salva em "temp/plaintext/test"
 */
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import com.google.api.services.drive.model.File;

public class UploadDownloadCipherExample {
	
	private static Cipher cipher;
	private static KeyGenerator keyGenerator;

	public static void main(String[] args) throws Exception {
		cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(56);
		
		GoogleDrive googleDrive = new GoogleDrive();
		Key key = keyGenerator.generateKey();
		
		CipherFile.cipherFile("TEST/test", cipher, key);
		File file = googleDrive.uploadFile(CipherFile.CIPHER_FOLDER + "test");
		
		String name = file.getName();
		String id = file.getId();
				
		googleDrive.downloadFile(id);
		CipherFile.decipherFile(name, cipher, key);
	}

}
