package APACE;
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
        int test = ii();
//        double t1 = System.currentTimeMillis();
        for (int tc = 1; tc <= test; tc++) {
            out.printf("Case #%d: ", tc);
            n = ii();
            int d = ii();
            if (d == 1) {
                if (n % 2 == 1) {
                    long ans = (2L * n * n + 10L * n + 3 - (2L * n - 3)) / 16;
                    out.println(ans);
                    continue;
                } else {
                    long ans = (2L * n * n + 10L * n + 3 + (2L * n - 3)) / 16;
                    out.println(ans);
                    continue;
                }
            }
            long ans = 0;
            dp = new long[3][n + 1];
            for (int D = d; D <= n; D += d) {
                for (int i = 0; i < 3; i++) Arrays.fill(dp[i], -1);
                this.d = D;
                ans = ans + solve1(n - D, 0);
            }
            out.println(ans);
        }
//        out.println(System.currentTimeMillis() - t1);
    }

    int n, d;
    long dp[][];

    long solve1(int k, int last) {
        if (k == 0) return 1;
        if (k < 0) return 0;
        long ans = 0;
        if (dp[last][k] != -1)
            return dp[last][k];
        if (last == 0) {
            for (int i = d; i <= d + 2; i++)
                ans += solve1(k - i, i - d);
        }
        if (last == 1) {
            for (int i = d + 1; i <= d + 2; i++)
                ans += solve1(k - i, i - d);
        }
        if (last == 2) {
            ans += solve1(k - d - 2, 2);
        }
        return dp[last][k] = ans;
    }


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
//        out = new PrintWriter(System.out);
//        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        obj = check.isEmpty() ? new FileInputStream("/home/ankurverma1994/Downloads/inp.in") : new ByteArrayInputStream(check.getBytes());
        out = new PrintWriter("/home/ankurverma1994/output.txt");
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
