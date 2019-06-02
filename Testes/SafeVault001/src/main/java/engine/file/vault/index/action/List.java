package engine.file.vault.index.action;

import java.util.ArrayList;

import engine.file.File;
import engine.file.vault.index.Index;

public class List {
	
	public static ArrayList<File> listIndex(File index) throws Exception {
		String content = Index.readContent(index);
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

}
