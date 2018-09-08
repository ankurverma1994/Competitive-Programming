package aaa;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

class UTILS {
    public static void main(String[] args) {

    }

    void subset(int n, int c[]) {
//        n is length of c
        for (int counter = 0; counter < (1 << n); counter++) {
            for (int j = 0; j < n; j++) {
                if ((counter & (1 << j)) > 0) {
                    // include c[j]
                }
                // one set is complete
            }
        }
    }

    int BinaryStringConversionCount(char a[], char x, char y, int t) {
        /* minimum number of swaps needed (not contiguous) to change
         to alternate characters of x and y
         t=1 for starting from x
         t=-1 for starting from y */
        int count = 0;
        for (char c : a) {
            if (t == 1) {
                if (c != x)
                    count++;
            } else {
                if (c != y)
                    count++;
            }
            t *= -1;
        }
        return count / 2;
    }

    String cipher(String s, int shift) {
        if (shift == 0)
            return s;
        char a[] = s.toCharArray();
        for (int i = 0; i < a.length; i++) {
            int b = a[i];
            if ('A' <= a[i] && a[i] <= 'Z') {
                b = (a[i] + shift);
                if (b > 'Z')
                    b -= 26;
            } else if ('a' <= a[i] && a[i] <= 'z') {
                b = (a[i] + shift);
                if (b > 'z')
                    b -= 26;
            }
            a[i] = (char) b;
        }
        StringBuilder builder = new StringBuilder();
        for (char c : a)
            builder.append(c);
        return builder.toString();
    }

    public static long[] radixSort(long[] f) {
        long[] to = new long[f.length];
        {
            int[] b = new int[65537];
            for (int i = 0; i < f.length; i++) b[1 + (int) (f[i] & 0xffff)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < f.length; i++) to[b[(int) (f[i] & 0xffff)]++] = f[i];
            long[] d = f;
            f = to;
            to = d;
        }
        {
            int[] b = new int[65537];
            for (int i = 0; i < f.length; i++) b[1 + (int) (f[i] >>> 16 & 0xffff)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < f.length; i++) to[b[(int) (f[i] >>> 16 & 0xffff)]++] = f[i];
            long[] d = f;
            f = to;
            to = d;
        }
        {
            int[] b = new int[65537];
            for (int i = 0; i < f.length; i++) b[1 + (int) (f[i] >>> 32 & 0xffff)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < f.length; i++) to[b[(int) (f[i] >>> 32 & 0xffff)]++] = f[i];
            long[] d = f;
            f = to;
            to = d;
        }
        {
            int[] b = new int[65537];
            for (int i = 0; i < f.length; i++) b[1 + (int) (f[i] >>> 48 & 0xffff)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < f.length; i++) to[b[(int) (f[i] >>> 48 & 0xffff)]++] = f[i];
            long[] d = f;
            f = to;
            to = d;
        }
        return f;
    }
    public static int[] radixSort(int[] f){ return radixSort(f, f.length); }
    public static int[] radixSort(int[] f, int n)
    {
        int[] to = new int[n];
        {
            int[] b = new int[65537];
            for(int i = 0;i < n;i++)b[1+(f[i]&0xffff)]++;
            for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
            for(int i = 0;i < n;i++)to[b[f[i]&0xffff]++] = f[i];
            int[] d = f; f = to;to = d;
        }
        {
            int[] b = new int[65537];
            for(int i = 0;i < n;i++)b[1+(f[i]>>>16)]++;
            for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
            for(int i = 0;i < n;i++)to[b[f[i]>>>16]++] = f[i];
            int[] d = f; f = to;to = d;
        }
        return f;
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

    // finds maximum from each submatrix of size x by y. Orginal size of matrix is n by m
    /*
    for (int i = 0; i + x <= n; i++)
                for (int j = 0; j + y <= m; j++)
                    out.println(max[i][j]);
     */
    int[][] getMax(int a[][], int x, int y, int n, int m) {
        int b[][] = new int[n][m];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            q = new ArrayDeque<>();
            for (int j = 0; j < y; j++) {
                while (!q.isEmpty() && a[i][j] >= a[i][q.getLast()])
                    q.pollLast();
                q.addLast(j);
            }
            for (int j = y; j < m; j++) {
                b[i][j - y] = a[i][q.getFirst()];
                while (!q.isEmpty() && a[i][j] >= a[i][q.getLast()])
                    q.pollLast();
                while (!q.isEmpty() && q.getFirst() <= j - y)
                    q.pollFirst();
                q.addLast(j);
            }
            b[i][m - y] = a[i][q.getFirst()];
        }

        int c[][] = new int[n][m];
        for (int j = 0; j < m; j++) {
            q = new ArrayDeque<>();
            for (int i = 0; i < x; i++) {
                while (!q.isEmpty() && b[i][j] >= b[q.getLast()][j])
                    q.pollLast();
                q.addLast(i);
            }
            for (int i = x; i < n; i++) {
                c[i - x][j] = b[q.getFirst()][j];
                while (!q.isEmpty() && b[i][j] >= b[q.getLast()][j])
                    q.pollLast();
                while (!q.isEmpty() && q.getFirst() <= i - x)
                    q.pollFirst();
                q.addLast(i);
            }
            c[n - x][j] = b[q.getFirst()][j];
        }
        return c;
    }
}