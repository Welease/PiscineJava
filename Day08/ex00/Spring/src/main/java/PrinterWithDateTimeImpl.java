import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
    private Renderer renderer;
    private LocalDateTime dateTime;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String text) {
        System.out.println(dateTime + " " + text);
    }

    public void setPrefix(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
