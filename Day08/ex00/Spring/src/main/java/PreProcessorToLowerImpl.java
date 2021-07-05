public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String preProc(String text) {
        return text.toLowerCase();
    }
}
