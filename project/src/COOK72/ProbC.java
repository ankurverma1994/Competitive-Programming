package COOK72;
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
        char a[] = {'L', 'R', 'U', 'D'};
        n = ii();
        m = ii();
        char grid[][] = new char[n][m];
        for (int i = 0; i < n; i++)
            grid[i] = is().toCharArray();
        Pair capital = new Pair(0, 0);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'C') {
                    capital.x = i;
                    capital.y = j;
                    break;
                }
            }
        Pair parent[][] = bfs(grid, capital.x, capital.y);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (grid[i][j] == '.') {
//                    out.println(i + " " + j + findpath(parent, capital, new Pair(i, j)));
                    StringBuilder temp = findpath(parent, capital, new Pair(i, j));
                    ans.append(temp);
                    ans.append(reverse(temp.toString()));
                    ans.append(temp);
                }
        out.println(ans);
//        out.println(ans.length());
    }

    StringBuilder reverse(String s) {
        char a[] = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        for (char c : a)
            ans.append(mirror(c));
        return ans.reverse();
    }

    char mirror(char c) {
        if (c == 'U')
            return 'D';
        if (c == 'D')
            return 'U';
        if (c == 'R')
            return 'L';
        return 'R';
    }

    StringBuilder findpath(Pair parent[][], Pair capital, Pair source) {
        int u = source.x, v = source.y;
        StringBuilder ans = new StringBuilder();
        while (u != capital.x || v != capital.y) {
            Pair p = parent[u][v];
            int sx = u, sy = v;
            u = p.x;
            v = p.y;
            int dx = u, dy = v;
            ans.append(direction(sx, sy, dx, dy));
        }
        return ans;
    }

    char direction(int sx, int sy, int dx, int dy) {
        if (sx == dx) {
            if (dy < sy)
                return 'L';
            return 'R';
        }
        if (dx < sx)
            return 'U';
        return 'D';
    }

    int n, m;

    Pair[][] bfs(char graph[][], int sx, int sy) {
        int d[][] = new int[n][m];
        Pair parent[][] = new Pair[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                d[i][j] = Integer.MAX_VALUE / 2;
        d[sx][sy] = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(sx, sy));


        while (!q.isEmpty()) {
            Pair u = q.poll();
            for (int w = 0; w < 4; w++) {
                if (valid(u.x + xx[w], u.y + yy[w])
                        && d[u.x + xx[w]][u.y + yy[w]] == Integer.MAX_VALUE / 2
                        && graph[u.x + xx[w]][u.y + yy[w]] == '.') {

                    d[u.x + xx[w]][u.y + yy[w]] = d[u.x][u.y] + 1;
                    parent[u.x + xx[w]][u.y + yy[w]] = new Pair(u.x, u.y);
                    q.add(new Pair(u.x + xx[w], u.y + yy[w]));

                }
            }
        }

        return parent;
    }

    int xx[] = {0, 1, -1, 0};
    int yy[] = {1, 0, 0, -1};

    boolean valid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    class Pair {
        int x, y;

        Pair(int a, int b) {
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
