import java.util.Scanner;

public class Program {

    private final static int MAX_COUNT = 10;
    private final static int DAY_IN_WEEK = 7;

    private final static String[] Days = {
            "MO",
            "TU",
            "WE",
            "TH",
            "FR",
            "SA",
            "SU"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] students = new String[MAX_COUNT];
        String[][] table = new String[MAX_COUNT][MAX_COUNT];

        scanStudents(scanner, students);

        int[] times = new int[5];
        String [] days = new String[5];
        int[] dates = new int[MAX_COUNT];
        int[] timesOfClasses = new int[MAX_COUNT];
        int count = scanClasses(scanner, times, days);

        sortDays(days, times, count);

        fillTable(times, days, table, count, dates, timesOfClasses);

        fillAbsents(timesOfClasses, dates, table, students, scanner);

        printTable(table, students);
    }

    private static int findInTimesOfClasses(int time, int day, int[] timesOfclasses, int[] dates) {
        for (int i = 0; i < MAX_COUNT; ++i) {
            if (time == timesOfclasses[i] && day == dates[i]) {
                return i;
            }
        }
        return -1;
    }

    private static int findInStudents(String name, String[] students) {
        for (int i = 0; i < MAX_COUNT; ++i) {
            if (name.equals(students[i])) {
                return i;
            }
        }
        return -1;
    }

    private static void fillAbsents(int[] timesOfClasses, int[] dates, String[][] table, String[] students, Scanner scanner) {
        String input;
        int i, j, t, d;
        boolean here;
        for (;;) {
            input = scanner.next();
            if (input.equals(".")) {
                break;
            }
            i = findInStudents(input, students);
            t = scanner.nextInt();
            d = scanner.nextInt();
            input = scanner.next();
            here = !input.equals("NOT_HERE");
            j = findInTimesOfClasses(t, d, timesOfClasses,dates);
            if (j != -1) {
                table[i + 1][j] = here ? "1" : "-1";
            }
        }
    }

    private static void printTable(String[][] table, String[] students) {
        int len;
        for (int l = 0; l < MAX_COUNT; ++l) {
            if (l != 0) {
                if (students[l - 1] == null) {
                    l++;
                    continue;
                }
                len = students[l - 1].length();
                while (10 - len != 0) {
                    System.out.print(" ");
                    len++;
                }
                System.out.print(students[l - 1]);
            }
            else {
                System.out.print("          ");
            }
            for (int k = 0; k < MAX_COUNT; ++k) {
                if (table[l][k] != null) {
                    len = table[l][k].length();
                    while (table[0][k].length() - len != 0) {
                        System.out.print(" ");
                        len++;
                    }
                    System.out.print(table[l][k]);
                    System.out.print("|");
                }
                else {
                    int until;
                    if (table[0][k] == null) {
                        until = 0;
                    }
                    else {
                        until = table[0][k].length();
                    }
                    for (int x = 0; x < until; ++x) {
                        System.out.print(" ");
                    }
                    if (until != 0) {
                        System.out.print("|");
                    }
                }
            }
            System.out.print("\n");
        }
    }

    private static int findInDays(String day) {
        for (int i = 0; i < DAY_IN_WEEK; ++i) {
            if (day.equals(Days[i])) {
                return i;
            }
        }
        return -1;
    }

    private static void sortDays(String[] days, int[] times, int count) {
        String buf;
        int    timeBuf;

        int n = count;
        int fir, sec, t1, t2;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                fir = findInDays(days[j]);
                sec = findInDays(days[j + 1]);
                t1 = times[j];
                t2 = times[j + 1];
                if (fir > sec || (fir == sec && t1 > t2)) {
                    timeBuf = times[j];
                    buf = days[j];

                    times[j] = times[j + 1];
                    days[j] = days[j + 1];

                    times[j + 1] = timeBuf;
                    days[j + 1] = buf;
                }
            }
        }
    }

    private static void fillTable(int[] times, String[] days, String[][] table, int count, int[] dates, int[] timesOfClasses) {
        int j = 0;
        int date;
        String tmp;
        for (int k = 0; k < 5; ++k) {
            for (int i = 0; i < count; ++i) {
                if (j > 9) {
                    break;
                }
                int l = findInDays(days[i]);
                date = k * DAY_IN_WEEK + l;
                if (date != 0 && date < 31) {
                    dates[j] = date;
                    timesOfClasses[j] = times[i];
                    tmp = String.valueOf(times[i]) + ":00 " + days[i] + " " + date;
                    table[0][j] = tmp;
                    j++;
                }
            }
        }
    }

    private static void scanStudents(Scanner scanner, String[] students) {
        String input;
        for (int i = 0; i < MAX_COUNT + 1; ++i) {
            input = scanner.nextLine();
            if (input.equals(".")) {
                break;
            }
            students[i] = input;
        }
    }

    private static int scanClasses(Scanner scanner, int[] times, String [] days) {
        String input;
        int index = 0;
        int time;

        for (int i = 0; i < 8; ++i) {
            if (scanner.hasNextInt()) {
                time = scanner.nextInt();
                times[index] = time;
            } else {
                input = scanner.next();
                if (input.equals(".")) {
                    break;
                }
                days[index] = input;
                index++;
            }
        }
        return index;
    }
}

