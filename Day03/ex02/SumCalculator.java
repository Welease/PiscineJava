public class SumCalculator implements Runnable{
    public static int sum;
    private int num;
    private int[] pieceOfArray;
    private int begin;
    private int end;

    public SumCalculator(int[] arr, int begin, int end, int n) {
        pieceOfArray = new int[end - begin + 1];
        num = n;
        this.begin = begin;
        this.end = end;
        for (int i = begin, j = 0; i <= end; ++i, ++j)
            pieceOfArray[j] = arr[i];
    }

    public void run() {
        int res = 0;
        for (int i = 0; i < pieceOfArray.length; ++i) {
            res += pieceOfArray[i];
        }
        sum += res;
        System.out.println("Thread " + num + ": from " + begin + " to " + end + " sum is " + res);
    }

}
