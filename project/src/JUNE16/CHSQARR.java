package JUNE16;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;

class CHSQARR {
    final int inf = Integer.MAX_VALUE;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        n = ii();
        m = ii();
        int a[][] = iim(n, m);

        preProcess(a);

        for (int Q = ii(); Q > 0; Q--) {
            int x = ii(), y = ii(), sum = firstMatrix(a, x - 1, y - 1, 0), ans = sum, elements = x * y, max[][] = getMax(a, x, y);
            int oldsum = sum;
            for (int i = 0; i + x <= n; i++) {
                if (i != 0) {
                    sum = oldsum - (max[i - 1][0] * y - sumQuery(i - 1, 0, i - 1, y - 1))
                            + (max[i][0] * y - sumQuery(i + x - 1, 0, i + x - 1, y - 1))
                            + (max[i][0] - max[i - 1][0]) * (elements - y);
                    oldsum = sum;
                    ans = Math.min(ans, sum);
                }

                for (int j = 1; j + y <= m; j++) {
                    sum = sum - (max[i][j - 1] * x - sumQuery(i, j - 1, i + x - 1, j - 1))
                            + (max[i][j] * x - sumQuery(i, j + y - 1, i + x - 1, j + y - 1))
                            + ((max[i][j] - max[i][j - 1]) * (elements - x));
                    ans = Math.min(ans, sum);
                }
            }
            out.println(ans);
        }
    }

    int[][] getMax(int a[][], int x, int y) {
        int b[][] = new int[n][m];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            q=new ArrayDeque<>();
            for (int j = 0; j < y; j++) {
                while (!q.isEmpty() && a[i][j] >= a[i][q.getLast()])
                    q.pollLast();
                q.addLast(j);
            }
            for (int j = y; j < m; j++) {
                b[i][j - y] = a[i][q.getFirst()];
                while (!q.isEmpty() && a[i][j] >= a[i][q.getLast()])
                    q.pollLast();
                while (!q.isEmpty() && q.getFirst() <= j - y)
                    q.pollFirst();
                q.addLast(j);
            }
            b[i][m - y] = a[i][q.getFirst()];
        }

        int c[][] = new int[n][m];
        for (int j = 0; j < m; j++) {
            q=new ArrayDeque<>();
            for (int i = 0; i < x; i++) {
                while (!q.isEmpty() && b[i][j] >= b[q.getLast()][j])
                    q.pollLast();
                q.addLast(i);
            }
            for (int i = x; i < n; i++) {
                c[i - x][j] = b[q.getFirst()][j];
                while (!q.isEmpty() && b[i][j] >= b[q.getLast()][j])
                    q.pollLast();
                while (!q.isEmpty() && q.getFirst() <= i - x)
                    q.pollFirst();
                q.addLast(i);
            }
            c[n - x][j] = b[q.getFirst()][j];
        }
        return c;
    }

    int n, m, cum[][];

    int firstMatrix(int a[][], int x, int y, int start) {
        int sum = 0, elements = 0;
        int max = 0;
        for (int i = start; i <= x + start; i++) {
            for (int j = 0; j <= y; j++) {
                if (max < a[i][j]) {
                    sum = sum - elements * max + elements * a[i][j];
                    max = a[i][j];
                } else {
                    sum = sum + (max - a[i][j]);
                }
                elements++;
            }
        }
        return sum;
    }

    void preProcess(int a[][]) {
        cum = new int[n][m];

        for (int i = 0; i < m; i++) cum[0][i] = a[0][i];

        for (int i = 1; i < n; i++)
            for (int j = 0; j < m; j++)
                cum[i][j] = a[i][j] + cum[i - 1][j];

        for (int i = 0; i < n; i++)
            for (int j = 1; j < m; j++)
                cum[i][j] += cum[i][j - 1];
    }

    int sumQuery(int x1, int y1, int x2, int y2) {
        int res = cum[x2][y2];
        if (x1 > 0) res = res - cum[x1 - 1][y2];
        if (y1 > 0) res = res - cum[x2][y1 - 1];
        if (x1 > 0 && y1 > 0) res = res + cum[x1 - 1][y1 - 1];
        return res;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new CHSQARR().main1();
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

    int[][] iim(int n, int m) {
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = ii();
        return a;
    }
}