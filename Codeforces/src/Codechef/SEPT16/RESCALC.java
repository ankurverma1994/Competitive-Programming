package Codechef.SEPT16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class RESCALC {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int N = ii();
            Score sc[] = new Score[N];
            for (int Q = 0; Q < N; Q++) {
                int n = ii();
                int a[] = iia(n);
                int f[] = new int[7];
                for (int i : a) f[i]++;
                int ans = n;
                while (true) {
                    int count = 0;
                    for (int i = 1; i <= 6; i++)
                        if (f[i] > 0) count++;
                    if (count < 4) break;
                    for (int i = 1; i <= 6; i++) {
                        if (f[i] > 0)
                            f[i]--;
                    }
                    if (count == 4) ans += 1;
                    if (count == 5) ans += 2;
                    if (count == 6) ans += 4;
                }
                sc[Q] = new Score(ans, Q);
            }
            Arrays.sort(sc);
            if (N == 1) out.println("chef");
            else if (sc[0].val == sc[1].val) out.println("tie");
            else out.println(sc[0].i == 0 ? "chef" : (sc[0].i + 1));
        }
    }

    class Score implements Comparable<Score> {
        int val, i;

        Score(int val, int i) {
            this.val = val;
            this.i = i;
        }

        @Override
        public int compareTo(Score o) {
            return Integer.compare(o.val, val);
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
                    new RESCALC().main1();
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
