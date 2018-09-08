package IPC15P1A;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class Swap {
    //------------> Solution starts here!!
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            long x = il(), m = il();
            if (m % 2 == 0) {
                long ans;
                if (m == 0) {
                    ans = x;
//                    out.println(ans);
                    out.println(ans == 0 ? "BOB" : "ALICE");
                    continue;
                } else if (x % 2 == 0) {
                    ans = getXorEven(x, x + m - 2);
//                    out.println(ans);
                } else
                    ans = getXorOdd(x, x + m - 2);
                if ((x + m) % 2 == 0) {
                    ans = ans ^ getXorEven(x + m, x + 2 * m - 2);
//                    out.println(ans);
                } else
                    ans = ans ^ getXorOdd(x + m, x + 2 * m - 2);
//                out.println(ans);
                out.println(ans == 0 ? "BOB" : "ALICE");
                continue;
            } else {
                long ans = getXorCons(x + m - 1, x + 2 * m - 2);
                if (m == 1) {
//                    out.println(ans);
                    out.println(ans == 0 ? "BOB" : "ALICE");
                    continue;
                }
                if (x % 2 == 0)
                    ans = ans ^ getXorEven(x, x + m - 3);
                else
                    ans = ans ^ getXorOdd(x, x + m - 3);
                if ((x + m) % 2 == 0)
                    ans = ans ^ getXorEven(x + m, x + 2 * m - 3);
                else
                    ans = ans ^ getXorOdd(x + m, x + 2 * m - 3);
//                out.println(ans);
                out.println(ans == 0 ? "BOB" : "ALICE");
            }
        }
    }

    long getXorOdd(long a, long b) {
        if(a<0 && b<0)
            return 0;
        if (a >= b)
            return a;
        if (a == 0 || a == 1)
            return fff(b);
        return fff(b) ^ fff(a - 1);
    }

    long fff(long a) {
        long res[] = {0, a, a, 2, 2, a + 2, a + 2, 0};
            return res[(int) (a % 8)];
    }

    long getXorEven(long a, long b) {
        if(a<0 && b<0)
            return 0;
        if (a >= b)
            return a;
        if (a == 0 || a == 1)
            return ff(b);
        return (ff(b) ^ ff(a - 1));
    }

    long ff(long a) {
        long res[] = {a, a, 2, 2, a + 2, a + 2, 0, 0};
            return res[(int) (a % 8)];
    }

    long f(long a) {
        long res[] = {a, 1, a + 1, 0};
            return res[(int) (a % 4)];
    }

    long getXorCons(long a, long b) {
        if(a<0 && b<0)
            return 0;
        if (a >= b)
            return a;
        if (a == 0)
            return f(b);
        return f(b) ^ f(a - 1);
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Swap().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        // obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/d001951c-2-input-d001891.txt") : new ByteArrayInputStream(check.getBytes());
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
