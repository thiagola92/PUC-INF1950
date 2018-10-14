/*
 * Para rodar esse exemplo:
 * 
 * Baixe sua credencial do Google Drive em 
 * https://console.cloud.google.com
 * Renomei ela para credentials.json
 * Bote na pasta bin (mesma pasta onde se encontra UploadExample.class)
 */

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow.Builder;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Create;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class UploadExample {
	
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    
    // Escolha o SCOPE da sua aplicação
    // https://developers.google.com/drive/api/v3/about-auth
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);

	public static void main(String[] args) throws Exception {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		
		System.out.println(">> Connecting to Drive");
		Drive drive = new Drive(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT));
		
		System.out.println(">> Selecting File");
		File fileMetadata = new File();
		fileMetadata.setName("test");
		java.io.File filePath = new java.io.File("test"); // Busca na pasta UploadExample
		FileContent fileContent = new FileContent(null, filePath);
		
		System.out.println(">> Uploading File");
		Files files = drive.files();
		Create create = files.create(fileMetadata, fileContent);
		create = create.setFields("id");
		File file = create.execute();
		System.out.println(file.getId());
		
		System.out.println(">> Finishing");
	}
	
	private static Credential getCredentials(NetHttpTransport HTTP_TRANSPORT) throws Exception {
		
		System.out.println(">> Loading Client Secrets");
		InputStream in = UploadExample.class.getResourceAsStream("credentials.json"); // Busca no mesmo local do UploadExample.class
		InputStreamReader inReader = new InputStreamReader(in);
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, inReader);

		System.out.println(">> Getting Data Store");
		java.io.File dataDirectory = new java.io.File("tokens");
		FileDataStoreFactory dataStore = new FileDataStoreFactory(dataDirectory);
		
		System.out.println(">> Building Google Authorization Code Flow");
		Builder builder = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES);
		builder = builder.setDataStoreFactory(dataStore);
		builder = builder.setAccessType("offline");
		GoogleAuthorizationCodeFlow flow = builder.build();
		
		System.out.println(">> Getting Credential");
		LocalServerReceiver receiver = new LocalServerReceiver();
		AuthorizationCodeInstalledApp authorizationApp = new AuthorizationCodeInstalledApp(flow, receiver);
		Credential credential = authorizationApp.authorize("user");

		System.out.println(">> Returning Credential");
		return credential;
	}

}
