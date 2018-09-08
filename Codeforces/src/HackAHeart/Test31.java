import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Test31 {
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(obj.readLine());
        int max = 1000000 + 100;
        f = new long[max];
        fi = new long[max];
        f[0] = fi[0] = 1;
        int mod = (int) 1e9 + 7;
        for (int i = 1; i < max; i++) {
            f[i] = (1L * f[i - 1] * i) % mod;
            fi[i] = modpow(i, mod - 2);
        }
        for (int i = 0; i < t; i++) {
            String s[] = obj.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            long ans = (((f[n] * fi[k]) % mod) * fi[n - k]) % mod;
            ans = (ans * modpow(2, k)) % mod;
            System.out.println(ans);
        }
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

    static long f[], fi[],mod=(int)1e9+7;

}