package example;

import java.util.ArrayList;

public class DigestCalculator {

	public static void main(String[] args) throws Exception {
		
		if(args.length < 3) {
			System.err.println("Usage: DigestCalculator Tipo_Digest Caminho_ArqListaDigest Caminho_Arq1... Caminho_ArqN");
			System.exit(1);
		}
		
		Checker checker = new Checker();
		checker.setDigestType(args[0]);
		checker.setFilePath(args[1]);
		
		ArrayList<String> fileList = new ArrayList<String>();
		for(int i = 2; i < args.length; i++) {
			System.out.println("File " + (i-1) + " name: " + args[i]);
			fileList.add(args[i]);
		}
		checker.setFilesList(fileList);
		System.out.println("//////////////////////////////////");
		
		checker.check();
	}

}
