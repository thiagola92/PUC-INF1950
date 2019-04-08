package plugin.googledrive;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public class Query {
	
	private Drive drive;
	
	public Query(Drive drive) {
		this.drive = drive;
	}

	// One query gives you a list of files and folders
	public List<File> doQuery(String query) throws Exception {
		return drive.files()
				.list()
				.setQ(query)
				.setFields("files(id, name, parents, mimeType)")
				.execute()
				.getFiles();
	}
	
	// Every file and folder have an ID
	// The root folder ID is "root"
	public String getRootID() throws Exception {
		return drive.files()
				.get("root")
				.execute()
				.getId();
	}
	
	public List<File> getEverything(String folderID) throws Exception {
		if(folderID == null)
			return null;
		
		String query = "trashed = false"
						+ " and parents = '%s'"; // Should i use " and '%s' in parents" ?
		query = String.format(query, folderID);
		
		return doQuery(query);
	}

	// Inside one folder can exist many folders with the same name
	public List<File> getFoldersNamed(String parentID, String folderName) throws Exception {
		if(parentID == null || folderName == null)
			return null;
		
		String query = "mimeType = 'application/vnd.google-apps.folder'"
						+ " and trashed = false"
						+ " and parents = '%s'" // Should i use " and '%s' instead parents" ?
						+ " and name = '%s'";
		query = String.format(query, parentID, folderName);
		
		return doQuery(query);
	}

	// Inside one folder can exist many files with the same name
	public List<File> getFilesNamed(String parentID, String fileName) throws Exception {
		if(parentID == null || fileName == null)
			return null;
		
		String query = "mimeType != 'application/vnd.google-apps.folder'"
						+ " and trashed = false"
						+ " and parents = '%s'" // Should i use " and '%s' instead parents" ?
						+ " and name = '%s'";
		query = String.format(query, parentID, fileName);
		
		return doQuery(query);
	}
	
	// Starting from root, you will navigate through the parent folders until the last parent
	public String getParentID(String filePath) throws Exception {
		Path parents = Paths.get(filePath).getParent();
		
		if(parents == null)
			return getRootID();

		ArrayList<String> parentsList = new ArrayList<String>();
		
		parents.forEach(folderName -> {
			parentsList.add(folderName.toString());
		});
		
		String lastParent = getRootID();
		
		for(int i = 0; i < parentsList.size(); i++) {
			String folderName = parentsList.get(i);
			String folderID = getFoldersNamed(lastParent, folderName).get(0).getId();
			
			lastParent = folderID;
		}
		
		return lastParent;
	}

}
