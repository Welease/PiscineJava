import java.util.Scanner;

public class Program {

    private final static int COLUMN = 10;
    private final static int LINE = 12;

    public static void main(String[] args) {
        int countOfDifChars = 0;
        char [] symbols;
        int [] alphabet = new int[65535];
        int maxValue;

        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        symbols = inputString.toCharArray();
        for (int i = 0; i < symbols.length; ++i) {
            if (alphabet[symbols[i]] == 0)
                countOfDifChars++;
            alphabet[symbols[i]] += 1;
        }

        int[] intArray = new int[countOfDifChars];
        char[] charArray = new char[countOfDifChars];

        maxValue = fillArrays(alphabet, intArray, charArray);

        sort(intArray, charArray, 0, intArray.length - 1);

        int [][] resultMap = new int[LINE][COLUMN];

        int k1 = charArray.length > 10 ? charArray.length - 10 : 0;

        fillMap(resultMap, maxValue, k1, intArray, charArray);

        printMap(resultMap, maxValue, k1, intArray, charArray);
    }


    private static int fillArrays(int [] alphabet, int[] intArray, char[] charArray) {
        int indexInArr = 0;
        int maxValue = 0;
        for (int i = 0; i < alphabet.length; ++i) {
            if (alphabet[i] > 0) {
                maxValue = maxValue < alphabet[i] ? alphabet[i] : maxValue;
                intArray[indexInArr] = alphabet[i];
                charArray[indexInArr] = (char)i;
                indexInArr++;
            }
        }
        return maxValue;
    }


    private static void fillMap(int[][] resultMap, int maxValue, int k1, int[] intArray, char[] charArray) {
        for (int i = 0; i < COLUMN && k1 < charArray.length; ++i) {
            resultMap[11][i] = charArray[k1];
            int tmp = intArray[k1] * 10 / maxValue;
            int j = 10;
            for (; tmp != 0; --j, --tmp) {
                resultMap[j][i] = (int)'#';
            }
            resultMap[j][i] = intArray[k1];
            k1++;
        }
    }


    private static void printMap(int[][] resultMap, int maxValue, int k1, int[] intArray, char[] charArray) {
        int maxValueOfOcc = countOfDis(maxValue);
        maxValueOfOcc += 2;
        for (int i = 0; i < 12; ++i) {
            for (int j = COLUMN - 1; j >= 0; --j) {
                if (k1 + j >= charArray.length)
                    continue;
                int tmp = intArray[j + k1] * 10 / maxValue;
                int until = i >= (11 - tmp) ? maxValueOfOcc : (maxValueOfOcc - countOfDis(resultMap[i][j]) + 1);
                if (resultMap[i][j] == 0)
                    until = maxValueOfOcc;
                for (int k = 0; k < until ; ++k)
                    System.out.print(" ");
                if (i >= 11 - tmp) {
                    System.out.print((char) (resultMap[i][j]));
                } else
                    System.out.print(resultMap[i][j] != 0 ? resultMap[i][j] : " ");
            }
            System.out.print("\n");
        }
    }

    private static void merge(int intArr[], char charArr[], int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int iL[] = new int[n1];
        int iR[] = new int[n2];

        char cL[] = new char[n1];
        char cR[] = new char[n2];

        for (int i = 0; i < n1; ++i) {
            iL[i] = intArr[left + i];
            cL[i] = charArr[left + i];
        }

        for (int j = 0; j < n2; ++j) {
            iR[j] = intArr[middle + 1 + j];
            cR[j] = charArr[middle + 1 + j];
        }

        int i = 0, j = 0;

        int k = left;
        while (i < n1 && j < n2) {
            if (iL[i] < iR[j] && cL[i] < cR[j]) {
                intArr[k] = iL[i];
                charArr[k] = cL[i++];
            }
            else {
                intArr[k] = iR[j];
                charArr[k] = cR[j++];
            }
            k++;
        }

        while (i < n1) {
            intArr[k] = iL[i];
            charArr[k++] = cL[i++];
        }

        while (j < n2) {
            intArr[k] = iR[j];
            charArr[k++] = cR[j++];
        }
    }

    private static void sort(int intArr[], char charArr[], int left, int right)  {
        if (left < right) {
            int m = left + (right-left) / 2;
            sort(intArr, charArr ,left, m);
            sort(intArr, charArr,m + 1, right);
            merge(intArr, charArr, left, m, right);
        }
    }

    private static int countOfDis(int num) {
        int i = 0;
        while (num / 10 != 0) {
            i++;
            num /= 10;
        }
        if (num == 0)
            return 0;
        return i + 1;
    }
}

//"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO"
