package JUNE16;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class FRJUMP {

    final int mod = (int) 1e9 + 7;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), a[] = iia(n);
        if (n <= 10) {
            bruteforce(n, a);
            return;
        }
        long table[] = new long[n];
        double logtable[] = new double[n];
        int factors[][] = new int[n + 3][600];
        int len[] = new int[n + 3];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * i <= n; j++) {
                factors[j * i][len[j * i]++] = i;
            }
        }
        Arrays.fill(table, 1);
        for (int i = 1; i <= n; i++) {
            for (int R = 1; R <= n; R += i) {
                if (R == 1) continue;
                table[i - 1] = ((1L * table[i - 1] * a[R - 1]) % mod);
                logtable[i - 1] += Math.log10(a[R - 1]);
            }
        }
        for (int Q = ii(); Q > 0; Q--) {
//            System.out.println(Arrays.toString(a));
            int type = ii();
            if (type == 2) {
                int r = ii() - 1;
                long ans = ((1L * a[0] * table[r]) % mod);
                double y = logtable[r] + Math.log10(a[0]);
                long firstdigit = (long) Math.pow(10, (y - (int) y));
                out.println(String.valueOf(firstdigit).charAt(0) + " " + ans);
            } else {
                int x = ii(), val = ii();
                if (x == 1) {
                    a[0] = val;
                    continue;
                }
                table[0] = ((table[0] * inversemodp(a[x - 1])) % mod * val) % mod;
                logtable[0] = logtable[0] - Math.log10(a[x - 1]) + Math.log10(val);
//                int list[] = list(x, n);
                for (int j = 0; j < len[x - 1]; j++) {
                    int i = factors[x - 1][j];
                    if (i == 1) continue;
                    table[i - 1] = ((table[i - 1] * inversemodp(a[x - 1])) % mod * val) % mod;
                    logtable[i - 1] = logtable[i - 1] - Math.log10(a[x - 1]) + Math.log10(val);
                }
                a[x - 1] = val;
            }
        }
    }

    void bruteforce(int n, int a[]) {
        for (int Q = ii(); Q > 0; Q--) {
            int type = ii();
            if (type == 2) {
                int r = ii();
                long ans = 1;
                for (int i = 0; i < n; i += r)
                    ans = ans * a[i];
                out.println(String.valueOf(ans).charAt(0) + " " + ans % mod);
            } else {
                int in = ii() - 1, val = ii();
                a[in] = val;
            }
        }
    }

    public long modpow(long base, int exp) {
        base %= mod;
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    // inverse modulo prime number
    // a^(-1) mod P = a^(P-2) mod P
    public long inversemodp(long base) {
        return modpow(base, mod - 2);
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new FRJUMP().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
//        out = new PrintWriter("A:\\out.txt");
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
