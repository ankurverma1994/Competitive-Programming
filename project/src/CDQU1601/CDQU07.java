package CDQU1601;
/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;
import java.math.*;

class CDQU07 {
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String s;
        int count = 0;
        while (!(s = obj.readLine().trim()).equals("//")) {
            String a[] = s.split(" ");
            if (a[0].charAt(0) == '#') continue;
            if (s.contains("int ")) continue;
            if (s.contains("float ")) continue;
            if (s.contains("void ")) continue;
            if (s.contains("char ")) continue;
            if (s.contains("while ")) continue;
            if (s.contains("for ")) continue;
            if (s.contains("if ")) continue;
            if (s.contains("else ")) continue;
            if (s.contains("{")) continue;
            if (s.contains("}")) continue;
            String b = a[a.length - 1];
            if (b.charAt(b.length() - 1) == ';') continue;
            count++;
        }
        out.println(count);
        out.flush();
    }
}