public class RendererStandartImpl implements Renderer {
    private PreProcessor processor;

    public RendererStandartImpl(PreProcessor preProcessor) {
        processor = preProcessor;
    }
    @Override
    public void print(String text) {
        System.out.println(processor.preProc(text));
    }
}
