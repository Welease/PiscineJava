public class Program {
    private static int count = 0;

    private static void wrongInputExit() {
        System.out.println("Usage: java Program --count=<number>");
        System.exit(0);
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            wrongInputExit();
        }
        if (args[0].startsWith("--count=")) {
            try {
                count = Integer.parseInt(args[0].substring(8));
            } catch (Exception e) {
                wrongInputExit();
            }
            Printer egg = new Printer("Egg", count);
            Printer hen = new Printer("Hen", count);

            Thread eggThread = new Thread(egg);
            Thread henThread = new Thread(hen);

            eggThread.start();
            henThread.start();
            eggThread.join();
            henThread.join();
            for (int i = 0; i < count; ++i)
                System.out.println("Human");
        } else {
            wrongInputExit();
        }
    }
}
