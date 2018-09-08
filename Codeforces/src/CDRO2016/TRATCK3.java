package CDRO2016;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class TRATCK3 {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), m = ii();
            int a[] = new int[n];
            Arrays.fill(a, (int) 1e9);
            LazySegmentTree st = new LazySegmentTree(a, n);
            for (int q = 0; q < m; q++) {
                int type = ii();
                if (type == 0) {
                    int l = ii() - 1, r = ii() - 1;
                    out.println(Math.max(st.query(l, r), 0));
                } else {
                    int x = ii(), l = ii() - 1, r = ii() - 1;
                    st.update(l, r, -x);
                }
            }
        }
    }


    class LazySegmentTree {
        long tree[], lazy[];
        int a[];
        int n;

        long operation(long a, long b) {
            return Math.max(a,b);
        }

        public LazySegmentTree(int a[], int n) {
            tree = new long[4 * n];
            lazy = new long[4 * n];
            this.a = new int[n];
            this.n = n;
            for (int i = 0; i < n; i++)
                this.a[i] = a[i];
            build(0, n - 1, 0);
        }

        public void build(int s, int e, int c) {
            if (s == e) {
                tree[c] = a[s];
                return;
            }
            int m = (s + e) >> 1;
            build(s, m, 2 * c + 1);
            build(m + 1, e, 2 * c + 2);
            tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
        }

        void push(int node, int start, int end) {
            if (lazy[node] != 0) {
                // This node needs to be updated
                // TODO for sum, change according to operation
                tree[node] += lazy[node];    // Update it
                if (start != end) {
                    lazy[node * 2 + 1] += lazy[node];                  // Mark child as lazy
                    lazy[node * 2 + 2] += lazy[node];                // Mark child as lazy
                }
                lazy[node] = 0;                                  // Reset it
            }
        }

        // a[x] += v
        void update(int l, int r, long val) {
            updateRange(0, 0, n - 1, l, r, val);
        }

        long query(int l, int r) {
            return queryRange(0, 0, n - 1, l, r);
        }

        void updateRange(int node, int start, int end, int l, int r, long val) {
            push(node, start, end);
            if (start > end || start > r || end < l)              // Current segment is not within range [l, r]
                return;
            if (start >= l && end <= r) {
                // Segment is fully within range
                // TODO for sum, change according to operation
                tree[node] += val;
                if (start != end) {
                    // Not leaf node
                    lazy[node * 2 + 1] += val;
                    lazy[node * 2 + 2] += val;
                }
                return;
            }
            int mid = (start + end) / 2;
            updateRange(node * 2 + 1, start, mid, l, r, val);        // Updating left child
            updateRange(node * 2 + 2, mid + 1, end, l, r, val);   // Updating right child
            tree[node] = operation(tree[node * 2 + 1], tree[node * 2 + 2]);        // Updating root with max value
        }

        long queryRange(int node, int start, int end, int l, int r) {
            if (start > end || start > r || end < l)
                return 0;         // Out of range
            push(node, start, end);
            if (start >= l && end <= r)             // Current segment is totally within range [l, r]
                return tree[node];
            int mid = (start + end) / 2;
            long p1 = queryRange(node * 2 + 1, start, mid, l, r);         // Query left child
            long p2 = queryRange(node * 2 + 2, mid + 1, end, l, r); // Query right child
            return operation(p1, p2);
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
                    new TRATCK3().main1();
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
