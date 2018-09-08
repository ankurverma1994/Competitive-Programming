package LTIME36;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 * credits: https://www.hackerearth.com/problem/algorithm/xsquare-and-increasing-sequences/editorial/
 */

import java.util.*;
import java.io.*;
import java.math.*;

class ProbA {

    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            String ar = is();
            int n = ar.length();
            char s[] = new char[n + 1];
            for (int i = 1; i < n + 1; i++) s[i] = ar.charAt(i - 1);
            int k = ii();
            int dp[][] = new int[28][n + 15];
            for (int i = 0; i < 28; i++)
                for (int j = 0; j < n + 15; j++)
                    dp[i][j] = -1;
            int correct[] = new int[n + 5];

            for (int i = n - 1; i >= 0; i--) {
                for (int j = 1; j < 27; j++) {
                    dp[j][i] = dp[j][i + 1];
                }
                dp[s[i + 1] - 'a' + 1][i] = i + 1;
            }

            for (int i = n; i >= 1; i--) {
                int ans = 0;
                for (int j = s[i] - 'a' + 1; j < 27; j++) {
                    if (dp[j][i] != -1) {
                        ans = Math.max(ans, correct[dp[j][i]]);
                    }
                }
                correct[i] = ans + 1;
            }

            char last = 'a';
            int i = 0;
            while (i < n && k > 0) {
                char c = 'z';
                for (int j = 1; j < 27; j++) {
                    if (dp[j][i] != -1 && correct[dp[j][i]] >= k && last <= ('a' + j - 1)) {
                        c = (char) Math.min(c, s[dp[j][i]]);
                    }
                }
                out.print(c);
                last = c;
                i = dp[c - 'a' + 1][i];
                k--;
            }

            out.println();
        }
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new ProbA().main1();
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