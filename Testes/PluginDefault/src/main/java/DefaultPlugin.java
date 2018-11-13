import java.awt.Desktop;
import java.io.File;
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
		if("".equals(folderPath)) // isso é para eu não deletar esse projeto
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

		Files.delete(path); // deleta permanentemente, ou seja, n move para a lixeira
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
		Path path = Paths.get(filePath);
		
		if(Files.isDirectory(path))
			return;
		
		Files.delete(path); // deleta permanentemente, ou seja, n move para a lixeira
	}

}
