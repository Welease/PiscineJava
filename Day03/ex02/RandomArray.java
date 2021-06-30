public class RandomArray {
    private int count;
    private int[] array;
    public RandomArray(int c) {
        count = c;
        array = new int[c];
        for (int i = 0; i < count; ++i) {
            array[i] = getRandomNumber(0, 1000);
        }
    }

    public int getSumOfElements() {
        int result = 0;
        for (int i = 0; i < count; ++i)
            result += array[i];
        return result;
    }

    public int[] getArray() {
        return array;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
