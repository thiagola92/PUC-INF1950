package engine.file.vault.index;

import engine.file.File;
import engine.file.Utility;
import engine.file.vault.cryptography.Decrypt;
import engine.file.vault.cryptography.Encrypt;

public class Index {
	
	public static String separator = "|";
	public static String regexSeparator = "\\|";
	
	public static String readContent(File index) throws Exception {
		byte[] container = index.getDrive().getPlugin().readFile(index.getPath());
		byte[] decryptedContent = Decrypt.getDecryptedFile(container, index.getDrive().getPrivateKey(), index.getDrive().getPublicKey());
		
		return new String(decryptedContent);
	}
	
	public static void writeContent(File index, String content) throws Exception {
		byte[] container = Encrypt.getEncryptedFile(content.getBytes(), index.getDrive().getPrivateKey(), index.getDrive().getPublicKey());
		index.getDrive().getPlugin().writeFile(index.getPath(), container);
	}

	public static File getIndex(File folder, String indexName) throws Exception {
		String indexPath = Utility.concatPath(folder.getPath(), indexName);
		return new File(folder.getDrive(), indexPath, "file");
	}
}
