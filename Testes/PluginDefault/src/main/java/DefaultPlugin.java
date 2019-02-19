import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DefaultPlugin implements Plugin {

	@Override
	public void createFolder(String folderPath) throws Exception {
		Path path = Paths.get(folderPath);
		
		try {
			Files.createDirectory(path);
		} catch(FileAlreadyExistsException e) {
			return;
		} catch(NoSuchFileException e) {
			createFolder(path.getParent().toString());
			Files.createDirectory(path);
		}
	}

	@Override
	public ArrayList<String> listFolder(String folderPath) throws Exception {
		ArrayList<String> filesList = new ArrayList<String>();
		
		Path path = Paths.get(folderPath);
		
		DirectoryStream<Path> filesStream = Files.newDirectoryStream(path);
		filesStream.forEach((Path file) -> {
			filesList.add(file.toString());
		});
		
		return filesList;
	}

	@Override
	public void deleteFolder(String folderPath) throws Exception {
		if("".equals(folderPath)) // isso é para eu não deletar esse projeto (de novo)
			return;
		
		Path path = Paths.get(folderPath);
		if(Files.isDirectory(path) == false)
			return;
		
		ArrayList<String> filesList = listFolder(folderPath);
		for(String file : filesList) {
			Path filePath = Paths.get(file);
			
			if(Files.isDirectory(filePath))
				deleteFolder(filePath.toString());
			else
				deleteFile(filePath.toString());
		}

		// Deleta Permanentemente
		Files.delete(path); 
	}

	@Override
	public void createFile(String filePath, byte[] fileBytes) throws Exception {
		Path path = Paths.get(filePath);
		Files.createFile(path);
		Files.write(path, fileBytes);
	}

	@Override
	public byte[] readFile(String filePath) throws Exception {
		Path path = Paths.get(filePath);
		byte[] fileBytes = Files.readAllBytes(path);

		return fileBytes;
	}

	@Override
	public void writeFile(String filePath, byte[] fileBytes) throws Exception {
		Path path = Paths.get(filePath);

		Files.write(path, fileBytes);
	}

	@Override
	public void deleteFile(String filePath) throws Exception {
		Path path = Paths.get(filePath);
		
		if(Files.isDirectory(path))
			return;
		
		// Deleta Permanentemente
		Files.delete(path);
	}

}
