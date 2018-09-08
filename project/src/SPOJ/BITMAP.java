package SPOJ;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class BITMAP {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), m = ii(), g[][] = new int[n][m];
            for (int i = 0; i < n; i++) {
                char s[] = is().toCharArray();
                for (int j = 0; j < m; j++)
                    g[i][j] = s[j] - '0';
            }
            Queue<Pair> queue = new LinkedList<>();
            int d[][] = new int[n][m];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    if (g[i][j] == 1) {
                        d[i][j] = 0;
                        queue.add(new Pair(i, j));
                    } else
                        d[i][j] = Integer.MAX_VALUE;

            d = bfs(d, queue, n, m);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++)
                    out.print(d[i][j] + " ");
                out.println();
            }
        }
    }

    static class Pair {
        int x, y;

        Pair(int i, int j) {
            x = i;
            y = j;
        }
    }

    int[][] bfs(int d[][], Queue<Pair> q, int n, int m) {
        int ax[] = {0, 1, 0, -1};
        int ay[] = {1, 0, -1, 0};
        while (!q.isEmpty()) {
            Pair u = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = u.x + ax[i], y = u.y + ay[i];
                if (valid(x, y, n, m)) {
                    int dist = d[u.x][u.y] + 1;
                    if (dist < d[x][y]) {
                        d[x][y] = dist;
                        q.add(new Pair(x, y));
                    }
                }
            }
        }
        return d;
    }

    boolean valid(int x, int y, int n, int m) {
        if (0 <= x && x < n && 0 <= y && y < m)
            return true;
        return false;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new BITMAP().main1();
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

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }
}