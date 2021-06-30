public class Program {
    private static void wrongInputExit() {
        System.out.println("Usage: java Program --arraySize=<> --threadsCount=<>");
        System.exit(0);
    }
    private static int arraySize;
    private static int threadsCount;

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 2) {
            if (args[0].startsWith("--arraySize=") && args[1].startsWith("--threadsCount")) {
                try {
                    arraySize = Integer.parseInt(args[0].substring(12));
                    threadsCount = Integer.parseInt(args[1].substring(15));
                } catch (Exception ex) {
                    wrongInputExit();
                }
                Thread[] threads = new Thread[threadsCount];
                RandomArray array = new RandomArray(arraySize);
                int c, endC, i;
                System.out.println("Sum: " + array.getSumOfElements());
                c = arraySize / threadsCount;
                endC = arraySize - c * (threadsCount - 1);

                for (i = 0; i < threadsCount - 1; ++i) {
                    threads[i] = new Thread(new SumCalculator(array.getArray(), i * c, i * c + c - 1, i));
                }

                threads[i] = new Thread(new SumCalculator(array.getArray(), i * c, (i) * c + endC - 1, i));

                for (i = 0; i < threadsCount; ++i) {
                    threads[i].start();
                    threads[i].join();
                }
                System.out.println("Sum by threads: " + SumCalculator.sum);
            } else {
                wrongInputExit();
            }
        } else {
            wrongInputExit();
        }
    }
}
