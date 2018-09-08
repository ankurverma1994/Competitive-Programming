//package CodeJam2016;
///**
// * Created by ankurverma1994
// */
//
//import java.io.*;
//import java.util.*;
//import java.math.*;
//
//class BFFs {
//    final int mod = (int) 1e9 + 7;
//    final double eps = 1e-6;
//    final double pi = Math.PI;
//
//    //------------> Solution starts here!!
//    @SuppressWarnings("Main Logic")
//    void solve() {
//        for (int tc = ii(); tc > 0; tc--) {
//            n = ii();
//            nbits = 1 << n;
//            d = new int[n][n];
//            for (int i = 0; i < n; i++)
//                for (int j = 0; j < n; j++) {
//                    d[i][j] = -1;
//                }
//            m = new int[n][n];
//            p = new int[n][n];
//            for (int i = 0; i < n; i++) {
//                int x = ii() - 1;
//                d[i][x] = 1;
//            }
//        }
//    }
//
//    int d[][], m[][], p[][], n, nbits;
//
//    int subsolve(int b, unsigned visited) {
//        if (visited == (1 << b)) {
//        /* A single vertex */
//            p[b][visited] = -1;
//            return 0;
//        }
//
//        if (m[b][visited] == -2) {
//        /* Haven't solved this subproblem yet */
//            int best = -1, bestPred = -1;
//            unsigned i;
//            for (i = 0; i < N; ++i) {
//                if (i != b && ((visited >> i) & 1) && d[i][b] != -1) {
//                    int x = subsolve(i, visited & ~(1 << b));
//                    if (x != -1) {
//                        x += d[i][b];
//                        if (x > best) {
//                            best = x;
//                            bestPred = i;
//                        }
//                    }
//                }
//            }
//
//            m[b][visited] = best;
//            p[b][visited] = bestPred;
//        }
//
//        return m[b][visited];
//    }
//
//    /* Maximum path length for d[][].
//       n must be <= N.
//       *last will contain the last vertex in the path; use p[][] to trace back. */
//    int solve(int n, int*last) {
//        int b, i;
//        int best = -1;
//
//    /* Need to blank the DP and predecessor matrices */
//        for (b = 0; b < this.n; ++b) {
//            for (i = 0; i < nbits; ++i) {
//                m[b][i] = -2;
//                p[b][i] = -2;
//            }
//        }
//
//        for (b = 0; b < n; ++b) {
//            int x = subsolve(b, (1 << n) - 1);
//            if (x > best) {
//                best = x;
//                *last = b;
//            }
//        }
//
//        return best;
//    }
//
//    //------------> Solution ends here!!
//    InputStream obj;
//    PrintWriter out;
//    String check = "";
//
//    public static void main(String[] args) throws IOException {
//        new Thread(null, new Runnable() {
//            public void run() {
//                try {
//                    new BFFs().main1();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (StackOverflowError e) {
//                    System.out.println("RTE");
//                }
//            }
//        }, "1", 1 << 26).start();
//    }
//
//    void main1() throws IOException {
//        out = new PrintWriter(System.out);
//        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        // obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/input.txt") : new ByteArrayInputStream(check.getBytes());
//        solve();
//        out.flush();
//        out.close();
//    }
//
//    byte inbuffer[] = new byte[1024];
//    int lenbuffer = 0, ptrbuffer = 0;
//
//    int readByte() {
//        if (lenbuffer == -1) throw new InputMismatchException();
//        if (ptrbuffer >= lenbuffer) {
//            ptrbuffer = 0;
//            try {
//                lenbuffer = obj.read(inbuffer);
//            } catch (IOException e) {
//                throw new InputMismatchException();
//            }
//        }
//        if (lenbuffer <= 0) return -1;
//        return inbuffer[ptrbuffer++];
//    }
//
//    String is() {
//        int b = skip();
//        StringBuilder sb = new StringBuilder();
//        while (!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
//        {
//            sb.appendCodePoint(b);
//            b = readByte();
//        }
//        return sb.toString();
//    }
//
//    int ii() {
//        int num = 0, b;
//        boolean minus = false;
//        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
//        if (b == '-') {
//            minus = true;
//            b = readByte();
//        }
//        while (true) {
//            if (b >= '0' && b <= '9') {
//                num = num * 10 + (b - '0');
//            } else {
//                return minus ? -num : num;
//            }
//            b = readByte();
//        }
//    }
//
//    long il() {
//        long num = 0;
//        int b;
//        boolean minus = false;
//        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
//        if (b == '-') {
//            minus = true;
//            b = readByte();
//        }
//        while (true) {
//            if (b >= '0' && b <= '9') {
//                num = num * 10 + (b - '0');
//            } else {
//                return minus ? -num : num;
//            }
//            b = readByte();
//        }
//    }
//
//    boolean isSpaceChar(int c) {
//        return (!(c >= 33 && c <= 126));
//    }
//
//    int skip() {
//        int b;
//        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
//        return b;
//    }
//
//    float nf() {
//        return Float.parseFloat(is());
//    }
//
//    double id() {
//        return Double.parseDouble(is());
//    }
//
//    char ic() {
//        return (char) skip();
//    }
//
//    int[] iia(int n) {
//        int a[] = new int[n];
//        for (int i = 0; i < n; i++) a[i] = ii();
//        return a;
//    }
//
//    long[] ila(int n) {
//        long a[] = new long[n];
//        for (int i = 0; i < n; i++) a[i] = il();
//        return a;
//    }
//
//    String[] isa(int n) {
//        String a[] = new String[n];
//        for (int i = 0; i < n; i++) a[i] = is();
//        return a;
//    }
//
//    double[][] idm(int n, int m) {
//        double a[][] = new double[n][m];
//        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = id();
//        return a;
//    }
//
//    int[][] iim(int n, int m) {
//        int a[][] = new int[n][m];
//        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = ii();
//        return a;
//    }
//}
