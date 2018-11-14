
public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("Example");
		DefaultPlugin defaultPlugin = new DefaultPlugin();
		byte[] x = "coe".getBytes();
		defaultPlugin.writeFile("a/abs", x);

	}

}
