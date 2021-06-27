import java.util.Scanner;

public class Program {
    public static void printGraphic(long info) {
        long mark;
        int i = 1;

        while (info > 0){
            mark = info % 10;
            System.out.print("Week " + i + " ");
            while (mark > 0){
                System.out.print("=");
                mark--;
            }
            System.out.println(">");
            i++;
            info /= 10;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String week;
        int numOfWeek, tmp, tmpNumOfWeek, i = 1;
        long marks = 0;

        week = scanner.next();

        if (week.equals("42")) System.exit(1);

        numOfWeek = scanner.nextInt();

        while (i <= 18){
            if (numOfWeek != i) ilArgExit();
            tmp = readWeeksData(scanner);
            marks = marks * 10 + tmp;
            week = scanner.next();
            if (week.equals("42")) break;
            tmpNumOfWeek = numOfWeek;
            numOfWeek = scanner.nextInt();
            if (tmpNumOfWeek > numOfWeek) ilArgExit();
            i++;
        }

        marks = reverse(marks);
        printGraphic(marks);
    }

    public static int readWeeksData(Scanner scan) {
        int min = 9, i = 0, num;

        while (i++ < 5) {
            num = scan.nextInt();
            if (num > 9) ilArgExit();
            if (num < min) min = num;
        }

        return (min);
    }

    public static long reverse(long num){
        long newNum = 0;

        while (num > 0){
            newNum = (newNum * 10) + num % 10;
            num /= 10;
        }
        return (newNum);
    }

    public static void ilArgExit() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }
}
