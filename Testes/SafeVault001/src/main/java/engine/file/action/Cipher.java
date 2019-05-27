package engine.file.action;

import engine.file.File;
import engine.file.vault.cryptography.Encrypt;

public class Cipher {
	
	public static void cipherFile(File file) throws Exception {
		byte[] fileBytes = file.getDrive().getPlugin().readFile(file.getPath());
		byte[] fileEncrypted = Encrypt.getEncryptedFile(fileBytes, file.getDrive().getPrivateKey(), file.getDrive().getPublicKey());
		file.getDrive().getPlugin().writeFile(file.getPath(), fileEncrypted);
	}

}
