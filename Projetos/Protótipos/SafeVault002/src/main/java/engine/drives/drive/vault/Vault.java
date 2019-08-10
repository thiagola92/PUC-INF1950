package engine.drives.drive.vault;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

import engine.drives.drive.Drive;
import engine.drives.drive.file.File;
import engine.drives.drive.vault.index.Index;

public class Vault {

	private String name;
	private File folder;
	
	private String secret_phrase;
	private PublicKey publickey;
	private PrivateKey privatekey;

	private Drive drive;
	private Index index;
	
	public Vault(Drive drive) {
		this.name = ".safevault";
		this.drive = drive;
		this.index = null;
		
		this.secret_phrase = null;
		this.publickey = null;
		this.privatekey = null;
	}
	
	public String name() {
		return name;
	}
	
	public void set_name(String name) {
		this.name = name;
		
		drive.notify("update vault");
	}
	
	public File folder() {
		return folder;
	}
	
	public String secret_phrase() {
		return secret_phrase;
	}
	
	public void set_secret_phrase(String secret_phrase) {
		this.secret_phrase = secret_phrase;
		
		drive.notify("update vault");
	}
	
	public PublicKey publickey() {
		return publickey;
	}
	
	public void set_publickey(PublicKey publickey) {
		this.publickey = publickey;
		
		drive.notify("update vault");
	}
	
	public PrivateKey privatekey() {
		return privatekey;
	}
	
	public void set_privatekey(PrivateKey privatekey) {
		this.privatekey = privatekey;
		
		drive.notify("update vault");
	}
	
	public Drive drive() {
		return drive;
	}
	
	public Index index() {
		return index;
	}
	
	public void create() throws Exception {
		create_folder();
		create_index();
		
		drive.notify("create vault");
	}
	
	public void destroy() throws Exception {
		String path = drive.utilities().strings().concatpath(drive.path(), name);
		
		delete_folder(path);
		
		folder = null;
		index = null;
		
		drive.notify("destroy vault");
	}
	
	private void create_folder() throws Exception {
		String path = drive.utilities().strings().concatpath(drive.path(), name);
		drive.plugin().createFolder(path);
		folder = new File(drive, path, "folder");
	}
	
	private void create_index() throws Exception {
		byte[] container = drive.utilities().cryptography().encrypt().content("".getBytes(), privatekey, publickey);
		
		String name = drive.utilities().indexes().indexname(secret_phrase);
		String path = drive.utilities().strings().concatpath(folder.path(), name);
		
		drive.plugin().createFile(path);
		drive.plugin().writeFile(path, container);
		
		File file = new File(drive, path, "folder");
		file.set_name(this.name);
		index = new Index(this, file);
	}
	
	private void delete_folder(String folder) throws Exception {
		ArrayList<String[]> files = drive.plugin().listFolder(folder);
		
		for(String[] file : files) {
			String path = drive.utilities().strings().concatpath(folder, file[0]);
			
			if(file[1].equals("folder"))
				delete_folder(path);
			else
				drive.plugin().deleteFile(path);
		}
		
		drive.plugin().deleteFolder(folder);
	}

}
