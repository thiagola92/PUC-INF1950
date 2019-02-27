import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public class GoogleDriveUtility {
	
	private Drive drive;
	
	public GoogleDriveUtility(Drive drive) {
		this.drive = drive;
	}
	
	// Every file and folder have an ID
	// The root folder ID is "root"
	public String getRootID() throws Exception {
		return drive.files()
				.get("root")
				.execute()
				.getId();
	}

	// One query gives you a list of files and folders
	public List<File> getFiles(String query) throws Exception {
		return drive.files()
				.list()
				.setQ(query)
				.setFields("files(id, name, parents)")
				.execute()
				.getFiles();
	}

	// Inside one folder can exist many folders with the same name
	public List<File> getFoldersNamed(String parentID, String folderName) throws Exception {
		if(parentID == null || folderName == null)
			return null;
		
		String baseQuery = "mimeType = 'application/vnd.google-apps.folder'"
						+ " and trashed = false"
						+ " and parents = '%s'" // Should i use " and '%s' instead parents" ?
						+ " and name = '%s'";
		String query = String.format(baseQuery, parentID, folderName);
		
		return getFiles(query);
	}

	// Inside one folder can exist many files with the same name
	public List<File> getFilesNamed(String parentID, String fileName) throws Exception {
		if(parentID == null || fileName == null)
			return null;
		
		String baseQuery = "mimeType != 'application/vnd.google-apps.folder'"
						+ " and trashed = false"
						+ " and parents = '%s'" // Should i use " and '%s' instead parents" ?
						+ " and name = '%s'";
		String query = String.format(baseQuery, parentID, fileName);
		
		return getFiles(query);
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
