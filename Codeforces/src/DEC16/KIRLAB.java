package DEC16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class KIRLAB {
    int lpf[];
    HashSet<Integer> seq;

    //------------> Solution starts here!!
    void solve() {
        lpf = enumLowestPrimeFactors((int) 1e7);
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), a[] = iia(n);
            int ans[] = new int[n];


            HashMap<Integer, Integer> prime = new HashMap<>();

            for (int i = n - 1; i >= 0; i--) {
                primeFactor(a[i]);

                for (int x : seq) {
                    if (!prime.containsKey(x)) prime.put(x, 0);
                    prime.put(x, prime.get(x) + 1);
                    ans[i] = Math.max(ans[i], prime.get(x));
                }
                for (int x : seq) {
                    prime.put(x, ans[i]);
                }

            }

            int max = 0;
            for (int x : ans) max = Math.max(max, x);
            out.println(max == 0 ? 1 : max);
        }
    }

    public void primeFactor(int n) {
        seq = new HashSet<>();
        while (n > 1) {
            seq.add(lpf[n]);
            n /= lpf[n];
        }
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
        new KIRLAB().main1();
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

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }
}
