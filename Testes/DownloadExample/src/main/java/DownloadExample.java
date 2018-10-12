/*
 * Para rodar esse exemplo:
 * 
 * Baixe sua credencial do Google Drive em 
 * https://console.cloud.google.com
 * Renomei ela para credentials.json
 * Bote na pasta bin (mesma pasta onde se encontra DownloadExample.class)
 * 
 * Teste:
 * Faça upload do "TestandoDownload.png" ou "TestandoDownload"
 * (bote no google drive de maneira normal)
 * Delete o arquivo da pasta
 * Rode esse exemplo para ver se ele pega o arquivo no Google Drive
 * (o exemplo baixa o arquivo mais recente do Google Drive)
 */

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow.Builder;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Get;
import com.google.api.services.drive.Drive.Files.List;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collections;

public class DownloadExample {
	
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    
    // Escolha o SCOPE da sua aplicação
    // https://developers.google.com/drive/api/v3/about-auth
    private static final java.util.List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);

	public static void main(String[] args) throws Exception {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		
		System.out.println(">> Connecting to Drive");
		Drive drive = new Drive(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT));
		
		System.out.println(">> Request List of Files");
		Files filesRequest = drive.files();
		List listRequest = filesRequest.list();
		listRequest = listRequest.setPageSize(10);
		listRequest = listRequest.setFields("nextPageToken, files(id, name, size)");
		FileList fileList = listRequest.execute();
		
		System.out.println(">> List of Files");
		java.util.List<File> files = fileList.getFiles();
		
		if(files == null || files.isEmpty()) {
			System.out.println(">> No Files Found");
		} else {
			System.out.println(">> Getting Recent File id");
			File file = files.get(0);
			String id = file.getId();
			
			System.out.println(">> Downloading File");
			OutputStream out = new java.io.FileOutputStream(file.getName());
			Get fileRequest = filesRequest.get(id);
			fileRequest.executeMediaAndDownloadTo(out);
			out.flush();
			out.close();
		}
		
		System.out.println(">> Finishing");
	}
	
	private static Credential getCredentials(NetHttpTransport HTTP_TRANSPORT) throws Exception {
		
		System.out.println(">> Loading Client Secrets");
		InputStream in = DownloadExample.class.getResourceAsStream("./credentials.json"); // Busca no mesmo local do UploadExample.class
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
