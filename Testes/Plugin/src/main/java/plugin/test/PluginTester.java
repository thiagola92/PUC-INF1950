package plugin.test;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.api.client.http.HttpResponseException;

import plugin.PluginManager;

public class PluginTester {
	
	PluginManager plugin;
	
	public PluginTester(String pluginName) throws Exception {
		plugin = new PluginManager(pluginName);
	}
	
	public PluginError tryCreateFolder(String folder) {		
		try {
			plugin.createFolder(folder);
		} catch(NullPointerException e) {
			return PluginError.NULL_POINTER;
		} catch(NoSuchFileException e) {
			return PluginError.NO_SUCH_FILE;
		} catch(FileAlreadyExistsException e) {
			return PluginError.FILE_ALREADY_EXISTS;
		} catch(IndexOutOfBoundsException e) {
			return PluginError.INDEX_OUT_OF_BOUNDS;
		} catch(Exception e) {
			System.out.println(e);
			return PluginError.EXCEPTION;
		}
		
		return PluginError.NO_ERROR;
	}
	
	public PluginError tryListFolder(String folder) {
		try {
			ArrayList<String[]> files = plugin.listFolder(folder);
			
			System.out.println("[");
			for(int i=0; i < files.size(); i++)
				System.out.format("    %s, %s\n", files.get(i)[0], files.get(i)[1]);
			System.out.println("]");
			
		} catch(NullPointerException e) {
			return PluginError.NULL_POINTER;
		} catch(NoSuchFileException e) {
			return PluginError.NO_SUCH_FILE;
		} catch(NotDirectoryException e) {
			return PluginError.NOT_A_DIRECTORY;
		} catch(IndexOutOfBoundsException e) {
			return PluginError.INDEX_OUT_OF_BOUNDS;
		}  catch(Exception e) {
			System.out.println(e);
			return PluginError.EXCEPTION;
		}
		
		return PluginError.NO_ERROR;
	}
	
	public PluginError tryDeleteFolder(String folder) {		
		try {
			plugin.deleteFolder(folder);
		} catch(NullPointerException e) {
			return PluginError.NULL_POINTER;
		} catch(DirectoryNotEmptyException e) {
			return PluginError.DIRECTORY_NOT_EMPTY;
		} catch(NoSuchFileException e) {
			return PluginError.NO_SUCH_FILE;
		} catch(IndexOutOfBoundsException e) {
			return PluginError.INDEX_OUT_OF_BOUNDS;
		} catch(Exception e) {
			System.out.println(e);
			return PluginError.EXCEPTION;
		}
		
		return PluginError.NO_ERROR;
	}
	
	public PluginError tryCreateFile(String path) {		
		try {
			plugin.createFile(path);
		} catch(NullPointerException e) {
			return PluginError.NULL_POINTER;
		} catch(FileAlreadyExistsException e) {
			return PluginError.FILE_ALREADY_EXISTS;
		} catch(ArrayIndexOutOfBoundsException e) {
			return PluginError.ARRAY_INDEX_OUT_OF_BOUNDS;
		} catch(NoSuchFileException e) {
			return PluginError.NO_SUCH_FILE;
		} catch(IndexOutOfBoundsException e) {
			return PluginError.INDEX_OUT_OF_BOUNDS;
		} catch(Exception e) {
			System.out.println(e);
			return PluginError.EXCEPTION;
		}
		
		return PluginError.NO_ERROR; 
	}
	
	public PluginError tryReadFile(String path, byte[] contentExpected) {
		try {
			if(Arrays.equals(plugin.readFile(path), contentExpected) == false)
				return PluginError.WRONG_CONTENT;
		} catch(NullPointerException e) {
			return PluginError.NULL_POINTER;
		} catch(NoSuchFileException e) {
			return PluginError.NO_SUCH_FILE;
		} catch(HttpResponseException e) {
			return PluginError.HTTP_RESPONSE;
		} catch(IOException e) {
			return PluginError.IS_A_DIRECTORY;
		} catch(IndexOutOfBoundsException e) {
			return PluginError.INDEX_OUT_OF_BOUNDS;
		}  catch(Exception e) {
			System.out.println(e);
			return PluginError.EXCEPTION;
		}
		
		return PluginError.NO_ERROR;
	}
	
	public PluginError tryWriteFile(String path, byte[] content) {
		try {
			plugin.writeFile(path, content);
		} catch(NullPointerException e) {
			return PluginError.NULL_POINTER;
		} catch(NoSuchFileException e) {
			return PluginError.NO_SUCH_FILE;
		} catch(IOException e) {
			return PluginError.IS_A_DIRECTORY;
		} catch(IndexOutOfBoundsException e) {
			return PluginError.INDEX_OUT_OF_BOUNDS;
		} catch(Exception e) {
			System.out.println(e);
			return PluginError.EXCEPTION;
		}
		
		return PluginError.NO_ERROR;
	}
	
	public PluginError tryDeleteFile(String path) {		
		try {
			plugin.deleteFile(path);
		} catch(NullPointerException e) {
			return PluginError.NULL_POINTER;
		} catch(NoSuchFileException e) {
			return PluginError.NO_SUCH_FILE;
		} catch(IndexOutOfBoundsException e) {
			return PluginError.INDEX_OUT_OF_BOUNDS;
		} catch(Exception e) {
			System.out.println(e);
			return PluginError.EXCEPTION;
		}
		
		return PluginError.NO_ERROR; 
	}

}
