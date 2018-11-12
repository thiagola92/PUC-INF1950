import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
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
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Create;
import com.google.api.services.drive.Drive.Files.Delete;
import com.google.api.services.drive.Drive.Files.Get;
import com.google.api.services.drive.Drive.Files.List;
import com.google.api.services.drive.Drive.Files.Update;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class GoogleDrivePlugin implements Plugin {
	
	private String CREDENTIAL_PATH = "credentials.json";
	private String TOKEN_DIRECOTRY = "tokens";
	
	private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private final java.util.List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
	
	private NetHttpTransport HTTP_TRANSPORT;
	private Drive drive;
	
	public GoogleDrivePlugin() throws Exception {
		HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		drive = new Drive(HTTP_TRANSPORT, JSON_FACTORY, getCredentials());
	}
	
	private Credential getCredentials() throws Exception {
		InputStream credentialFile = GoogleDrivePlugin.class.getResourceAsStream(CREDENTIAL_PATH);
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
	
	private String getRootID() throws Exception {
		Files driveFiles = drive.files();
		Get getRequest = driveFiles.get("root");
		File rootMetadata = getRequest.execute();
		String rootID = rootMetadata.getId();
		System.out.println(">> root ID: " + rootID);

		return rootID;
	}
	
	private String getFolderID(String parentID, String folderName) throws Exception {
		if(parentID == null || folderName == null)
			return null;
		
		String baseQuery = "mimeType = 'application/vnd.google-apps.folder'"
						+ " and trashed = false"
						+ " and parents = '%s'" // Should i use " and '%s' in parents" ?
						+ " and name = '%s'";
		String query = String.format(baseQuery, parentID, folderName);
		
		Files driveFiles = drive.files();
		List listRequest = driveFiles.list();
		
		listRequest.setQ(query);
		listRequest.setFields("files(id, name, parents)");
		
		FileList fileList = listRequest.execute();
		ArrayList<File> fileArray = (ArrayList<File>) fileList.getFiles();
		File firstFile = fileArray.get(0);
		String folderID = firstFile.getId();

		System.out.println(">> folder ID: " + folderID);
		
		return folderID;
	}
	
	private ArrayList<String> getParentsID(String filePath) throws Exception {
		Path parents = Paths.get(filePath).getParent();
		
		if(parents == null)
			return null;

		ArrayList<String> parentsList = new ArrayList<String>();
		
		parents.forEach((Path folderName) -> {
			parentsList.add(folderName.toString());
			System.out.println(">> folder name: " + folderName);
		});
		
		String lastParent = getRootID();
		
		for(int i = 0; i < parentsList.size(); i++) {
			String folderName = parentsList.get(i);
			String folderID = getFolderID(lastParent, folderName);
			
			parentsList.set(i, folderID);
			lastParent = folderID;
		}
		
		return parentsList;
	}
	
	private String getParentID(String filePath) throws Exception {
		ArrayList<String> parentsList = getParentsID(filePath);
		
		if(parentsList == null)
			return null;
		
		int lastIndex = parentsList.size() - 1;
		return parentsList.get(lastIndex);
	}

	@Override
	public void createFolder(String folderPath) throws Exception {
		Path path = Paths.get(folderPath);
		String folderName = path.getFileName().toString();
		ArrayList<String> parent = new ArrayList<String>();
		
		String parentID = getParentID(folderPath);
		
		if(parentID == null)
			parent.add(getRootID());
		else
			parent.add(parentID);
		
		File folderMetadata = new File();
		folderMetadata.setName(folderName);
		folderMetadata.setParents(parent);
		folderMetadata.setMimeType("application/vnd.google-apps.folder");
		
		Files driveFiles = drive.files();
		Create createRequest = driveFiles.create(folderMetadata);
		createRequest.setFields("id, name, parents");
		
		File driveFile = createRequest.execute();
	}

	@Override
	public ArrayList<String> listFolder(String folderPath) throws Exception {
		Path path = Paths.get(folderPath);
		String folderName = path.getFileName().toString();
		
		String parentID = getParentID(folderPath);
		if(parentID == null)
			parentID = getRootID();
		
		String folderID = parentID;
		if("".equals(folderName) == false)	// Se tiver nome então procurar pela ID, se não tiver então a ID dele é a do pai
			folderID = getFolderID(parentID, folderName);
		
		String baseQuery = "trashed = false"
						+ " and parents = '%s'"; // Should i use " and '%s' in parents" ?
		String query = String.format(baseQuery, folderID);
		
		Files driveFiles = drive.files();
		List listRequest = driveFiles.list();
		
		listRequest.setQ(query);
		listRequest.setFields("files(id, name, parents)");

		FileList fileList = listRequest.execute();
		ArrayList<File> fileMetadataList = (ArrayList<File>) fileList.getFiles();
		ArrayList<String> folderList = new ArrayList<String>();

		fileMetadataList.forEach((File file) -> {
			folderList.add(file.getName());
			System.out.println(">> file name: " + file.getName());
		});
		
		return folderList;
	}

	@Override
	public void deleteFolder(String folderPath) throws Exception {
		Path path = Paths.get(folderPath);
		String folderName = path.getFileName().toString();
		
		String parentID = getParentID(folderPath);
		if(parentID == null)
			parentID = getRootID();
		
		String folderID = getFolderID(parentID, folderName);
		
		File fileMetadata = new File();
		fileMetadata.setTrashed(true);

		Files driveFiles = drive.files();
		Update updateRequest = driveFiles.update(folderID, fileMetadata);
		updateRequest.execute();
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
