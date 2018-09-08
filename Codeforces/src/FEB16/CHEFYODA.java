package FEB16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class CHEFYODA {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), m = ii(), p = ii(), k = ii();
            short ans = 0;
            if (n % 2 == 0 || m % 2 == 0) ans++;
            if (n % 2 == 0 && m % 2 == 0) ans++;
            if (p == 0) {
                out.printf("%.6f\n", 1.0);
                continue;
            }
            if (ans == 2) {
                out.printf("%.6f\n", 1.0);
                continue;
            }
            if (ans == 0) {
                out.printf("%.6f\n", 0.0);
                continue;
            }
            double x[] = binomialSum(k, p - 1);
            int exponent = (int) Math.floor(x[1]) - k;
            double ANS = x[0] * Math.pow(2, exponent);
            ANS = 1.0 - ANS;
            out.printf("%.6f\n", ANS);
        }
    }
    int n, m;
    boolean visit[][];
    int dx[] = {1, -1, 0, 0, 1, -1, -1, 1};
    int dy[] = {0, 0, 1, -1, 1, -1, 1, -1};

    boolean game1(int x, int y, int player) {
        boolean ans = false;
        boolean child = false;
        for (int i = 0; i < 4; i++) {
            int p = x + dx[i], q = y + dy[i];
            if (valid(p, q)) {
                visit[p][q] = true;
                child = true;
                ans = ans | game1(p, q, 2);
                visit[p][q] = false;
            }
        }
        if (!child) ans = false;
        if (player == 0 || player == 1) return ans;
        else return !ans;
    }

    boolean game2(int x, int y, int player) {
        boolean ans = false;
        boolean child = false;
        for (int i = 4; i < 8; i++) {
            int p = x + dx[i], q = y + dy[i];
            if (valid(p, q)) {
                visit[p][q] = true;
                child = true;
                ans = ans | game2(p, q, 2);
                visit[p][q] = false;
            }
        }
        if (!child) ans = false;
        if (player == 0 || player == 1) return ans;
        else return !ans;
    }

    boolean valid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m && !visit[x][y];
    }

    long C(int n, int m) {
        if (m > n - m) m = n - m;
        long ans = 1;
        for (int i = 0; i < m; i++) ans = ans * (n - i) / (i + 1);
        return ans;
    }


    public double[] binomialSum(int N, int K) {
        // cC0 + nC1 + nC2 + .. nCk
        // hint taken from Raziman TV answer on math.stackexchange
        int cexpo = 0, texpo = 0;
        double cman = 1.0, tman = 1.0;
        for (int i = 1; i <= K; i++) {
            cman = (N - i + 1) * cman / i;
            while (cman >= 2) {
                cman /= 2;
                cexpo++;
            }
            while (cman <= 0.5) {
                cman *= 2;
                cexpo--;
            }
            tman += cman * Math.pow(2, cexpo - texpo);
            while (tman >= 2) {
                tman /= 2;
                texpo++;
            }
        }
        // ans = total_mantisa * 2^total_exponent
        return new double[]{tman, texpo};
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new CHEFYODA().main1();
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