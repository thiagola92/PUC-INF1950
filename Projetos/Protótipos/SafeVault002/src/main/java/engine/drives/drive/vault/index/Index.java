package engine.drives.drive.vault.index;

import java.util.ArrayList;
import java.util.regex.Pattern;

import engine.drives.drive.file.File;
import engine.drives.drive.vault.Vault;

public class Index {
	
	private String separator;
	
	private Vault vault;
	private File file;
	
	public Index(Vault vault, File file) {
		separator = "|";
		
		this.vault = vault;
		this.file = file;
	}
	
	public Vault vault() {
		return vault;
	}
	
	public File file() {
		return file;
	}
	
	public ArrayList<File> read() throws Exception {
		byte[] container = file.drive().plugin().readFile(file.path());
		byte[] bytes = file.drive().utilities().cryptography().decrypt().container(container, vault.privatekey(), vault.publickey());
		
		String content = new String(bytes);
		ArrayList<File> files = new ArrayList<File>();
		
		if(content.isEmpty())
			return files;
		
		String[] lines = content.split("\n");
		String pre_path = vault.folder().path();
		
		for(int i = 0; i < lines.length; i++) {
			String regex = Pattern.quote(separator);
			String[] information = lines[i].split(regex);
			
			File new_file = new File(vault.drive(), pre_path + information[0], information[2]);
			new_file.set_name(information[1]).set_parent(file);
			
			files.add(new_file);
		}		
		
		return files;
	}
	
	public void write(ArrayList<File> files) throws Exception {
		String content = "";
		
		for(int i = 0; i < files.size(); i++) {
			File file = files.get(i);
			
			String path = vault.drive().utilities().indexes().path_inside_vault(file);
			String name = file.name();
			String type = file.type();
			
			content += path + separator + name + separator  + type + "\n";
		}
		
		byte[] container = vault.drive().utilities().cryptography().encrypt().content(content.getBytes(), vault.privatekey(), vault.publickey());
		vault.drive().plugin().writeFile(file.path(), container);
	}
	
	public void add(File file) throws Exception {
		ArrayList<File> files = read();
		files.add(file);
		write(files);
	}
	
	public void remove(File file) throws Exception {
		ArrayList<File> files = read();
		
		for(File f : files) {
			if(file.equal_to(f)) {
				files.remove(f);
				break;
			}
		}
		
		write(files);
	}

}
