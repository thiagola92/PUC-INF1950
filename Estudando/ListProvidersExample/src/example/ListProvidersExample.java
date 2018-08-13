package example;

import java.security.Provider;
import java.security.Security;

public class ListProvidersExample {

	public static void main(String[] args) {
		
		Provider[] providers = Security.getProviders();
		for(int i = 0; i != providers.length; i++) {
			System.out.println("Name: " + providers[i].getName() + "\t\t" + 
								"Version: " + providers[i].getVersionStr() + "\n" + 
								"Info: " + providers[i].getInfo() + "\n");
		}
		
	}

}