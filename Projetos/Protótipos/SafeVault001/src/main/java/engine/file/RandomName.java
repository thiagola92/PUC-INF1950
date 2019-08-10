package engine.file;

import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;

import engine.file.action.List;

public class RandomName {
	
	public static String generateRandomName() {
		String validChars = "1234567890abcdefghijklmnopqrstuvwxyz";
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomName = new byte[8];

        for(int i = 0; i < randomName.length; i++)
            randomName[i] = (byte)validChars.charAt(secureRandom.nextInt(validChars.length()));
        
        return new String(randomName);
	}
	
	public static String generatePseudoRandomName(String secretPhrase) throws Exception {
		String validChars = "1234567890abcdefghijklmnopqrstuvwxyz";
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] randomName = new byte[8];

        secureRandom.setSeed(secretPhrase.getBytes());

        for(int i = 0; i < randomName.length; i++)
            randomName[i] = (byte)validChars.charAt(secureRandom.nextInt(validChars.length()));
        
        return new String(randomName);
	}
	
	public static boolean isNameUsed(ArrayList<File> files, String name) {
		for(int i = 0; i < files.size(); i++) {
			String fileName = Paths.get(files.get(i).getPath()).getFileName().toString();
			
			if(name.equalsIgnoreCase(fileName))
				return true;
		}
		
		return false;
	}
	
	public static String createRandomName(File folder) throws Exception {
		ArrayList<File> files = List.listSafeFolder(folder);
		
		String name;
		boolean nameExist = false;
		
		do {
			name = generateRandomName();
			nameExist = isNameUsed(files, name);
		} while(nameExist);
		
		return name;
	}

}
