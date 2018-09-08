package aaa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StringMatching {
    public static void main(String[] args) throws IOException {
        BufferedReader bj = new BufferedReader(new InputStreamReader(System.in));

        char s[] = bj.readLine().toCharArray();
        System.out.println("Z algo");
        System.out.println(Arrays.toString(StringMatching.Z(s)));
        System.out.println("KMP table");
        System.out.println(Arrays.toString(StringMatching.kmpTable(s)));
//        char a[] = ("c" + "$" + "abccc").toCharArray();
//        System.out.println(Arrays.toString(StringMatching.Z(a)));
    }

    public static int[] kmpTable(char[] str) {
        int n = str.length;
        int[] kmp = new int[n + 1];
        kmp[0] = -1;
        kmp[1] = 0;
        for (int i = 2, j = 0; i <= n; i++) {
            while (j > 0 && str[i - 1] != str[j]) j = kmp[j];
            kmp[i] = str[i - 1] == str[j] ? ++j : 0;
        }
        return kmp;
    }

    public static int[] Z(char[] str) {
        int n = str.length;
        int[] z = new int[n];
        if (n == 0) return z;
        z[0] = n;
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i > r) {
                l = r = i;
                while (r < n && str[r - l] == str[r]) r++;
                z[i] = r - l;
                r--;
            } else {
                int k = i - l;
                if (z[k] < r - i + 1) {
                    z[i] = z[k];
                } else {
                    l = i;
                    while (r < n && str[r - l] == str[r]) r++;
                    z[i] = r - l;
                    r--;
                }
            }
        }
        return z;
    }
}