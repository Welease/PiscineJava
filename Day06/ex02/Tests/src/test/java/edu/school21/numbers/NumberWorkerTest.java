package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {
    NumberWorker numberWorker = new NumberWorker();

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 11, 19, 23, 179, 601})
    public void isPrimeForPrimes (int arg) { assertTrue(numberWorker.isPrime(arg)); }

    @ParameterizedTest
    @ValueSource(ints = {24, 56, 782, 555, 12, 36, 302})
    public void isPrimeForNotPrimes (int arg) { assertFalse(numberWorker.isPrime(arg)); }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, -1, -100, -4542})
    public void isPrimeForIncorrectNumbers(int arg) {
        assertThrows(NumberWorker.IllegalNumberException.class, () -> {
        numberWorker.isPrime(arg); });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void isDigitsSumCorrect(int arg, int result) {
        Assertions.assertEquals(numberWorker.digitsSum(arg), result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data_fails.csv")
    void isDigitsSumCorrectForIncorrect(int arg, int result) {
        Assertions.assertEquals(numberWorker.digitsSum(arg), result);
    }
}