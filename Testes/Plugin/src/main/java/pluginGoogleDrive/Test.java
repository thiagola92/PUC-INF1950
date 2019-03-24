package pluginGoogleDrive;

import plugin.test.PluginError;
import plugin.test.PluginTester;

public class Test {
	
	public static void run() throws Exception {
		PluginTester plugin = new PluginTester("googledrive");

		/** Testing tryCreateFolder **/
		
		assert(plugin.createFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder") == PluginError.NO_ERROR);

		assert(plugin.createFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.createFolder("testFolder/anotherFolder") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder/anotherFolder") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder") == PluginError.NO_ERROR);
		
		assert(plugin.createFolder("") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("New folder") == PluginError.NO_ERROR);
		
		assert(plugin.createFolder(".") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder(".") == PluginError.NO_ERROR);
		
		assert(plugin.createFolder("..") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("..") == PluginError.NO_ERROR);
		
		assert(plugin.createFolder(null) == PluginError.NULL_POINTER);

		assert(plugin.createFolder("testFolder/anotherFolder") == PluginError.INDEX_OUT_OF_BOUNDS);
		
		assert(plugin.createFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.createFolder("testFolder") == PluginError.FILE_ALREADY_EXISTS);
		assert(plugin.deleteFolder("testFolder") == PluginError.NO_ERROR);

		/** Testing tryListFolder **/
		
		assert(plugin.listFolder("") == PluginError.NO_ERROR);
		
		assert(plugin.createFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.listFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder") == PluginError.NO_ERROR);
		
		assert(plugin.createFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.createFolder("testFolder/testFolderAgain") == PluginError.NO_ERROR);
		assert(plugin.listFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.listFolder("testFolder/testFolderAgain") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder/testFolderAgain") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder") == PluginError.NO_ERROR);
		
		assert(plugin.listFolder(null) == PluginError.NULL_POINTER);
		
		assert(plugin.listFolder(".") == PluginError.INDEX_OUT_OF_BOUNDS);
		
		assert(plugin.listFolder("..") == PluginError.INDEX_OUT_OF_BOUNDS);
		
		assert(plugin.listFolder("fileNotCreated") == PluginError.INDEX_OUT_OF_BOUNDS);
		
		assert(plugin.createFile("testFile") == PluginError.NO_ERROR);
		assert(plugin.listFolder("testFile") == PluginError.INDEX_OUT_OF_BOUNDS);
		assert(plugin.deleteFile("testFile") == PluginError.NO_ERROR);
		
		/** Testing tryDeleteFolder **/
		
		assert(plugin.createFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.createFolder("testFolder/testFolderAgain") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder") == PluginError.DIRECTORY_NOT_EMPTY);
		assert(plugin.deleteFolder("testFolder/testFolderAgain") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder") == PluginError.NO_ERROR);
		
		assert(plugin.deleteFolder(null) == PluginError.NULL_POINTER);
		
		assert(plugin.deleteFolder("folderNotCreated") == PluginError.INDEX_OUT_OF_BOUNDS);
		
		/** Testing tryCreateFile **/

		assert(plugin.createFile("testFile") == PluginError.NO_ERROR);
		assert(plugin.deleteFile("testFile") == PluginError.NO_ERROR);

		assert(plugin.createFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.createFile("testFolder/testFile") == PluginError.NO_ERROR);
		assert(plugin.deleteFile("testFolder/testFile") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder") == PluginError.NO_ERROR);

		assert(plugin.createFile("") == PluginError.NO_ERROR);
		assert(plugin.deleteFile("Untitled") == PluginError.NO_ERROR);
		
		assert(plugin.createFile(".") == PluginError.NO_ERROR);
		assert(plugin.deleteFile(".") == PluginError.NO_ERROR);
		
		assert(plugin.createFile("..") == PluginError.NO_ERROR);
		assert(plugin.deleteFile("..") == PluginError.NO_ERROR);
		
		assert(plugin.createFile(null) == PluginError.NULL_POINTER);

		assert(plugin.createFile("testFolder/testFile") == PluginError.INDEX_OUT_OF_BOUNDS);

		assert(plugin.createFile("testFile") == PluginError.NO_ERROR);
		assert(plugin.createFile("testFile") == PluginError.FILE_ALREADY_EXISTS);
		assert(plugin.deleteFile("testFile") == PluginError.NO_ERROR);
		
		/** Testing tryReadFile **/
		
		byte text[] = "testing".getBytes();

		assert(plugin.createFile("testFile") == PluginError.NO_ERROR);
		assert(plugin.writeFile("testFile", text) == PluginError.NO_ERROR);
		assert(plugin.readFile("testFile", text) == PluginError.NO_ERROR);
		assert(plugin.deleteFile("testFile") == PluginError.NO_ERROR);
		
		assert(plugin.readFile(null, text) == PluginError.NULL_POINTER);

		assert(plugin.readFile("", text) == PluginError.INDEX_OUT_OF_BOUNDS);

		assert(plugin.readFile(".", text) == PluginError.INDEX_OUT_OF_BOUNDS);

		assert(plugin.readFile("..", text) == PluginError.INDEX_OUT_OF_BOUNDS);

		assert(plugin.readFile("testFile", text) == PluginError.INDEX_OUT_OF_BOUNDS);

		assert(plugin.createFile("testFile") == PluginError.NO_ERROR);
		assert(plugin.readFile("testFile", text) == PluginError.HTTP_RESPONSE);
		assert(plugin.readFile("testFile", "".getBytes()) == PluginError.HTTP_RESPONSE);
		assert(plugin.deleteFile("testFile") == PluginError.NO_ERROR);
		
		/** Testing tryWriteFile **/
		
		assert(plugin.writeFile(null, text) == PluginError.NULL_POINTER);

		assert(plugin.writeFile("", text) == PluginError.INDEX_OUT_OF_BOUNDS);

		assert(plugin.writeFile(".", text) == PluginError.INDEX_OUT_OF_BOUNDS);

		assert(plugin.writeFile("..", text) == PluginError.INDEX_OUT_OF_BOUNDS);
		
		assert(plugin.writeFile("testFile", text) == PluginError.INDEX_OUT_OF_BOUNDS);
		
		/** Testing tryDeleteFile **/
		
		assert(plugin.deleteFile("testFile") == PluginError.INDEX_OUT_OF_BOUNDS);
	}

}
