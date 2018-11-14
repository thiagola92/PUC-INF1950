
public class Main {
	
	static GoogleDrivePlugin googleDrive;

	public static void main(String[] args) throws Exception {
		System.out.println("Example");
		googleDrive = new GoogleDrivePlugin();
		googleDrive.createFile("test/asdf", "coe".getBytes());
	}

}
