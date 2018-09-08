package SamsungRandD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class prob {
    long DP[][];
    int mod = 1000000000 + 7;

    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new prob().solution();
            } catch (StackOverflowError e) {
                System.out.println("RTE");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "1", 1 << 26).start();
    }

    void solution() throws IOException {
        boolean primes[] = new boolean[100000 + 111];
        for (int i = 0; i < 100000 + 11; i++)
            primes[i] = true;
        for (int p = 2; p * p <= 100000 + 11; p++) {
            if (primes[p]) {
                for (int i = p * 2; i <= 100000 + 11; i += p)
                    primes[i] = false;
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ar[] = br.readLine().split(" ");
        int n = Integer.parseInt(ar[0]), m = Integer.parseInt(ar[1]);
        int arr[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            ar = br.readLine().split(" ");
            for (int j = 0; j < m; j++)
                arr[i][j] = Integer.parseInt(ar[j]);
        }
        DP = new long[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                DP[i][j] = -11;
            }
        }
        long x = possible(0, 0, n, m, primes, arr);
        System.out.println(x);
        if (x > 0) generatePaths(0, 0, n, m, primes, arr);
    }

    long possible(int i, int j, int n, int m, boolean P[], int arr[][]) {
        if (i >= n || i < 0 || j >= m || j < 0)
            return 0;
        if (DP[i][j] != -11)
            return DP[i][j];
        if (i == n - 1 && j == m - 1)
            return DP[i][j] = 1;
        long ret = 0;
        if (canReach(i + 1, j + 1, n, m) && P[arr[i + 1][j + 1]]) {
            ret += possible(i + 1, j + 1, n, m, P, arr);
            if (ret >= mod)
                ret %= mod;
        }
        if (canReach(i + 1, j, n, m) && P[arr[i + 1][j]]) {
            ret += possible(i + 1, j, n, m, P, arr);
            if (ret >= mod)
                ret %= mod;
        }
        if (canReach(i, j + 1, n, m) && P[arr[i][j + 1]]) {
            ret += possible(i, j + 1, n, m, P, arr);
            if (ret >= mod)
                ret %= mod;
        }
        DP[i][j] = ret;
        return DP[i][j];
    }

    boolean canReach(int i, int j, int n, int m) {
        return 0 <= i && i < n && 0 <= j && j < m;
    }


    void generatePaths(int i, int j, int n, int m, boolean prime[], int arr[][]) {
        System.out.println((i + 1) + " " + (j + 1));
        if (i == n - 1 && j == m - 1)
            return;
        if (canReach(i + 1, j + 1, n, m) && DP[i + 1][j + 1] > 0 && prime[arr[i + 1][j + 1]])
            generatePaths(i + 1, j + 1, n, m, prime, arr);
        else if (canReach(i + 1, j, n, m) && DP[i + 1][j] > 0 && prime[arr[i + 1][j]])
            generatePaths(i + 1, j, n, m, prime, arr);
        else if (canReach(i, j + 1, n, m) && DP[i][j + 1] > 0 && prime[arr[i][j + 1]])
            generatePaths(i, j + 1, n, m, prime, arr);
    }
}