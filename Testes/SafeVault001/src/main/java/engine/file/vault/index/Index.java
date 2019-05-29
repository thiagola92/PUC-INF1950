package engine.file.vault.index;

import java.util.ArrayList;

import engine.file.File;
import engine.file.action.List;
import engine.file.vault.cryptography.Decrypt;

public class Index {
	
	private static String regexSeparator = "\\|";
	
	public static String indexToString(File index) throws Exception {
		byte[] container = index.getDrive().getPlugin().readFile(index.getPath());
		byte[] decryptedContent = Decrypt.getDecryptedFile(container, index.getDrive().getPrivateKey(), index.getDrive().getPublicKey());
		
		return new String(decryptedContent);
	}
	
	public static ArrayList<File> readIndex(File index) throws Exception {
		String content = indexToString(index);
		String[] contentLines = content.split("\n");
		ArrayList<File> files = new ArrayList<>();
		
		for(int i=0; i < contentLines.length; i++) {
			String[] line = contentLines[i].split(regexSeparator);
			
			File file = new File(index.getDrive(), line[0], line[2]);
			file.setName(line[1]);
			files.add(file);
		}
		
		return files;
	}

	public static File getMainIndex(File vault) throws Exception {
		ArrayList<File> files = List.listFolder(vault);
		
		for(int i = 0; i < files.size(); i++)
			if(files.get(i).getName().equals("index"))
				return files.get(i);
		
		return null;
	}
}
