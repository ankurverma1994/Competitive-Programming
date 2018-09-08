package aaa;

import java.util.Arrays;
import java.util.Random;

class COMBINATORICS1 {
    public static void main(String[] args) {

    }

    /* Returns all the prime factors of n */
    public static int[] facs(int n, int[] primes) {
        /*  Code picked up from "uwi" submissions */
        int[] ret = new int[9];
        int rp = 0;
        for (int p : primes) {
            if (p * p > n) break;
            int i;
            for (i = 0; n % p == 0; n /= p, i++) ;
            if (i > 0) ret[rp++] = p;
        }
        if (n != 1) ret[rp++] = n;
        return Arrays.copyOf(ret, rp);
    }

    //factor of number. Eg: 378 = 2,3,3,3,7
    public static int[] facs(int n) {
        int ret[] = new int[1000];
        int j = 0;
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                ret[j++] = i;
                n /= i;
            }
        }
        if (n > 1) ret[j++] = n;
        return Arrays.copyOf(ret, j);
    }

    ////////////////////////////////////////////////////
    /* Enumerates all lowest prime number upto given number
    *  Eg for input 10 returned array would be
    *  [0, 0, 2, 3, 2, 5, 2, 7, 2, 3, 2]*/
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

    // returns the prime factor of factorial of number
    public static long[][] primeFactorOfFactorial(int n, int[] primes) {
        long[][] f = new long[100000][];
        int i = 0;
        for (int j = 0; j < primes.length; j++) {
            if (primes[j] > n)
                break;
            long p = primes[j], x = primes[j];
            long count = 0;
            while ((n / p) > 0) {
                count += (n / p);
                p = p * x;
            }
            f[i++] = new long[]{x, count};
        }
        return Arrays.copyOf(f, i);
    }

    public static int[] factorSeqFast(int n, int[] lpf) {
        /*  Code picked up from "uwi" submissions */
        int[] seq = new int[26];
        int p = 0;
        while (n > 1) {
            seq[p++] = lpf[n];
            n /= lpf[n];
        }
        return Arrays.copyOf(seq, p);
    }

    // lpf means Lowest Prime Factors
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
    //////////////////////////////////////////////////////////////////
    /* Returns list of all prime numbers in an array*/

    public static int[] sieveEratosthenes(int n) {
        /*  Code picked up from "uwi" submissions */
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
        int[] magic = {0, 1, 23, 2, 29, 24, 19, 3, 30, 27, 25, 11, 20, 8, 4,
                13, 31, 22, 28, 18, 26, 10, 7, 12, 21, 17, 9, 6, 16, 5, 15, 14};
        int h = n / 2;
        for (int i = 0; i < sup; i++) {
            for (int j = ~isnp[i]; j != 0; j &= j - 1) {
                int pp = i << 5 | magic[(j & -j) * 0x76be629 >>> 27];
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

    //////////////////////////////////////////////////////////////
    /*Decodes the number in base d and returns the array of length n*/
    /*  Code picked up from "uwi" submission */
    static int[] dec(int code, int n, int d) {
        /*  Code picked up from "uwi" submissions */
        int[] a = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            a[i] = code % d;
            code /= d;
        }
        return a;
    }

    /*Encoded the number in base d to original number*/
    /*  Code picked up from "uwi" submission */
    static int enc(int[] a, int d) {
        /*  Code picked up from "uwi" submissions */
        int code = 0;
        for (int i = 0; i < a.length; i++) {
            code = code * d + a[i];
        }
        return code;
    }
    /*
		public static void sieve(int n) {
		prime = new boolean[n + 1];
		Arrays.fill(prime, true);
		prime[0] = prime[1] = false;
 
		for (int i = 2; i * i <= n; i++)
			if (prime[i])
				for (int k = i * i; k <= n; k += i)
					prime[k] = false;
	}

		public static void findMobious(int n) {
		u = new int[n + 1];
		Arrays.fill(u, 1);
 
		for (int i = 2; i <= n; i++) {
			if (prime[i]) {
				for (int j = i; j <= n; j += i) {
					u[j] *= -1;
					if (j % (i * i) == 0)
						u[j] = 0;
				}
			}
		}
 
		u[0] = 0;
	}
	*/

    ///////////////////////////////////////////////////////////////////////
    // reduces elements in array like
    // 1000000000, 100000, 25550, 0, 1, 2, 1531333,0 ---> [6, 4, 3, 0, 1, 2, 5, 0]
    public static int[] shrink(int[] a) {
        /*  Code picked up from "uwi" submissions */
        int n = a.length;
        long[] b = new long[n];
        for (int i = 0; i < n; i++)
            b[i] = (long) a[i] << 32 | i;
        Arrays.sort(b);
        int[] ret = new int[n];
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && (b[i] ^ b[i - 1]) >> 32 != 0)
                p++;
            ret[(int) b[i]] = p;
        }
        return ret;
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
}
