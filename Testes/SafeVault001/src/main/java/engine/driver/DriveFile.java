package engine.driver;

public class DriveFile {
	
	private String name;
	private String type;
	
	public DriveFile(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String toString() {
		return name;
	}

}
