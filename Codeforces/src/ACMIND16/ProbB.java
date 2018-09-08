package ACMIND16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

class ProbB {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
//            Random rnd = new Random();
//            int n = rnd.nextInt(3) + 1;
//            int m = rnd.nextInt(n * n) + 1;
//            int d = rnd.nextInt(n) + 1;
//            int D = rnd.nextInt(n) + 1;
//            while (D < d) {
//                D = rnd.nextInt(n) + 1;
//            }
            int n = ii(), m = ii(), d = ii(), D = ii();
            System.out.println(n + " " + m + " " + d + " " + D);
            int A[] = new int[n + 1];
            int B[] = new int[n + 1];
            ArrayList<String> ans = new ArrayList<>();
            boolean graph[][] = new boolean[n + 1][n + 1];

            // left wale ki atleast d -d
            for (int i = 1; i <= n; i++) {
                if (ans.size() >= m) break;
                for (int j = 1; j <= n; j++) {
                    if (A[i] < d && B[j] < d && !graph[i][j]) {
                        A[i]++;
                        B[j]++;
                        graph[i][j] = true;
                        ans.add(i + " " + j);
                    }
                    if (ans.size() >= m) break;
                }
            }
            for (int i = 1; i <= n; i++) {
                if (ans.size() >= m) break;
                for (int j = 1; j <= n; j++) {
                    if (B[i] < d && A[j] < d && !graph[j][i]) {
                        A[j]++;
                        B[i]++;
                        graph[j][i] = true;
                        ans.add(j + " " + i);
                    }
                    if (ans.size() >= m) break;
                }
            }

            // d-D
            for (int i = 1; i <= n; i++) {
                if (ans.size() >= m) break;
                for (int j = 1; j <= n; j++) {
                    if (A[i] < d && B[j] < D && !graph[i][j]) {
                        graph[i][j] = true;
                        A[i]++;
                        B[j]++;
                        ans.add(i + " " + j);
                    }
                    if (ans.size() >= m) break;
                }
            }

            // D-d
            for (int i = 1; i <= n; i++) {
                if (ans.size() >= m) break;
                for (int j = 1; j <= n; j++) {
                    if (A[i] < D && B[j] < d && !graph[i][j]) {
                        graph[i][j] = true;
                        A[i]++;
                        B[j]++;
                        ans.add(i + " " + j);
                    }
                    if (ans.size() >= m) break;
                }
            }

            // D-D
            for (int i = 1; i <= n; i++) {
                if (ans.size() >= m) break;
                for (int j = 1; j <= n; j++) {
                    if (A[i] < D && B[j] < D && !graph[i][j]) {
                        graph[i][j] = true;
                        A[i]++;
                        B[j]++;
                        ans.add(i + " " + j);
                    }
                    if (ans.size() >= m) break;
                }
            }

            for (int i = 1; i <= n; i++) {
                if (ans.size() >= m) break;
                for (int j = 1; j <= n; j++) {
                    if (B[i] < D && A[j] < D && !graph[j][i]) {
                        graph[j][i] = true;
                        B[i]++;
                        A[j]++;
                        ans.add(j + " " + i);
                    }
                    if (ans.size() >= m) break;
                }
            }

            boolean ANS = true;
            if (ans.size() < m) {
                ANS = false;
//                out.println("-1");
            } else {
                boolean valid = true;
                for (int i = 1; i <= n; i++) {
                    if (!(d <= A[i] && A[i] <= D)) {
                        valid = false;
                        break;
                    }
                    if (!(d <= B[i] && B[i] <= D)) {
                        valid = false;
                        break;
                    }
                }
                if (!valid) {
                    ANS = false;
//                    out.println("-1");
                } else {
//                    for (int i = 0; i < ans.size(); i++)
//                        out.println(ans.get(i));
                }
            }
            int min = n * d;
            int max = n * D;
            boolean k = m < min || m > max;
            k = !k;
            System.out.println(ANS + " " + k);
            int lower = m / n;
            int extra = m % n;

            for (int i = 0; i < n; i++) {
                int now = lower;
                if (i < extra)
                    now++;
                for (int j = i; j < i + now; j++) {
                    System.out.println((i+1) + " " + ((j%n)+1));
                }
            }
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
                    new ProbB().main1();
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
