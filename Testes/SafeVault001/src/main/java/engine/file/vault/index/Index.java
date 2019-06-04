package engine.file.vault.index;

import java.util.ArrayList;

import engine.file.File;
import engine.file.action.List;
import engine.file.vault.Vault;
import engine.file.vault.index.exception.IndexNotFoundException;
import engine.file.vault.index.indexfile.IndexFile;

public class Index {

	public static String indexName = "index";
	public static String separator = "|";
	public static String regexSeparator = "\\|";
	
	public static IndexFile getIndex(File file) throws Exception {
		File vault = Vault.getVault(file);
		ArrayList<File> files = List.listFolder(vault);
		
		for(int i = 0; i < files.size(); i++)
			if(files.get(i).getName().equals(indexName) && files.get(i).getType().equals("file"))
				return new IndexFile(files.get(i));
		
		throw new IndexNotFoundException();
	}
}
