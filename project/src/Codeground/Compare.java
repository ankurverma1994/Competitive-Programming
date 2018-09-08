package Codeground;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

class Compare {
    public static void main(String[] args) throws IOException {
        fha(new FileInputStream("A:\\fuck1.txt"), new FileInputStream("A:\\fuck2.txt"));
    }

    public static void fha(InputStream is1, InputStream is2) throws IOException {
        Scanner sc1 = new Scanner(is1);
        Scanner sc2 = new Scanner(is2);
        int tc = 1;
        while (sc1.hasNext() && sc2.hasNext()) {
            String str1 = sc1.next();
            String str2 = sc2.next();
            if (!str1.equals(str2))
                System.out.println(str1 + " != " + str2 + "    " + tc);
            tc++;
        }
        while (sc1.hasNext())
            System.out.println(sc1.next() + " != EOF");
        while (sc2.hasNext())
            System.out.println("EOF != " + sc2.next());
        sc1.close();
        sc2.close();
    }
}