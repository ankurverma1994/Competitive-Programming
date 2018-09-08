package FEB16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

class INTERVAL {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
//        map = new HashMap<>();
//        map = new long[200 + 10][300000 + 1];
        for (int tc = ii(); tc > 0; tc--) {
            n = ii();
            m = ii();
            map = new long[m + 10][n + 1];
            for (int i = 0; i <= m + 9; i++)
                for (int j = 0; j <= n; j++)
                    map[i][j] = -1;
            a = iia(n);
            b = iia(m);
            cum = new long[n];
            cum[0] = a[0];
            for (int i = 1; i < n; i++) cum[i] += (cum[i - 1] + a[i]);
            int len = b[m - 1];
            int N = n - len + 1;
            long A[] = new long[N];
            for (int i = 0; i < len; i++) A[0] += a[i];
            for (int i = 1; i < N; i++) A[i] = A[i - 1] - a[i - 1] + a[len + i - 1];
            segmentTree = new SegmentTree(A, N);
            out.println(solve(-1, n, 0));
        }
    }

    int n, m, a[], b[];
    long cum[];
    long map[][];
    SegmentTree segmentTree;

    long sum(int l, int r) {
        if (l == 0) return cum[r];
        return cum[r] - cum[l - 1];
    }

    long solve(int l, int r, int turn) {
        if (turn == m - 1) {
            long ans = segmentTree.query(l + 1, r - b[turn]);
            if (turn % 2 == 1) ans *= -1;
            return ans;
        }
        if (l != -1 && map[turn][l] != -1)
            return map[turn][l];
        long ans = 0;
        int len = b[turn];
        if ((turn & 1) == 0) {
            for (int i = l + 1; i + len - 1 < r; i++)
                ans = Math.max(ans, solve(i, i + len - 1, turn + 1) + sum(i, i + len - 1));
        } else {
            ans = inf;
            for (int i = l + 1; i + len - 1 < r; i++)
                ans = Math.min(ans, solve(i, i + len - 1, turn + 1) - sum(i, i + len - 1));
        }
        if (l != -1)
            return map[turn][l] = ans;
        return ans;
    }

    class SegmentTree {
        long tree[];
        long a[];
        int n;

        long operation(long a, long b) {
            return Math.max(a, b);
        }

        SegmentTree(long a[], int n) {
            tree = new long[4 * n];
            this.a = new long[n];
            this.n = n;
            for (int i = 0; i < n; i++)
                this.a[i] = a[i];
            build(0, n - 1, 0);
        }

        //  TODO check this function
        void build(int s, int e, int c) {
            if (s == e) {
                tree[c] = a[s];
                return;
            }
            int m = (s + e) >> 1;
            build(s, m, 2 * c + 1);
            build(m + 1, e, 2 * c + 2);
            tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
        }

        long query(int l, int r) {
            return get(0, n - 1, 0, l, r);
        }

        long get(int s, int e, int c, int l, int r) {
            if (s == l && e == r) return tree[c];
            int m = (s + e) >> 1;
            if (r <= m) return get(s, m, 2 * c + 1, l, r);
            if (l > m) return get(m + 1, e, 2 * c + 2, l, r);
            return operation(get(s, m, 2 * c + 1, l, m), get(m + 1, e, 2 * c + 2, m + 1, r));
        }
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {

        new INTERVAL().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//         obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/input.txt") : new ByteArrayInputStream(check.getBytes());
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
 