package plugin._default;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import plugin.Plugin;

public class Default implements Plugin {
	
	public Default() {
		
	}

	/**
	 * @throws NullPointerException First parameter is null
	 * @throws FileAlreadyExistsException Directory already exists
	 * @throws NoSuchFileException One or more directories from path doesn't exists
	 */
	@Override
	public void createFolder(String folderPath) throws Exception {
		Path path = Paths.get(folderPath);
		
		Files.createDirectory(path);
	}

	/**
	 * @throws NullPointerException First parameter is null
	 * @throws NoSuchFileException One or more directories from path doesn't exists
	 * @throws NotDirectoryException Is not a directory
	 */
	@Override
	public ArrayList<String[]> listFolder(String folderPath) throws Exception {
		ArrayList<String[]> filesList = new ArrayList<String[]>();
		
		Path path = Paths.get(folderPath);
		
		DirectoryStream<Path> filesStream = Files.newDirectoryStream(path);
		
		filesStream.forEach((Path file) -> {
			String[] filesInfo = new String[2];
			filesInfo[0] = file.getFileName().toString();
			
			if(Files.isDirectory(file))
				filesInfo[1] = "folder";
			else
				filesInfo[1] = "file";
			
			filesList.add(filesInfo);
		});
		
		return filesList;
	}

	/**
	 * @throws NullPointerException First parameter is null
	 * @throws DirectoryNotEmptyException Directory is not empty
	 * @throws NoSuchFileException It's a file, not a directory
	 * @throws NoSuchFileException One or more directories from path doesn't exists
	 */
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

	/**
	 * @throws NullPointerException First parameter is null
	 * @throws ArrayIndexOutOfBoundsException File name is empty
	 * @throws FileAlreadyExistsException File already exists
	 * @throws NoSuchFileException One or more directories from path doesn't exists
	 */
	@Override
	public void createFile(String filePath) throws Exception {		
		Path path = Paths.get(filePath);
		Files.createFile(path);
	}

	/**
	 * @throws NullPointerException First parameter is null
	 * @throws IOException It's a directory, not a file
	 * @throws NoSuchFileException File doesn't exist
	 * @throws NoSuchFileException One or more directories from path doesn't exists
	 */
	@Override
	public byte[] readFile(String filePath) throws Exception {
		Path path = Paths.get(filePath);
		byte[] fileBytes = Files.readAllBytes(path);

		return fileBytes;
	}

	/**
	 * @throws NullPointerException First parameter is null
	 * @throws IOException It's a directory, not a file
	 * @throws NoSuchFileException File doesn't exist
	 * @throws NoSuchFileException One or more directories from path doesn't exists
	 */
	@Override
	public void writeFile(String filePath, byte[] fileBytes) throws Exception {
		Path path = Paths.get(filePath);
		Files.write(path, fileBytes, StandardOpenOption.TRUNCATE_EXISTING);
	}

	/**
	 * @throws NullPointerException First parameter is null
	 * @throws IOException It's a directory, not a file
	 * @throws NoSuchFileException File doesn't exist
	 * @throws NoSuchFileException One or more directories from path doesn't exists
	 * @throws NoSuchFileException It's a directory, not a file
	 */
	@Override
	public void deleteFile(String filePath) throws Exception {
		Path path = Paths.get(filePath);
		
		if(Files.isDirectory(path) == true)
			throw new NoSuchFileException("NoSuchFileException");
		
		// Deleta Permanentemente
		Files.delete(path);
	}

}
