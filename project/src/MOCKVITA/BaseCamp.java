package MOCKVITA;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

class BaseCamp {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        while (true) {
            r = ii();
            if (r == -1)
                break;
            c = ii();
            char grid[][] = new char[r][c];
            for (int i = 0; i < r; i++)
                for (int j = 0; j < c; j++)
                    grid[i][j] = ic();

            Pair[][] parent = new Pair[r][c];

            for (int i = 0; i < r; i++)
                for (int j = 0; j < c; j++)
                    if (grid[i][j] == 'd')
                        parent = bfs(grid, i, j);
            char value = 'a';
            for (int i = 0; i < r; i++)
                for (int j = 0; j < c; j++)
                    if (grid[i][j] == 's') {
                        out.println();
                        print(i, j, grid, parent, value);
                        value++;
                    }
        }
    }

    void print(int sx, int sy, char graph[][], Pair[][] parent, char value) {
        char output[][] = new char[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                output[i][j] = graph[i][j] == 's' ? '-' : graph[i][j];

        while (graph[sx][sy] != 'd') {
            output[sx][sy] = value;
            Pair u = parent[sx][sy];
            sx = u.x;
            sy = u.y;
        }
        output[sx][sy] = 'd';

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                out.print(output[i][j] + " ");
            }
            out.println();
        }
    }

    int r, c;

    Pair[][] bfs(char graph[][], int sx, int sy) {
        int d[][] = new int[r][c];
        Pair parent[][] = new Pair[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                d[i][j] = Integer.MAX_VALUE / 2;
        d[sx][sy] = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(sx, sy));


        while (!q.isEmpty()) {
            Pair u = q.poll();
            for (int w = 0; w < 8; w++) {
                if (valid(u.x + xx[w], u.y + yy[w])
                        && d[u.x + xx[w]][u.y + yy[w]] == Integer.MAX_VALUE / 2
                        && (graph[u.x + xx[w]][u.y + yy[w]] == '-' || graph[u.x + xx[w]][u.y + yy[w]] == 's')) {

                    d[u.x + xx[w]][u.y + yy[w]] = d[u.x][u.y] + 1;
                    parent[u.x + xx[w]][u.y + yy[w]] = new Pair(u.x, u.y);
                    if (graph[u.x + xx[w]][u.y + yy[w]] == '-')
                        q.add(new Pair(u.x + xx[w], u.y + yy[w]));

                }
            }
        }

        return parent;
    }

    boolean valid(int x, int y) {
        return 0 <= x && x < r && 0 <= y && y < c;
    }

    int xx[] = {1, 0, 0, -1, 1, 1, -1, -1};
    int yy[] = {0, 1, -1, 0, 1, -1, 1, -1};

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
                    new BaseCamp().main1();
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
