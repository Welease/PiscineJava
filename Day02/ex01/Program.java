import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Program {
    public static void main(String[] args) throws IOException {
        FileInputStream aFile = new FileInputStream(args[0]);
        FileInputStream bFile = new FileInputStream(args[1]);
        FileOutputStream dictFile = new FileOutputStream("dictionary.txt");
        read(aFile, bFile, dictFile);
    }

    private static void read(FileInputStream aFile, FileInputStream bFile, FileOutputStream dictFile) throws IOException{
        TreeSet<String> dict = new TreeSet<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(aFile));

        ArrayList<String> a = readWords(reader, dict);
        reader = new BufferedReader(new InputStreamReader(bFile));
        ArrayList<String> b = readWords(reader, dict);

        String tmp;
        for (Iterator<String> iterator = dict.iterator(); iterator.hasNext(); ) {
            tmp = iterator.next();
            for (int i = 0; i < tmp.length(); ++i) {
                dictFile.write(tmp.charAt(i));
            }
            dictFile.write(' ');
        };
        count(dict, a, b);
    }

    public static void count(TreeSet<String> dict, ArrayList<String> a, ArrayList<String> b) {
        ArrayList<Integer> aCount = new ArrayList<Integer>();
        ArrayList<Integer> bCount = new ArrayList<Integer>();

        fillCountArray(aCount, a, dict);
        fillCountArray(bCount, b, dict);

        int numerator = 0;
        int a1 , b1;
        for (Iterator <Integer> ia = aCount.iterator(), ib = bCount.iterator(); ia.hasNext() && ib.hasNext();) {
            a1 = ia.next();
            b1= ib.next();
            numerator += a1 * b1;
        }

        int sumOfA = 0;
        for (Iterator <Integer> i = aCount.iterator(); i.hasNext();) {
            sumOfA += Math.pow(i.next(), 2);
        }
        int sumOfB = 0;
        for (Iterator <Integer> i = bCount.iterator(); i.hasNext();) {
            sumOfB += Math.pow(i.next(), 2);
        }
        double res = numerator / ((Math.pow(sumOfA, 0.5) * Math.pow(sumOfB, 0.5)));

        System.out.print("Similarity = ");
        if ((Math.pow(sumOfA, 0.5) * Math.pow(sumOfB, 0.5)) == 0) {
            System.out.println("0");
        } else {
            BigDecimal result = new BigDecimal(res);
            result = result.setScale(2, RoundingMode.DOWN);
            System.out.printf("%.2f\n", result);
        }
    }

    public static void fillCountArray(ArrayList<Integer> aCount, ArrayList<String> a, TreeSet<String> dict) {
        int count;
        for (Iterator <String> it = dict.iterator(); it.hasNext(); ) {
            count = 0;
            String tmp = it.next();
            for (Iterator <String> it1 = a.iterator(); it1.hasNext(); ) {
                if (it1.next().equals(tmp)) {
                    count++;
                }
            }
            aCount.add(count);
        }
    }

    private static ArrayList<String> readWords(BufferedReader reader, TreeSet<String> dict) throws IOException{
        String tmp;
        String[] wordsArray;
        ArrayList<String> list = new ArrayList<String>();
        do {
            tmp = reader.readLine();
            if (tmp != null) {
                wordsArray = tmp.split(" ");
                for (int i = 0; i < wordsArray.length; ++i) {
                    dict.add(wordsArray[i]);
                    list.add(wordsArray[i]);
                }
            }
        } while (tmp != null);
        return list;
    }
}
