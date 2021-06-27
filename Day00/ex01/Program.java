import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
		try {
			n = scanner.nextInt();
		} catch (Exception ex) {
			ilArgExit();
		}
        if (n < 2) ilArgExit();
        int count = 1, i = 2;
        while (i * i <= n)  {
            if (n % i++ == 0)  {
                printMessage(count, false);
                System.exit(0);
            }
            count++;
        }
        printMessage(count, true);
    }

    private static void ilArgExit() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

    private static void printMessage(int counter, boolean result) {
        System.out.println((result ? "true" : "false") + " " + counter);
    }
}
