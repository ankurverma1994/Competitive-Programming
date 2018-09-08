package SPOJ;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class TAXI {

    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int person = ii(), taxi = ii(), speed = ii(), time = ii();
            Points per[] = new Points[person];
            for (int i = 0; i < person; i++) {
                per[i] = new Points(ii(), ii());
            }
            Points tax[] = new Points[taxi];
            for (int i = 0; i < taxi; i++) {
                tax[i] = new Points(ii(), ii());
            }
            int from[] = new int[person + taxi];
            int to[] = new int[person + taxi];
            int cnt = 0;

            for (int i = 0; i < person; i++) {
                for (int j = 0; j < taxi; j++) {
                    if (dist(per[i], tax[j]) <= speed * time / 200) {
                        from[cnt] = i;
                        to[cnt++] = j;
                    }
                }
            }

            int g[][] = packD(person, from, to, cnt);
            out.println(doBipartiteMatchingHKNoRec(g, taxi));
        }
    }

    public static int doBipartiteMatchingHKNoRec(int[][] g, int m) {
        int n = g.length;
        if (n == 0) return 0;
        int[] from = new int[m];
        int[] to = new int[n];
        Arrays.fill(to, -1);
        Arrays.fill(from, n);

        int[] d = new int[n + 1];
        int mat = 0;
        int[] stack = new int[n + 1];
        int[] adjind = new int[n + 1];
        while (true) {
            Arrays.fill(d, -1);
            int[] q = new int[n];
            int r = 0;
            for (int i = 0; i < n; i++) {
                if (to[i] == -1) {
                    d[i] = 0;
                    q[r++] = i;
                }
            }

            for (int p = 0; p < r; p++) {
                int cur = q[p];
                for (int adj : g[cur]) {
                    int nex = from[adj];
                    if (d[nex] == -1) {
                        if (nex != n) q[r++] = nex;
                        d[nex] = d[cur] + 1;
                    }
                }
            }
            if (d[n] == -1) break;

            for (int i = 0; i < n; i++) {
                if (to[i] == -1) {
                    int sp = 1;
                    stack[0] = i;
                    adjind[0] = 0;
                    boolean prevB = false;
                    outer:
                    while (sp >= 1) {
                        int cur = stack[sp - 1];
                        if (cur == n) {
                            prevB = true;
                            sp--;
                            continue;
                        }
                        for (; adjind[sp - 1] < 2 * g[cur].length; ) {
                            int adj = g[cur][adjind[sp - 1] / 2];
                            if (adjind[sp - 1] % 2 == 0) {
                                int nex = from[adj];
                                if (d[nex] == d[cur] + 1) {
                                    stack[sp] = nex;
                                    adjind[sp] = 0;
                                    adjind[sp - 1]++;
                                    sp++;
                                    continue outer;
                                } else {
                                    adjind[sp - 1] += 2;
                                }
                            } else {
                                if (prevB) {
                                    to[cur] = adj;
                                    from[adj] = cur;
                                    prevB = true;
                                    sp--;
                                    continue outer;
                                }
                                adjind[sp - 1]++;
                            }
                        }
                        d[cur] = -1;
                        prevB = false;
                        sp--;
                    }
                    if (prevB) mat++;
                }
            }
        }

        return mat;
    }

    // for directed graph, max is length of from or to array
    static int[][] packD(int n, int[] from, int[] to, int max) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int i = 0; i < max; i++) p[from[i]]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < max; i++) {
            g[from[i]][--p[from[i]]] = to[i];
        }
        return g;
    }

    int dist(Points a, Points b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    class Points {
        int x, y;

        Points(int a, int b) {
            x = a;
            y = b;
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
                    new TAXI().main1();
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
