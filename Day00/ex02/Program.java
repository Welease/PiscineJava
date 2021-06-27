import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long inputNum;
        long counter = 0;
        do {
            inputNum = scanner.nextLong();
            if (isCoffeeRequest(inputNum))
                counter++;
        } while (inputNum != 42);
        System.out.print("Count of coffee-request - ");
        System.out.println(counter);
    }

    private static long sumOfDigits(long num) {
        int res = 0;
        while (num / 10 > 0) {
            res += num % 10;
            num /= 10;
        }
        res += num % 10;
        return res;
    }

    private static boolean isCoffeeRequest(long num) {
        long i = 2;
        long sumOfDigits = sumOfDigits(num);
        long until = ft_sqrt(sumOfDigits);
        if (num == 0)
            return false;
        for (; i <= until; ++i)
            if (sumOfDigits % i == 0)
                return false;
        return true;
    }

    private static int ft_sqrt(long num) {
        double sqrtRes = 1.0;
        for (long i = 1; i <= 10; i++)
            sqrtRes -= (sqrtRes*sqrtRes - num) / (2*sqrtRes);
        if (sqrtRes % 1 > 0.5)
            sqrtRes += 1;
        return (int) sqrtRes;
    }
}
