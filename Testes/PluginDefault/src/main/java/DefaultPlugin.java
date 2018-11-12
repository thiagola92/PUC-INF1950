import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DefaultPlugin implements Plugin {

	@Override
	public void createFolder(String folderPath) throws Exception {
		Path path = Paths.get(folderPath);
		
		Files.createDirectory(path);
	}

	@Override
	public ArrayList<String> listFolder(String folderPath) throws Exception {
		ArrayList<String> filesList = new ArrayList<String>();
		
		Path path = Paths.get(folderPath);
		
		DirectoryStream<Path> filesStream = Files.newDirectoryStream(path);
		filesStream.forEach((Path file) -> {
			filesList.add(file.toString());
			System.out.println(">> file name: " + file);
		});
		
		return filesList;
	}

	@Override
	public void deleteFolder(String folderPath) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createFile(String filePath, byte[] fileBytes) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] readFile(String filePath) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeFile(String filePath, byte[] fileBytes) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(String filePath) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
