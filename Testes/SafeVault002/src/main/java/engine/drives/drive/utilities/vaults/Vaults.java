package engine.drives.drive.utilities.vaults;

import engine.drives.drive.file.File;

public class Vaults {
	
	public Vaults() {
		
	}
	
	public boolean is_secure(File file) {
		if(file.drive().vault() == null)
			return false;
		
		String name = file.drive().vault().name();
		
		if(file.path().startsWith(name))
			return true;
		
		if(file.path().contains(java.io.File.separator + name))
			return true;
		
		return false;
	}

}
