package engine.file;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class Utility {
	
	public static boolean isInsideSafeVault(File file) {
		Iterator<Path> iterator = Paths.get(file.getPath()).iterator();
		
		while(iterator.hasNext()) {
			String name = iterator.next().toString();
			
			System.out.println(name);
			
			if(name.equals(".SafeVault"))
				return true;
		}

		
		return false;
	}

}
