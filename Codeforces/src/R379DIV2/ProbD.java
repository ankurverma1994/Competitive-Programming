package R379DIV2;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import com.sun.org.apache.bcel.internal.generic.BIPUSH;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.math.*;

public class ProbD {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), x = ii(), y = ii();
        Pair pair[] = new Pair[n];
        Pairx pairx[] = new Pairx[n];
        Pairy pairy[] = new Pairy[n];
        for (int i = 0; i < n; i++) {
            char c = ic();
            int xx = ii(), yy = ii();
            pair[i] = new Pair(xx, yy, c);
            pairx[i] = new Pairx(xx, yy, c);
            pairy[i] = new Pairy(xx, yy, c);
        }
        Arrays.sort(pairx);
        Arrays.sort(pairy);
        for (int i = 0; i < n; i++) {
            if (pair[i].c == 'B') {

            }
        }
    }

//    boolean horizontal(int xk, int yk, int i, Pairx pairx[]) {
//        if (pairx[i].y != yk) return false;
//        int lower = BinarySearchLowerBound(pairx, xk);
//        int upper = BinarySearchUpperBound(pairx, xk);
//        if (lower == pairx.length) lower--;
//        if (upper == -1) upper++;
//        if (upper > lower) return true;
//
//    }

    public int BinarySearchUpperBound(Pairx[] a, int v) {
        int low = -1, high = a.length;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h].x <= v) {
                low = h;
            } else {
                high = h;
            }
        }
        return low;
    }

    public int BinarySearchLowerBound(Pairx[] a, int v) {
        int low = -1, high = a.length;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h].x >= v) {
                high = h;
            } else {
                low = h;
            }
        }
        return high;
    }

    class Pair {
        int x, y;
        char c;

        Pair(int x, int y, char c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }

    class Pairx implements Comparable<Pairx> {
        int x, y;
        char c;

        Pairx(int x, int y, char c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(Pairx o) {
            if (x != o.x)
                return Integer.compare(x, o.x);
            return Integer.compare(y, o.y);
        }
    }

    class Pairy implements Comparable<Pairy> {
        int x, y;
        char c;

        Pairy(int x, int y, char c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(Pairy o) {
            if (y != o.y)
                return Integer.compare(y, o.y);
            return Integer.compare(x, o.x);
        }
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ProbD().main1();
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
