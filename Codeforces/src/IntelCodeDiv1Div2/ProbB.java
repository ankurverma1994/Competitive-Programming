package IntelCodeDiv1Div2;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbB {
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(obj.readLine());
        String s[] = obj.readLine().split(" ");
        int p[] = new int[n];
        for (int j = 0; j < n; j++)
            p[j] = Integer.parseInt(s[j]);
        for (int i = 0; i < n; i++) {
            int count = 0;
            char x[] = obj.readLine().toCharArray();
            for (char z : x) {
                if (vowel(z))
                    count++;
            }
            if (count != p[i]) {
                out.println("NO");
                out.flush();
                return;
            }
        }
        out.println("YES");
        out.close();
    }

    static boolean vowel(char c) {
        String s = "aeiouy";
        String a = "" + c;
        return s.contains(a);
    }
}