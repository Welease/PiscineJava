public class RendererErrImpl implements Renderer {
    private PreProcessor processor;

    public RendererErrImpl(PreProcessor preProcessor) {
        processor = preProcessor;
    }
    @Override
    public void print(String text) {
        System.err.println(processor.preProc(text));
    }
}
