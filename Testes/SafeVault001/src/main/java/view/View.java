package view;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import view.drivelist.DriveList;
import view.frame.driveframe.DriveFrame;
import view.update.Update;

public class View {

	public static Update update = new Update();
	public static DriveList driverList = new DriveList();
	public static DriveFrame driveFrame = new DriveFrame();
	
	public static PublicKey publicKey;
	public static PrivateKey privateKey;

	public static void main(String[] args) {		
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
