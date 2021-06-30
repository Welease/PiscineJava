public class Program {
    private static int count = 0;

    private static void wrongInputExit() {
        System.out.println("Usage: java Program --count=<number>");
        System.exit(0);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            wrongInputExit();
        }
        if (args[0].startsWith("--count=")) {
            try {
                count = Integer.parseInt(args[0].substring(8));
            } catch (Exception e) {
                wrongInputExit();
            }
            Printer pc = new Printer(count);
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    pc.eggRun();
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    pc.henRun();
                }
            });

            t1.start();
            t2.start();
        } else {
            wrongInputExit();
        }
    }
}
