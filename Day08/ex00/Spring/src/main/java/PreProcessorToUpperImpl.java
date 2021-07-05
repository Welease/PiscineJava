public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String preProc(String text) {
        return text.toUpperCase();
    }
}
