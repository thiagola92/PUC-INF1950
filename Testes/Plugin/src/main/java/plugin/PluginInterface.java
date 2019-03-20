package plugin;

import java.util.ArrayList;

public interface PluginInterface {
	
	public void createFolder(String folderPath) throws Exception;
	public ArrayList<String[]> listFolder(String folderPath) throws Exception;
	public void deleteFolder(String folderPath) throws Exception;
	
	public void createFile(String filePath) throws Exception;
	public byte[] readFile(String filePath) throws Exception;
	public void writeFile(String filePath, byte[] fileBytes) throws Exception;
	public void deleteFile(String filePath) throws Exception;

}
