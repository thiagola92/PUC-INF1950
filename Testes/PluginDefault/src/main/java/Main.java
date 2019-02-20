import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	
	public enum Error {
		NO_ERROR,
		EXCEPTION,
		NULL_POINTER,
		NO_SUCH_FILE,
		NOT_A_DIRECTORY,
		FILE_ALREADY_EXISTS,
		WRONG_CONTENT,
	};

	public static DefaultPlugin defaultPlugin = new DefaultPlugin();
	
	// Os plugins foram desenvolvidos de maneira que repasse a menor quantidade de erros para o usuário
	// Exemplo,
	// Normalmente mandar criar pasta "folder1/folder2" geraria exception se folder1 não existisse
	// Porém o plugin adotou a metodologia de criar para o usuário as pastas que forem necessárias,
	// ou seja, criar folder1 antes de criar folder2
	public static void main(String[] args) throws Exception {
		testCreateFolder();
		testListFolder();
		testDeleteFolder();
		testCreateFile();
		testReadFile();
		//testWriteFile();
		testDeleteFile();
	}
	
	public static void testCreateFolder() {
		// Error when creating null folder
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
	}
	
	public static void testListFolder() {
		assert(tryListFolder("") == Error.NO_ERROR);
		assert(tryListFolder(".") == Error.NO_ERROR);
		assert(tryListFolder("/") == Error.NO_ERROR);
		assert(tryListFolder("..") == Error.NO_ERROR);
		
		// Error when listing null
		assert(tryListFolder(null) == Error.NULL_POINTER);
		
		// Error when listing folder that doesn't exist
		assert(tryListFolder("fileNotCreated") == Error.NO_SUCH_FILE);
		
		// Error when listing folder that is a file
		assert(tryCreateFile("testFile", "".getBytes()) == Error.NO_ERROR);
		assert(tryListFolder("testFile") == Error.NOT_A_DIRECTORY);
		assert(tryDeleteFile("testFile") == Error.NO_ERROR);
	}
	
	public static void testDeleteFolder() {
		// Error when deleting null folder
		assert(tryDeleteFolder(null) == Error.NULL_POINTER);
		
		// Doesn't delete a folder that doesn't exist
		assert(tryCreateFolder("testFolder") == Error.NO_ERROR);
		assert(tryListFolder("testFolder") == Error.NO_ERROR);
		assert(tryDeleteFolder("testFolder") == Error.NO_ERROR);
		assert(tryDeleteFolder("testFolder") == Error.NO_ERROR);
		
		// Doesn't delete a file thinking that is a folder
		assert(tryCreateFile("testFolder", "".getBytes()) == Error.NO_ERROR);
		assert(tryListFolder("") == Error.NO_ERROR);
		assert(tryDeleteFolder("testFolder") == Error.NO_ERROR);
		assert(tryDeleteFile("testFolder") == Error.NO_ERROR);
		
		// Delete folder recursively
		assert(tryCreateFolder("testFolder") == Error.NO_ERROR);
		assert(tryCreateFolder("testFolder/anotherFolder") == Error.NO_ERROR);
		assert(tryCreateFolder("testFolder/anotherFolderAgain") == Error.NO_ERROR);
		assert(tryListFolder("testFolder") == Error.NO_ERROR);
		assert(tryDeleteFolder("testFolder") == Error.NO_ERROR);
	}
	
	public static void testCreateFile() {
		// Error when creating null file
		assert(tryCreateFile(null, null) == Error.NULL_POINTER);
		assert(tryCreateFile(null, "test".getBytes()) == Error.NULL_POINTER);
		
		// Creating file with null content
		assert(tryCreateFile("testFile", null) == Error.NO_ERROR);
		assert(tryListFolder("") == Error.NO_ERROR);
		assert(tryDeleteFile("testFile") == Error.NO_ERROR);
		
		// Create a file writing an empty string
		assert(tryCreateFile("testFile", "".getBytes()) == Error.NO_ERROR);
		assert(tryListFolder("") == Error.NO_ERROR);
		assert(tryDeleteFile("testFile") == Error.NO_ERROR);
		
		// Error when creating a file that already exists
		assert(tryCreateFile("testFile", "test".getBytes()) == Error.NO_ERROR);
		assert(tryCreateFile("testFile", "testAgain".getBytes()) == Error.FILE_ALREADY_EXISTS);
		assert(tryListFolder("") == Error.NO_ERROR);
		assert(tryDeleteFile("testFile") == Error.NO_ERROR);
	}
	
	public static void testReadFile() {
		// Error when reading null file
		assert(tryReadFile(null, null) == Error.NULL_POINTER);

		// Error when reading file that doesn't exists
		assert(tryReadFile("testFile", null) == Error.NO_SUCH_FILE);
		
		// Error when reading empty file doesn't return null
		assert(tryCreateFile("testFile", null) == Error.NO_ERROR);
		assert(tryReadFile("testFile", null) == Error.WRONG_CONTENT);
		assert(tryDeleteFile("testFile") == Error.NO_ERROR);
		
		// Reading empty file returns an empty array
		assert(tryCreateFile("testFile", null) == Error.NO_ERROR);
		assert(tryReadFile("testFile", "".getBytes()) == Error.NO_ERROR);
		assert(tryDeleteFile("testFile") == Error.NO_ERROR);
		
		// Doesn't lost any byte when recovering
		assert(tryCreateFile("testFile", "test".getBytes()) == Error.NO_ERROR);
		assert(tryReadFile("testFile", "test".getBytes()) == Error.NO_ERROR);
		assert(tryDeleteFile("testFile") == Error.NO_ERROR);
		
		// Reading doesn't affect the file
		assert(tryCreateFile("testFile", "test".getBytes()) == Error.NO_ERROR);
		assert(tryReadFile("testFile", "test".getBytes()) == Error.NO_ERROR);
		assert(tryReadFile("testFile", "test".getBytes()) == Error.NO_ERROR);
		assert(tryDeleteFile("testFile") == Error.NO_ERROR);
		
	}
	
	public static void testWriteFile() {
		
	}
	
	public static void testDeleteFile() {
		// Error when deleting null
		assert(tryDeleteFile(null) == Error.NULL_POINTER);
		
		// Error when deleting a file that doesn't exists
		assert(tryCreateFile("testFile", "test".getBytes()) == Error.NO_ERROR);
		assert(tryListFolder("") == Error.NO_ERROR);
		assert(tryDeleteFile("testFile") == Error.NO_ERROR);
		assert(tryDeleteFile("testFile") == Error.NO_SUCH_FILE);

		// Doesn't delete a folder thinking that is a file
		assert(tryCreateFolder("testFolder") == Error.NO_ERROR);
		assert(tryListFolder("") == Error.NO_ERROR);
		assert(tryDeleteFile("testFolder") == Error.NO_ERROR);
		assert(tryDeleteFolder("testFolder") == Error.NO_ERROR);
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
	
	public static Error tryDeleteFolder(String folder) {		
		try {
			defaultPlugin.deleteFolder(folder);
		} catch(NullPointerException e) {
			return Error.NULL_POINTER;
		} catch(Exception e) {
			System.out.println(e);
			return Error.EXCEPTION;
		}
		
		return Error.NO_ERROR;
	}
	
	public static Error tryCreateFile(String path, byte[] content) {		
		try {
			defaultPlugin.createFile(path, content);
		} catch(NullPointerException e) {
			return Error.NULL_POINTER;
		} catch(FileAlreadyExistsException e) {
			return Error.FILE_ALREADY_EXISTS;
		} catch(Exception e) {
			System.out.println(e);
			return Error.EXCEPTION;
		}
		
		return Error.NO_ERROR; 
	}
	
	public static Error tryReadFile(String path, byte[] contentExpected) {
		try {
			if(Arrays.equals(defaultPlugin.readFile(path), contentExpected) == false)
				return Error.WRONG_CONTENT;
		} catch(NullPointerException e) {
			return Error.NULL_POINTER;
		} catch(NoSuchFileException e) {
			return Error.NO_SUCH_FILE;
		} catch(Exception e) {
			System.out.println(e);
			return Error.EXCEPTION;
		}
		
		return Error.NO_ERROR;
	}
	
	public static Error tryWriteFile(String path, byte[] content) {
		try {
			defaultPlugin.writeFile(path, content);
		} catch(Exception e) {
			System.out.println(e);
			return Error.EXCEPTION;
		}
		
		return Error.NO_ERROR;
	}
	
	public static Error tryDeleteFile(String path) {		
		try {
			defaultPlugin.deleteFile(path);
		} catch(NullPointerException e) {
			return Error.NULL_POINTER;
		} catch(NoSuchFileException e) {
			return Error.NO_SUCH_FILE;
		} catch(Exception e) {
			System.out.println(e);
			return Error.EXCEPTION;
		}
		
		return Error.NO_ERROR; 
	}

}
