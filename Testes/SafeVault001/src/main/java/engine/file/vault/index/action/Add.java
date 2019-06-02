package engine.file.vault.index.action;

import engine.file.File;
import engine.file.vault.Vault;
import engine.file.vault.index.Index;

public class Add {
	
	public static void addFile(File index, File file) throws Exception {
		String content = Index.readContent(index);
		String path = Vault.pathInsideVault(file);
		String name = file.getName();
		String type = file.getType();
		
		content += "\n" + path + Index.separator + name + Index.separator + type;
		content = content.replace("\n\n", "\n");
		
		Index.writeContent(index, content);
	}

}
