import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow.Builder;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Create;
import com.google.api.services.drive.Drive.Files.Get;
import com.google.api.services.drive.Drive.Files.List;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class GoogleDrive {
	
	private String CREDENTIAL_PATH = "credentials.json";
	private String TOKEN_DIRECOTRY = "tokens";
	
	private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private final java.util.List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
	
	private NetHttpTransport HTTP_TRANSPORT;
	private Drive drive;
	
	public GoogleDrive() throws Exception {
		HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		drive = new Drive(HTTP_TRANSPORT, JSON_FACTORY, getCredentials());
	}
	
	private Credential getCredentials() throws Exception {
		InputStream credentialFile = GoogleDrive.class.getResourceAsStream(CREDENTIAL_PATH);
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
	
	public File uploadFile(String filePath) throws Exception {
		Path path = Paths.get(filePath);
		java.io.File file = new java.io.File(path.toString());
		FileContent fileContent = new FileContent(null, file);

		File fileMetadata =  new File().setName(path.getFileName().toString());
		
		Files driveFiles = drive.files();
		Create createRequest = driveFiles.create(fileMetadata, fileContent);
		File driveFile = createRequest.setFields("id, name").execute();
		
		return driveFile;
	}
	
	public java.util.List<File> listFiles(String query) throws Exception {
		Files driveFiles = drive.files();
		List listRequest = driveFiles.list();
		FileList fileList = listRequest.setQ(query).setFields("nextPageToken, files(id, name)").execute();
		java.util.List<File> files = fileList.getFiles();
		
		return files;
	}
	
	public File downloadFile(String fileId) throws Exception {
		Files driveFiles = drive.files();
		Get fileRequest = driveFiles.get(fileId);
		File fileMetadata = fileRequest.execute();
		
		FileOutputStream file = new FileOutputStream(fileMetadata.getName());
		fileRequest.executeMediaAndDownloadTo(file);

		return fileMetadata;
	}

}
