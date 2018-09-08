package APAC_A;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class C {

    final int mod = (int) 1e9 + 7;
    final double eps = 1e-15;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int T = ii();
        for (int tc = 1; tc <= T; tc++) {
            out.printf("Case #%d: ", tc);
            m = ii();
            cost = iia(m + 1);
            if (value(1) >= 0 && value(-1) <= 0) {
                //increasing
//                out.println("Increasing");
                double ans = BinarySearchLowerBound();
                if (Math.abs(-eps - ans) <= eps)
                    out.println("0.000000000");
                else
                    out.printf("%.9f\n", ans);
            } else {
                double ans = BinarySearchUpperBound();
//                out.println("Upper");
                if (Math.abs(-eps - ans) <= eps)
                    out.println("0.000000000");
                else
                    out.printf("%.12f\n", ans);
            }
        }
    }

    int m, cost[];

    double value(double r) {
        double ans = -Math.pow((1 + r), m) * cost[0];
        int po = m - 1;
        for (int i = 1; i <= m; i++) {
            ans = ans + Math.pow(1 + r, po) * cost[i];
//            out.println(po);
            po--;
        }
        return ans;
    }

    public double BinarySearchLowerBound() {
        double low = -1, high = 1;
        while (high - low >= eps) {
            double h = (high + low) / 2;
//            out.println(h);
            if (value(h) > eps) {
                high = h;
            } else {
                low = h;
            }
        }
        return (low + high) / 2;
    }

    public double BinarySearchUpperBound() {
        double low = -1, high = 1;
        while (high - low >= eps) {
            double h = (high + low) / 2;
//            out.println(h+" "+value(h)+" "+(value(h)>=eps));
//            out.println(h);
            if (value(h) >= eps) {
                low = h;
            } else {
                high = h;
            }
        }
        return (low + high) / 2;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new C().main1();
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
        obj = check.isEmpty() ? new FileInputStream("A:\\C-large-practice.in") : new ByteArrayInputStream(check.getBytes());
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
