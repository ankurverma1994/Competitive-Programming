package aaa;

class EulerTOITENT {
    public static void main(String[] args) {

    }

    public static int[] enumTotientByLpf(int n, int[] lpf) {
        int[] ret = new int[n + 1];
        ret[1] = 1;
        for (int i = 2; i <= n; i++) {
            int j = i / lpf[i];
            if (lpf[j] != lpf[i]) {
                ret[i] = ret[j] * (lpf[i] - 1);
            } else {
                ret[i] = ret[j] * lpf[i];
            }
        }
        return ret;
    }

    public static long sumTotient(int n) {
        if (n == 0) return 0L;
        if (n == 1) return 1L;

        int s = (int) Math.sqrt(n);
        long[] cacheu = new long[n / s];
        long[] cachel = new long[s + 1];
//		Arrays.fill(cacheu, -1);
//		Arrays.fill(cachel, -1);

        // fill cache
        int X = (int) Math.pow(n, 0.66);
        int[] lpf = enumLowestPrimeFactors(X);
        int[] tot = enumTotientByLpf(X, lpf);
        long sum = 0;
        int p = cacheu.length - 1;
        for (int i = 1; i <= X; i++) {
            sum += tot[i];
            if (i <= s) {
                cachel[i] = sum;
            } else if (p > 0 && i == n / p) {
                cacheu[p] = sum;
                p--;
            }
        }

        // go
        for (int i = p; i >= 1; i--) {
            int x = n / i;
            long all = (long) x * (x + 1) / 2;
            int ls = (int) Math.sqrt(x);
            for (int j = 2; x / j > ls; j++) {
                long lval = i * j < cacheu.length ? cacheu[i * j] : cachel[x / j];
                all -= lval;
            }
            for (int v = ls; v >= 1; v--) {
                long w = x / v - x / (v + 1);
                all -= cachel[v] * w;
            }
            cacheu[(int) i] = all;
        }
        return cacheu[1];
    }

    public static int[] enumLowestPrimeFactors(int n) {
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
}