package SegmentTree;

import java.io.*;
import java.util.*;

class SPOJGSS2 {

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), a[] = iia(n);
        SegmentTree tree = new SegmentTree(a, n);
        StringBuilder sb = new StringBuilder();
        for (int tc = ii(); tc > 0; tc--) {
            int type = ii();
            int x = ii() - 1, y = ii() - 1;
            if (type == 1)
                sb.append(tree.query(x, y).ans + "\n");
            else {
                y++;
                tree.update(x, y);
            }
        }
        out.println(sb);
    }

    class Data {
        long sum, prefix, suffix, ans;
    }

    class SegmentTree {
        Data tree[];
        int a[];
        int n;

        Data operation(Data l, Data r) {
            Data res = new Data();
            res.sum = l.sum + r.sum;
            res.prefix = Math.max(l.prefix, l.sum + r.prefix);
            res.suffix = Math.max(r.suffix, r.sum + l.suffix);
            res.ans = Math.max(l.ans, Math.max(r.ans, l.suffix + r.prefix));
            return res;
        }

        Data makeData(int v) {
            Data res = new Data();
            res.sum = v;
//            res.prefix = res.suffix = res.ans = Math.max(0, v);
            res.prefix = res.suffix = res.ans = v;
            return res;
        }

        SegmentTree(int a[], int n) {
            tree = new Data[4 * n];
            this.a = new int[n];
            this.n = n;
            for (int i = 0; i < n; i++)
                this.a[i] = a[i];
            build(0, n - 1, 0);
        }

        void build(int s, int e, int c) {
            if (s == e) {
                tree[c] = makeData(a[s]);
                return;
            }
            int m = (s + e) >> 1;
            build(s, m, 2 * c + 1);
            build(m + 1, e, 2 * c + 2);
            tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
        }

        void update(int x, int v) {
            put(0, n - 1, 0, x, v);
        }

        void put(int s, int e, int c, int x, int v) {
            if (s == e) {
                a[s] = v;
                tree[c] = makeData(a[s]);
                return;
            }
            int m = (s + e) >> 1;
            if (x <= m) put(s, m, 2 * c + 1, x, v);
            else put(m + 1, e, 2 * c + 2, x, v);
            tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
        }

        Data query(int l, int r) {
            return get(0, n - 1, 0, l, r);
        }

        Data get(int s, int e, int c, int l, int r) {
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
                    new SPOJGSS2().main1();
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
