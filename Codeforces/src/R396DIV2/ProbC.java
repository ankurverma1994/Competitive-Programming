package R396DIV2;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbC {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        n = ii();
        s = is().toCharArray();
        max = iia(26);
        freq = new int[26][n][n];
        for (int x = 0; x < 26; x++) {
            for (int i = 0; i < n; i++) {
                int f[] = new int[26];
                for (int j = i; j < n; j++) {
                    f[s[j] - 'a']++;
                    freq[x][i][j] = f[x];
                }
            }
        }
        dp = new long[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        longest = 1;
        int minimum = 1;
        int i = 0;
        for (int j = 0; j < n; j++) {
            boolean valid = true;
            for (int x = 0; x < 26; x++) {
                if (freq[x][i][j] > 0) {
                    if ((j - i + 1) <= max[x]) valid = true;
                    else {
                        valid = false;
                        break;
                    }
                }
            }
            if (!valid) {
                minimum++;
                i = j;
            }
        }
        out.println(solve(0, 0, 1) + "\n" + longest + "\n" + minimum);
    }

    int longest;
    long dp[][];

    class DP {
        long ways;
        int longest, count;
    }

    long solve(int i, int j, int longest) {
        if (i > j) return 0;
        if (j == n - 1) {
            this.longest = Math.max(this.longest, longest);
            return 1;
        }
        if (dp[i][j] != -1)
            return dp[i][j];
        long ans = 0;
        ans = solve(j + 1, j + 1, longest);
        if (ans >= mod) ans -= mod;

        boolean valid = true;
        for (int x = 0; x < 26; x++) {
            if (freq[x][i][j + 1] > 0) {
                if ((j + 1 - i + 1) <= max[x]) valid = true;
                else {
                    valid = false;
                    break;
                }
            }
        }
        if (valid) {
            ans += solve(i, j + 1, Math.max(longest, (j + 1 - i + 1)));
            if (ans >= mod) ans %= mod;
        }
        return dp[i][j] = ans;
    }

    int n;
    char s[];
    int max[];
    int freq[][][];

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ProbC().main1();
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

    String is() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
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

    long il() {
        long num = 0;
        int b;
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

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    float nf() {
        return Float.parseFloat(is());
    }

    double id() {
        return Double.parseDouble(is());
    }

    char ic() {
        return (char) skip();
    }

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }

    long[] ila(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++) a[i] = il();
        return a;
    }

    String[] isa(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++) a[i] = is();
        return a;
    }

    double[][] idm(int n, int m) {
        double a[][] = new double[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = id();
        return a;
    }

    int[][] iim(int n, int m) {
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = ii();
        return a;
    }
}
