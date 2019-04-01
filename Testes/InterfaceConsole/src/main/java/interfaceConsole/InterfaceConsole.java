package interfaceConsole;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import main.Main;

public class InterfaceConsole {
	
	private Path location;
	
	public InterfaceConsole() {
		location = Paths.get("");
	}

	public void run() throws Exception {
		Scanner scanner = new Scanner(System.in);
		String command = scanner.nextLine();
		
		while(command.equals("exit") == false) {
			if(command.startsWith("ls"))
				list();
			else if(command.startsWith("cd"))
				changeDirectory(command);
				
			
			command = scanner.nextLine();
		}
		
		scanner.close();
	}

	public void list() throws Exception {
		ArrayList<String[]> list = Main.plugin.listFolder(location.toString());
		
		System.out.println(location.toString());
		for(int i = 0; i < list.size(); i++) {
			String type = list.get(i)[1];
			String fileName = Paths.get(list.get(i)[0]).getFileName().toString();
			
			System.out.format("(%s) %s\n", type, fileName);
		}
	}

	public void changeDirectory(String command) throws Exception {
		String[] args = command.split(" ");
		
		location = Paths.get(location.toString(), args[1]).normalize();
	}

}
