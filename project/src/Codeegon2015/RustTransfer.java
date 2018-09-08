package Codeegon2015;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class RustTransfer {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), m = ii();
            int from[] = new int[m];
            int to[] = new int[m];
            int r[] = new int[m];
            int t[] = new int[m];
            for (int i = 0; i < m; i++) {
                int x = ii() - 1, y = ii() - 1;
                from[i] = x;
                to[i] = y;
                r[i] = ii();
                t[i] = ii();
            }
            int s = ii() - 1, d = ii() - 1;
            long ans = DijisktrausingMinHeap.dijk(packWU(n, from, to, r), s, d);
            for (int i = 0; i < m; i++) {
                int recopy[] = new int[m];
                for (int j = 0; j < m; j++)
                    recopy[j] = r[j];
                recopy[i] = t[i];
                ans = Math.min(ans, DijisktrausingMinHeap.dijk(packWU(n, from, to, recopy), s, d));
            }
            out.println(ans);
        }
    }

    // for undirected weighted graph
    public static int[][][] packWU(int n, int[] from, int[] to, int[] w) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][][] g = new int[n][][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]][2];
        for (int i = 0; i < from.length; i++) {
            --p[from[i]];
            g[from[i]][p[from[i]]][0] = to[i];
            g[from[i]][p[from[i]]][1] = w[i];
        }
        return g;
    }

    static class DijisktrausingMinHeap {

        public static long dijk(int[][][] g, int from, int dest) {
        /* This part of code is picked up from "uwi" previous submission */
            int n = g.length;
            long[] td = new long[n];

            Arrays.fill(td, Long.MAX_VALUE / 2); // max
            MinHeapL q = new MinHeapL(n);
            q.add(from, 0);
            td[from] = 0;

            while (q.size() > 0) {
                int cur = q.argmin();
                q.remove(cur);

                for (int[] e : g[cur]) {
                    int next = e[0];
                    long nd = td[cur] + e[1];
                    if (nd < td[next]) {
                        td[next] = nd;
                        q.update(next, nd);
                    }
                }
            }
            return td[dest];
        }

        public static class MinHeapL {
            /* This part of code is picked up from "uwi" previous submission */
            public long[] a;
            public int[] map;
            public int[] imap;
            public int n;
            public int pos;
            public static long INF = Long.MAX_VALUE;

            public MinHeapL(int m) {
                n = Integer.highestOneBit((m + 1) << 1);
                a = new long[n];
                map = new int[n];
                imap = new int[n];
                Arrays.fill(a, INF);
                Arrays.fill(map, -1);
                Arrays.fill(imap, -1);
                pos = 1;
            }

            public long add(int ind, long x) {
                int ret = imap[ind];
                if (imap[ind] < 0) {
                    a[pos] = x;
                    map[pos] = ind;
                    imap[ind] = pos;
                    pos++;
                    up(pos - 1);
                }
                return ret != -1 ? a[ret] : x;
            }

            public long update(int ind, long x) {
                int ret = imap[ind];
                if (imap[ind] < 0) {
                    a[pos] = x;
                    map[pos] = ind;
                    imap[ind] = pos;
                    pos++;
                    up(pos - 1);
                } else {
                    a[ret] = x;
                    up(ret);
                    down(ret);
                }
                return x;
            }

            public long remove(int ind) {
                if (pos == 1) return INF;
                if (imap[ind] == -1) return INF;

                pos--;
                int rem = imap[ind];
                long ret = a[rem];
                map[rem] = map[pos];
                imap[map[pos]] = rem;
                imap[ind] = -1;
                a[rem] = a[pos];
                a[pos] = INF;
                map[pos] = -1;

                up(rem);
                down(rem);
                return ret;
            }

            public long min() {
                return a[1];
            }

            public int argmin() {
                return map[1];
            }

            public int size() {
                return pos - 1;
            }

            private void up(int cur) {
                for (int c = cur, p = c >>> 1; p >= 1 && a[p] > a[c]; c >>>= 1, p >>>= 1) {
                    long d = a[p];
                    a[p] = a[c];
                    a[c] = d;
                    int e = imap[map[p]];
                    imap[map[p]] = imap[map[c]];
                    imap[map[c]] = e;
                    e = map[p];
                    map[p] = map[c];
                    map[c] = e;
                }
            }

            private void down(int cur) {
                for (int c = cur; 2 * c < pos; ) {
                    int b = a[2 * c] < a[2 * c + 1] ? 2 * c : 2 * c + 1;
                    if (a[b] < a[c]) {
                        long d = a[c];
                        a[c] = a[b];
                        a[b] = d;
                        int e = imap[map[c]];
                        imap[map[c]] = imap[map[b]];
                        imap[map[b]] = e;
                        e = map[c];
                        map[c] = map[b];
                        map[b] = e;
                        c = b;
                    } else {
                        break;
                    }
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
                    new RustTransfer().main1();
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