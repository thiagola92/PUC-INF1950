package engine.file.action;

import engine.file.File;
import engine.file.vault.cryptography.Decrypt;

public class Decipher {
	
	public static void decipherFile(File file) throws Exception {
		byte[] fileBytes = file.getDrive().getPlugin().readFile(file.getPath());
		byte[] fileEncrypted = Decrypt.getDecryptedFile(fileBytes, file.getDrive().getPrivateKey(), file.getDrive().getPublicKey());
		file.getDrive().getPlugin().writeFile(file.getPath(), fileEncrypted);
	}

}
