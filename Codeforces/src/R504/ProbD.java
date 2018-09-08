package R504;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbD {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), q = ii();
        int a[][] = new int[n][2];
        int org[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i][0] = ii();
            a[i][1] = i;
            org[i] = a[i][0];
        }
        Arrays.sort(a, (int aa[], int bb[]) ->
        {
            if (aa[0] == bb[0])
                if (aa[1] < bb[1])
                    return -1;
                else
                    return 1;
            if (aa[0] < bb[0])
                return -1;
            return 1;
        });
        int i;
        for (i = 0; i < n; i++) {
            if (a[i][0] != 0) break;
        }
//        boolean seen_index[] = new boolean[n];
        TreeSet<Integer> seen_index = new TreeSet<>();
        i++;
        for (; i < n; i++) {
//            seen_index[a[i - 1][1]] = true;
            if (a[i - 1][0] != a[i][0]) {
                seen_index.add(a[i - 1][1]);
                continue;
            }
            if (a[i][1] - a[i - 1][1] == 1) {
                seen_index.add(a[i - 1][1]);
                continue;
            }
            if (seen_index.headSet(a[i - 1][1]).size() !=
                    seen_index.headSet(a[i][1]).size()) {
                out.println("NO");
                return;
            }
            seen_index.add(a[i - 1][1]);
        }
        int last = q;
        boolean valid = false;
        for (int x : org) {
            if (x == q) {
                valid = true;
                break;
            }
        }
        if (valid) {
            for (i = n - 1; i >= 0; i--) {
                if (org[i] != 0) {
                    last = org[i];
                    break;
                }
            }
        }
        for (i = n - 1; i >= 0; i--) {
            if (org[i] == 0) {
                org[i] = last;
                valid = true;
            }
            if (valid)
                last = org[i];
        }
        valid = false;
        for (int x : org) {
            if (x == q) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            out.println("NO");
            return;
        }
        out.println("YES");
        for (i = 0; i < n; i++)
            out.print(org[i] + " ");
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ProbD().main1();
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
