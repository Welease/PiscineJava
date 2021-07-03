package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number) throws IllegalNumberException {
        boolean isPrime = true;
        if (number < 2)
            throw new IllegalNumberException();
        for (int i = 2; i <= number / 2; ++i) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public int digitsSum(int number) {
        int n, sum = 0;
        while (number > 0) {
            n = number % 10;
            sum = sum + n;
            number = number / 10;
        }
        return number < 0 ? sum : sum * -1;
    }

    public static class IllegalNumberException extends RuntimeException {
        public IllegalNumberException() {}
    }
}
