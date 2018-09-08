package CodeJam2016;
/**
 * Created by ankurverma1994
 */

import java.io.*;
import java.util.*;
import java.math.*;

class ProbC {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int test = ii();
        for (int tc = 1; tc <= test; tc++) {
            out.printf("Case #%d: ", tc);
            int j = ii(), p = ii(), s = ii(), k = ii();
            int a[] = new int[j * p * s];
            int b[] = new int[j * p * s];
            int c[] = new int[j * p * s];
            int ind = 0;
            for (int x = 1; x <= j; x++) {
                for (int y = 1; y <= p; y++) {
                    for (int z = 1; z <= s; z++) {
                        a[ind] = x;
                        b[ind] = y;
                        c[ind] = z;
//                        out.println(a[ind]+" "+b[ind]+" "+c[ind]);
                        ind++;
                    }
                }
            }
            int validin[] = new int[j * p * s];
            for (int i = 0; i < j * p * s; i++)
                validin[i] = i;

            ArrayList<Triplet> ans1 = new ArrayList<>();
            for (int ra = 0; ra <= 100000; ra++) {
                validin = shuffle(validin, new Random());
                ArrayList<Triplet> ans = new ArrayList<>();
                ans.add(new Triplet(a[validin[0]], b[validin[0]], c[validin[0]]));
                for (int i = 1; i < j * p * s; i++) {
                    int cx = a[validin[i]], cy = b[validin[i]], cz = c[validin[i]];
                    int pair1 = 0, pair2 = 0, pair3 = 0;
                    for (ind = 0; ind < ans.size(); ind++) {
                        Triplet tr = ans.get(ind);
                        if (tr.x == cx && tr.y == cy) pair1++;
                        if (tr.y == cy && tr.z == cz) pair2++;
                        if (tr.z == cz && tr.x == cx) pair3++;
                    }
                    if (pair1 < k && pair2 < k && pair3 < k) {
                        ans.add(new Triplet(cx, cy, cz));
                    }
                }
                if (ans.size() > ans1.size())
                    ans1 = ans;
            }
            out.println(ans1.size());
            for (int i = 0; i < ans1.size(); i++) {
                Triplet tr = ans1.get(i);
                out.println(tr.x + " " + tr.y + " " + tr.z);
            }
        }
    }

    public static int[] shuffle(int[] a, Random gen) {
        for (int i = 0, n = a.length; i < n; i++) {
            int ind = gen.nextInt(n - i) + i;
            int d = a[i];
            a[i] = a[ind];
            a[ind] = d;
        }
        return a;
    }

    static class Triplet {
        int x, y, z;

        Triplet(int a, int b, int c) {
            x = a;
            y = b;
            z = c;
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
//        out = new PrintWriter(System.out);
        out=new PrintWriter("/home/ankurverma1994/tmp/out.tx");
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
         obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/tmp/input.in") : new ByteArrayInputStream(check.getBytes());
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
