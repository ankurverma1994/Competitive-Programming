package SearDotsAndArrow;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbD {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
//        int n = 5, Q = 3;
//        System.out.println(n + " " + Q);
        int n = ii(), Q = ii();
        this.n = n;
        taken = new boolean[n + 2];
        int a[] = iia(n);
//        int a[] = new int[n];
//        for (int i = 1; i <= n; i++) a[i - 1] = i;
//        a = shuffle(a, new Random());
//        System.out.println(Arrays.toString(a));
        int BLOCK_SIZE = (int) (2 * Math.floor(Math.sqrt(n)));
        int queryl[][] = new int[Q][2];
        int queryr[] = new int[Q];
        int mo_answer[] = new int[Q];
        for (int i = 0; i < Q; i++) {
            queryl[i][0] = ii() - 1;
            queryl[i][1] = i;
            queryr[i] = ii() - 1;
        }
//        Random rnd = new Random();
//        for (int i = 0; i < Q; i++) {
//            queryl[i][0] = rnd.nextInt(n);
//            queryl[i][1] = i;
//            int r = rnd.nextInt(n);
//            while (r < queryl[i][0]) r = rnd.nextInt(n);
//            queryr[i] = r;
//            lambai[i] = queryl[i][0] + 1;
//            R[i] = queryr[i] + 1;
//            System.out.println((queryl[i][0] + 1) + " " + (queryr[i] + 1));
//        }

        Arrays.sort(queryl, (int x[], int b[]) -> {
            int block_a = x[0] / BLOCK_SIZE;
            int block_b = b[0] / BLOCK_SIZE;
            if (block_a != block_b) {
                if (block_a > block_b)
                    return 1;
                return -1;
            }
            if (queryr[x[1]] > queryr[b[1]])
                return 1;
            return -1;
        });
//        System.out.println("New Query");
//        for (int i = 0; i < Q; i++) {
//            System.out.println((queryl[i][0] + 1) + " " + (queryr[queryl[i][1]] + 1));
//        }
        int mo_left = 0, mo_right = -1;
        current_answer = 0;
        for (int i = 0; i < Q; i++) {
            int left = queryl[i][0];
            int right = queryr[queryl[i][1]];
            while (mo_right < right) {
                mo_right++;
                add(a[mo_right]);
//                if (i == 2) {
//                    print(mo_left, mo_right);
//                    System.out.println("A");
//                }
            }
            while (mo_right > right) {
                remove(a[mo_right]);
                mo_right--;
//                if (i == 2) {
//                    print(mo_left, mo_right);
//                    System.out.println("B");
//                }
            }
            while (mo_left < left) {
                remove(a[mo_left]);
                mo_left++;
//                if (i == 2) {
//                    print(mo_left, mo_right);
//                    System.out.println("C");
//                }
            }
            while (mo_left > left) {
                mo_left--;
                add(a[mo_left]);
//                if (i == 2) {
//                    print(mo_left, mo_right);
//                    System.out.println("D");
//                }
            }
            mo_answer[queryl[i][1]] = current_answer;
//            out.println(Arrays.toString(taken));
        }
        for (int i = 0; i < Q; i++)
            out.println(mo_answer[i]);
//        out.println("brute");
//        brrute(n, Q, a,lambai,R);
    }

    void print(int l, int r) {
        System.out.println(l + " " + r + " " + Arrays.toString(taken));
    }

    int n;
    int current_answer;
    boolean taken[];

    void add(int x) {
        if (!taken[x]) {
            taken[x] = true;
            if (taken[x - 1] && taken[x + 1])
                current_answer--;
            if (!taken[x - 1] && !taken[x + 1])
                current_answer++;
        }
    }

    void remove(int x) {
        if (taken[x]) {
            taken[x] = false;
            if (taken[x - 1] && taken[x + 1])
                current_answer++;
            if (!taken[x - 1] && !taken[x + 1])
                current_answer--;
        }
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

    void brrute(int n, int q, int a[], int x[], int y[]) {

        this.n = n;
        for (int i = 0; i < n; i++) {
            a[i]--;
        }
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = i;
            to[i] = i + 1;
        }
        g = packU(n, from, to, n - 1);
        for (int Q = 0; Q < q; Q++) {
            int count = 0;
            can = new boolean[n];
            visit = new boolean[n];
            x[Q]--;
            y[Q]--;
            for (int i = x[Q]; i <= y[Q]; i++) {
                can[a[i]] = true;
            }
            for (int i = x[Q]; i <= y[Q]; i++) {
                if (!visit[a[i]]) {
                    count++;
                    dfs(a[i]);
                }
            }
            out.println(count);
        }
    }

    boolean can[] = new boolean[n];
    boolean visit[] = new boolean[n];
    int g[][];

    void dfs(int curr) {
        visit[curr] = true;
        for (int next : g[curr]) {
            if (!visit[next] && can[next])
                dfs(next);
        }
    }

    public static int[][] packU(int n, int[] from, int[] to, int max) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int i = 0; i < max; i++) p[from[i]]++;
        for (int i = 0; i < max; i++) p[to[i]]++;
        for (int i = 0; i < n; i++) g[i] = new int[p[i]];
        for (int i = 0; i < max; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new ProbD().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        // obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/input.txt") : new ByteArrayInputStream(check.getBytes());
        solve();
        out.flush();
        out.close();
    }

    byte inbuffer[] = new byte[1024];
    int lenbuffer = 0, ptrbuffer = 0;

    int readByte() {
        if (lenbuffer == -1) throw new InputMismatchException();
        if (ptrbuffer >= lenbuffer) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if (lenbuffer <= 0) return -1;
        return inbuffer[ptrbuffer++];
    }

    String is() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    int ii() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    long il() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    float nf() {
        return Float.parseFloat(is());
    }

    double id() {
        return Double.parseDouble(is());
    }

    char ic() {
        return (char) skip();
    }

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }

    long[] ila(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++) a[i] = il();
        return a;
    }

    String[] isa(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++) a[i] = is();
        return a;
    }

    double[][] idm(int n, int m) {
        double a[][] = new double[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = id();
        return a;
    }

    int[][] iim(int n, int m) {
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = ii();
        return a;
    }
}
