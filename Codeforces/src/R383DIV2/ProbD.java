package R383DIV2;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

public class ProbD {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 10;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        n = ii();
        m = ii();
        W = ii();
        w = iia(n);
        b = iia(n);
        int from[] = new int[m];
        int to[] = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = ii() - 1;
            to[i] = ii() - 1;
        }
        g = packU(n, from, to, m);
        weight = new ArrayList<>();
        beauty = new ArrayList<>();
        compNo = new int[n];
        num = 0;
        element = new ArrayList[n];
        visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                be = 0;
                we = 0;
                element[num] = new ArrayList<>();
                dfs(i);
                weight.add(we);
                beauty.add(be);
                num++;
            }
        }
//        out.println(weight.toString());
//        out.println(beauty.toString());
//        out.println(Arrays.toString(compNo));
//        for (int i = 0; i < num; i++) out.println(element[i].toString());
//        System.out.println(num);
        dp = new long[num][W + 1];
        for (int i = 0; i < num; i++)
            Arrays.fill(dp[i], -1);
        out.println(solve(0, W));
    }

    long dp[][];

    long solve(int i, int remaining) {
        if (i >= num) return 0;
        if (dp[i][remaining] != -1)
            return dp[i][remaining];
        long ans = 0;
        ans = Math.max(ans, solve(i + 1, remaining));
        if (weight.get(i) <= remaining) {
//            System.out.println(i+" "+(remaining-weight.get(i)));
            ans = Math.max(ans, beauty.get(i) + solve(i + 1, remaining - weight.get(i)));
        }
        for (int x = 0; x < element[i].size(); x++) {
            int next = element[i].get(x);
            if (w[next] <= remaining)
                ans = Math.max(ans, b[next] + solve(i + 1, remaining - w[next]));
        }
        return dp[i][remaining] = ans;
    }

//    long solve(int i, int remaining) {
//        System.out.println(i + " " + remaining);
//        if (i >= num) return 0;
//        long ans = 0;
//        if (weight.get(i) <= remaining)
//            ans = Math.max(ans, solve(i + 1, remaining - weight.get(i)) + beauty.get(i));
//        int comp = compNo[i];
//        ans = Math.max(ans, solve(i + 1, remaining));
//        for (int j = 0; j < element[comp].size(); j++) {
//            int key = element[comp].get(j);
//            if (w[key] <= remaining)
//                ans = Math.max(ans, solve(i + 1, remaining - w[key]) + b[key]);
//        }
//        return ans;
//    }

    boolean visit[];
    int be, we;
    int num;

    void dfs(int curr) {
        visit[curr] = true;
        compNo[curr] = num;
        be += b[curr];
        we += w[curr];
        element[num].add(curr);
        for (int next : g[curr]) {
            if (!visit[next])
                dfs(next);
        }
    }

    ArrayList<Integer> element[];
    ArrayList<Integer> weight, beauty;
    int compNo[];
    int n, m, W, w[], b[], g[][];

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
                    new ProbD().main1();
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
