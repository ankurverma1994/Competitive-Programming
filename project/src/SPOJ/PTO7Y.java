package SPOJ;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

// for given undirected graph check it is tree or not
class PTO7Y {
    //------------> Solution starts here!!
    void solve() {
        int n = ii(), m = ii();
        UF uf = new UF(n);
        boolean cycle = false;
        for (int i = 0; i < m; i++) {
            int x = ii() - 1, y = ii() - 1;
            if (x == y)
                cycle = true;
            if (!cycle) {
                if (uf.connected(x, y))
                    cycle = true;
                else
                    uf.union(x, y);
            }
        }
        // this part is for tree is not connected
        if (uf.count != 1)
            cycle = true;
        if (!cycle)
            out.println("YES");
        else
            out.println("NO");
    }

    static class UF {

        private int[] parent;  // parent[i] = parent of i
        private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
        private int count;     // number of components
        private int[] size;

        public UF(int N) {
            //    if (N < 0) throw new IllegalArgumentException();
            count = N;
            parent = new int[N];
            size = new int[N];
            rank = new byte[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                rank[i] = 0;
                size[i] = 1;
            }
        }

        public int find(int p) {
            //   validate(p);
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }

        public int count() {
            return count;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            // make root of smaller rank point to root of larger rank
            if (rank[rootP] < rank[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] = size[rootP] = size[rootP] + size[rootQ];
            } else if (rank[rootP] > rank[rootQ]) {
                size[rootP] = size[rootQ] = size[rootP] + size[rootQ];
                parent[rootQ] = rootP;
            } else {
                size[rootQ] = size[rootP] = size[rootP] + size[rootQ];
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
            count--;
        }

        // size of connected component in which i belongs
        public int size(int i) {
            int root = find(i);
            return size[root];
        }

        // validate that p is a valid index
        private void validate(int p) {
            int N = parent.length;
            if (p < 0 || p >= N) {
                throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N - 1));
            }
        }

//        public static void main(String[] args) {
//            Scanner StdIn = new Scanner(System.in);
//            int N = StdIn.nextInt();
//            UF uf = new UF(N);
//            while (!StdIn.hasNext()) {
//                int p = StdIn.nextInt();
//                int q = StdIn.nextInt();
//                if (uf.connected(p, q)) continue;
//                uf.union(p, q);
//                System.out.println(p + " " + q);
//            }
//            System.out.println(uf.count() + " components");
//        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new PTO7Y().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        // obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/d001951c-2-input-d001891.txt") : new ByteArrayInputStream(check.getBytes());
        try {
            solve();
        } catch (Exception e) {
            e.printStackTrace(out);
        }
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
