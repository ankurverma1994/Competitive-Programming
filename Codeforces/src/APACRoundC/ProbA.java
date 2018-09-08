package APACRoundC;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbA {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int TC = ii();
        for (int tc = 1; tc <= TC; tc++) {
            out.printf("Case #%d: ", tc);
            R = ii();
            C = ii();
            int x = ii(), v = ii();
            S = ii();
            p = id();
            q = id();
            s = new char[R][C];
            int f[][] = new int[R][C];
            for (int i = 0; i < R; i++)
                for (int j = 0; j < C; j++)
                    s[i][j] = ic();
            ANS = 0;
            solve(x, v, f, 0, 0);
            out.printf("%.8f\n", ANS);
        }
    }

    char s[][];
    int dx[] = {1, -1, 0, 0};
    int dy[] = {0, 0, 1, -1};
    double p, q;
    int R, C, S;
    double ANS;

    void solve(int x, int y, int f[][], int step, double ans) {
        if (step >= S) {
            ANS = Math.max(ANS, ans);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int u = x + dx[i], v = y + dy[i];
            if (valid(u, v)) {
                f[u][v]++;
                double z = q;
                if (s[u][v] == 'A') z = p;
                double temp = (Math.pow((1 - z), f[u][v] - 1) * z);
//                System.out.println(u + " " + v + " " + (ans + temp));
                solve(u, v, f, step + 1, ans + temp);
                f[u][v]--;
            }
        }
    }

    boolean valid(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ProbA().main1();
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
//        out = new PrintWriter("/home/ankurverma1994/A-small-output.txt");
        out = new PrintWriter("/home/ankurverma1994/A-large-output.txt");
//        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        obj = check.isEmpty() ? new FileInputStream("/home/ankurverma1994/Downloads/A-small-attempt0.in") : new ByteArrayInputStream(check.getBytes());
        obj = check.isEmpty() ? new FileInputStream("/home/ankurverma1994/Downloads/A-large.in") : new ByteArrayInputStream(check.getBytes());
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
