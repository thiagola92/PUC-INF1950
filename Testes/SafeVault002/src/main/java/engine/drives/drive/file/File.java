package engine.drives.drive.file;

import java.nio.file.Path;
import java.nio.file.Paths;

import engine.drives.drive.Drive;

public class File {

	private Drive drive;
	private File parent;
	
	private String name;
	private String path;
	private String type;
	private String to_string;

	public File(Drive drive, String path, String type) {
		this.drive = drive;
		this.parent = null;
		
		this.path = path;
		this.type = type;
		this.name = null;
		this.to_string = null;
	}
	
	public Drive drive() {
		return drive;
	}
	
	public File parent() {
		return parent;
	}
	
	public File set_parent(File file) {
		this.parent = file;
		
		return this;
	}
	
	public String path() {
		return path;
	}
	
	public String type() {
		return type;
	}
	
	public String filename() {
		Path filename = Paths.get(path).getFileName();
		
		if(filename == null)
			return null;
		
		return filename.toString();
	}
	
	public String name() {
		if(name == null)
			return filename();
		
		return name;
	}
	
	public File set_name(String name) {
		this.name = name;
		
		return this;
	}
	
	public String to_string() {
		if(to_string == null)
			return name();
		
		return to_string;
	}
	
	public File set_to_string(String to_string) {
		this.to_string = to_string;
		
		return this;
	}
	
	public File reset() {
		File file = new File(drive, path, type);
		
		return file.set_parent(parent);
	}
	
	public File copy() {
		File file = new File(drive, path, type);
		file.set_parent(parent);
		file.set_name(name);
		
		return file;
	}
	
	public boolean equal_to(File file) {		
		if(!path.equals(file.path))
			return false;
		
		if(!type.equals(file.type))
			return false;
		
		if(!drive.name().equals(file.drive.name()))
			return false;
		
		return true;
	}
	
	public String toString() {
		return to_string();
	}
}
