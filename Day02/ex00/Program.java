import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Program {
    private static final String EOF = "42";
    public static void main(String[] args) throws IOException{
        FileOutputStream result = new FileOutputStream("result.txt");
        FileOutputStream tmp;
        File f;
        int index;
        String bytes;
        LinkedHashMap<String, String> mapOfSigns = new LinkedHashMap<String, String>();
        String input = "", type = "";
        Scanner scanner = new Scanner(System.in);

        parsSignatures(mapOfSigns);

        while (!input.equals(EOF)) {
            input = scanner.next();
            index = input.indexOf('.');
            if (index != -1) {
                type = input.substring(index + 1);
                bytes = mapOfSigns.get(type);
                if (bytes != null) {
                    f = new File(input);
                    if (f.exists() && !f.isDirectory()) {
                        tmp = new FileOutputStream(input, true);
                        for (int i = 0; i < bytes.length(); ++i) {
                            tmp.write(bytes.charAt(i));
                        }
                        bytes = type.toUpperCase();
                        for (int i = 0; i < bytes.length(); ++i) {
                            result.write(bytes.charAt(i));
                        }
                        result.write('\n');
                        System.out.println("PROCESSED");
                    } else {
                        System.out.println("UNDEFINED");
                    }
                }
                else {
                    System.out.println("UNDEFINED");
                }
            } else if (!input.equals("42")){
                System.out.println("UNDEFINED");
            }
        }
    }

    private static void parsSignatures(LinkedHashMap<String, String> mapOfSigns) throws IOException {
        FileInputStream signatures = new FileInputStream("signatures.txt");
        int i, k;
        StringBuilder in = new StringBuilder();
        while ((i = signatures.read()) != -1) {
            if (i == '\n') {
                k =  in.indexOf(",");
                mapOfSigns.put((in.substring(0,k)).toLowerCase(), in.substring(k + 2));
                in.delete(0, in.length());
            }
            else {
                in.append((char) i);
            }
        }
        k =  in.indexOf(",");
        mapOfSigns.put(in.substring(0,k).toLowerCase(), in.substring(k + 2));
    }
}
