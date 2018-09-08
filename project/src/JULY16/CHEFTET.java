package JULY16;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class CHEFTET {

    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), b[] = iia(n), a[] = iia(n);
            long suma = 0, sumb = 0;


            for (int i : a) suma += i;
            for (int i : b) sumb += i;

            ArrayList<Integer> ans = new ArrayList<>();

            ans.add(-1);
            if (n >= 2 && suma + sumb == 1L * n * (a[0] + b[0] + b[1]) && possible(a, b, a[0] + b[0] + b[1]))
                ans.add(a[0] + b[0] + b[1]);

            if (suma + sumb == 1L * n * (a[0] + b[0]) && possible(a, b, a[0] + b[0])) ans.add(a[0] + b[0]);

            if (n >= 2 && suma + sumb == 1L * n * (a[0] + b[1]) && possible(a, b, a[0] + b[1])) ans.add(a[0] + b[1]);

            if (suma + sumb == 1L * n * a[0] && possible(a, b, a[0])) ans.add(a[0]);

            Collections.sort(ans);

            out.println(ans.get(ans.size() - 1));
        }
    }

    boolean possible(int a[], int b[], int val) {
        int n = a.length;
        boolean visit[] = new boolean[n];
        for (int i = 0; i < n; i++) {

            if (a[i] == val) continue;
            if (valid(i - 1, n, visit) && a[i] + b[i - 1] == val) {
                visit[i - 1] = true;
                continue;
            }
            if (valid(i - 1, n, visit) && valid(i, n, visit) && a[i] + b[i] + b[i - 1] == val) {
                visit[i - 1] = visit[i] = true;
                continue;
            }
            if (valid(i - 1, n, visit) && valid(i + 1, n, visit) && a[i] + b[i - 1] + b[i + 1] == val) {
                visit[i - 1] = visit[i + 1] = true;
                continue;
            }
            if (valid(i - 1, n, visit) && valid(i, n, visit) && valid(i + 1, n, visit) && a[i] + b[i - 1] + b[i] + b[i + 1] == val) {
                visit[i - 1] = visit[i] = visit[i + 1] = true;
                continue;
            }
            if (valid(i, n, visit) && a[i] + b[i] == val) {
                visit[i] = true;
                continue;
            }
            if (valid(i, n, visit) && valid(i + 1, n, visit) && a[i] + b[i] + b[i + 1] == val) {
                visit[i] = visit[i + 1] = true;
                continue;
            }
            if (valid(i + 1, n, visit) && a[i] + b[i + 1] == val) {
                visit[i + 1] = true;
                continue;
            }
            return false;
        }

        for (boolean v : visit) if (!v) return false;

        return true;
    }

    boolean valid(int i, int n, boolean visit[]) {
        if (0 <= i && i < n && !visit[i])
            return true;
        return false;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new CHEFTET().main1();
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
