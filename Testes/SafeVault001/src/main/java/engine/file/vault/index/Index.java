package engine.file.vault.index;

import java.util.ArrayList;

import engine.file.File;
import engine.file.action.List;
import engine.file.vault.Vault;
import engine.file.vault.cryptography.Decrypt;
import engine.file.vault.cryptography.Encrypt;
import engine.file.vault.index.exception.IndexNotFoundException;

public class Index {
	
	public static String separator = "|";
	public static String regexSeparator = "\\|";
	
	public static ArrayList<File> readIndex(File index) throws Exception {
		byte[] container = index.getDrive().getPlugin().readFile(index.getPath());
		byte[] decryptedContent = Decrypt.getDecryptedFile(container, index.getDrive().getPrivateKey(), index.getDrive().getPublicKey());
		
		String content = new String(decryptedContent);
		String[] contentLines = content.split("\n");
		ArrayList<File> files = new ArrayList<>();
		
		String prePath = index.getPath().replaceFirst("." + index.getName() + "$", "");
		
		for(int i=0; i < contentLines.length; i++) {
			String[] line = contentLines[i].split(Index.regexSeparator);
			
			File file = new File(index.getDrive(), prePath + line[0], line[2]);
			file.setName(line[1]);
			files.add(file);
		}
		
		return files;
	}
	
	public static void writeIndex(ArrayList<File> files, File index) throws Exception {
		String content = "";
		
		for(int i = 0; i < files.size(); i++) {
			String path = Vault.pathInsideVault(files.get(i));
			String name = files.get(i).getName();
			String type = files.get(i).getType();
			
			content += path + Index.separator + name + Index.separator + type + "\n";
		}

		byte[] container = Encrypt.getEncryptedFile(content.getBytes(), index.getDrive().getPrivateKey(), index.getDrive().getPublicKey());
		index.getDrive().getPlugin().writeFile(index.getPath(), container);
	}
	
	public static File getIndex(File folder, String indexName) throws Exception {
		ArrayList<File> files = List.listFolder(folder);
		
		for(int i = 0; i < files.size(); i++)
			if(files.get(i).getName().equals(indexName) && files.get(i).getType().equals("file"))
				return files.get(i);
		
		throw new IndexNotFoundException();
	}
}
