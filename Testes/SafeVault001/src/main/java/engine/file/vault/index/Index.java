package engine.file.vault.index;

import java.util.ArrayList;

import engine.file.File;
import engine.file.Utility;
import engine.file.vault.Vault;
import engine.file.vault.cryptography.Decrypt;
import engine.file.vault.cryptography.Encrypt;

public class Index {
	
	private static String separator = "|";
	private static String regexSeparator = "\\|";
	
	public static String indexToString(File index) throws Exception {
		byte[] container = index.getDrive().getPlugin().readFile(index.getPath());
		byte[] decryptedContent = Decrypt.getDecryptedFile(container, index.getDrive().getPrivateKey(), index.getDrive().getPublicKey());
		
		return new String(decryptedContent);
	}
	
	public static void stringToIndex(File index, String content) throws Exception {
		byte[] container = Encrypt.getEncryptedFile(content.getBytes(), index.getDrive().getPrivateKey(), index.getDrive().getPublicKey());
		index.getDrive().getPlugin().writeFile(index.getPath(), container);
	}
	
	public static ArrayList<File> readIndex(File index) throws Exception {
		String content = indexToString(index);
		String[] contentLines = content.split("\n");
		ArrayList<File> files = new ArrayList<>();
		
		String prePath = index.getPath().replaceFirst("." + index.getName() + "$", "");
		
		for(int i=0; i < contentLines.length; i++) {
			String[] line = contentLines[i].split(regexSeparator);
			
			File file = new File(index.getDrive(), prePath + line[0], line[2]);
			file.setName(line[1]);
			files.add(file);
		}
		
		return files;
	}
	
	public static void addToIndex(File index, File file) throws Exception {
		String content = indexToString(index);
		String path = Vault.pathInsideVault(file);
		String name = file.getName();
		String type = file.getType();
		
		content += "\n" + path + separator + name + separator + type;
		content = content.replace("\n\n", "\n");
		
		stringToIndex(index, content);
	}
	
	public static void removeFromIndex(File index, File file) throws Exception {
		
	}

	public static File getIndex(File folder, String indexName) throws Exception {
		String indexPath = Utility.concatPath(folder.getPath(), indexName);
		return new File(folder.getDrive(), indexPath, "file");
	}
}
