public class PrinterWithPrefixImpl implements Printer {
    private Renderer renderer;
    private String pref = "";

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String text) {
        renderer.print(pref + text);
    }

    public void setPref(String pref) {
        this.pref = pref;
    }
}
