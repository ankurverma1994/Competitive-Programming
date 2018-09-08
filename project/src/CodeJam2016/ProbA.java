package CodeJam2016;
/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;
import java.math.*;

class ProbA {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int test = ii();
        for (int tc = 1; tc <= test; tc++) {
            out.printf("Case #%d: ", tc);
            int n = ii();
            int a[][] = new int[n][2];
            for (int i = 0; i < n; i++) {
                a[i][0] = ii();
                a[i][1] = i;
            }
            Arrays.sort(a, (aa, bb) -> Integer.compare(aa[0], bb[0]));
            while (true) {
                int i = n - 1;
//                out.println();
//                for (int j = 0; j < n; j++)
//                    out.println(a[j][0]);
                if (a[i][0] == 1)
                    break;
                while (a[i][0] != a[i - 1][0]) {
                    out.print((char) (a[i][1] + 'A') + " ");
                    a[i][0]--;
                }
                Arrays.sort(a, (aa, bb) -> Integer.compare(aa[0], bb[0]));
                if (a[n - 1][0] == 1) break;
                if (a[n - 1][0] == a[n - 2][0])
                    out.print((char) (a[i][1] + 'A') + "" + (char) (a[i - 1][1] + 'A') + " ");
                a[i][0]--;
                a[i - 1][0]--;
                Arrays.sort(a, (aa, bb) -> Integer.compare(aa[0], bb[0]));
                if (a[n - 1][0] == 1) break;
            }
            int count = 0;
            for (int i = n - 1; i >= 0; i--)
                if (a[i][0] == 1) count++;
            if (count % 2 == 1) {
                out.print((char) (a[n - 1][1] + 'A') + " ");
                for (int i = n - 2; i >= 1; i -= 2) {
                    if (a[i - 1][0] == 0) break;
                    out.print((char) (a[i][1] + 'A') + "" + (char) (a[i - 1][1] + 'A')+" ");
                }
            }
            else {
                for (int i = n - 1; i >= 1; i -= 2) {
                    if (a[i - 1][0] == 0) break;
                    out.print((char) (a[i][1] + 'A') + "" + (char) (a[i - 1][1] + 'A')+" ");
                }
            }
            out.println();
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
//        out = new PrintWriter(System.out);
        out=new PrintWriter("/home/ankurverma1994/tmp/out.tx");
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
         obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/tmp/input.in") : new ByteArrayInputStream(check.getBytes());
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
