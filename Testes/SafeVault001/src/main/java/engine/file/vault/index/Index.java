package engine.file.vault.index;

import java.util.ArrayList;

import engine.file.File;
import engine.file.action.List;
import engine.file.vault.cryptography.Decrypt;

public class Index {

	public static void createIndex(File folder) throws Exception {
		String filePath = engine.file.Utility.concatPath(folder.getPath(), "index");
		folder.getDrive().getPlugin().createFile(filePath);
		
//		byte[] fileBytes = Encrypt.getEncryptedFile("".getBytes(), "teste".getBytes());
//		folder.getDrive().getPlugin().writeFile(filePath, fileBytes);
	}
	
	public static boolean existIndex(ArrayList<File> files) throws Exception {		
		for(int i = 0; i < files.size(); i++)
			if(files.get(i).getName().equals("index"))
				return true;
		
		return false;
	}
	
	public static ArrayList<File> readIndex(File index) throws Exception {
		byte[] container = index.getDrive().getPlugin().readFile(index.getPath());
		byte[] decryptedContent = Decrypt.getDecryptedFile(container, null, null);
		
		String content = new String(decryptedContent);
		String[] contentLines = content.split("\n");
		ArrayList<File> files = new ArrayList<>();
		
		for(int i=0; i < contentLines.length; i++) {
			String[] line = contentLines[i].split("|");
			
			File file = new File(index.getDrive(), line[0], line[2]);
			file.setName(line[1]);
			files.add(file);
		}
		
		return files;
	}
}
