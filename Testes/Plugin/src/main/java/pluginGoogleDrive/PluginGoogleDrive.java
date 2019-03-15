package pluginGoogleDrive;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow.Builder;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import plugin.Plugin;

public class PluginGoogleDrive implements Plugin {
	
	private static String CREDENTIAL_PATH = "credentials.json";
	private static String TOKEN_DIRECOTRY = "tokens";
	
	private final static JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private final static java.util.List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
	
	private static NetHttpTransport HTTP_TRANSPORT;
	private Drive drive;
	private UtilityGoogleDrive utility;
	
	public PluginGoogleDrive() throws Exception {
		HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		drive = new Drive(HTTP_TRANSPORT, JSON_FACTORY, getCredentials());
		utility = new UtilityGoogleDrive(drive);
	}

	private static Credential getCredentials() throws Exception {
		InputStream credentialFile = PluginGoogleDrive.class.getResourceAsStream(CREDENTIAL_PATH);
		InputStreamReader credentialReader = new InputStreamReader(credentialFile);
		GoogleClientSecrets googleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, credentialReader);
		
		java.io.File tokenDirectory = new java.io.File(TOKEN_DIRECOTRY);
		FileDataStoreFactory dataStore = new FileDataStoreFactory(tokenDirectory);
		Builder builder = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, googleClientSecrets, SCOPES);
		GoogleAuthorizationCodeFlow flow = builder.setDataStoreFactory(dataStore).setAccessType("offline").build();
		
		LocalServerReceiver receiver = new LocalServerReceiver();
		AuthorizationCodeInstalledApp authorizationApp = new AuthorizationCodeInstalledApp(flow, receiver);
		Credential credential = authorizationApp.authorize("user");
		
		return credential;
	}

	@Override
	public void createFolder(String folderPath) throws Exception {
		String folderName = Paths.get(folderPath).getFileName().toString();
		String parentID = utility.getParentID(folderPath);
		
		if(utility.getFoldersNamed(parentID, folderName).size() > 0)
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
		String parentID = utility.getParentID(folderPath);
		String folderID = parentID;
		
		// Se a pasta tiver nome, pegar a id da pasta
		if(folderName.isEmpty() == false)
			folderID = utility.getFoldersNamed(parentID, folderName).get(0).getId();

		ArrayList<File> fileMetadataList = (ArrayList<File>) utility.getEverything(folderID);
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
//		fileMetadataList.forEach((File file) -> {
//			String[] filesInfo = new String[2];
//			filesInfo[1] = file.getName();
//			
//			if(file.getMimeType().equals("application/vnd.google-apps.folder"))
//				filesInfo[2] = "folder";
//			else
//				filesInfo[2] = "file";
//			
//			filesList.add(filesInfo);
//		});
		
		return filesList;
	}

	@Override
	public void deleteFolder(String folderPath) throws Exception {
		String folderName = Paths.get(folderPath).getFileName().toString();
		String parentID = utility.getParentID(folderPath);
		String folderID = utility.getFoldersNamed(parentID, folderName).get(0).getId();
		
		if(utility.getEverything(folderID).size() > 0)
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
		String parentID = utility.getParentID(filePath);
		
		if(utility.getFilesNamed(parentID, fileName).size() > 0)
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
		String parentID = utility.getParentID(filePath);
		String fileID = utility.getFilesNamed(parentID, fileName).get(0).getId();
		
		ByteArrayOutputStream fileBytes =  new ByteArrayOutputStream(); 
		
		drive.files()
			.get(fileID)
			.executeMediaAndDownloadTo(fileBytes);
		
		return fileBytes.toByteArray();
	}

	@Override
	public void writeFile(String filePath, byte[] fileBytes) throws Exception {
		String fileName = Paths.get(filePath).getFileName().toString();
		String parentID = utility.getParentID(filePath);
		String fileID = utility.getFilesNamed(parentID, fileName).get(0).getId();
		
		ByteArrayContent fileContent = new ByteArrayContent(null, fileBytes);
		
		drive.files()
			.update(fileID, null, fileContent)
			.execute();
	}

	@Override
	public void deleteFile(String filePath) throws Exception {
		String fileName = Paths.get(filePath).getFileName().toString();
		String parentID = utility.getParentID(filePath);
		String fileID = utility.getFilesNamed(parentID, fileName).get(0).getId();
		
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
