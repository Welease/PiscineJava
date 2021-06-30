package edu.school21.printer.app;
import edu.school21.printer.logic.*;
import java.io.FileInputStream;
class Program {
	private static void errorExit() {
		System.out.println("Usage: Program <white char> <black char>");
		System.exit(0);
	}

	public static void main(String[] args) {
		if (args.length != 2 || args[0].length() != 1 || args[1].length() != 1) {
			errorExit();
		}
		try {
			BMPreader reader = new BMPreader(args[0].charAt(0), args[1].charAt(0), new FileInputStream("src/resources/image.bmp"));
			reader.printBMP();
		} catch (Exception ex) {
			System.out.println("No such file");
		}
	}
}
