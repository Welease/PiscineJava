public class Printer {
    private final int count;
    private boolean flag;

    public Printer(int c) {
        count = c;
        flag = false;
    }

    public synchronized void eggRun() {
        for (int i = 0; i < count; ++i) {
                while (flag) {
                    try {
                        wait();
                    } catch (InterruptedException ex) { ex.printStackTrace(); }
                }
                System.out.println("Egg");
                flag = true;
                notify();
        }
    }

    public synchronized void henRun() {
        for (int i = 0; i < count; ++i) {
                while (!flag) {
                    try {
                        wait();
                    } catch (InterruptedException ex) { ex.printStackTrace(); }
                }
                System.out.println("Hen");
                flag = false;
                notify();
        }
    }

}
