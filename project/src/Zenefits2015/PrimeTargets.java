package Zenefits2015;

/**
 * Created by ankurverma1994 on 21/11/15.
 */

import java.io.*;
import java.util.*;

class PrimeTargets {
    public static void main(String[] args) throws IOException {
        new PrimeTargets().solve();
    }

    void solve() throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        prime = new boolean[31624]; //sqrt of 10^9 is 31622.77
        K = new int[100000];
        Arrays.fill(prime, true);
        sieve();
        int N = Integer.parseInt(obj.readLine());
        String s[] = obj.readLine().split(" ");
        int a[] = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        int m = 1, n = N + 1;
        if (m < 31624) {
            for (int i = m; i <= n && i < 31624; i++)
                if (prime[i]) {
                    K[idx++] = i;
                }
            if (n >= 31624)
                SegmentedSieve(31624, n);
        } else
            SegmentedSieve(m, n);
        //  System.out.println(Arrays.toString(K));
        long ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < idx; j++) {
                if ((i + K[j]) < N) {
                    ans = ans + a[i + K[j]] - a[i];
                    // System.out.println(ans);
                } else
                    break;
            }
        }
        out.println(ans);
        out.flush();
        out.close();
    }

    boolean prime[];
    int K[];
    int idx = 0;

    void sieve() {
        prime[0] = prime[1] = false;
        for (int i = 2; 2 * i < 31624; i++)
            prime[2 * i] = false;
        for (int i = 3; i < 31624; i += 2)
            if (prime[i])
                for (int j = 3; i * j < 31624; j += 2)
                    prime[i * j] = false;
    }

    void SegmentedSieve(int L, int U) {
        int d = U - L + 1;
        boolean flag[] = new boolean[d];
        Arrays.fill(flag, true);
        int j = L % 2 != 0 ? 1 : 0;
        for (int i = j; i < d; i += 2)
            flag[i] = false;
        for (int i = 3; i * i <= U; i += 2) {
            j = (L / i) * i; //nearest multiple of i
            if (j < L)
                j += i;
            for (j = j - L; j < d; j += i)
                flag[j] = false;
        }
        for (int i = 0; i < d; i++)
            if (flag[i]) {
                K[idx++] = L + i;
            }
    }
}
