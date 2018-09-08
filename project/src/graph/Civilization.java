package graph;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

public class Civilization {

    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final int inf = Integer.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), m = ii(), q = ii();
        DJSet uf = new DJSet(n);
        int from[] = new int[m];
        int to[] = new int[m];
        int longest[] = new int[n];
        d = new int[n];
        f = new int[n];
        Arrays.fill(d, inf);
        Arrays.fill(f, inf);
        Arrays.fill(longest, inf);
        for (int i = 0; i < m; i++) {
            from[i] = ii() - 1;
            to[i] = ii() - 1;
            uf.union(from[i], to[i]);
        }
        int g[][] = packU(n, from, to, m);
        visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (d[i] == inf) {
//                out.println("Strat " + i);
                int length = bfs(i, g);
                longest[uf.root(i)] = length;
            }
        }

        for (int Q = 0; Q < q; Q++) {
            int type = ii();
            if (type == 1) {
                int x = ii() - 1;
                int root = uf.root(x);
                out.println(longest[root]);
            } else {
                int x = ii() - 1, y = ii() - 1;
                if (uf.equiv(x, y)) continue;
                int l1 = longest[uf.root(x)], l2 = longest[uf.root(y)];
                uf.union(x, y);
                int root = uf.root(x);
                longest[root] = Math.max(Math.max(l1, l2), (l1 + 1) / 2 + (l2 + 1) / 2 + 1);
            }
        }
    }

    boolean visit[];
//    ArrayList vertex;

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

    int d[], f[];

    int bfs(int v, int graph[][]) {
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(v);
        d[v] = 0;
        int far = v;
        visit[v] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            far = cur;
            for (int e : graph[cur]) {
                if (d[e] > d[cur] + 1) {
                    d[e] = d[cur] + 1;
                    q.add(e);
                    visit[e] = true;
                }
            }
        }
        f[far] = 0;
        q.add(far);
        visit[far] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            far = cur;
            for (int e : graph[cur]) {
                if (f[e] > f[cur] + 1) {
                    f[e] = f[cur] + 1;
                    q.add(e);
                    visit[e] = true;
                }
            }
        }
        return f[far];
    }

    public static class DJSet {
        public int[] upper;

        public DJSet(int n) {
            upper = new int[n];
            Arrays.fill(upper, -1);
        }

        public int root(int x) {
            return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
        }

        public boolean equiv(int x, int y) {
            return root(x) == root(y);
        }

        public boolean union(int x, int y) {
            x = root(x);
            y = root(y);
            if (x != y) {
                if (upper[y] < upper[x]) {
                    int d = x;
                    x = y;
                    y = d;
                }
                upper[x] += upper[y];
                upper[y] = x;
            }
            return x == y;
        }

        public int count() {
            int ct = 0;
            for (int u : upper)
                if (u < 0)
                    ct++;
            return ct;
        }
    }

    class ArrayList {

        private int[] myStore;
        private int actSize = 0;

        public ArrayList() {
            myStore = new int[2];
        }

        public int get(int index) {
            if (index < actSize)
                return myStore[index];
            else
                throw new ArrayIndexOutOfBoundsException();
        }

        public void add(int obj) {
            if (myStore.length - actSize <= 1)
                increaseListSize();
            myStore[actSize++] = obj;
        }

        public int size() {
            return actSize;
        }

        public void clear() {
            actSize = 0;
        }

        private void increaseListSize() {
            myStore = Arrays.copyOf(myStore, myStore.length * 2);
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Civilization().main1();
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
