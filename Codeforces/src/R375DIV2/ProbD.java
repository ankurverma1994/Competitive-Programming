package R375DIV2;
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
        m = ii();
        k = ii();
        g = new char[n][m];
        char original[][] = new char[n][m];
        visit = new boolean[n][m];
        for (int i = 0; i < n; i++)
            g[i] = is().toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                original[i][j] = g[i][j];
            }
        }
        for (int j = 0; j < m; j++) {
            if (!visit[0][j] && g[0][j] == '.')
                dfs(0, j);
            if (!visit[n - 1][j] && g[n - 1][j] == '.')
                dfs(n - 1, j);
        }
        for (int i = 0; i < n; i++) {
            if (!visit[i][0] && g[i][0] == '.')
                dfs(i, 0);
            if (!visit[i][m - 1] && g[i][m - 1] == '.')
                dfs(i, m - 1);
        }
        Comp comp[] = new Comp[2500 + 50];
        int len = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j] && g[i][j] == '.') {
//                    out.println(i+" "+j+" start");
                    size = 0;
                    dfs(i, j);
                    comp[len] = new Comp(i, j, size);
                    len++;
                }
            }
        }
        Comp components[] = new Comp[len];
        for (int i = 0; i < len; i++)
            components[i] = comp[i];
        Arrays.sort(components);
//        for (int i = 0; i < len; i++)
//            out.println(components[i].x + " " + components[i].y + " " + components[i].count);
        visit = new boolean[n][m];
//        out.println(len-k);
        int zz = 0;
//        out.println(len);
        for (int i = 0; i < len - k; i++) {
            int x = components[i].x, y = components[i].y;
            zz += components[i].count;
//            out.println(x + " " + y + " " + components[i].count);
            dfs1(x, y);
        }
        out.println(zz);
        for (int i = 0; i < n; i++)
            out.println(new String(g[i]));
    }

    class Comp implements Comparable<Comp> {
        int x, y, count;

        Comp(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Comp o) {
            return Integer.compare(count, o.count);
        }
    }

    char g[][];
    boolean visit[][];
    int n, m, k;
    int dx[] = {1, -1, 0, 0};
    int dy[] = {0, 0, 1, -1};

    int size = 0;

    void dfs(int x, int y) {
//        out.println(x + " $ " + y);
        visit[x][y] = true;
        size++;
        for (int i = 0; i < 4; i++) {
            int u = x + dx[i], v = y + dy[i];
            if (valid(u, v) && g[u][v] == '.' && !visit[u][v])
                dfs(u, v);
        }
    }

    void dfs1(int x, int y) {
        visit[x][y] = true;
//        out.println(x+" "+y+" change");
        g[x][y] = '*';
        size++;
        for (int i = 0; i < 4; i++) {
            int u = x + dx[i], v = y + dy[i];
//            out.println(u+" "+v);
            if (valid(u, v) && g[u][v] == '.' && !visit[u][v])
                dfs1(u, v);
        }
    }

    boolean valid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
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