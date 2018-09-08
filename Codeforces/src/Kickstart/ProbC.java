package Kickstart;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbC {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final int inf = Integer.MAX_VALUE / 3;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int test = ii();
        for (int tc = 1; tc <= test; tc++) {
            out.printf("Case #%d: ", tc);
            int n = ii();
            int x[] = new int[n];
            int y[] = new int[n];
            int z[] = new int[n];
            int r[] = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = ii();
                y[i] = ii();
                z[i] = ii();
                r[i] = ii();
            }
            int ans = inf;
            for (int counter = 0; counter < (1 << n); counter++) {
                int maxX = -inf, maxY = -inf, maxZ = -inf;
                int minX = inf, minY = inf, minZ = inf;
                int maxX1 = -inf, maxY1 = -inf, maxZ1 = -inf;
                int minX1 = inf, minY1 = inf, minZ1 = inf;
                boolean one = false, two = false;
                for (int j = 0; j < n; j++) {
                    if ((counter & (1 << j)) > 0) {
                        // include c[j]
                        one = true;
                        maxX = Math.max(maxX, x[j] + r[j]);
                        maxY = Math.max(maxY, y[j] + r[j]);
                        maxZ = Math.max(maxZ, z[j] + r[j]);
                        minX = Math.min(minX, x[j] - r[j]);
                        minY = Math.min(minY, y[j] - r[j]);
                        minZ = Math.min(minZ, z[j] - r[j]);
                    } else {
                        two = true;
                        maxX1 = Math.max(maxX1, x[j] + r[j]);
                        maxY1 = Math.max(maxY1, y[j] + r[j]);
                        maxZ1 = Math.max(maxZ1, z[j] + r[j]);
                        minX1 = Math.min(minX1, x[j] - r[j]);
                        minY1 = Math.min(minY1, y[j] - r[j]);
                        minZ1 = Math.min(minZ1, z[j] - r[j]);
                    }
                }
                int cube = 0;
                if (one) {
                    cube = Math.max(cube, Math.abs(maxX - minX));
                    cube = Math.max(cube, Math.abs(maxY - minY));
                    cube = Math.max(cube, Math.abs(maxZ - minZ));
                }
                if (two) {
                    cube = Math.max(cube, Math.abs(maxX1 - minX1));
                    cube = Math.max(cube, Math.abs(maxY1 - minY1));
                    cube = Math.max(cube, Math.abs(maxZ1 - minZ1));
                }
                ans = Math.min(ans, cube);
//                out.println(cube);
            }
            out.println(ans);
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
                    new ProbC().main1();
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
//        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        obj = check.isEmpty() ? new FileInputStream("/home/ankurverma1994/Downloads/C-small-attempt0.in") : new ByteArrayInputStream(check.getBytes());
        out = new PrintWriter("/home/ankurverma1994/output.txt");
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
