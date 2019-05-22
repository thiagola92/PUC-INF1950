package engine.file.index;

import java.util.ArrayList;

import engine.file.File;
import engine.file.action.List;
import engine.file.action.Utility;
import engine.file.cryptography.Encrypt;

public class Index {

	public static void createIndex(File folder) throws Exception {
		String filePath = Utility.concatPath(folder.getPath(), "index");
		folder.getDrive().getPlugin().createFile(filePath);
		
//		byte[] fileBytes = Encrypt.getEncryptedFile("".getBytes(), "teste".getBytes());
//		folder.getDrive().getPlugin().writeFile(filePath, fileBytes);
	}
	
	public static boolean existIndex(File folder) throws Exception {
		ArrayList<File> files = List.listFolder(folder);
		
		for(int i = 0; i < files.size(); i++)
			if(files.get(i).getName().equals("index"))
				return true;
		
		return false;
	}
}
