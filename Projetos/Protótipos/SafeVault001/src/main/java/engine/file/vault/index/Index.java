package engine.file.vault.index;

import java.util.ArrayList;

import engine.file.File;
import engine.file.RandomName;
import engine.file.action.List;
import engine.file.drive.Drive;
import engine.file.vault.Vault;
import engine.file.vault.index.exception.IndexNotFoundException;
import engine.file.vault.index.indexfile.IndexFile;

public class Index {
	
	public static String separator = "|";
	public static String regexSeparator = "\\|";
	
	public static IndexFile getIndex(File file) throws Exception {
		File vault = Vault.getVault(file);
		ArrayList<File> files = List.listFolder(vault);
		
		String indexName = getIndexName(file.getDrive());
		
		for(int i = 0; i < files.size(); i++)
			if(files.get(i).getName().equals(indexName) && files.get(i).getType().equals("file"))
				return new IndexFile(files.get(i));
		
		throw new IndexNotFoundException();
	}
	
	public static File getSafeFile(File file) throws Exception {
		ArrayList<File> files = Index.getIndex(file).readIndex();
		
		for(File fileFromList : files)
			if(fileFromList.getPath().equals(file.getPath()) && fileFromList.getType().equals(file.getType()))
				return fileFromList;
		
		return file;
	}
	
	public static String getIndexName(Drive drive) throws Exception {
		String secretPhrase = drive.getSecretPhrase();
		String indexName = RandomName.generatePseudoRandomName(secretPhrase);
		
		return indexName;
	}
}
