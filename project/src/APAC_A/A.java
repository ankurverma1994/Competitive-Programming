package APAC_A;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class A {
    public static void main(String args[]) throws IOException {
//        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter out = new PrintWriter(System.out);
        BufferedReader obj=new BufferedReader(new FileReader("A:\\A-large.in"));
        PrintWriter out=new PrintWriter("A:\\out.txt");
        int T = Integer.parseInt(obj.readLine());
        for (int tc = 1; tc <= T; tc++) {
            out.printf("Case #%d: ", tc);
            int n = Integer.parseInt(obj.readLine());
            String a[] = new String[n];
            for (int i = 0; i < n; i++)
                a[i] = obj.readLine();

            Arrays.sort(a);
            int max = 0, maxi = 0;
            for (int i = 0; i < n; i++) {
                int f[] = new int[26];
                for (int j = 0; j < a[i].length(); j++) {
                    char s = a[i].charAt(j);
                    if (s != ' ') {
                        f[s - 'A']++;
                    }
                }
                int count = 0;
                for (int j = 0; j < 26; j++)
                    if (f[j] > 0)
                        count++;
                if (count > max) {
                    max = count;
                    maxi = i;
                }
            }
            out.println(a[maxi]);
        }
        out.flush();
        out.close();
    }
}
