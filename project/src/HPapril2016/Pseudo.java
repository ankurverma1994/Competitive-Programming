package HPapril2016;
/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;
import java.math.*;

class Pseudo {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++)
                dp[i][j] = -1;
        }
        long freq[] = new long[81 * 200 + 10];
        int n = ii();
        for (int digit = 1; digit <= n; digit++) {
            for (int sum = 0; sum <= 81 * digit; sum++) {
                long ans = solve(digit, sum);
                freq[sum] = (freq[sum] + ans) % mod;
            }
        }
        freq[1]++;
        long a = 0;
        for (long x : freq) {
            if (x > 0) {
                a = (a + (x % mod * (x - 1) % mod) % mod) % mod;
            }
        }
        out.println((a + modpow(10, n, mod) + 1) % mod);
    }

    public static long modpow(long base, int exp, int mod) {
        base %= mod;
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    long sum(int a) {
        char x[] = String.valueOf(a).toCharArray();
        int s = 0;
        for (char c : x) {
            s += ((c - '0') * (c - '0'));
        }
        return s;
    }

    long solve(int n, int sum) {
        long ans = 0;
        for (int i = 1; i <= 9; i++) {
            int x = i * i;
            if (sum - x >= 0)
                ans = (ans + count(n - 1, sum - x)) % mod;
        }
        return ans;
    }

    long count(int n, int sum) {
        if (n == 0) {
            if (sum == 0) return 1;
            return 0;
        }
        if (dp[n][sum] != -1)
            return dp[n][sum];
        long ans = 0;
        for (int i = 0; i <= 9; i++) {
            int x = i * i;
            if (sum - x >= 0)
                ans = (ans + count(n - 1, sum - x)) % mod;
        }
        return dp[n][sum] = ans;
    }

    long dp[][] = new long[200][81 * 200 + 10];

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new Pseudo().main1();
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
}
