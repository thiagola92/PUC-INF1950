package engine.file.vault.index.indexfile;

import java.util.ArrayList;

import engine.file.File;
import engine.file.vault.Vault;
import engine.file.vault.cryptography.Decrypt;
import engine.file.vault.cryptography.Encrypt;
import engine.file.vault.index.Index;

public class IndexFile extends File {

	public IndexFile(File file) {
		super(file.getDrive(), file.getPath(), file.getType());
		super.setName(file.getName());
	}
	
	public ArrayList<File> readIndex() throws Exception {
		byte[] container = this.getDrive().getPlugin().readFile(this.getPath());
		byte[] decryptedContent = Decrypt.getDecryptedFile(container, this.getDrive().getPrivateKey(), this.getDrive().getPublicKey());

		ArrayList<File> files = new ArrayList<>();
		String content = new String(decryptedContent);
		
		if(content.isEmpty())
			return files;
		
		String[] contentLines = content.split("\n");
		String prePath = this.getPath().replaceFirst("." + this.getName() + "$", "");
		
		for(int i=0; i < contentLines.length; i++) {
			String[] line = contentLines[i].split(Index.regexSeparator);
			
			File file = new File(this.getDrive(), prePath + line[0], line[2]);
			file.setName(line[1]);
			files.add(file);
		}
		
		return files;
	}
	
	public void writeIndex(ArrayList<File> files) throws Exception {
		String content = "";
		
		for(int i = 0; i < files.size(); i++) {
			String path = Vault.pathInsideVault(files.get(i));
			String name = files.get(i).getName();
			String type = files.get(i).getType();
			
			content += path + Index.separator + name + Index.separator + type + "\n";
		}

		byte[] container = Encrypt.getEncryptedFile(content.getBytes(), this.getDrive().getPrivateKey(), this.getDrive().getPublicKey());
		this.getDrive().getPlugin().writeFile(this.getPath(), container);
	}
	
	public void addFile(File newFile) throws Exception {
		ArrayList<File> files = this.readIndex();
		files.add(newFile);
		writeIndex(files);	
	}
	
	public void removeFile(File file) throws Exception {
		ArrayList<File> files = readIndex();
		
		for(File f : files) {			
			if(file.isEqualTo(f)) {
				files.remove(f);
				break;
			}
		}
		
		writeIndex(files);
	}

}
