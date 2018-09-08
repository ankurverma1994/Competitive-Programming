package R380DIV2;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbC {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), k = ii(), s = ii(), t = ii();
        int price[] = new int[n];
        int vol[] = new int[n];
        for (int i = 0; i < n; i++) {
            price[i] = ii();
            vol[i] = ii();
        }
        int gas[] = shuffle(iia(k), new Random());
        Arrays.sort(gas);
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int maxVOL = 0;
        q.add(gas[0] - 0);
        maxVOL = Math.max(maxVOL, gas[0] - 0);
        q.add(s - gas[k - 1]);
        maxVOL = Math.max(maxVOL, s - gas[k - 1]);
        for (int i = 1; i < k; i++) {
            q.add(gas[i] - gas[i - 1]);
            maxVOL = Math.max(maxVOL, gas[i] - gas[i - 1]);
        }
        if (t < s) {
            out.println("-1");
            return;
        }
        int maxTime = 2 * s;
        while (t < maxTime && !q.isEmpty()) {
            int gap = q.poll();
            if (maxTime - gap < t) {
                maxVOL = Math.max(maxVOL, 2 * (maxTime - t) + gap - (maxTime - t));
//                out.println(maxTime + " " + t);
                break;
            }
            maxTime -= gap;
            maxVOL = Math.max(maxVOL, 2 * gap);
        }
//        out.println(maxTime);
//        out.println(maxVOL);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (vol[i] >= maxVOL) ans = Math.min(ans, price[i]);
        }
        if (ans == Integer.MAX_VALUE) out.println("-1");
        else out.println(ans);
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
