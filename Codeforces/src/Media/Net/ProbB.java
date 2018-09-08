package Media.Net;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class ProbB {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;
    final int max = 24;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        pre();
        for (int tc = ii(); tc > 0; tc--) {
            r = ii();
            c = ii();
            long ans = 0;
            visit = new int[max][max][4];
            s = new char[max][max];
            for (int i = 1; i <= r; i++) {
                for (int j = 1; j <= c; j++) {
                    s[i][j] = ic();
                }
            }

            for (int i = 1; i <= r; i++) {
                for (int j = 1; j <= c; j++) {
                    if (s[i][j] == '.') {
                        for (int d = 0; d < 4; d++) {
                            valid = new boolean[22][22];
                            count = 0;
                            different++;
                            dfs(i, j, d);
                            ans = Math.max(ans, count);
                        }
                    }
                }
            }

            out.println(ans);
        }
    }

    int dx[] = {1, 0, -1, 0};
    int dy[] = {0, 1, 0, -1};

    void dfs(int x, int y, int d) {
        if (x <= 0 || x > r || y <= 0 || y > c)
            return;
        if (visit[x][y][d] == different)
            return;
        if (!valid[x][y])
            count++;
        valid[x][y] = true;
        visit[x][y][d] = different;
        if (s[x][y] == 'r' || s[x][y] == 'l')
            d = neighb[d][s[x][y] == 'r' ? 0 : 1];
        dfs(x + dx[d], y + dy[d], d);
    }


    void pre() {
        neighb = new int[4][2];
        neighb[0][0] = 3;
        neighb[0][1] = 1;
        neighb[1][0] = 0;
        neighb[1][1] = 2;
        neighb[2][0] = 1;
        neighb[2][1] = 3;
        neighb[3][0] = 2;
        neighb[3][1] = 0;
    }

    int visit[][][], count, r, c, different, neighb[][];
    boolean valid[][];
    char s[][];

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ProbB().main1();
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
