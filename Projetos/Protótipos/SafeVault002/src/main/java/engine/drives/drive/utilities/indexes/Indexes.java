package engine.drives.drive.utilities.indexes;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.regex.Pattern;

import engine.drives.drive.file.File;

public class Indexes {
	
	private String chars = "1234567890abcdefghijklmnopqrstuvwxyz";
	private int namesize = 10;
	
	public Indexes() {
		
	}
	
	public String indexname(String secretphrase) throws Exception {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(secretphrase.getBytes());
		
		String name = "";
		for(int i = 0; i < namesize; i++)
			name += chars.charAt(random.nextInt(chars.length()));
		
		return name;
	}
	
	private String filename() {
		SecureRandom random = new SecureRandom();
		
		String name = "";
		for(int i = 0; i < namesize; i++)
			name += chars.charAt(random.nextInt(chars.length()));
		
		return name;	
	}
	
	private boolean is_name_used(String name, ArrayList<File> files) {		
		return files.stream().anyMatch((file) -> file.filename().equals(name));
	}
	
	public String validname(ArrayList<File> files) {
		String name = filename();
		
		while(is_name_used(name, files))
			name = filename();
		
		return name;
	}
	
	public String path_inside_vault(File file) {
		String regex = Pattern.quote(file.drive().vault().name());
		String path = file.path().replaceFirst(".*" + regex, "");
		
		if(path.isEmpty())
			return java.io.File.separator;
		
		return path;
	}

}
