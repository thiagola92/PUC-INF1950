package engine.file;

import java.security.SecureRandom;
import java.util.ArrayList;

import engine.file.action.List;

public class Random {
	
	public static String generateRandomName() {
		String validChars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomName = new byte[8];

        for(int i = 0; i < randomName.length; i++)
            randomName[i] = (byte)validChars.charAt(secureRandom.nextInt(validChars.length()));
        
        return new String(randomName);
	}
	
	public static String createRandomName(File folder) throws Exception {
		ArrayList<File> files = List.listFolder(folder);
		
		String name;
		boolean nameExist = false;
		
		do {
			name = generateRandomName();
			
			for(int i = 0; i < files.size(); i++)
				if(files.get(i).getName().equalsIgnoreCase(name))
					nameExist = true;
		} while(nameExist);
		
		return name;
	}

}
