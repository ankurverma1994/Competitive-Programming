package SPOJ;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

class FASTFLOW {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), m = ii();
        List<Edge> es = new ArrayList<>();
        int src = 1, sink = n - 1;
        for (int i = 0; i < m; i++) {
            int u = ii() - 1, v = ii() - 1, c = ii();
            if (u == v) continue;
            es.add(new Edge(u, v, c));
        }
        out.println(maximumFlowDinicNoRec(compileWU(n, es), src, sink));
    }

    public static class Edge {
        public int from, to;
        public int capacity;
        public int flow;
        public Edge complement;
        // public int iniflow;

        public Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
        }
    }

    public static Edge[][] compileWD(int n, List<Edge> edges) {
        Edge[][] g = new Edge[n][];
        // cloning
        for (int i = edges.size() - 1; i >= 0; i--) {
            Edge origin = edges.get(i);
            Edge clone = new Edge(origin.to, origin.from, origin.capacity);
            clone.flow = origin.capacity;
            clone.complement = origin;
            origin.complement = clone;
            edges.add(clone);
        }

        int[] p = new int[n];
        for (Edge e : edges) p[e.from]++;
        for (int i = 0; i < n; i++) g[i] = new Edge[p[i]];
        for (Edge e : edges) g[e.from][--p[e.from]] = e;
        return g;
    }

    public static Edge[][] compileWU(int n, List<Edge> edges) {
        Edge[][] g = new Edge[n][];
        // cloning
        for (int i = edges.size() - 1; i >= 0; i--) {
            Edge origin = edges.get(i);
            Edge clone = new Edge(origin.to, origin.from, origin.capacity * 2);
            origin.flow = origin.capacity;
            clone.flow = origin.capacity;
            clone.complement = origin;
            origin.complement = clone;
            origin.capacity *= 2;
            edges.add(clone);
        }

        int[] p = new int[n];
        for (Edge e : edges) p[e.from]++;
        for (int i = 0; i < n; i++) g[i] = new Edge[p[i]];
        for (Edge e : edges) g[e.from][--p[e.from]] = e;
        return g;
    }

    public static long maximumFlowDinic(Edge[][] g, int source, int sink) {
        int n = g.length;
        int[] d = new int[n];
        int[] q = new int[n];
        long ret = 0;
        while (true) {
            Arrays.fill(d, -1);
            q[0] = source;
            int r = 1;
            d[source] = 0;

            for (int v = 0; v < r; v++) {
                int cur = q[v];
                for (Edge ne : g[cur]) {
                    if (d[ne.to] == -1 && ne.capacity - ne.flow > 0) {
                        q[r++] = ne.to;
                        d[ne.to] = d[cur] + 1;
                    }
                }
            }
            if (d[sink] == -1) break;
            int[] sp = new int[n];
            for (int i = 0; i < n; i++) sp[i] = g[i].length - 1;
            ret += dfsDinic(d, g, sp, source, sink, Long.MAX_VALUE);
        }

        return ret;
    }

    private static long dfsDinic(int[] d, Edge[][] g, int[] sp, int cur, int sink, long min) {
        if (cur == sink) return min;
        long left = min;
        for (int i = sp[cur]; i >= 0; i--) {
            Edge ne = g[cur][i];
            if (d[ne.to] == d[cur] + 1 && ne.capacity - ne.flow > 0) {
                long fl = dfsDinic(d, g, sp, ne.to, sink, Math.min(left, ne.capacity - ne.flow));
                if (fl > 0) {
                    left -= fl;
                    ne.flow += fl;
                    ne.complement.flow -= fl;
                    if (left == 0) {
                        sp[cur] = i;
                        return min;
                    }
                }
            }
        }
        sp[cur] = -1;
        return min - left;
    }

    public static long maximumFlowDinicNoRec(Edge[][] g, int source, int sink) {
        int n = g.length;
        int[] d = new int[n]; // distance
        int[] q = new int[n]; // queue for BFS
        long ret = 0;
        int[] stack = new int[n];
        long[] lefts = new long[n]; // left to flow
        long[] parflow = new long[n];
        int[] probe = new int[n]; // search pointer
        while (true) {
            // BFS
            Arrays.fill(d, -1);
            q[0] = source;
            int r = 1;
            d[source] = 0;
            for (int v = 0; v < r; v++) {
                int cur = q[v];
                for (Edge ne : g[cur]) {
                    if (d[ne.to] == -1 && ne.capacity - ne.flow > 0) {
                        q[r++] = ne.to;
                        d[ne.to] = d[cur] + 1;
                    }
                }
            }
            if (d[sink] == -1) break;

            // DFS
            for (int i = 0; i < n; i++) probe[i] = g[i].length;
            int sp = 0;
            stack[sp] = source;
            lefts[sp] = Long.MAX_VALUE;
            parflow[sp] = 0;
            sp++;
            long delta = 0;
            boolean down = true;
            while (sp > 0) {
                int cur = stack[sp - 1];
                long fl = lefts[sp - 1];
                if (cur == sink) {
                    delta += fl;
                    parflow[sp - 1] = fl;
                    sp--;
                    down = false;
                    continue;
                }
                if (!down && parflow[sp] > 0) {
                    lefts[sp - 1] -= parflow[sp];
                    fl = lefts[sp - 1];
                    Edge ne = g[cur][probe[cur]];
                    ne.flow += parflow[sp];
                    ne.complement.flow -= parflow[sp];
                    parflow[sp - 1] += parflow[sp];
                }
                if (fl > 0 && probe[cur] > 0) {
                    down = true;
                    Edge ne = g[cur][--probe[cur]];
                    if (d[ne.to] == d[cur] + 1 && ne.capacity - ne.flow > 0) {
                        lefts[sp] = Math.min(lefts[sp - 1], ne.capacity - ne.flow);
                        stack[sp] = ne.to;
                        parflow[sp] = 0;
                        sp++;
                    }
                } else {
                    down = false;
                    sp--;
                }
            }
            ret += delta;
        }
        return ret;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new FASTFLOW().main1();
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
