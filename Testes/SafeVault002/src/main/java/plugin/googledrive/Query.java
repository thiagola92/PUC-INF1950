package plugin.googledrive;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public class Query {
	
	private Drive drive;
	private HashMap<String, String> pathsIDs;
	
	public Query(Drive drive, HashMap<String, String> pathsIDs) {
		this.drive = drive;
		this.pathsIDs = pathsIDs;
		
		try {
			root();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<File> query(String query) throws Exception {
		return drive.files()
				.list()
				.setQ(query)
				.setFields("files(id, name, parents, mimeType)")
				.execute()
				.getFiles();
	}

	public String root() throws Exception {
		if(pathsIDs.get("/") != null)
			return pathsIDs.get("/");
		
		String id = drive.files()
				.get("root")
				.execute()
				.getId();
		
		pathsIDs.put("/", id);
		
		return id;
	}
	
	public List<File> everything(String folderID) throws Exception {		
		String query = "trashed = false"
						+ " and parents = '%s'";
		query = String.format(query, folderID);
		
		return query(query);
	}
	
	public List<File> foldersNamed(String parentID, String folderName) throws Exception {		
		String query = "mimeType = 'application/vnd.google-apps.folder'"
						+ " and trashed = false"
						+ " and parents = '%s'"
						+ " and name = '%s'";
		query = String.format(query, parentID, folderName);
		
		return query(query);
	}

	public List<File> filesNamed(String parentID, String fileName) throws Exception {		
		String query = "mimeType != 'application/vnd.google-apps.folder'"
						+ " and trashed = false"
						+ " and parents = '%s'"
						+ " and name = '%s'";
		query = String.format(query, parentID, fileName);
		
		return query(query);
	}
	
	public String fileID(String filePath) throws Exception {
		filePath = normalize(filePath);
		
		if(pathsIDs.get(filePath) != null)
			return pathsIDs.get(filePath);
		
		Path path = Paths.get(filePath);
		Path parent = path.getParent();
		String fileName = path.getFileName().toString();
		String parentID = null;
		
		if(parent == null)
			parentID = root();
		else
			parentID = parentID(parent.toString());
		
		String fileID = filesNamed(parentID, fileName).get(0).getId();
		
		pathsIDs.put(filePath, fileID);
		
		return fileID;
	}
	
	public String parentID(String filePath) throws Exception {
		filePath = normalize(filePath);
		
		Path path = Paths.get(filePath).getParent();
		
		if(path == null)
			return root();
		
		return folderID(path.toString());
	}
	
	public String folderID(String folderPath) throws Exception {
		folderPath = normalize(folderPath);
		
		if(pathsIDs.get(folderPath) != null)
			return pathsIDs.get(folderPath);
		
		Path path = Paths.get(folderPath).normalize();
		Path parent = path.getParent();
		String folderName = path.getFileName().toString();
		String parentID = null;
		
		if(parent == null)
			parentID = root();
		else
			parentID = folderID(parent.toString());
		
		String folderID = foldersNamed(parentID, folderName).get(0).getId();
		
		pathsIDs.put(folderPath, folderID);
		
		return folderID;
	}
	
	private String normalize(String path) {
		return Paths.get(path).normalize().toString();
	}

}
