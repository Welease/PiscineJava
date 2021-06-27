public class Program {
    public static void main(String[] args) {
        int num = 479598;
        int firstDis = num / 100000;
        int secondDis = num / 10000 - firstDis * 10;
        int thirdDis = num / 1000 - firstDis * 100 - secondDis * 10;
        int forthDis = num / 100 - firstDis * 1000 - secondDis * 100 - thirdDis * 10;
        int fifthDis = num / 10 - firstDis * 10000 - secondDis * 1000 - thirdDis * 100 - forthDis * 10;
        int sixthDis = num % 10;

        System.out.print(firstDis + secondDis + thirdDis + forthDis + fifthDis + sixthDis);
    }
}
