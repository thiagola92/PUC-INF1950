package pluginDefault;

import plugin.test.PluginError;
import plugin.test.PluginTester;

public class Test {
	
	public static void run() throws Exception {
		PluginTester plugin = new PluginTester(null);

		/** Testing tryCreateFolder **/
		
		assert(plugin.createFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder") == PluginError.NO_ERROR);

		assert(plugin.createFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.createFolder("testFolder/anotherFolder") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder/anotherFolder") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder") == PluginError.NO_ERROR);
		
		assert(plugin.createFolder(null) == PluginError.NULL_POINTER);
		
		assert(plugin.createFolder("") == PluginError.FILE_ALREADY_EXISTS);
		
		assert(plugin.createFolder(".") == PluginError.FILE_ALREADY_EXISTS);
		
		assert(plugin.createFolder("..") == PluginError.FILE_ALREADY_EXISTS);

		assert(plugin.createFolder("testFolder/anotherFolder") == PluginError.NO_SUCH_FILE);
		
		assert(plugin.createFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.createFolder("testFolder") == PluginError.FILE_ALREADY_EXISTS);
		assert(plugin.deleteFolder("testFolder") == PluginError.NO_ERROR);

		/** Testing tryListFolder **/
		
		assert(plugin.listFolder("") == PluginError.NO_ERROR);
		
		assert(plugin.listFolder(".") == PluginError.NO_ERROR);
		
		assert(plugin.listFolder("..") == PluginError.NO_ERROR);
		
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
		
		assert(plugin.listFolder("fileNotCreated") == PluginError.NO_SUCH_FILE);
		
		assert(plugin.createFile("testFile") == PluginError.NO_ERROR);
		assert(plugin.listFolder("testFile") == PluginError.NOT_A_DIRECTORY);
		assert(plugin.deleteFile("testFile") == PluginError.NO_ERROR);
		
		/** Testing tryDeleteFolder **/
		
		assert(plugin.createFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.createFolder("testFolder/testFolderAgain") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder") == PluginError.DIRECTORY_NOT_EMPTY);
		assert(plugin.deleteFolder("testFolder/testFolderAgain") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder") == PluginError.NO_ERROR);
		
		assert(plugin.deleteFolder(null) == PluginError.NULL_POINTER);
		
		assert(plugin.deleteFolder("testFolder") == PluginError.NO_SUCH_FILE);
		
		/** Testing tryCreateFile **/

		assert(plugin.createFile("testFile") == PluginError.NO_ERROR);
		assert(plugin.deleteFile("testFile") == PluginError.NO_ERROR);

		assert(plugin.createFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.createFile("testFolder/testFile") == PluginError.NO_ERROR);
		assert(plugin.deleteFile("testFolder/testFile") == PluginError.NO_ERROR);
		assert(plugin.deleteFolder("testFolder") == PluginError.NO_ERROR);
		
		assert(plugin.createFile(null) == PluginError.NULL_POINTER);

		assert(plugin.createFile("") == PluginError.ARRAY_INDEX_OUT_OF_BOUNDS);
		
		assert(plugin.createFile(".") == PluginError.FILE_ALREADY_EXISTS);
		
		assert(plugin.createFile("..") == PluginError.FILE_ALREADY_EXISTS);

		assert(plugin.createFile("testFolder/testFile") == PluginError.NO_SUCH_FILE);

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

		assert(plugin.readFile("", text) == PluginError.IS_A_DIRECTORY);

		assert(plugin.readFile(".", text) == PluginError.IS_A_DIRECTORY);

		assert(plugin.readFile("..", text) == PluginError.IS_A_DIRECTORY);

		assert(plugin.readFile("testFile", text) == PluginError.NO_SUCH_FILE);

		assert(plugin.createFile("testFile") == PluginError.NO_ERROR);
		assert(plugin.readFile("testFile", text) == PluginError.WRONG_CONTENT);
		assert(plugin.readFile("testFile", "".getBytes()) == PluginError.NO_ERROR);
		assert(plugin.deleteFile("testFile") == PluginError.NO_ERROR);
		
		/** Testing tryWriteFile **/
		
		assert(plugin.writeFile(null, text) == PluginError.NULL_POINTER);

		assert(plugin.writeFile("", text) == PluginError.IS_A_DIRECTORY);

		assert(plugin.writeFile(".", text) == PluginError.IS_A_DIRECTORY);

		assert(plugin.writeFile("..", text) == PluginError.IS_A_DIRECTORY);
		
		assert(plugin.writeFile("testFile", text) == PluginError.NO_SUCH_FILE);
		
		/** Testing tryDeleteFile **/
		
		assert(plugin.deleteFile("testFile") == PluginError.NO_SUCH_FILE);
	}

}
