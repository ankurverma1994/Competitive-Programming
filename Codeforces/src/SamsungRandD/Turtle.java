package SamsungRandD;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class Turtle {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        prime = new boolean[100000 + 1];
        int x[] = sieveEratosthenes(100000);
        for (int i : x) prime[i] = true;
        n = ii();
        m = ii();
        a = iim(n, m);
        dp = new long[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        long ans = ways(0, 0);
        out.println(ans);
        if (ans > 0) {
            path(0, 0);
        }
    }

    long ways(int i, int j) {
        if (i == n - 1 && j == m - 1)
            return dp[i][j] = 1;
        if (i >= n || i < 0 || j >= m || j < 0)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        long ans = 0;
        if (valid(i + 1, j)) {
            ans += ways(i + 1, j);
            ans %= mod;
        }
        if (valid(i, j + 1)) {
            ans += ways(i, j + 1);
            ans %= mod;
        }
        if (valid(i + 1, j + 1)) {
            ans += ways(i + 1, j + 1);
            ans %= mod;
        }
        return dp[i][j] = ans;
    }

    void path(int i, int j) {
        out.println((i + 1) + " " + (j + 1));
        if (i == n - 1 && j == m - 1)
            return;
        if (valid(i + 1, j + 1) && dp[i + 1][j + 1] > 0)
            path(i + 1, j + 1);
        else if (valid(i + 1, j) && dp[i + 1][j] > 0)
            path(i + 1, j);
        else if (valid(i, j + 1) && dp[i][j + 1] > 0)
            path(i, j + 1);
    }

    boolean valid(int i, int j) {
        return 0 <= i && i < n && 0 <= j && j < m && prime[a[i][j]];
    }

    int a[][], n, m;
    boolean prime[];
    long dp[][];

    public static int[] sieveEratosthenes(int n) {
        /*  Code picked up from "uwi" submissions */
        if (n <= 32) {
            int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
            for (int i = 0; i < primes.length; i++) {
                if (n < primes[i]) {
                    return Arrays.copyOf(primes, i);
                }
            }
            return primes;
        }
        int u = n + 32;
        double lu = Math.log(u);
        int[] ret = new int[(int) (u / lu + u / lu / lu * 1.5)];
        ret[0] = 2;
        int pos = 1;
        int[] isnp = new int[(n + 1) / 32 / 2 + 1];
        int sup = (n + 1) / 32 / 2 + 1;
        int[] tprimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
        for (int tp : tprimes) {
            ret[pos++] = tp;
            int[] ptn = new int[tp];
            for (int i = (tp - 3) / 2; i < tp << 5; i += tp)
                ptn[i >> 5] |= 1 << (i & 31);
            for (int j = 0; j < sup; j += tp) {
                for (int i = 0; i < tp && i + j < sup; i++) {
                    isnp[j + i] |= ptn[i];
                }
            }
        }
        int[] magic = {0, 1, 23, 2, 29, 24, 19, 3, 30, 27, 25, 11, 20, 8, 4,
                13, 31, 22, 28, 18, 26, 10, 7, 12, 21, 17, 9, 6, 16, 5, 15, 14};
        int h = n / 2;
        for (int i = 0; i < sup; i++) {
            for (int j = ~isnp[i]; j != 0; j &= j - 1) {
                int pp = i << 5 | magic[(j & -j) * 0x76be629 >>> 27];
                int p = 2 * pp + 3;
                if (p > n)
                    break;
                ret[pos++] = p;
                if ((long) p * p > n)
                    continue;
                for (int q = (p * p - 3) / 2; q <= h; q += p)
                    isnp[q >> 5] |= 1 << q;
            }
        }
        return Arrays.copyOf(ret, pos);
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new Turtle().main1();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StackOverflowError e) {
                    System.out.println("RTE");
                }
            }
        }, "1", 1 << 26).start();
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

    int[][] iim(int n, int m) {
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = ii();
        return a;
    }
}
