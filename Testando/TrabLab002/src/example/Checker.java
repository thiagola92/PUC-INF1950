package example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class Checker {
	
	private Path infoPath;
	private MessageDigest messageDigest;
	
	private ArrayList<String> fileList;
	private ArrayList<String[]> filesInfo;
	
	private enum Status {
		UNKNOW,
		OK,
		NOT_OK,
		NOT_FOUND,
		COLLISION,
	}
	
	public void setDigestType(String type) throws Exception {
		System.out.println("Tipo do digest: " + type);
		messageDigest = MessageDigest.getInstance(type);
		System.out.println("Provider: " + messageDigest.getProvider());
		
		System.out.println("//////////////////////////////////");
	}
	
	public void setFilePath(String path) throws Exception {
		System.out.println("Files info: " + path);
		infoPath = Paths.get(path);
		System.out.println("Path to files info: " + infoPath.toAbsolutePath());
		
		filesInfo = new ArrayList<String[]>();
		List<String> info = Files.readAllLines(infoPath);
		for(int i = 0; i < info.size(); i++) {
			filesInfo.add(info.get(i).split(" "));
			System.out.println(info.get(i));
		}
		
		System.out.println("//////////////////////////////////");
	}
	
	public void setFilesList(ArrayList<String> fileList) {
		this.fileList = fileList;
	}
	
	private String digestFromFile(Path filePath) throws Exception {
		
		if(Files.exists(filePath) == false) {
			System.err.print("FILE DON'T EXIST, EXITING \n");
			System.exit(2);
		}
		
		byte[] fileBytes = Files.readAllBytes(filePath);
		messageDigest.update(fileBytes, 0, fileBytes.length);
		fileBytes = messageDigest.digest();
		
		return convertToString(fileBytes);
	}
	
	private String digestFromInfo(int index) throws Exception {
		String[] information = filesInfo.get(index);
		for(int i = 1; i < information.length; i=i+2) {
			String type = information[i];
			
			if(type.equals(messageDigest.getAlgorithm()))
				return information[i+1];
		}
		
		return null;
	}
	
	private String convertToString(byte[] fileDigest) {
		String digest = "";
		
		for(int i = 0; i < fileDigest.length; i++)
			digest = digest + String.format("%02X", fileDigest[i]);
		
		return digest;
	}
	
	private Status findFileStatus(String fileName, String fileDigest) throws Exception {	
		Status status = Status.UNKNOW;
		
		// Looking at arguments
		for(int i = 0; i < fileList.size(); i++) {
			Path path = Paths.get(fileList.get(i));
			String name = path.getFileName().toString();
			
			if(fileName.equals(name))
				continue;
			
			String digest = digestFromFile(path);
			
			if(fileDigest.equals(digest))
				status = Status.COLLISION;
		}
		
		// Looking at list
		for(int i = 0; i < filesInfo.size(); i++) {
			String name = filesInfo.get(i)[0];
			String digest = digestFromInfo(i);
			
			if(fileName.equals(name) && status == Status.UNKNOW) {
				if(fileDigest.equals(digest))
					status = Status.OK;
				else if(digest != null)
					status = Status.NOT_OK;
			} else if(fileDigest.equals(digest))
				status = Status.COLLISION;
		}
		
		if(status == Status.UNKNOW)
			status = Status.NOT_FOUND;
		
		return status;
	}
	
	private void newFileDigest(String fileName, String fileDigest) throws Exception {
		
		for(int i = 0; i < filesInfo.size(); i++) {
			String name = filesInfo.get(i)[0];
			
			if(name.equals(fileName)) {
				int length = filesInfo.get(i).length;
				
				String[] oldInfo = filesInfo.get(i);
				String[] newInfo = new String[length + 2];
				
				for(int j = 0; j < oldInfo.length; j++)
					newInfo[j] = oldInfo[j];
				
				newInfo[length] = messageDigest.getAlgorithm();
				newInfo[length + 1] = fileDigest;
				
				filesInfo.set(i, newInfo);
				
				return;
			}
		}
		
		String[] newInfo = new String[3];
		newInfo[0] = fileName;
		newInfo[1] = messageDigest.getAlgorithm();
		newInfo[2] = fileDigest;
		
		filesInfo.add(newInfo);
	}
	
	private void reWriteFile() throws Exception {
		StringBuilder text = new StringBuilder();
		
		for(int i = 0; i < filesInfo.size(); i++) {
			String[] info = filesInfo.get(i);
			
			for(int j = 0; j < info.length; j++)
				text.append(info[j] + " ");
			
			text.append("\n");
		}
		
		Files.write(infoPath, text.toString().getBytes(),StandardOpenOption.WRITE);
	}
	
	public void check( ) throws Exception {
		boolean notFound = true;
		
		for(int i = 0; i < fileList.size(); i++) {
			Path filePath = Paths.get(fileList.get(i));
			
			String fileName = filePath.getFileName().toString();
			String fileDigest = digestFromFile(filePath);
			Status status = findFileStatus(fileName, fileDigest);
			
			System.out.print(fileName + " ");
			System.out.print(messageDigest.getAlgorithm() + " ");
			System.out.print(fileDigest + " ");
			System.out.print(status + " ");
			
			if(status == Status.NOT_FOUND) {
				newFileDigest(fileName, fileDigest);
				notFound = true;
			}
			
			System.out.println();
		}
		
		if(notFound == true)
			reWriteFile();
	}

}
