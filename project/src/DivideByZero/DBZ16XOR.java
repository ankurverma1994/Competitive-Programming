package DivideByZero;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class DBZ16XOR {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        n = ii();
        a = iia(n);
        pre = new int[n][m];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                pre[i][j] = (a[i] & (1 << j)) > 0 ? 1 : 0;
                if (i > 0)
                    pre[i][j] += pre[i - 1][j];
            }
        }
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                out.print(pre[i][j] + " ");
            }
            out.println();
        }

        tree = new SegmentTree(a, n);
        merge(0, n - 1);
        bruteforce();
        out.println(ans);
    }

    void bruteforce() {
        long ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i != j) {
                    ans += (tree.query(i, j) ^ a[i] ^ a[j]);
                }
            }
        }
        out.println(ans);
    }

    int a[], n, m = 20, pre[][];
    SegmentTree tree;
    long ans = 0;

    void merge(int i, int j) {
        if (i < j) {
            int mid = (i + j) >> 1;
            int am = tree.query(i, j);
//            if (j - i == 1) {
//                ans += (Math.min(a[i], a[j]));
//            } else {
            for (int z = 0; z < m; z++) {
                long l1, l0, r1, r0;
                l1 = pre[mid][z] - (i > 0 ? pre[i - 1][z] : 0);
                r1 = pre[j][z] - (mid > 0 ? pre[mid - 1][z] : 0);
                l0 = (mid - i + 1) - l1;
                r0 = (j - mid + 1) - r1;
//                out.println("i=" + i + " j=" + j + " mid=" + mid + " bit=" + z);
//                out.println(l0 + " " + l1);
//                out.println(r0 + " " + r1);
                if (i == 3 && j == 4) {
                    out.println("bit= " + z + " l1=" + l1 + " l0=" + l0 + " r1=" + r1 + " r0=" + r0);
                }

                if ((am & (1 << z)) > 0) {
                    // bit set
                    long temp = (l1 * r1 + l0 * r0 - 1);
                    if (temp > 0)
                        ans += (temp * (1L << z));
                } else {
                    long temp = (l0 * r1 + l1 * r0 - 1);
                    if (temp > 0)
                        ans += (temp * (1L << z));
                }
                out.println(ans);
//                }
            }
            out.println(i + " " + j + " " + ans);
            merge(i, mid - 1);
            merge(mid + 1, j);
        }
    }

    class SegmentTree {
        int tree[];
        int a[];
        int n;

        int operation(int a, int b) {
            return Math.max(a, b);
        }

        SegmentTree(int a[], int n) {
            tree = new int[4 * n];
            this.a = new int[n];
            this.n = n;
            for (int i = 0; i < n; i++)
                this.a[i] = a[i];
            build(0, n - 1, 0);
        }

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

        int query(int l, int r) {
            return get(0, n - 1, 0, l, r);
        }

        int get(int s, int e, int c, int l, int r) {
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
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new DBZ16XOR().main1();
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

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }
}