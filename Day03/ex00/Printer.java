public class Printer implements Runnable {
    private String message;
    private int    count;

    public Printer(String mes, int c) {
        message = mes;
        count = c;
    }

    public void run() {
        for (int i = 0; i < count; ++i)
            System.out.println(message);
    }
}
