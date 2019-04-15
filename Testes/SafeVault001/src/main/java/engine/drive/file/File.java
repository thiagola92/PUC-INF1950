package engine.drive.file;

import java.nio.file.Paths;

import engine.drive.Drive;

public class File {

	private Drive drive;
	private String path;
	private String type;
	private String name;
	
	public File(Drive drive, String path, String type) {
		this.drive = drive;
		this.path = path;
		this.type = type;
		this.name = Paths.get(path).getFileName().toString();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Drive getDrive() {
		return drive;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}

}
