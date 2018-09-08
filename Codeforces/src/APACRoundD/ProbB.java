package APACRoundD;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbB {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int TC = ii();
        for (int tc = 1; tc <= TC; tc++) {
            out.printf("Case #%d: ", tc);
            r = ii();
            c = ii();
            ans = 0;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    count = 0;
                    visit = new boolean[r][c];
                    sit = new boolean[r][c];
                    solve(i, j);
                }
            }
            out.println(ans);
        }
    }

    boolean sit[][];
    boolean visit[][];
    int r, c, ans, count;

    void solve(int i, int j) {
        if (i < 0 || j < 0 || i >= r || j >= c)
            return;
        if (visit[i][j])
            return;
        visit[i][j] = true;
        boolean baithSakteHai = true;
        if (i - 1 >= 0 && sit[i - 1][j]) {
            if (i - 2 >= 0 && sit[i - 2][j]) {
                baithSakteHai = false;
            }
        }
        if (baithSakteHai && i + 1 < r && sit[i + 1][j]) {
            if (i + 2 < r && sit[i + 2][j]) {
                baithSakteHai = false;
            }
        }
        if (baithSakteHai && j - 1 >= 0 && sit[i][j - 1]) {
            if (j - 2 >= 0 && sit[i][j - 2]) {
                baithSakteHai = false;
            }
        }
        if (baithSakteHai && j + 1 < c && sit[i][j + 1]) {
            if (j + 2 < c && sit[i][j + 2]) {
                baithSakteHai = false;
            }
        }
        if (baithSakteHai && i + 1 < r && sit[i + 1][j]) {
            if (i - 1 >= 0 && sit[i - 1][j]) {
                baithSakteHai = false;
            }
        }
        if (baithSakteHai && j + 1 < c && sit[i][j + 1]) {
            if (j - 1 >= 0 && sit[i][j - 1]) {
                baithSakteHai = false;
            }
        }
        sit[i][j] = baithSakteHai;
        if (baithSakteHai)
            count++;
        ans = Math.max(ans, count);
        solve(i + 1, j);
        solve(i - 1, j);
        solve(i, j + 1);
        solve(i, j - 1);
    }
    /*

    int solve(boolean sit[][], int i, int j, int r, int c) {
        if (i >= r)
            return 0;
        int ans = 0;
        sit[i][j] = true;
        if (valid(sit, r, c)) {
            int nexti = i, nextj = j + 1;
            if (nextj == c) {
                nextj = 0;
                nexti++;
            }
            ans = Math.max(ans, 1 + solve(sit, nexti, nextj, r, c));
        }
        sit[i][j] = false;
        int nexti = i, nextj = j + 1;
        if (nextj == c) {
            nextj = 0;
            nexti++;
        }
        ans = Math.max(ans, solve(sit, nexti, nextj, r, c));
        return ans;
    }

    void print(boolean sit[][], int i, int j) {
        for (int p = 0; p < sit.length; p++) {
            for (int q = 0; q < sit[0].length; q++) {
                out.print(sit[p][q] + " ");
            }
            out.println();
        }
        out.println(i + " " + j);
    }

    boolean valid(boolean sit[][], int r, int c) {
        boolean crowded = false;
        for (int p = 0; p < r; p++) {
            for (int q = 0; q < c; q++) {
                if (sit[p][q]) {
                    if (0 <= p - 1 && p + 1 < r) {
                        if (sit[p - 1][q] && sit[p + 1][q]) {
                            crowded = true;
                        }
                    }
                    if (0 <= q - 1 && q + 1 < c) {
                        if (sit[p][q - 1] && sit[p][q + 1]) {
                            crowded = true;
                        }
                    }
                }
            }
        }
        return !crowded;
    }
*/

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
//        out = new PrintWriter(System.out);
//        out = new PrintWriter("/home/ankurverma1994/B-small-output.txt");
        out = new PrintWriter("/home/ankurverma1994/B-large-output.txt");
//        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        obj = check.isEmpty() ? new FileInputStream("/home/ankurverma1994/Downloads/B-small-attempt2.in") : new ByteArrayInputStream(check.getBytes());
        obj = check.isEmpty() ? new FileInputStream("/home/ankurverma1994/Downloads/B-large (2).in") : new ByteArrayInputStream(check.getBytes());
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
