package Codechef.SEPT16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

/*
Ismei simply node 1 ka answer 0 hoga kyuki 1 se 1 ka distance 0 hota hai.
Ab kisi aur node par jayenge toh hmhe max 'K' length tk uske upar path mei dekhna padega ki
usmei jo min hai + apni node ka weight le kar curr node ka answer calculate kar lenge
Segment Tree se min nikal lenge efficiently :-)
 */
import java.io.*;
import java.util.*;

public class JTREE {
    final long inf = Long.MAX_VALUE / 2;
    long ans[];
    SegmentTree tree;
    ArrayList<Ticket> tick[];
    int g[][];

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), m = ii();
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = ii() - 1;
            to[i] = ii() - 1;
        }
        g = packU(n, from, to, n - 1);
        tick = new ArrayList[n];
        for (int i = 0; i < n; i++) tick[i] = new ArrayList<>();
        for (int M = 0; M < m; M++) {
            int v = ii() - 1, k = ii(), w = ii();
            tick[v].add(new Ticket(k, w));
        }
        ans = new long[n];
        tree = new SegmentTree(n);
        dfs(0, -1, 0);
        for (int Q = ii(); Q > 0; Q--) {
            int h = ii() - 1;
            out.println(ans[h]);
        }
    }

    void dfs(int curr, int par, int index) {
        long ans = inf;
        for (int j = 0; j < tick[curr].size(); j++) {
            Ticket s = tick[curr].get(j);
            ans = Math.min(ans, s.w + tree.query(Math.max(0, index - s.k), Math.max(0, index - 1)));
        }
        if (par == -1) ans = 0;
        tree.update(index, ans);
        this.ans[curr] = ans;
        for (int i = 0; i < g[curr].length; i++) {
            int next = g[curr][i];
            if (next != par)
                dfs(next, curr, index + 1);
        }
    }

    class Ticket {
        int k, w;

        Ticket(int k, int w) {
            this.w = w;
            this.k = k;
        }
    }

    class SegmentTree {
        long tree[];
        long a[];
        int n;

        long operation(long a, long b) {
            return Math.min(a, b);
        }

        SegmentTree(int n) {
            tree = new long[4 * n];
            this.a = new long[n];
            this.n = n;
            Arrays.fill(a, inf);
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

        void update(int x, long v) {
            put(0, n - 1, 0, x, v);
        }

        void put(int s, int e, int c, int x, long v) {
            if (s == e) {
                tree[c] = a[s] = v;
                return;
            }
            int m = (s + e) >> 1;
            if (x <= m) put(s, m, 2 * c + 1, x, v);
            else put(m + 1, e, 2 * c + 2, x, v);
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

    // for undirected graph, max is length of from or to array
    public static int[][] packU(int n, int[] from, int[] to, int max) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int i = 0; i < max; i++) p[from[i]]++;
        for (int i = 0; i < max; i++) p[to[i]]++;
        for (int i = 0; i < n; i++) g[i] = new int[p[i]];
        for (int i = 0; i < max; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new JTREE().main1();
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

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }
}
