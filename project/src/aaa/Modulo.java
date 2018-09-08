package aaa;

class Modulo {
    // Complexity O(log n)
    public static long modpow(long base, int exp, int mod) {
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
    public static long inversemodp(long base, int mod) {
        return modpow(base, mod - 2, mod);
    }

    /* here modulo is not prime*/
    public static long inverse(long a, long mod) {
        /*  Code picked up from "uwi" submission */
        long b = mod;
        long p = 1, q = 0;
        while (b > 0) {
            long c = a / b;
            long d;
            d = a;
            a = b;
            b = d % b;
            d = p;
            p = q;
            q = d - c * q;
        }
        return p < 0 ? p + mod : p;
    }

    //fif
    public static int[][] EnumerateFactorialAndInverseFactorial(int n, int mod) {
        /*  Code picked up from "uwi" submissions */
        int[] f = new int[n + 1];
        int[] invf = new int[n + 1];
        f[0] = 1;
        //factorial % mod
        for (int i = 1; i <= n; i++) {
            f[i] = (int) ((long) f[i - 1] * i % mod);
        }
        long a = f[n];
        long b = mod;
        long p = 1, q = 0;
        while (b > 0) {
            long c = a / b;
            long d;
            d = a;
            a = b;
            b = d % b;
            d = p;
            p = q;
            q = d - c * q;
        }
        invf[n] = (int) (p < 0 ? p + mod : p);
        // inverse factorial % mod
        for (int i = n - 1; i >= 0; i--) {
            invf[i] = (int) ((long) invf[i + 1] * (i + 1) % mod);
        }
        return new int[][]{f, invf};
    }

    public static long C(int n, int r, int mod, int[][] fif) {
        /*  Code picked up from "uwi" submissions */
        if (n < 0 || r < 0 || r > n)
            return 0;
        return (long) fif[0][n] * fif[1][r] % mod * fif[1][n - r] % mod;
    }

    public long mul(long a, long b, long mod) {
        /*Code picked up from "uwi's" previous submission on Codechef*/
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
//    @https://www.hackerearth.com/notes/powerful-tricks-with-calculation-modulo/

//    Trick #1: (A / B) % MOD = (A % (MOD * B)) / B
//    Conditions: none.
//            Advices: use this trick only if B can be not coprime with MOD,
// because new modulus = MOD * B can be large.
// How to avoid overflow working with large modulus read at the trick #5.

//    Trick #2: (A / B) % MOD = ((A % MOD) * (B^(phi(MOD) - 1 )% MOD)) % MOD
//    where phi is Euler's totient function
//    Conditions: B and MOD are coprimes.

//    Trick #4: AN % MOD = AN % phi(MOD) % MOD
//    Conditions: A and MOD are coprimes.
//            Advices: use this trick only if N can't be present in any standart data type, otherwise use Fast exponentiation.

    //    Trick #5: (A * B) % MOD
//    where MOD can't be present in int data type
//
//    function mulmod(A, B, MOD) {
//        RES = 0;
//        while (B > 0) {
//            if (B is odd) {
//                RES = (RES + A) % MOD;
//            }
//            A = (A * 2) % MOD;
//            B = B / 2;
//        }
//        return RES;
//    }
//
//    Conditions: 2 * MOD can be present in a standart data type.
//            Advices: use this trick only if (A % MOD) * (B % MOD) can't be present in any standart data type
// because of overflow and you don't want to use BigIntegers. But keep in mind that it works in O(logB) operations,
// not in O(1) as (A % MOD) * (B % MOD).
    public static void main(String[] args) {
    }
}