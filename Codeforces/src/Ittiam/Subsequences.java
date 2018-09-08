package Ittiam;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class Subsequences {
    final static int mod = (int) 1e9 + 7;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), k = ii(), a[] = iia(n);
        int max = 0;
        for (int i : a) max = Math.max(max, i);
        int constant = Math.max(n, k) + 10;
        long ft[][] = new long[max + 10][constant + 10];
        long dp[][] = new long[constant + 10][constant + 10];
        long K = 0;
        for (int i = 0; i < n; i++) {
            K += a[i];
            K = Math.min(K, k);
            for (int j = a[i]; j <= K; j++) dp[i][j] = sumFenwick(ft, a[i] - 1, j - a[i]);
            for (int j = a[i]; j <= K; j++)
                if (dp[i][j] > 0) addFenwick(ft, a[i], j, dp[i][j]);
            addFenwick(ft, a[i], a[i], 1);
        }
        long ans = 0;
        for (int i = 0; i < K; i++) ans = (ans + sumFenwick(ft, max, i)) % mod;
        out.println(ans);
    }

    public static long sumFenwick(long[][] ft, int r, int c) {
        long sum = 0;
        for (int rr = r; rr > 0; rr -= rr & -rr) {
            sum = (sum + ft[rr][c]) % mod;
        }
        return sum;
    }

    public static void addFenwick(long[][] ft, int r, int c, long v) {
        if (v == 0) return;
        int R = ft.length;
        for (int rr = r; rr < R; rr += rr & -rr) {
            ft[rr][c] = (ft[rr][c] + v) % mod;
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Subsequences().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        // obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/input.txt") : new ByteArrayInputStream(check.getBytes());
        solve();
        out.flush();
        out.close();
    }

    byte inbuffer[] = new byte[1024];
    int lenbuffer = 0, ptrbuffer = 0;

    int readByte() {
        if (lenbuffer == -1) throw new InputMismatchException();
        if (ptrbuffer >= lenbuffer) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if (lenbuffer <= 0) return -1;
        return inbuffer[ptrbuffer++];
    }


    int ii() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }
}
