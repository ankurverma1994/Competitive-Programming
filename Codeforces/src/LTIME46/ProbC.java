package LTIME46;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

 class ProbC {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        outer:
        for (int tc = ii(); tc > 0; tc--) {
            n = ii();
            s = new char[n][n];
            for (int i = 0; i < n; i++) s[i] = is().toCharArray();
            visit = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (s[i][j] == 'G') {
                        if (!validG(i, j)) {
                            out.println(0);
                            continue outer;
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (s[i][j] == 'P' && !visit[i][j]) {
                        vp = true;
                        validP(i, j);
                        if (!vp) {
                            out.println("0");
                            continue outer;
                        }
                    }
                }
            }
            visit = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (s[i][j] == 'B' && !visit[i][j]) {
                        vp = true;
                        validB(i, j);
                        if (!vp) {
                            out.println("0");
                            continue outer;
                        }
                    }
                }
            }
            long ans = 1;
            visit = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (s[i][j] == '?' && !visit[i][j]) {
                        ques = 0;
                        hash = new HashSet<>();
                        dfs(i, j);
                        if (hash.contains('B') && hash.size() == 1) ans *= 1;
                        else if (hash.contains('P') && hash.size() == 1) ans *= 1;
                        else if (hash.size() == 0 && ques == 1) ans = (ans * 3) % mod;
                        else if (hash.size() == 0) ans = (ans * 2) % mod;
                        else {
                            out.println("0");
                            continue outer;
                        }
                    }
                }
            }
            out.println(ans);
        }
    }

    boolean vp;

    void validP(int x, int y) {
        visit[x][y] = true;
        if (s[x][y] == 'G' || s[x][y] == 'B')
            vp = false;
        for (int i = 0; i < 4; i++) {
            int u = x + dx[i], v = y + dy[i];
            if (0 <= u && u < n && 0 <= v && v < n)
                if (s[u][v] != '.' && !visit[u][v])
                    validP(u, v);
        }
    }

    void validB(int x, int y) {
        visit[x][y] = true;
        if (s[x][y] == 'G' || s[x][y] == 'P')
            vp = false;
        for (int i = 0; i < 4; i++) {
            int u = x + dx[i], v = y + dy[i];
            if (0 <= u && u < n && 0 <= v && v < n)
                if (s[u][v] != '.' && !visit[u][v])
                    validB(u, v);
        }
    }

    boolean validG(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int u = x + dx[i], v = y + dy[i];
            if (0 <= u && u < n && 0 <= v && v < n)
                if (s[u][v] != '.')
                    return false;
        }
        return true;
    }

    void dfs(int x, int y) {
        visit[x][y] = true;
        if (s[x][y] != '?') hash.add(s[x][y]);
        else ques++;
        for (int i = 0; i < 4; i++) {
            int u = x + dx[i], v = y + dy[i];
            if (valid(u, v))
                dfs(u, v);
        }
    }

    int dx[] = {1, 0, -1, 0};
    int dy[] = {0, -1, 0, 1};

    boolean valid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n && s[x][y] != '.' && !visit[x][y];
    }

    char s[][];
    int n;
    int ques;
    boolean visit[][];
    HashSet<Character> hash;


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ProbC().main1();
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
