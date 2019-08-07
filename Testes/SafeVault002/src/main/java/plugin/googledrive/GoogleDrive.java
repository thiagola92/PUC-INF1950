package plugin.googledrive;

import java.io.ByteArrayOutputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import plugin.Plugin;

public class GoogleDrive implements Plugin {

	private HashMap<String, String> pathsIDs;
	private Drive drive;
	private Query query;
	
	private final String name = "Google Drive";
	
	public GoogleDrive(String tokenName) throws Exception {
		pathsIDs = new HashMap<String, String>();
		drive = Utility.getDrive(tokenName);
		query = new Query(drive, pathsIDs);
	}
	
	public String name() {
		return name;
	}

	/**
	 * @throws NullPointerException First parameter is null
	 * @throws IndexOutOfBoundsException One or more directories from path doesn't exists
	 * @throws FileAlreadyExistsException Directory already exists
	 */
	@Override
	public void createFolder(String folderPath) throws Exception {
		String folderName = Paths.get(folderPath).getFileName().toString();
		String parentID = query.parentID(folderPath);
		
		if(query.foldersNamed(parentID, folderName).size() > 0)
			throw new FileAlreadyExistsException("There is one or more folders with this name inside this folder");
		
		ArrayList<String> parent = new ArrayList<String>();
		parent.add(parentID);
		
		File folderMetadata = new File()
				.setName(folderName)
				.setParents(parent)
				.setMimeType("application/vnd.google-apps.folder");
		
		String id = drive.files()
						.create(folderMetadata)
						.setFields("id, name, parents")
						.execute()
						.getId();
		
		pathsIDs.put(folderPath, id);
	}

	/**
	 * @throws NullPointerException First parameter is null
	 * @throws IndexOutOfBoundsException Folder doesn't exists
	 * @throws IndexOutOfBoundsException One or more directories from path doesn't exists
	 */
	@Override
	public ArrayList<String[]> listFolder(String folderPath) throws Exception {
		String folderID = query.folderID(folderPath);

		ArrayList<File> fileMetadataList = (ArrayList<File>) query.everything(folderID);
		ArrayList<String[]> filesList = new ArrayList<String[]>();
		
		for(int i = 0; i < fileMetadataList.size(); i++) {
			String name = fileMetadataList.get(i).getName();
			String mime = fileMetadataList.get(i).getMimeType();
			String id = fileMetadataList.get(i).getId();
			
			String[] filesInfo = new String[2];
			
			filesInfo[0] = name;
			
			if(mime.equals("application/vnd.google-apps.folder"))
				filesInfo[1] = "folder";
			else
				filesInfo[1] = "file";
			
			filesList.add(filesInfo);
			
			pathsIDs.put(folderPath + java.io.File.separator + name, id);
		}
		
		return filesList;
	}

	/**
	 * @throws NullPointerException First parameter is null
	 * @throws IndexOutOfBoundsException Folder doesn't exists
	 * @throws IndexOutOfBoundsException One or more directories from path doesn't exists
	 * @throws DirectoryNotEmptyException Directory is not empty
	 */
	@Override
	public void deleteFolder(String folderPath) throws Exception {
		String folderID = query.folderID(folderPath);
		
		if(query.everything(folderID).size() > 0)
			throw new DirectoryNotEmptyException("There is one or more files in this folder");
		
		drive.files()
			.delete(folderID)
			.execute();
		
		pathsIDs.put(folderPath, null);
	}

	/**
	 * @throws NullPointerException First parameter is null
	 * @throws IndexOutOfBoundsException One or more directories from path doesn't exists
	 * @throws FileAlreadyExistsException File already exists
	 */
	@Override
	public void createFile(String filePath) throws Exception {
		String fileName = Paths.get(filePath).getFileName().toString();
		String parentID = query.parentID(filePath);
		
		if(query.filesNamed(parentID, fileName).size() > 0)
			throw new FileAlreadyExistsException("There is one or more files with this name inside this folder");

		ArrayList<String> parent = new ArrayList<String>();
		parent.add(parentID);
		
		File fileMetadata = new File()
				.setName(fileName)
				.setParents(parent);
		
		String id = drive.files()
						.create(fileMetadata)
						.setFields("id, name, parents")
						.execute()
						.getId();
		
		pathsIDs.put(filePath, id);
	}

	/**
	 * @throws NullPointerException First parameter is null
	 * @throws IndexOutOfBoundsException File doesn't exists
	 * @throws IndexOutOfBoundsException One or more directories from path doesn't exists
	 * @throws HttpResponseException File is empty
	 */
	@Override
	public byte[] readFile(String filePath) throws Exception {
		String fileID = query.fileID(filePath);
		
		ByteArrayOutputStream fileBytes =  new ByteArrayOutputStream(); 
		
		drive.files()
			.get(fileID)
			.executeMediaAndDownloadTo(fileBytes);
		
		return fileBytes.toByteArray();
	}

	/**
	 * @throws NullPointerException First parameter is null
	 * @throws IndexOutOfBoundsException File doesn't exists
	 * @throws IndexOutOfBoundsException One or more directories from path doesn't exists
	 */
	@Override
	public void writeFile(String filePath, byte[] fileBytes) throws Exception {
		String fileID = query.fileID(filePath);
		
		ByteArrayContent fileContent = new ByteArrayContent(null, fileBytes);
		
		drive.files()
			.update(fileID, null, fileContent)
			.execute();
	}

	/**
	 * @throws NullPointerException First parameter is null
	 * @throws IndexOutOfBoundsException File doesn't exists
	 * @throws IndexOutOfBoundsException One or more directories from path doesn't exists
	 */
	@Override
	public void deleteFile(String filePath) throws Exception {
		String fileID = query.fileID(filePath);
		
		drive.files()
			.delete(fileID)
			.execute();
		
		pathsIDs.put(filePath, null);
	}

}
