package APAC;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class gMatrix {

    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int T = ii();
        for (int tc = 1; tc <= T; tc++) {
            out.printf("Case #%d: ", tc);
            int n = ii(), k = ii(), c = ii(), x = ii(), a[] = iia(n), b[] = iia(n);
            int m[][] = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    m[i][j] = (a[i] * (i + 1) + b[j] * (j + 1) + c) % x;
            long ans = 0;
            int max[][] = getMax(m, k, k, n, n);
            for (int i = 0; i + k <= n; i++)
                for (int j = 0; j + k <= n; j++)
                    ans += max[i][j];
            out.println(ans);
        }
    }

    int[][] getMax(int a[][], int x, int y, int n, int m) {
        int b[][] = new int[n][m];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            q = new ArrayDeque<>();
            for (int j = 0; j < y; j++) {
                while (!q.isEmpty() && a[i][j] >= a[i][q.getLast()])
                    q.pollLast();
                q.addLast(j);
            }
            for (int j = y; j < m; j++) {
                b[i][j - y] = a[i][q.getFirst()];
                while (!q.isEmpty() && a[i][j] >= a[i][q.getLast()])
                    q.pollLast();
                while (!q.isEmpty() && q.getFirst() <= j - y)
                    q.pollFirst();
                q.addLast(j);
            }
            b[i][m - y] = a[i][q.getFirst()];
        }

        int c[][] = new int[n][m];
        for (int j = 0; j < m; j++) {
            q = new ArrayDeque<>();
            for (int i = 0; i < x; i++) {
                while (!q.isEmpty() && b[i][j] >= b[q.getLast()][j])
                    q.pollLast();
                q.addLast(i);
            }
            for (int i = x; i < n; i++) {
                c[i - x][j] = b[q.getFirst()][j];
                while (!q.isEmpty() && b[i][j] >= b[q.getLast()][j])
                    q.pollLast();
                while (!q.isEmpty() && q.getFirst() <= i - x)
                    q.pollFirst();
                q.addLast(i);
            }
            c[n - x][j] = b[q.getFirst()][j];
        }
        return c;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new gMatrix().main1();
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
        out = new PrintWriter("A:\\out.txt");
//        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        obj = check.isEmpty() ? new FileInputStream("A:\\D-large-practice (1).in") : new ByteArrayInputStream(check.getBytes());
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
