package APAC;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class gWheels {

    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int T = ii();
        for (int tc = 1; tc <= T; tc++) {
            out.printf("Case #%d:\n", tc);

            int Np = ii(), Ne = ii(), Nt = ii();
            int a[] = iia(Np), b[] = iia(Ne), c[] = iia(Nt), size1 = 0, size2 = 0;
            Ratio PT[] = new Ratio[Np * Nt];
            Ratio EE[] = new Ratio[Ne * Ne];
            for (int i = 0; i < Np; i++) {
                for (int j = 0; j < Nt; j++) {
                    PT[size1++] = new Ratio(a[i], c[j]);
                }
            }
            for (int i = 0; i < Ne; i++) {
                for (int j = 0; j < Ne; j++) {
                    if (i == j) continue;
                    EE[size2++] = new Ratio(b[i], b[j]);
                }
            }
            for (int M = ii(); M > 0; M--) {
                long p = ii(), q = ii(), g = gcd(p, q);
                p /= g;
                q /= g;
                HashSet<Ratio> map = new HashSet<>();
                for (int i = 0; i < size1; i++) {
                    long x = PT[i].x, y = PT[i].y;
                    Ratio r = new Ratio(q * x, y);
                    map.add(r);
                }

                boolean found = false;
                for (int i = 0; i < size2; i++) {
                    long x = EE[i].x, y = EE[i].y;
                    Ratio r = new Ratio(p * x, y);
                    if (map.contains(r)) {
                        out.println("Yes");
                        found = true;
                        break;
                    }
                }
                if (!found) out.println("No");
            }
        }
    }

    long gcd(long a, long b) {
        while (b > 0) {
            long c = a;
            a = b;
            b = c % b;
        }
        return a;
    }

    class Ratio {
        long x, y;

        Ratio(long a, long b) {
            long gcd = gcd(a, b);
            x = a / gcd;
            y = b / gcd;
        }

        @Override
        public boolean equals(Object o) {
            Ratio obj = (Ratio) o;
            if (obj.x == x && obj.y == y)
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            return new Long(x).hashCode() * 31 + new Long(y).hashCode();
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
                    new gWheels().main1();
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
        obj = check.isEmpty() ? new FileInputStream("A:\\B-large-practice.in") : new ByteArrayInputStream(check.getBytes());
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
