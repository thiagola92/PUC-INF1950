package pluginGoogleDrive;

import java.io.ByteArrayOutputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import plugin.PluginInterface;

public class PluginGoogleDrive implements PluginInterface {
	
	private Drive drive;
	private Query query;
	
	public PluginGoogleDrive() throws Exception {
		drive = Utility.getDrive();
		query = new Query(drive);
	}

	@Override
	public void createFolder(String folderPath) throws Exception {
		String folderName = Paths.get(folderPath).getFileName().toString();
		String parentID = query.getParentID(folderPath);
		
		if(query.getFoldersNamed(parentID, folderName).size() > 0)
			throw new FileAlreadyExistsException("There is one or more folders with this name inside this folder");
		
		ArrayList<String> parent = new ArrayList<String>();
		parent.add(parentID);
		
		File folderMetadata = new File()
				.setName(folderName)
				.setParents(parent)
				.setMimeType("application/vnd.google-apps.folder");
		
		drive.files()
			.create(folderMetadata)
			.setFields("id, name, parents")
			.execute();
	}

	@Override
	public ArrayList<String[]> listFolder(String folderPath) throws Exception {
		String folderName = Paths.get(folderPath).getFileName().toString();
		String parentID = query.getParentID(folderPath);
		String folderID = parentID;
		
		// Se a pasta tiver nome, pegar a id da pasta
		if(folderName.isEmpty() == false)
			folderID = query.getFoldersNamed(parentID, folderName).get(0).getId();

		ArrayList<File> fileMetadataList = (ArrayList<File>) query.getEverything(folderID);
		ArrayList<String[]> filesList = new ArrayList<String[]>();
		
		for(int i = 0; i < fileMetadataList.size(); i++) {
			String[] filesInfo = new String[2];
			filesInfo[0] = fileMetadataList.get(i).getName();
			
			if(fileMetadataList.get(i).getMimeType().equals("application/vnd.google-apps.folder"))
				filesInfo[1] = "folder";
			else
				filesInfo[1] = "file";
			
			filesList.add(filesInfo);
		}
		
		return filesList;
	}

	@Override
	public void deleteFolder(String folderPath) throws Exception {
		String folderName = Paths.get(folderPath).getFileName().toString();
		String parentID = query.getParentID(folderPath);
		String folderID = query.getFoldersNamed(parentID, folderName).get(0).getId();
		
		if(query.getEverything(folderID).size() > 0)
			throw new DirectoryNotEmptyException("There is one or more files in this folder");
		
		// Movendo para lixeira
		//File fileMetadata = new File();
		//fileMetadata.setTrashed(true);
		//
		//Files driveFiles = drive.files();
		//Update updateRequest = driveFiles.update(folderID, fileMetadata);
		//updateRequest.execute();
		
		// Deleta Permanentemente
		drive.files()
			.delete(folderID)
			.execute();
	}

	@Override
	public void createFile(String filePath) throws Exception {
		String fileName = Paths.get(filePath).getFileName().toString();
		String parentID = query.getParentID(filePath);
		
		if(query.getFilesNamed(parentID, fileName).size() > 0)
			throw new FileAlreadyExistsException("There is one or more files with this name inside this folder");

		ArrayList<String> parent = new ArrayList<String>();
		parent.add(parentID);
		
		File fileMetadata = new File()
				.setName(fileName)
				.setParents(parent);
		
		drive.files()
			.create(fileMetadata)
			.setFields("id, name, parents")
			.execute();
	}

	@Override
	public byte[] readFile(String filePath) throws Exception {
		String fileName = Paths.get(filePath).getFileName().toString();
		String parentID = query.getParentID(filePath);
		String fileID = query.getFilesNamed(parentID, fileName).get(0).getId();
		
		ByteArrayOutputStream fileBytes =  new ByteArrayOutputStream(); 
		
		drive.files()
			.get(fileID)
			.executeMediaAndDownloadTo(fileBytes);
		
		return fileBytes.toByteArray();
	}

	@Override
	public void writeFile(String filePath, byte[] fileBytes) throws Exception {
		String fileName = Paths.get(filePath).getFileName().toString();
		String parentID = query.getParentID(filePath);
		String fileID = query.getFilesNamed(parentID, fileName).get(0).getId();
		
		ByteArrayContent fileContent = new ByteArrayContent(null, fileBytes);
		
		drive.files()
			.update(fileID, null, fileContent)
			.execute();
	}

	@Override
	public void deleteFile(String filePath) throws Exception {
		String fileName = Paths.get(filePath).getFileName().toString();
		String parentID = query.getParentID(filePath);
		String fileID = query.getFilesNamed(parentID, fileName).get(0).getId();
		
		// Movendo para lixeira
		//File fileMetadata = new File();
		//fileMetadata.setTrashed(true);
		//
		//Files driveFiles = drive.files();
		//Update updateRequest = driveFiles.update(fileID, fileMetadata);
		//updateRequest.execute();
		
		// Deleta Permanentemente
		drive.files()
			.delete(fileID)
			.execute();
		
	}

}
