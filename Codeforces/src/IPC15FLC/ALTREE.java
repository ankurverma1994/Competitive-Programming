package IPC15FLC;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ALTREE {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        n = ii();
        m = ii();
        gw = new ArrayList[n];
        gb = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            gw[i] = new ArrayList<>();
            gb[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int from = ii() - 1, to = ii() - 1, w = ii();
            char c = ic();
            if (c == 'W') {
                gw[from].add(to);
                gw[to].add(from);
                gw[from].add(w);
                gw[to].add(w);
            } else if (c == 'B') {
                gb[from].add(to);
                gb[to].add(from);
                gb[from].add(w);
                gb[to].add(w);
            }
        }
        long ans = inf;
        inti();
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, solve(1 << i, i, -1));
        }
        out.println(ans >= inf ? "-1" : ans);
    }

    void inti() {
        dp = new long[2][n][1 << n];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < n; j++)
                Arrays.fill(dp[i][j], -1);
    }

    int n, m;
    ArrayList<Integer> gw[], gb[];
    long dp[][][];

    /*
    * mask is set of nodes
    * color 0 --> black
    * color 1 --> white
     */
    long solve(int mask, int curr, int color) {
        if (mask == ((1 << n) - 1)) // all nodes are taken
            return 0;
        if (color != -1 && dp[color][curr][mask] != -1)
            return dp[color][curr][mask];
        long ans = inf;
        if (color == 0 || color == -1) {
            for (int i = 0; i < gw[curr].size(); i += 2) {
                int next = gw[curr].get(i);
                int w = gw[curr].get(i + 1);
                if ((mask & (1 << next)) > 0) // next node is already taken
                    continue;
                ans = Math.min(ans, w + solve(mask | (1 << next), next, 1)); // taking next node
            }
        }
        if (color == 1 || color == -1) {
            for (int i = 0; i < gb[curr].size(); i += 2) {
                int next = gb[curr].get(i);
                int w = gb[curr].get(i + 1);
                if ((mask & (1 << next)) > 0) // next node is already taken
                    continue;
                ans = Math.min(ans, w + solve(mask | (1 << next), next, 0)); // taking next node
            }
        }
        if (color == -1)
            return ans;
        return dp[color][curr][mask] = ans;
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ALTREE().main1();
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
