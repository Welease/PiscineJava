import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Iterator;
import java.util.LinkedList;

public class Downloader implements Runnable {
    private LinkedList<Pair<Integer, Pair<URL, Boolean>>> urls;
    private int numOfThread;

    public Downloader(LinkedList<Pair<Integer, Pair<URL, Boolean>>> list, int numOfThread) {
        this.numOfThread = numOfThread;
        urls = list;
    }

    public void run() {
        boolean isAllDownloaded = false, isAllChecked = false;
        ReadableByteChannel rbc;
        FileOutputStream fos;
        while (!isAllDownloaded) {
            isAllChecked = false;
            for (Pair<Integer, Pair<URL, Boolean>> pair : urls) {
                if (!pair.getSecond().getSecond()) {
                    pair.getSecond().setSecond(true);
                    isAllChecked = true;
                    try {
                        rbc = Channels.newChannel(pair.getSecond().getFirst().openStream());
                        System.out.println("Thread-" + numOfThread + " start download file number " + pair.getFirst());
                        fos = new FileOutputStream(pair.getSecond().getFirst().getFile().replace('/', '_'));
                        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                        System.out.println("Thread-" + numOfThread + " finish download file number " + pair.getFirst());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (!isAllChecked)
                isAllDownloaded = true;
        }
    }
}
