import java.io.*;
import java.net.URL;
import java.util.LinkedList;

public class Program {
    private static void wrongInputExit() {
        System.out.println("Usage: java Program --threadsCount=<number>");
        System.exit(0);
    }

    public static LinkedList<Pair<Integer, Pair<URL, Boolean>>> urls;

    public static void main(String[] args) throws IOException {
        if (args.length != 1 || !args[0].startsWith("--threadsCount")) {
            wrongInputExit();
        }
        urls = new LinkedList<>();
        int countOfThreads = Integer.parseInt(args[0].substring(15)), num;

        FileInputStream file = new FileInputStream("files_urls.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        String line;
        String[] buf;
        do {
            line = reader.readLine();
            if (line == null)
                break;
            buf = line.split(" ");
            num = Integer.parseInt(buf[0]);
            line = buf[1].trim();
            urls.add(new Pair<>(num, new Pair<>(new URL(line), false)));
        } while (true);

        Thread[] threads = new Thread[countOfThreads];

        for (int i = 0; i < countOfThreads; ++i) {
            threads[i] = new Thread(new Downloader(urls, i + 1));
        }

        for (int i = 0; i < countOfThreads; ++i) {
            threads[i].start();
        }
    }
}
