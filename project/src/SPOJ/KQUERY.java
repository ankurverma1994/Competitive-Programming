package SPOJ;
/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;
import java.math.*;

class KQUERY {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii();
        int a[][] = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = ii();
            a[i][1] = i;
        }
        Arrays.sort(a, new Comparator<int[]>() {
            public int compare(int[] aa, int[] bb) {
                return Long.compare(bb[0], aa[0]);
            }
        });
        int m = ii();
        query b[] = new query[m];
        int ans[] = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = new query(ii() - 1, ii() - 1, ii(), i);
        }
        Arrays.sort(b);
        SegmentTree tree = new SegmentTree(n);
        int x = 0;
        for (int i = 0; i < m; i++) {
            while (x < n && a[x][0] > b[i].val) {
                tree.update(a[x][1], 1);
                x++;
            }
            ans[b[i].i] = tree.query(b[i].l, b[i].r);
        }
        for (int i = 0; i < m; i++)
            out.println(ans[i]);
    }

    class query implements Comparable<query> {
        int l, r, i, val;

        query(int l, int r, int k, int i) {
            this.l = l;
            this.r = r;
            val = k;
            this.i = i;
        }

        @Override
        public int compareTo(query o) {
            return o.val - val;
        }
    }

    class SegmentTree {
        int tree[];
        int n;

        int operation(int a, int b) {
            return a + b;
        }

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int x, int v) {
            put(0, n - 1, 0, x, v);
        }

        void put(int s, int e, int c, int x, int v) {
            if (s == e) {
                tree[c] = v;
                return;
            }
            int m = (s + e) >> 1;
            if (x <= m) put(s, m, 2 * c + 1, x, v);
            else put(m + 1, e, 2 * c + 2, x, v);
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
                    new KQUERY().main1();
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
}