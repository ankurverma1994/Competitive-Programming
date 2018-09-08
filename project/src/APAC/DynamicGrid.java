package APAC;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class DynamicGrid {

    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int TC = ii();
        for (int tc = 1; tc <= TC; tc++) {
            out.printf("Case #%d:\n", tc);
            int r = ii(), c = ii();
            char grid[][] = new char[r][];
            for (int i = 0; i < r; i++) grid[i] = is().toCharArray();
            for (int Q = ii(); Q > 0; Q--) {
                char choice = ic();
                if (choice == 'M') {
                    int x = ii(), y = ii();
                    char z = ic();
                    grid[x][y] = z;
                } else {
                    visit = new boolean[r][c];
                    int ans = 0;
                    for (int i = 0; i < r; i++)
                        for (int j = 0; j < c; j++)
                            if (!visit[i][j] && grid[i][j] == '1') {
                                ans++;
                                dfs(i, j, r, c, grid);
                            }
                    out.println(ans);
                }
            }
        }
    }

    boolean visit[][];
    int x[] = {1, -1, 0, 0};
    int y[] = {0, 0, 1, -1};

    void dfs(int sx, int sy, int r, int c, char grid[][]) {
        visit[sx][sy] = true;
        for (int i = 0; i < 4; i++) {
            int vx = sx + x[i], vy = sy + y[i];
            if (0 <= vx && vx < r && 0 <= vy && vy < c && !visit[vx][vy] && grid[vx][vy] == '1')
                dfs(vx, vy, r, c, grid);
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
                    new DynamicGrid().main1();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StackOverflowError e) {
                    System.out.println("RTE");
                }
            }
        }, "1", 1 << 26).start();
    }

    void main1() throws IOException {
        out = new PrintWriter("A:\\out.txt");
//        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        obj = check.isEmpty() ? new FileInputStream("A:\\in.in") : new ByteArrayInputStream(check.getBytes());
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