package Kickstart;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbB {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int test = ii();
        for (int tc = 1; tc <= test; tc++) {
            out.printf("Case #%d: ", tc);
            a = is().toCharArray();
            b = is().toCharArray();
            dp = new int[a.length][b.length];
            for (int i = 0; i < a.length; i++)
                Arrays.fill(dp[i], -1);
            out.println(solve(0, 0) == 1 ? "TRUE" : "FALSE");
        }
    }

    int dp[][];

    char a[], b[];

    int solve(int i, int j) {
        if (i >= a.length && j >= b.length) return i == j ? 1 : 0;
        if (i >= a.length) {
            for (int x = j; x < b.length; x++) {
                if (b[x] != '*') {
                    return 0;
                }
            }
            return 1;
        }
        if (j >= b.length) {
            for (int x = i; x < a.length; x++)
                if (a[x] != '*')
                    return 0;
            return 1;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
//        System.out.println(i + " " + j);
        int ans = 0;
        if (a[i] != '*' && b[j] != '*') {
            if (a[i] != b[j])
                return 0;
            else
                return solve(i + 1, j + 1);
        }
        if (a[i] == '*' && b[j] == '*') {
            ans |= solve(i + 1, j + 1);
            ans |= solve(i + 1, j + 2);
            ans |= solve(i + 1, j + 3);
            ans |= solve(i + 1, j + 4);
            ans |= solve(i + 1, j + 5);
            ans |= solve(i + 2, j + 1);
            ans |= solve(i + 1, j + 1);
            ans |= solve(i + 1, j + 2);
            ans |= solve(i + 1, j + 3);
            ans |= solve(i + 1, j + 4);
            ans |= solve(i + 3, j + 1);
            ans |= solve(i + 2, j + 1);
            ans |= solve(i + 1, j + 1);
            ans |= solve(i + 1, j + 2);
            ans |= solve(i + 1, j + 3);
            ans |= solve(i + 4, j + 1);
            ans |= solve(i + 3, j + 1);
            ans |= solve(i + 2, j + 1);
            ans |= solve(i + 1, j + 1);
            ans |= solve(i + 1, j + 2);
            ans |= solve(i + 5, j + 1);
            ans |= solve(i + 4, j + 1);
            ans |= solve(i + 3, j + 1);
            ans |= solve(i + 2, j + 1);
            ans |= solve(i + 1, j + 1);
        } else if (a[i] == '*') {
            ans |= solve(i + 1, j);
            ans |= solve(i + 1, j + 1);
            ans |= solve(i + 1, j + 2);
            ans |= solve(i + 1, j + 3);
            ans |= solve(i + 1, j + 4);
        } else if (b[j] == '*') {
            ans |= solve(i, j + 1);
            ans |= solve(i + 1, j + 1);
            ans |= solve(i + 2, j + 1);
            ans |= solve(i + 3, j + 1);
            ans |= solve(i + 4, j + 1);
        }
        return dp[i][j] = ans;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ProbB().main1();
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
        obj = check.isEmpty() ? new FileInputStream("/home/ankurverma1994/Downloads/B-small-attempt2.in") : new ByteArrayInputStream(check.getBytes());
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
