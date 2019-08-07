package engine.drives.drive.utilities.cryptography;

import engine.drives.drive.utilities.cryptography.container.Container;
import engine.drives.drive.utilities.cryptography.decrypt.Decrypt;
import engine.drives.drive.utilities.cryptography.encrypt.Encrypt;

public class Cryptography {
	
	private Encrypt encrypt;
	private Decrypt decrypt;
	private Container container;
	
	public Cryptography() {
		encrypt = new Encrypt(this);
		decrypt = new Decrypt(this);
		container = new Container();
	}
	
	public Encrypt encrypt() {
		return encrypt;
	}
	
	public Decrypt decrypt() {
		return decrypt;
	}
	
	public Container container() {
		return container;
	}
}
