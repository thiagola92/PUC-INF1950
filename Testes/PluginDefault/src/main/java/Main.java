import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;

public class Main {
	
	public enum Error {
		NO_ERROR,
		EXCEPTION,
		NULL_POINTER,
		NO_SUCH_FILE,
		NOT_A_DIRECTORY,
	};

	public static DefaultPlugin defaultPlugin = new DefaultPlugin();
	
	public static void main(String[] args) throws Exception {
		assert(tryListFolder("") == Error.NO_ERROR);
		assert(tryListFolder(".") == Error.NO_ERROR);
		assert(tryListFolder("/") == Error.NO_ERROR);
		assert(tryListFolder("..") == Error.NO_ERROR);
		
		// Error when listing null
		assert(tryListFolder(null) == Error.NULL_POINTER);
		
		// Error when listing folder that doesn't exist
		assert(tryListFolder("fileNotCreated") == Error.NO_SUCH_FILE);
		
		// Error when creating folder null
		assert(tryCreateFolder(null) == Error.NULL_POINTER);
		
		// Doesn't create a folder that already exist
		assert(tryCreateFolder("testFolder") == Error.NO_ERROR);
		assert(tryCreateFolder("testFolder") == Error.NO_ERROR);
		assert(tryListFolder("testFolder") == Error.NO_ERROR);
		assert(tryDeleteFolder("testFolder") == Error.NO_ERROR);

		// Create folder inside another folder
		assert(tryCreateFolder("testFolder") == Error.NO_ERROR);
		assert(tryCreateFolder("testFolder/anotherFolder") == Error.NO_ERROR);
		assert(tryListFolder("testFolder") == Error.NO_ERROR);
		assert(tryDeleteFolder("testFolder") == Error.NO_ERROR);

		// Create folder recursively
		assert(tryCreateFolder("testFolder/anotherFolder") == Error.NO_ERROR);
		assert(tryDeleteFolder("testFolder") == Error.NO_ERROR);
		
		// Error when deleting folder null
		assert(tryDeleteFolder(null) == Error.NO_ERROR);
		
//		assert(tryCreateFolder("testFolder") == Error.NO_ERROR);
//		assert(tryListFolder("testFolder") == Error.NO_ERROR);
//		assert(tryDeleteFolder("testFolder") == Error.NO_ERROR);
//		assert(tryDeleteFolder("testFolder") == Error.NO_ERROR);
//		assert(tryDeleteFolder("testFolder/anotherFolder") == Error.NO_ERROR);
		
	}
	
	public static Error tryListFolder(String folder) {
		try {
			ArrayList<String> files = defaultPlugin.listFolder(folder);
			System.out.println(files);
		} catch(NullPointerException e) {
			return Error.NULL_POINTER;
		} catch(NoSuchFileException e) {
			return Error.NO_SUCH_FILE;
		} catch(NotDirectoryException e) {
			return Error.NOT_A_DIRECTORY;
		} catch(Exception e) {
			System.out.println(e);
			return Error.EXCEPTION;
		}
		
		return Error.NO_ERROR;
	}
	
	public static Error tryCreateFolder(String folder) {		
		try {
			defaultPlugin.createFolder(folder);
		} catch(NullPointerException e) {
			return Error.NULL_POINTER;
		} catch(Exception e) {
			System.out.println(e);
			return Error.EXCEPTION;
		}
		
		return Error.NO_ERROR;
	}
	
	public static Error tryDeleteFolder(String folder) {		
		try {
			defaultPlugin.deleteFolder(folder);
		} catch(Exception e) {
			System.out.println(e);
			return Error.EXCEPTION;
		}
		
		return Error.NO_ERROR;
	}
	
	public static void deleteFolder(String folder) throws Exception {
		boolean foundFolder = true;
		
		defaultPlugin.deleteFolder(folder);

		for(String f : defaultPlugin.listFolder(""))
			foundFolder = f.equals(folder) ? false : foundFolder;
		
		if(foundFolder == false) {
			System.err.println("Failed to delete folder");
			System.exit(2);
		}
	}
	
	public static void createFile(String folder, String file, String content) throws Exception {
		boolean foundFile = false;
		String path = folder + "/" + file;
		
		defaultPlugin.createFile(path, content.getBytes());

		for(String f : defaultPlugin.listFolder(folder))
			foundFile = f.equals(path) ? true : foundFile;
		
		if(foundFile == false) {
			System.err.println("Failed to create file");
			System.exit(3);
		}
	}
	
	public static void readFile(String folder, String file) throws Exception {
		String path = folder + "/" + file;
		
		byte[] bytes = defaultPlugin.readFile(path);
		
		System.out.println(new String(bytes));
	}

}
