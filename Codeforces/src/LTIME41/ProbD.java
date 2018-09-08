package LTIME41;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

class ProbD {

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = Integer.parseInt(obj.readLine()); tc > 0; tc--) {
            int n = Integer.parseInt(obj.readLine());
            int a[] = new int[n];
            int big = n;
            int d = n - 1;
            boolean found[] = new boolean[n + 1];
            while (count(found) > 1) {
                for (int i = 1; i <= n; i++) {
                    if (!found[i]) {
                        boolean flag = false;
                        for (int j = i + 1; j <= n; j++) {
                            if (found[j]) continue;
                            System.out.println("1 " + i + " " + j + " " + d);
                            System.out.flush();
                            String s = obj.readLine();
                            if (s.compareTo("Yes") == 0) {
                                System.out.println("2 " + i + " " + j);
                                System.out.flush();
                                s = obj.readLine();
                                found[i] = true;
                                found[j] = true;
                                if (s.compareTo("Yes") == 0) {
                                    a[j - 1] = big;
                                    a[i - 1] = big - d;
                                } else {
                                    a[i - 1] = big;
                                    a[j - 1] = big - d;
                                }
                                big--;
                                d--;
                                flag = true;
                                break;
                            }
                        }
                        if (flag) break;
                    }
                }
            }
            int num = 0;
            if (count(found) == 1) {
                int f[] = new int[n + 1];
                for (int x : a) f[x]++;
                for (int x = 1; x <= n; x++)
                    if (f[x] == 0)
                        num = x;
            }
            System.out.print("3 ");
            for (int x : a) System.out.print((x == 0 ? num : x) + " ");
            System.out.println();
            System.out.flush();
        }
    }

    public static int count(boolean found[]) {
        int ans = 0;
        for (int i = 1; i < found.length; i++) {
            if (!found[i])
                ans++;
        }
        return ans;
    }
}
