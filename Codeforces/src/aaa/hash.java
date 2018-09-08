package aaa;
class hash{
    static final int magic = (int) 1e5 + 1;
    static final int mod = (int) 1e9 + 7;

    static class Hashing {
        long p[];

        Hashing(char s[]) {
            p = new long[s.length];
            for (int i = 0; i < s.length; i++) {
                p[i] = s[i];
                if (i > 0)
                    p[i] += p[i - 1] * magic;
                p[i] %= mod;
            }
        }

        long getHash(int i, int j) {
            if (i == 0)
                return p[j];
            long ans = p[j];
            long left = p[i - 1];
            left *= modpow(magic, j - i + 1);
            left %= mod;
            ans -= left;
            ans %= mod;
            ans += mod;
            if (ans >= mod)
                ans -= mod;
            return ans;
        }

        public static long modpow(long base, int exp) {
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
    }
}