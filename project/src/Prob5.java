/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;
import java.math.*;

class Prob5 {
    final int inf = Integer.MAX_VALUE;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = 1; tc > 0; tc--) {
            out.println(maxHappy(new int[]{8, 18, 14, 10, 7, 16, 4, 19, 6, 12, 17, 10, 12, 3, 15, 8, 15, 12},
                    new int[]{0, 15, 1, 5, 7, 3, 17, 4, 15, 3, 13, 14, 4, 7},
                    new int[]{8, 10, 16, 13, 2, 10, 2, 10, 11, 13, 0, 9, 3, 6},
                    7));
        }
    }

    public int maxHappy(int[] x, int[] a, int[] b, int K) {
        int n = x.length, m = a.length;
        boolean used[] = new boolean[n];
        int ans = 0;
        for (int k = 0; k < K; k++) {
            int score[] = new int[m - k];
            int j = 0;
            for (int i = 0; i < m; i++) {
                if (used[a[i]] && used[b[i]])
                    continue;
                if (!used[a[i]]) score[j] += x[a[i]];
                if (!used[b[i]]) score[j] += x[b[i]];
                j++;
            }
//            out.println(Arrays.toString(score));
            Arrays.sort(score);
            int max = score[m - k - 1];
            ans += max;
            for (int i = 0; i < m; i++) {
                if (used[a[i]] && used[b[i]])
                    continue;
                int val = 0;
                if (!used[a[i]]) val += x[a[i]];
                if (!used[b[i]]) val += x[b[i]];

                if (val == max) {
                    used[b[i]] = used[a[i]] = true;
                    break;
                }
            }
        }
        return ans;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Prob5().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
//        out = new PrintWriter("A:\\fuck1.txt");
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
