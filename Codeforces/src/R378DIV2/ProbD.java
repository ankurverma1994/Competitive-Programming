//package R378DIV2;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
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
        int n = ii(), max = 0;
        int ans[] = new int[2];
        ans[0] = -1;
        ans[1] = -1;
        Block B[] = new Block[3 * n];
        for (int i = 0, j = 0; j < n; i += 3, j++) {
            int a = ii(), b = ii(), c = ii();
            int dia = Math.min(a, Math.min(b, c));
            if (max < dia) {
                max = dia;
                ans[0] = j + 1;
            }
            B[i + 0] = new Block(Math.min(a, b), Math.max(a, b), c, j);
            B[i + 1] = new Block(Math.min(c, b), Math.max(c, b), a, j);
            B[i + 2] = new Block(Math.min(a, c), Math.max(a, c), b, j);
        }
        Arrays.sort(B);
        for (int i = 0; i < 3 * n; i++) {
            int newI = i + 1;
            for (int j = i + 1; j < 3 * n; j++) {
                newI = j;
                if (B[i].a == B[j].a && B[i].b == B[j].b) {
                    if (B[i].index != B[j].index) {
                        int dia = Math.min(B[i].a, Math.min(B[i].b, B[i].c + B[j].c));
                        if (dia > max) {
                            max = dia;
                            ans[0] = B[i].index + 1;
                            ans[1] = B[j].index + 1;
                        }
                    }
                } else {
                    break;
                }
            }
            i = newI - 1;
        }
        if (ans[1] == -1) {
            out.println("1");
            out.println(ans[0]);
        } else {
            out.println("2");
            out.println(ans[0] + " " + ans[1]);
        }
    }

    class Block implements Comparable<Block> {
        int a, b, c, index;

        Block(int a, int b, int c, int index) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.index = index;
        }

        @Override
        public int compareTo(Block o) {
            if (o.a != a)
                return Integer.compare(a, o.a);
            return Integer.compare(b, o.b);
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
