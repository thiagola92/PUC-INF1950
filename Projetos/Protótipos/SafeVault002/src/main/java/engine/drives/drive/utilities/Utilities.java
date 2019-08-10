package engine.drives.drive.utilities;

import engine.drives.drive.utilities.cryptography.Cryptography;
import engine.drives.drive.utilities.indexes.Indexes;
import engine.drives.drive.utilities.strings.Strings;
import engine.drives.drive.utilities.vaults.Vaults;

public class Utilities {
	
	private Strings strings;
	
	private Vaults vaults;
	private Indexes indexes;
	private Cryptography cryptography;
	
	public Utilities() {
		strings = new Strings();
		
		vaults = new Vaults();
		indexes = new Indexes();
		cryptography = new Cryptography();
	}
	
	public Strings strings() {
		return strings;
	}
	
	public Vaults vaults() {
		return vaults;
	}

	public Indexes indexes() {
		return indexes;
	}
	
	public Cryptography cryptography() {
		return cryptography;
	}
}
