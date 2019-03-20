package pluginDefault;

import plugin.test.PluginError;
import plugin.test.PluginTester;

public class Test {
	
	public static void run() throws Exception {
		PluginTester plugin = new PluginTester(null);

		/** Testing tryCreateFolder **/
		
		assert(plugin.tryCreateFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.tryDeleteFolder("testFolder") == PluginError.NO_ERROR);

		assert(plugin.tryCreateFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.tryCreateFolder("testFolder/anotherFolder") == PluginError.NO_ERROR);
		assert(plugin.tryDeleteFolder("testFolder/anotherFolder") == PluginError.NO_ERROR);
		assert(plugin.tryDeleteFolder("testFolder") == PluginError.NO_ERROR);
		
		assert(plugin.tryCreateFolder(null) == PluginError.NULL_POINTER);
		
		assert(plugin.tryCreateFolder("") == PluginError.FILE_ALREADY_EXISTS);
		
		assert(plugin.tryCreateFolder(".") == PluginError.FILE_ALREADY_EXISTS);
		
		assert(plugin.tryCreateFolder("..") == PluginError.FILE_ALREADY_EXISTS);

		assert(plugin.tryCreateFolder("testFolder/anotherFolder") == PluginError.NO_SUCH_FILE);
		
		assert(plugin.tryCreateFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.tryCreateFolder("testFolder") == PluginError.FILE_ALREADY_EXISTS);
		assert(plugin.tryDeleteFolder("testFolder") == PluginError.NO_ERROR);

		/** Testing tryListFolder **/
		
		assert(plugin.tryListFolder("") == PluginError.NO_ERROR);
		
		assert(plugin.tryListFolder(".") == PluginError.NO_ERROR);
		
		assert(plugin.tryListFolder("..") == PluginError.NO_ERROR);
		
		assert(plugin.tryCreateFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.tryListFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.tryDeleteFolder("testFolder") == PluginError.NO_ERROR);
		
		assert(plugin.tryCreateFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.tryCreateFolder("testFolder/testFolderAgain") == PluginError.NO_ERROR);
		assert(plugin.tryListFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.tryListFolder("testFolder/testFolderAgain") == PluginError.NO_ERROR);
		assert(plugin.tryDeleteFolder("testFolder/testFolderAgain") == PluginError.NO_ERROR);
		assert(plugin.tryDeleteFolder("testFolder") == PluginError.NO_ERROR);
		
		assert(plugin.tryListFolder(null) == PluginError.NULL_POINTER);
		
		assert(plugin.tryListFolder("fileNotCreated") == PluginError.NO_SUCH_FILE);
		
		assert(plugin.tryCreateFile("testFile") == PluginError.NO_ERROR);
		assert(plugin.tryListFolder("testFile") == PluginError.NOT_A_DIRECTORY);
		assert(plugin.tryDeleteFile("testFile") == PluginError.NO_ERROR);
		
		/** Testing tryDeleteFolder **/
		
		assert(plugin.tryCreateFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.tryCreateFolder("testFolder/testFolderAgain") == PluginError.NO_ERROR);
		assert(plugin.tryDeleteFolder("testFolder") == PluginError.DIRECTORY_NOT_EMPTY);
		assert(plugin.tryDeleteFolder("testFolder/testFolderAgain") == PluginError.NO_ERROR);
		assert(plugin.tryDeleteFolder("testFolder") == PluginError.NO_ERROR);
		
		assert(plugin.tryDeleteFolder(null) == PluginError.NULL_POINTER);
		
		assert(plugin.tryDeleteFolder("testFolder") == PluginError.NO_SUCH_FILE);
		
		/** Testing tryCreateFile **/

		assert(plugin.tryCreateFile("testFile") == PluginError.NO_ERROR);
		assert(plugin.tryDeleteFile("testFile") == PluginError.NO_ERROR);

		assert(plugin.tryCreateFolder("testFolder") == PluginError.NO_ERROR);
		assert(plugin.tryCreateFile("testFolder/testFile") == PluginError.NO_ERROR);
		assert(plugin.tryDeleteFile("testFolder/testFile") == PluginError.NO_ERROR);
		assert(plugin.tryDeleteFolder("testFolder") == PluginError.NO_ERROR);
		
		assert(plugin.tryCreateFile(null) == PluginError.NULL_POINTER);

		assert(plugin.tryCreateFile("") == PluginError.ARRAY_INDEX_OUT_OF_BOUNDS);
		
		assert(plugin.tryCreateFile(".") == PluginError.FILE_ALREADY_EXISTS);
		
		assert(plugin.tryCreateFile("..") == PluginError.FILE_ALREADY_EXISTS);

		assert(plugin.tryCreateFile("testFolder/testFile") == PluginError.NO_SUCH_FILE);

		assert(plugin.tryCreateFile("testFile") == PluginError.NO_ERROR);
		assert(plugin.tryCreateFile("testFile") == PluginError.FILE_ALREADY_EXISTS);
		assert(plugin.tryDeleteFile("testFile") == PluginError.NO_ERROR);
		
		/** Testing tryReadFile **/
		
		byte text[] = "testing".getBytes();

		assert(plugin.tryCreateFile("testFile") == PluginError.NO_ERROR);
		assert(plugin.tryWriteFile("testFile", text) == PluginError.NO_ERROR);
		assert(plugin.tryReadFile("testFile", text) == PluginError.NO_ERROR);
		assert(plugin.tryDeleteFile("testFile") == PluginError.NO_ERROR);
		
		assert(plugin.tryReadFile(null, text) == PluginError.NULL_POINTER);

		assert(plugin.tryReadFile("", text) == PluginError.IS_A_DIRECTORY);

		assert(plugin.tryReadFile(".", text) == PluginError.IS_A_DIRECTORY);

		assert(plugin.tryReadFile("..", text) == PluginError.IS_A_DIRECTORY);

		assert(plugin.tryReadFile("testFile", text) == PluginError.NO_SUCH_FILE);

		assert(plugin.tryCreateFile("testFile") == PluginError.NO_ERROR);
		assert(plugin.tryReadFile("testFile", text) == PluginError.WRONG_CONTENT);
		assert(plugin.tryReadFile("testFile", "".getBytes()) == PluginError.NO_ERROR);
		assert(plugin.tryDeleteFile("testFile") == PluginError.NO_ERROR);
		
		/** Testing tryWriteFile **/
		
		assert(plugin.tryWriteFile(null, text) == PluginError.NULL_POINTER);

		assert(plugin.tryWriteFile("", text) == PluginError.IS_A_DIRECTORY);

		assert(plugin.tryWriteFile(".", text) == PluginError.IS_A_DIRECTORY);

		assert(plugin.tryWriteFile("..", text) == PluginError.IS_A_DIRECTORY);
		
		assert(plugin.tryWriteFile("testFile", text) == PluginError.NO_SUCH_FILE);
		
		/** Testing tryDeleteFile **/
		
		assert(plugin.tryDeleteFile("testFile") == PluginError.NO_SUCH_FILE);
	}

}
