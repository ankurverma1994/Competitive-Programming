package DivideByZero;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class OldMatrixProblem {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        n = ii();
        m = ii();
        p = ii();
        cell = iim(n, m);
        dp = new long[n][m][2][4];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int k = 0; k < 2; k++)
                    for (int l = 0; l < 4; l++)
                        dp[i][j][k][l] = -inf;

        long ans = solve(0, 0, 1, 3) + cell[0][0];
        // initial previous direction can be right or down :)
        out.println(ans);
    }

    int cell[][];
    int p, n, m;
    long dp[][][][];
    long inf = Long.MAX_VALUE / 2;

    // count 0 used
    // count 1 not used
    // prev 0 left
    // prev 1 right
    // prev 2 up
    // prev 3 down
    long solve(int i, int j, int count, int prev) {
        if (i < 0 || j < 0 || i >= n || j >= m)
            return -inf;

        if (i == n - 1 && j == m - 1)
            return 0;

        if (dp[i][j][count][prev] != -inf)
            return dp[i][j][count][prev];

        long a = -inf;
        a = Math.max(a, solve(i, j + 1, count, 1) + (prev == 0 || (j + 1 == m) ? 0 : cell[i][j + 1]));
        a = Math.max(a, solve(i + 1, j, count, 3) + (prev == 2 || (i + 1 == n) ? 0 : cell[i + 1][j]));

        if (count == 1) {
            a = Math.max(a, solve(i, j - 1, 0, 0) + (prev == 1 || (j - 1 < 0) ? 0 : cell[i][j - 1]) - p);
            a = Math.max(a, solve(i - 1, j, 0, 2) + (prev == 3 || (i - 1 < 0) ? 0 : cell[i - 1][j]) - p);
        }

        return dp[i][j][count][prev] = a;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new OldMatrixProblem().main1();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (StackOverflowError e) {
                System.out.println("RTE");
            }
        }, "1", 1 << 26).start();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
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