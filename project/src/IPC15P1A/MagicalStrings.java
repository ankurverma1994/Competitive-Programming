package IPC15P1A;
/**
 * Created by ankurverma1994 on 27/11/15.
 */

import java.io.*;
import java.util.*;

class MagicalStrings {
    //------------> Solution starts here!!
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n=ii(),m=ii();
            UF uf=new UF(n);
            HashMap<Float, String> mid=new HashMap<>();
            for(int i=0;i<m;i++){
                int l=ii()-1,r=ii()-1;
                if(!mid.containsKey((l+r)/2.0f)){
                    mid.put((l+r)/2.0f,(l+"A"+r));
                }
                else{
                    String k[]=mid.get((l+r)/2.0f).split("A");
                    int x=Integer.parseInt(k[0]);
                    int y=Integer.parseInt(k[1]);
                    if((r-l)>(y-x)){
                        mid.put((l+r)/2.0f,(l+"A"+r));
                    }
                }
            }
            Collection c=mid.values();
            Iterator i=c.iterator();
            while (i.hasNext()){
//                System.out.println(i.next().toString());
                String s[]=i.next().toString().split("A");
                int x=Integer.parseInt(s[0]);
                int y=Integer.parseInt(s[1]);
                while (x<y) {
                    uf.union(x, y);
                    x++;
                    y--;
                }
            }
            int count=uf.count();
            long ans=modpow(26,count,(int)(1e9+7));
            out.println(ans);
        }
    }
    // Complexity O(log n)
    public static long modpow(long base, int exp, int mod) {
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
                size[i]=1;
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

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            // make root of smaller rank point to root of larger rank
            if(rank[rootP] < rank[rootQ]){
                parent[rootP] = rootQ;
                size[rootQ]=size[rootP]=size[rootP]+size[rootQ];
            }
            else if (rank[rootP] > rank[rootQ]) {
                size[rootP]=size[rootQ]=size[rootP]+size[rootQ];
                parent[rootQ] = rootP;
            }
            else {
                size[rootQ]=size[rootP]=size[rootP]+size[rootQ];
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
            count--;
        }


//
//        public static void main(String[] args) {
//            Scanner StdIn=new Scanner(System.in);
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
        new MagicalStrings().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        // obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/d001951c-2-input-d001891.txt") : new ByteArrayInputStream(check.getBytes());
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
