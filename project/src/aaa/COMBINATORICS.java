/**
 * @author ankurverma1994
 */
package aaa;

class COMBINATORICS {
    public static void main(String[] args) {
    }
    /*
    long rs[][]=new long[][n][2];
            Arrays.sort(rs, new Comparator<long[]>() {
                public int compare(long[] a, long[] b) {
                    return Long.compare(a[0], b[0]);
                }
            });
     */
    /*
    * Arrays.sort(rem, (aa, bb) -> Integer.compare(aa[0], bb[0]));
    * */

    public static int[] radixSort(int[] f) {
        /*  Code picked up from "uwi" submissions */
        int[] to = new int[f.length];
        {
            int[] b = new int[65537];
            for (int i = 0; i < f.length; i++) b[1 + (f[i] & 65535)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < f.length; i++) to[b[f[i] & 65535]++] = f[i];
            int[] d = f;
            f = to;
            to = d;
        }
        {
            int[] b = new int[65537];
            for (int i = 0; i < f.length; i++) b[1 + (f[i] >>> 16)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < f.length; i++) to[b[f[i] >>> 16]++] = f[i];
            int[] d = f;
            f = to;
            to = d;
        }
        return f;
    }

    int gcd(int a, int b) {
        while (b > 0) {
            int c = a;
            a = b;
            b = c % b;
        }
        return a;
    }

    // returns { gcd(a,b), x, y } such that gcd(a,b) = a*x + b*y
    public static long[] euclid(long a, long b) {
        long x = 1, y = 0, x1 = 0, y1 = 1, t;
        while (b != 0) {
            long q = a / b;
            t = x;
            x = x1;
            x1 = t - q * x1;
            t = y;
            y = y1;
            y1 = t - q * y1;
            t = b;
            b = a - q * b;
            a = t;
        }
        return a > 0 ? new long[]{a, x, y} : new long[]{-a, -x, -y};
    }

    boolean powerOfTwo(int x) {
        if (x == 0)
            return false;
        return ((x & (x - 1)) == 0);
    }

    boolean isPowerOfTwo(int x) {
        while (((x % 2) == 0) && x > 1) /* While x is even and > 1 */
            x /= 2;
        return (x == 1);
    }

    boolean palindrome(String s) {
        int l = s.length();
        for (int i = 0; i <= l / 2; i++) {
            if (s.charAt(i) != s.charAt(l - i - 1))
                return false;
        }
        return true;
    }

    long nextPrime(long from) {
        if (from <= 2)
            return 2;
        from += 1 - (from & 1);
        while (!isPrime(from))
            from += 2;
        return from;
    }

    boolean isPrime(long number) {
        if (number < 2)
            return false;
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    /*nCr using recurence relation
    *      nCr = (n-1)C(r-1) + (n-1)C(r)
    *      Complexity O(nr)
    */
    long nCr(int n, int r, int mod) {
        long C[][] = new long[n + 1][r + 1];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= r && k <= i; k++)
                if (k == 0 || k == i)
                    C[i][k] = 1;
                else
                    C[i][k] = (C[i - 1][k - 1] + C[i - 1][k]) % mod;
        }
        return C[n][r];
    }

    /*without mod and better complexity*/
    long C(int n, int m) {
        if (m > n - m) m = n - m;
        long ans = 1;
        for (int i = 0; i < m; i++) ans = ans * (n - i) / (i + 1);
        return ans;
    }

    int numberOfDivisors(int x) {
        int count = 0;
        for (int i = 1; i * i <= x; i++) {
            if (x % i == 0) {
                count++;
                int r = x / i;
                if (r != i)
                    count++;
            }
        }
        return count;
    }

    void primeFactors(int n, int[] a) {
        int[] temp = new int[101];
        while (n % 2 == 0) {
            temp[2]++;
            n = n / 2;
        }
        if (temp[2] > a[2])
            a[2] = temp[2];
        // n must be odd at this point.  So we can skip one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i = i + 2) {
            // While i divides n, print i and divide n
            while (n % i == 0) {
                temp[i]++;
                if (temp[i] > a[i]) a[i] = temp[i];
                n = n / i;
            }
        }
        // This condition is to handle the case whien n is a prime number
        // greater than 2
        if (n > 2)
            temp[n]++;
        if (temp[n] > a[n])
            a[n] = temp[n];
    }
}
