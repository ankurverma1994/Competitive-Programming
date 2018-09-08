package DynamicProgramming1;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class AlyonaAndStrings {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        n = ii();
        m = ii();
        int k = ii();
        a = is().toCharArray();
        b = is().toCharArray();
        dp = new int[2][k + 1][n + 1][m + 1];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j <= k; j++)
                for (int x = 0; x <= n; x++)
                    for (int y = 0; y <= m; y++)
                        dp[i][j][x][y] = -1;
        out.println(solve(0, 0, k, 0));
    }

    char a[], b[];
    int n, m;
    int dp[][][][];

    int solve(int i, int j, int k, int liyeHai) {
//        out.println(i + " " + j + " " + k + " " + liyeHai);
        if (k == 0) {
            int count = 0;
            for (int x = i, y = j; x < n && y < m; x++, y++) {
                if (a[x] == b[y]) count++;
                else break;
            }
            return count;
        }
        if (i < 0 || i >= n || j < 0 || j >= m)
            return Integer.MIN_VALUE / 2;
        int ans = Integer.MIN_VALUE / 2;
        if (dp[liyeHai][k][i][j] != -1)
            return dp[liyeHai][k][i][j];
        if (liyeHai == 1) {
            ans = Math.max(ans, solve(i, j, k, 0));
            if (a[i] == b[j]) {
                ans = Math.max(ans, 1 + solve(i + 1, j + 1, k, 1));
            }
        } else {
            ans = Math.max(ans, solve(i + 1, j, k, liyeHai));
            ans = Math.max(ans, solve(i, j + 1, k, liyeHai));
            if (a[i] == b[j])
                ans = Math.max(ans, 1 + solve(i + 1, j + 1, k - 1, 1));
        }
//        out.println(i + " " + j + " " + k + " " + liyeHai + " " + ans);
        return dp[liyeHai][k][i][j] = ans;
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new AlyonaAndStrings().main1();
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
