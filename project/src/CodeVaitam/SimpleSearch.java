package CodeVaitam;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class SimpleSearch {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-8;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), q = ii();
            double a[] = new double[n];
            for (int i = 0; i < n; i++) a[i] = id();
            Arrays.sort(a);
            for (int Q = 0; Q < q; Q++) {
                double v = id();
                int i = BinarySearchUpperBound(a, v);
                out.println(n - i - 1);
            }
        }
    }

    int BinarySearchUpperBound(double[] a, double v) {
        int low = -1, high = a.length;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h] < v || Math.abs(a[h] - v) < eps) {
                low = h;
            } else {
                high = h;
            }
        }
        return low;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new SimpleSearch().main1();
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

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    double id() {
        return Double.parseDouble(is());
    }
}