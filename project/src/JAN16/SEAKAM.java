package JAN16;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class SEAKAM {
    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        double t1 = System.currentTimeMillis();
        int mod = (int) (1e9 + 7);
        int fac[] = new int[(int) (1e5 + 5)];
        int pow2[] = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};
        fac[0] = 1;
        for (int i = 1; i <= (int) 1e5; i++) {
            fac[i] = (int) (((long) i * fac[i - 1]) % mod);
        }
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), m = ii();
            int x[] = new int[m];
            int y[] = new int[m];
            for (int i = 0; i < m; i++) {
                x[i] = ii() - 1;
                y[i] = ii() - 1;
            }
//            bruteforce(n, m, x, y);
            long ans = fac[n];
            for (int mask = 0; mask < (1 << m); mask++) {

                boolean loop = false, selfLoop = false;
                int degree = 0, b = 0, edges = 0;
                HashMap<Integer, Integer> map = new HashMap<>();
                UF uf = new UF(n);

                for (int bit = 0; bit < m; bit++) {
                    if ((mask & (1 << bit)) > 0) {
//                        out.print(bit + " ");
                        if (x[bit] == y[bit]) {
                            selfLoop = true;
                            break;
                        }
                        if (!map.containsKey(x[bit]))
                            map.put(x[bit], 1);
                        else {
                            degree = map.get(x[bit]) + 1;
                            if (degree > 2) break;
                            map.put(x[bit], degree);
                        }
                        if (!map.containsKey(y[bit]))
                            map.put(y[bit], 1);
                        else {
                            degree = map.get(y[bit]) + 1;
                            if (degree > 2) break;
                            map.put(y[bit], degree);
                        }

                        if (uf.connected(x[bit], y[bit])) {
                            loop = true;
                            break;
                        }
                        if (uf.size[x[bit]] == 1 && uf.size[y[bit]] == 1)
                            b++;
                        else if (uf.size[x[bit]] > 1 && uf.size[y[bit]] > 1)
                            b--;
                        uf.union(x[bit], y[bit]);
                        edges++;
                    }
                }
//                out.println();
//                out.println(b + "  " + uf.comp);
                if (selfLoop || loop || degree > 2 || edges == 0) {
//                    out.println("break " + mask);
                    continue;
                }
                if ((edges & 1) == 0) {
                    ans = (ans + pow2[b] * (long) fac[uf.comp]) % mod;
                } else {
                    ans = (ans - pow2[b] * (long) fac[uf.comp] + mod) % mod;
                    while (ans < 0)
                        ans += mod;
                }
//                out.println("Current answer " + ans);
            }
            out.println(ans);
        }
        double t2 = System.currentTimeMillis();
//        out.println(t2 - t1);
    }

    public static class UF {

        // parent[i] = parent of i
        // rank[i] = rank of subtree rooted at i (never more than 31)
        public int rank[], parent[], size[];
        public int comp, n;     // number of components

        public UF(int N) {
            comp = n = N;
            parent = new int[N];
            size = new int[N];
            rank = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            size[rootQ] = size[rootP] = size[rootP] + size[rootQ];


            // make root of smaller rank point to root of larger rank
            if (rank[rootP] < rank[rootQ]) {
                parent[rootP] = rootQ;
            } else if (rank[rootP] > rank[rootQ]) {
                parent[rootQ] = rootP;
            } else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
            comp--;
        }

        // size of connected component in which i belongs
        public int size(int i) {
            int root = find(i);
            return size[root];
        }
    }


    void bruteforce(int n, int m, int a[], int b[]) {
        int num[] = new int[n];
        for (int i = 0; i < n; i++)
            num[i] = (i + 1);
        int x[] = new int[m];
        int y[] = new int[m];
        for (int i = 0; i < m; i++) {
            x[i] = a[i] + 1;
            y[i] = b[i] + 1;
        }
        permute(num, n, 0, m, x, y);
        out.println("bruteforce " + count1);
    }

    int count1 = 0;

    void permute(int ints[], int n, int i, int m, int ints1[], int ints2[]) {
        if (i == n) {
            boolean waah = true;
            for (int i1 = 0; i1 < m; i1++) {
                int i2 = 0;
                for (; i2 < n; i2++)
                    if (ints[i2] == ints1[i1])
                        break;
                if (i2 > 0)
                    if (ints[i2 - 1] == ints2[i1])
                        waah = false;
                if (i2 < n - 1)
                    if (ints[i2 + 1] == ints2[i1])
                        waah = false;
            }
            if (waah) {
                count1++;
//                for(j=0;j<n;j++)
//                    out.print(v[j]+" ");
//                out.println();
            }
        } else {
            for (int i2 = i; i2 < n; i2++) {
                int i1 = ints[i];
                ints[i] = ints[i2];
                ints[i2] = i1;
                permute(ints, n, i + 1, m, ints1, ints2);
                i1 = ints[i];
                ints[i] = ints[i2];
                ints[i2] = i1;
            }
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new SEAKAM().main1();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StackOverflowError e) {
                    System.out.println("RTE");
                }
            }
        }, "1", 1 << 26).start();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//         obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/input.txt") : new ByteArrayInputStream(check.getBytes());
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