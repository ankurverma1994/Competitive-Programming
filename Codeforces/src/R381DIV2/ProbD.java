package R381DIV2;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbD {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        n = ii();
        a = iia(n);
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        int w[] = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = ii() - 1;
            to[i] = i + 1;
            w[i] = ii();
        }
        g = packWD(n, from, to, w);
        dis = new long[n];
        ans = new int[n];
        node = new int[n];
        par = new int[n];
        Arrays.fill(par, -1);
        dfs(0, 0, 0);
//        out.println(Arrays.toString(ans));
        dfs2(0);
        for (int x : ans) out.print(x + " ");
    }

    int g[][][];
    int n, a[], par[];
    int ans[], node[];
    long dis[];

    void dfs(int curr, long distance, int index) {
        dis[index] = distance;
        node[index] = curr;
        int pos = Arrays.binarySearch(dis, 0, index + 1, dis[index] - a[curr]);
//        out.println(pos + " " + (dis[index] - a[curr]));
//        if (pos >= 0) pos++;
        if (pos < 0) pos = -pos - 1;
        if (pos < index) {
            // update
//            out.println("fucking");
//            ans[node[pos]]++;
//            ans[curr]--;
            if (curr != 0) ans[par[curr]]++;
//            System.out.println(node[pos] + " " + Arrays.toString(par) + " " + curr);
            if (node[pos] != 0) ans[par[node[pos]]]--;
        }
        long k[] = Arrays.copyOf(dis, index + 1);
//        out.println((curr + 1) + " " + distance + " " + Arrays.toString(k));
        for (int i = 0; i < g[curr].length; i++) {
            int next = g[curr][i][0];
            int w = g[curr][i][1];
            par[next] = curr;
            dfs(next, distance + w, index + 1);
        }
    }

    int dfs2(int curr) {
        int x = ans[curr];
        for (int i = 0; i < g[curr].length; i++) {
            int next = g[curr][i][0];
            x += dfs2(next);
        }
        return ans[curr] = x;
    }

    public int BinarySearchLowerBound(long[] a, long v, int high) {
        int low = -1;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h] >= v) {
                high = h;
            } else {
                low = h;
            }
        }
        return high;
    }

    // for directed weighted graph
    public static int[][][] packWD(int n, int[] from, int[] to, int[] w) {
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


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
//        new Thread(null, new Runnable() {
//            public void run() {
//                try {
                    new ProbD().main1();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (StackOverflowError e) {
//                    System.out.println("RTE");
//                }
//            }
//        }, "1", 1 << 26).start();
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
