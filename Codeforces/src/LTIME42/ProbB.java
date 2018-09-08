package LTIME42;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class ProbB {
    int lpf[];
    int a[];

    //------------> Solution starts here!!
    void solve() {
        lpf = enumLowestPrimeFactors(1000000);
        for (int tc = ii(); tc > 0; tc--) {
            // 0 even
            // 1 odd
            int ans = 0;
            int max = 0;
            int n = ii();
            a = iia(n);
            for (int i : a) max = Math.max(max, i);
            HashMap<Integer, Integer> count = new HashMap<>();
//            count = new int[max + 1];
            // FUCK ME!! For tree=10^6 and max=10^6 operations counts= 10^12
            // DON"tree EVER DO THIS.. WHEN TEST CASE IS LARGE THINK SMARTLY
            for (int i : a) {
                int fac[][] = factorFast(i, lpf);
                for (int j = 0; j < fac.length; j++) {
                    if ((fac[j][1] & 1) > 0) {
                        int val = fac[j][0];
                        if (count.containsKey(val)) count.put(val, count.get(val) + 1);
                        else count.put(val, 1);
                    }
                }
            }
            for (Map.Entry<Integer, Integer> x : count.entrySet()) {
                int val = x.getValue();
                ans += Math.min(val, n - val);
            }
            out.println(ans);
        }
    }

    public static int[][] factorFast(int n, int[] lpf) {
        /*  Code picked up from "uwi" submissions */
        int[][] f = new int[9][];
        int q = 0;
        while (lpf[n] > 0) {
            int p = lpf[n];
            if (q == 0 || p != f[q - 1][0]) {
                f[q++] = new int[]{p, 1};
            } else {
                f[q - 1][1]++;
            }
            n /= p;
        }
        if (n > 1) {
            // big prime
            return new int[][]{{n, 1}};
        }
        return Arrays.copyOf(f, q);
    }

    public static int[] enumLowestPrimeFactors(int n) {
        /*  Code picked up from "uwi" submissions */
        int tot = 0;
        int[] lpf = new int[n + 1];
        int u = n + 32;
        double lu = Math.log(u);
        int[] primes = new int[(int) (u / lu + u / lu / lu * 1.5)];
        for (int i = 2; i <= n; i++) lpf[i] = i;
        for (int p = 2; p <= n; p++) {
            if (lpf[p] == p) primes[tot++] = p;
            int tmp;
            for (int i = 0; i < tot && primes[i] <= lpf[p] && (tmp = primes[i] * p) <= n; i++) {
                lpf[tmp] = primes[i];
            }
        }
        return lpf;
    }


    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new ProbB().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        obj = check.isEmpty() ? new FileInputStream("/home/ankurverma1994/input.txt") : new ByteArrayInputStream(check.getBytes());
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

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }
}