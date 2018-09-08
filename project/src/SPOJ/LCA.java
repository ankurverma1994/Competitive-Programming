package SPOJ;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class LCA {

    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int Tc = ii();
        for (int tc = 1; tc <= Tc; tc++) {
            out.printf("Case %d:\n", tc);
            int n = ii();
            int g[][] = new int[n][];
            for (int i = 0; i < n; i++) {
                int m = ii();
                g[i] = new int[m];
                for (int j = 0; j < m; j++)
                    g[i][j] = ii() - 1;
            }
            P = new int[n];
            L = new int[n];
            T = new int[n];
            dfs1(g, 0, 0);
            int sqrtN = (int) Math.ceil(Math.floor(n));
            dfs2(0, sqrtN, g);
            int q = ii();
            for (int Q = 0; Q < q; Q++) {
                int x = ii() - 1, y = ii() - 1;
                out.println(lca(x, y) + 1);
            }
        }
    }

    // O(N) precomputation
    // O(sqrt(H)) query.. H--> max height of tree
    // nr = sqrt(N), N--> no. of nodes in tree
    // P--> uss substree ka root find karega
    // T--> father/parent of node precompute
    //root --> 1
    // L --> level of tree precompute
    int P[], L[], T[];

    void dfs1(int g[][], int node, int level) {
        L[node] = level;
        for (int i = 0; i < g[node].length; i++) {
            int v = g[node][i];
            if (v != node) {
                T[v] = node;
                dfs1(g, v, level + 1);
            }
        }
    }

    void dfs2(int node, int nr, int g[][]) {

        if (L[node] < nr)
            P[node] = 1;
        else if (L[node] % nr == 0)
            P[node] = T[node];
        else
            P[node] = P[T[node]];
        for (int i = 0; i < g[node].length; i++) {
            int v = g[node][i];
            if (v != node)
                dfs2(v, nr, g);
        }
    }

    int lca(int x, int y) {
        while (P[x] != P[y]) {
            if (L[x] > L[y])
                x = P[x];
            else
                y = P[y];
        }
        while (x != y) {
            if (L[x] > L[y])
                x = T[x];
            else
                y = T[y];
        }
        return x;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new LCA().main1();
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
