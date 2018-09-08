package SPOJ;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class TWOSQUARES {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        /*	Due to Fermat:
    A number N is expressible as a sum of 2 squares if and only if in the prime factorization of N,
	every prime of the form (4k+3) occurs an even number of times.

	A corollary of this fact is that every prime of the form (4k+1) can be written as the sum of two squares.	*/
        int prime[] = PrimeFactorisation.sieveEratosthenes(1000000 + 10);
        for (int tc = ii(); tc > 0; tc--) {
            long n = il();
            long x[][] = PrimeFactorisation.factorX(n, prime);
//            out.println(Arrays.deepToString(x));
            boolean ans = true;
            for (int i = 0; i < x.length; i++) {
                if (x[i][0] % 4 == 3) {
                    if (x[i][1] % 2 != 0) {
                        ans = false;
                        break;
                    }
                }
            }
            out.println(ans ? "Yes" : "No");
        }
    }

    static class PrimeFactorisation {
        //        public static void main(String[] args) {
//            int n=1000000;
//            int[] primes = sieveEratosthenes(n);
//            long x[][]=(factorX(n,primes));
//            for(int i=0;i<x.length;i++)
//                System.out.println(Arrays.toString(x[i]));
//        }
        static long[][] factorX(long n, int[] primes) {
            long[][] ret = new long[20][2];
            int rp = 0;
            for (int p : primes) {
                if ((long) p * p > n) break;
                int i;
                for (i = 0; n % p == 0; n /= p, i++) ;
                if (i > 0) {
                    ret[rp][0] = p;
                    ret[rp][1] = i;
                    rp++;
                }
            }
            if (n == 1) return Arrays.copyOf(ret, rp);
// P^2
            long sq = (long) Math.sqrt(n);
            for (long u = Math.max(2, sq - 2); u <= sq + 2; u++) {
                if (u * u == n) {
                    ret[rp][0] = u;
                    ret[rp][1] = 2;
                    rp++;
                    return Arrays.copyOf(ret, rp);
                }
            }
// Prime
            if (doMirrorRabin(n)) {
                ret[rp][0] = n;
                ret[rp][1] = 1;
                rp++;
                return Arrays.copyOf(ret, rp);
            }
// P*Q
            long f = rho(n);
            if (f == -1) throw new ArithmeticException();
            if (f > n / f) f = n / f;
            ret[rp][0] = f;
            ret[rp][1] = 1;
            rp++;
            ret[rp][0] = n / f;
            ret[rp][1] = 1;
            rp++;
            return Arrays.copyOf(ret, rp);
        }

        public static boolean doMirrorRabin(long n) {
// int[] P = {2, 7, 61}; // n<4759123141
            int[] P = {2, 3, 5, 7, 11, 13, 17, 19, 23}; // n=long
            int s = Long.numberOfTrailingZeros(n - 1);
            long d = n - 1 >> s;
            outer:
            for (int a : P) {
                if (a >= n) continue;
                long mul = a;
                long ad = 1;
                for (long e = d; e > 0; e >>>= 1) {
                    if ((e & 1) == 1) ad = mul(ad, mul, n);
                    mul = mul(mul, mul, n);
                }
                if (ad == 1) continue;
                for (int r = 0; r < s; r++) {
                    if (ad == n - 1) continue outer;
                    ad = mul(ad, ad, n);
                }
                return false;
            }
            return true;
        }

        public static long mul(long a, long b, long mod) {
            a %= mod;
            long ret = 0;
            int x = 63 - Long.numberOfLeadingZeros(b);
            for (; x >= 0; x--) {
                ret += ret;
                if (ret >= mod) ret -= mod;
                if (b << ~x < 0) {
                    ret += a;
                    if (ret >= mod) ret -= mod;
                }
            }
            return ret;
        }

        static long rho(long n) {
            Random gen = new Random();
            for (int u = 0; u < 100; u++) {
                long ran = (gen.nextLong() & Long.MAX_VALUE) % n;
                long x = 2, y = 2, d = 1;
                while (d == 1) {
                    x = (mul(x, x, n) + ran) % n;
                    y = (mul(y, y, n) + ran) % n;
                    y = (mul(y, y, n) + ran) % n;
                    d = gcd(Math.abs(x - y), n);
                }
                if (d < n) return d;
            }
            return -1;
        }

        public static long gcd(long a, long b) {
            while (b > 0) {
                long c = a;
                a = b;
                b = c % b;
            }
            return a;
        }

        public static int[] sieveEratosthenes(int n) {
            if (n <= 32) {
                int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
                for (int i = 0; i < primes.length; i++) {
                    if (n < primes[i]) {
                        return Arrays.copyOf(primes, i);
                    }
                }
                return primes;
            }

            int u = n + 32;
            double lu = Math.log(u);
            int[] ret = new int[(int) (u / lu + u / lu / lu * 1.5)];
            ret[0] = 2;
            int pos = 1;

            int[] isnp = new int[(n + 1) / 32 / 2 + 1];
            int sup = (n + 1) / 32 / 2 + 1;

            int[] tprimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
            for (int tp : tprimes) {
                ret[pos++] = tp;
                int[] ptn = new int[tp];
                for (int i = (tp - 3) / 2; i < tp << 5; i += tp)
                    ptn[i >> 5] |= 1 << (i & 31);
                for (int j = 0; j < sup; j += tp) {
                    for (int i = 0; i < tp && i + j < sup; i++) {
                        isnp[j + i] |= ptn[i];
                    }
                }
            }

// 3,5,7
// 2x+3=n
            int[] magic = {0, 1, 23, 2, 29, 24, 19, 3, 30, 27, 25, 11, 20, 8, 4,
                    13, 31, 22, 28, 18, 26, 10, 7, 12, 21, 17, 9, 6, 16, 5, 15, 14};
            int h = n / 2;
            for (int i = 0; i < sup; i++) {
                for (int j = ~isnp[i]; j != 0; j &= j - 1) {
                    int pp = i << 5 | magic[(j & -j) * 0x076be629 >>> 27];
                    int p = 2 * pp + 3;
                    if (p > n)
                        break;
                    ret[pos++] = p;
                    if ((long) p * p > n)
                        continue;
                    for (int q = (p * p - 3) / 2; q <= h; q += p)
                        isnp[q >> 5] |= 1 << q;
                }
            }

            return Arrays.copyOf(ret, pos);
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
                    new TWOSQUARES().main1();
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