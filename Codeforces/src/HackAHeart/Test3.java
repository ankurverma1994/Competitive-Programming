package HackAHeart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Test3 {
    public static double ret = 100000000000.0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int mod = 1000000007;
        BigInteger MOD = BigInteger.valueOf(mod);
        while (T-- > 0) {
            args = br.readLine().split(" ");
            int n = Integer.parseInt(args[0]);
            int k = Integer.parseInt(args[1]);
            long x = BigInteger.valueOf(2).modPow(BigInteger.valueOf(k), MOD).longValue();
            System.out.println(x * (nCr(n, k, mod)) % mod);
        }
    }

    static long nCr(int n, int r, int mod) {
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
}