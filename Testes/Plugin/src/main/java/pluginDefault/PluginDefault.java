package pluginDefault;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import plugin.Plugin;

public class PluginDefault implements Plugin {
	
	public PluginDefault() {
		
	}

	@Override
	public void createFolder(String folderPath) throws Exception {
		Path path = Paths.get(folderPath);
		
		Files.createDirectory(path);
	}

	@Override
	public ArrayList<String[]> listFolder(String folderPath) throws Exception {
		ArrayList<String[]> filesList = new ArrayList<String[]>();
		
		Path path = Paths.get(folderPath);
		
		DirectoryStream<Path> filesStream = Files.newDirectoryStream(path);
		
		filesStream.forEach((Path file) -> {
			String[] filesInfo = new String[2];
			filesInfo[1] = file.toString();
			
			if(Files.isDirectory(file))
				filesInfo[2] = "folder";
			else
				filesInfo[2] = "file";
			
			filesList.add(filesInfo);
		});
		
		return filesList;
	}

	@Override
	public void deleteFolder(String folderPath) throws Exception {		
		if("".equals(folderPath)) // isso é para eu não deletar esse projeto (de novo)
			return;
		
		Path path = Paths.get(folderPath);
		
		if(Files.isDirectory(path) == false)
			throw new NoSuchFileException("NoSuchFileException");

		// Deleta Permanentemente
		Files.delete(path); 
	}

	@Override
	public void createFile(String filePath) throws Exception {		
		Path path = Paths.get(filePath);
		Files.createFile(path);
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
		Files.write(path, fileBytes, StandardOpenOption.TRUNCATE_EXISTING);
	}

	@Override
	public void deleteFile(String filePath) throws Exception {
		Path path = Paths.get(filePath);
		
		if(Files.isDirectory(path) == true)
			return;
		
		// Deleta Permanentemente
		Files.delete(path);
	}

}
