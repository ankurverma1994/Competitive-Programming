package IEM;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

class BAADSHAH {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), m = ii();
        long a[] = ila(n);
        long cum[] = new long[n];
        cum[0] = a[0];
        for (int i = 1; i < n; i++) cum[i] = cum[i - 1] + a[i];
        long ft[] = buildFenwick(cum);
        long copy[] = Arrays.copyOf(a, n);
        for (int i = 0; i < m; i++) {
            int type = ii();
            if (type == 1) {
                int p = ii() - 1;
                long v = il();
                long val = v - a[p];
                a[p] = v;
                addRangeFenwick(copy, ft, p, n - 1, val);
            } else {
                long val = il();
                int index = findGFenwick(ft, n, val);
                if (index >= n || valFenwick(ft, index) != val) out.println("Not Found");
                else out.println("Found " + (index + 1));
            }
            out.println(Arrays.toString(a));
            out.println(Arrays.toString(copy));
            for (int j = 0; j < n; j++) out.print(valFenwick(ft, j) + " ");
            out.println();
        }
    }

    public static int findGFenwick(long[] ft, int n, long v) {
        int low = -1, high = n;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (valFenwick(ft, h) >= v) {
                high = h;
            } else {
                low = h;
            }
        }
        return high;
    }

    public static long sumFenwick(long[] ft, int i) {
        long sum = 0;
        for (i++; i > 0; i -= i & -i) sum += ft[i];
        return sum;
    }

    public static long valFenwick(long[] ft, int i) {
        return sumFenwick(ft, i) - sumFenwick(ft, i - 1);
    }

    public static void addFenwick(long[] ft, int i, long v) {
        if (v == 0 || i < 0) return;
        int n = ft.length;
        for (i++; i < n; i += i & -i) ft[i] += v;
    }


    public static long[] buildFenwick(long[] a) {
        int n = a.length;
        long[] ft = new long[n + 1];
        System.arraycopy(a, 0, ft, 1, n);
        for (int k = 2, h = 1; k <= n; k *= 2, h *= 2) {
            for (int i = k; i <= n; i += k) {
                ft[i] += ft[i - h];
            }
        }
        return ft;
    }

    public static void addRangeFenwick(long[] ft0, long[] ft1, int a, int b, long v) {
        if (a <= b) {
            addFenwick(ft1, b + 1, -v);
            addFenwick(ft0, b + 1, v * (b + 1));
            addFenwick(ft1, a, v);
            addFenwick(ft0, a, -v * a);
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
                    new BAADSHAH().main1();
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
