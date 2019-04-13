package engine.driver;

import java.nio.file.Paths;

public class DriveFile {

	private String path;
	private String type;
	private String name;
	
	public DriveFile(String path, String type) {
		this.path = path;
		this.type = type;
		this.name = Paths.get(path).getFileName().toString();
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
