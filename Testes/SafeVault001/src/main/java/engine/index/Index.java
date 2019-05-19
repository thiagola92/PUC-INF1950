package engine.index;

import java.util.ArrayList;

import engine.file.File;
import engine.file.action.List;

public class Index {

	public static void createIndex(File folder) {
	}
	
	public static boolean existIndex(File folder) throws Exception {
		ArrayList<File> files = List.listFolder(folder);
		
		for(int i = 0; i < files.size(); i++)
			if(files.get(i).getName().equals("index"))
				return true;
		
		return false;
	}
}
