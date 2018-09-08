package FEB16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class HLD {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        n = ii();
        m = ii();
        a = iia(n);
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = ii() - 1;
            to[i] = ii() - 1;
        }
        g = packU(n, from, to, n - 1);
        int[][] pars = parents3(g, 0);
        par = pars[0];
        int[] ord = pars[1];
        dep = pars[2];
        spar = logstepParents(par);
        subTree = new int[n];
        dfs(0, -1);
        pointer = 1;
        baseArray = new int[n + 1];
        chainNo = 0;
        chainInHead = new int[n];
        Arrays.fill(chainInHead, -1);
        posInBase = new int[n];
        chainInInd = new int[n];
        HLD(0, -1);
        makeTree();
        out.println(Arrays.toString(baseArray));
        out.println(Arrays.toString(a));
        for (int q = 0; q < m; q++) {
            int type = ii();
            if (type == 1) {
                int u = ii() - 1, v = ii() - 1;
                query(u, v);
                out.println("DONE");
            } else {
                int u = ii(), v = ii();
                // update
            }
        }
    }

    int n, m, a[], g[][], pointer, chainNo;
    int subTree[], baseArray[];
    int chainInHead[], posInBase[], chainInInd[];
    HashMap<Integer, Integer> map;
    HashMap<Integer, TreeSet<Integer>> set;
    int spar[][], dep[], par[];

    int query_up(int u, int v) {
//        if (u == v) return 0; // Trivial
        int uchain, vchain = chainInInd[v], ans = 0;
        // uchain and vchain are chain numbers of u and v
        int last = -1;
        while (true) {
            uchain = chainInInd[u];
            if (uchain == vchain) {
                // Both u and v are in the same chain, so we need to query from u to v, update answer and break.
                // We break because we came from u up till v, we are done
                if (u == v) break;
                out.println("query = " + (posInBase[v]) + " " + (posInBase[u]));
                out.println(query_tree(1, posInBase[v], posInBase[u], 1, pointer));
                ans += query_tree(1, posInBase[v], posInBase[u], 1, pointer);
                // Above is call to segment tree query function
                break;
            }
            out.println("query = " + posInBase[chainInHead[uchain]] + " " + (posInBase[u]));
            out.println(query_tree(1, posInBase[chainInHead[uchain]], posInBase[u], 1, pointer));
            ans += query_tree(1, posInBase[chainInHead[uchain]], posInBase[u], 1, pointer);
            // Above is call to segment tree query function. We do from chainHead of u till u. That is the whole chain from
            // start till head. We then update the answer
            u = chainInHead[uchain]; // move u to u's chainHead
            u = par[u]; //Then move to its parent, that means we changed chains
        }
        out.println(ans+" =ans");
        return ans;
    }

    int query_tree(int v, int l, int r, int tl, int tr) {
//        out.println(l + " " + r + " " + tl + " " + tr);
        int ans = query(1, l, r, r, tl, tr);
        return r - l + 1 - ans;
    }

    void query(int u, int v) {
    /*
     * We have a query from u to v, we break it into two queries, u to LCA(u,v) and LCA(u,v) to v
	 */
        int lca = lca2(u, v, spar, dep);
//        out.println(query_up(u, lca) + " dv");
//        out.println(query_up(v, lca) + " ds");
        int ans = query_up(u, lca) + query_up(v, lca); // One part of path
        out.println(ans);
//        if (temp > ans) ans = temp; // take the maximum of both paths
//        printf("%d\n", ans);
    }

    void makeTree() {
        segmentTree = new Node[4 * n];
        for (int i = 0; i < 4 * n; i++) {
            segmentTree[i] = new Node(Integer.MAX_VALUE);
            segmentTree[i].priority = Long.MAX_VALUE;
        }
        a = new int[n + 1];
        map = new HashMap<>();
        for (int i = n; i > 0; i--) {
            if (map.containsKey(baseArray[i])) {
                a[i] = map.get(baseArray[i]);
                map.put(baseArray[i], i);
            } else {
                a[i] = n + 10;
                map.put(baseArray[i], i);
            }
        }
        set = new HashMap<>();
        TreeSet<Integer> temp = new TreeSet<>();
        for (int i = 1; i <= n; i++) {
            if (set.containsKey(baseArray[i])) {
                temp = set.get(baseArray[i]);
                temp.add(i);
                set.put(baseArray[i], temp);
            } else {
                temp.clear();
                temp.add(-1);
                temp.add(n + 10);
                temp.add(i);
                set.put(baseArray[i], temp);
            }
        }
        // build
        for (int i = 1; i <= n; i++) {
            set(1, i, a[i], 1, n);
        }
    }

    void HLD(int curr, int prev) {
        if (chainInHead[chainNo] == -1) {
            chainInHead[chainNo] = curr;
        }
        chainInInd[curr] = chainNo;
        posInBase[curr] = pointer;
        baseArray[pointer++] = a[curr];

        int special = -1;
        for (int next : g[curr]) {
            if (next != prev) {
                if (special == -1 || subTree[special] < subTree[next]) {
                    special = next;
                }
            }
        }
        if (special != -1)
            HLD(special, curr);
        for (int next : g[curr]) {
            if (next != prev && next != special) {
                chainNo++;
                HLD(next, curr);
            }
        }
    }

    void dfs(int curr, int prev) {
        subTree[curr] = 1;
        for (int next : g[curr]) {
            if (next != prev) {
                dfs(next, curr);
                subTree[curr] += subTree[next];
            }
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

    public static int lca2(int a, int b, int[][] spar, int[] depth) {
        if (depth[a] < depth[b]) {
            b = ancestor(b, depth[b] - depth[a], spar);
        } else if (depth[a] > depth[b]) {
            a = ancestor(a, depth[a] - depth[b], spar);
        }

        if (a == b)
            return a;
        int sa = a, sb = b;
        for (int low = 0, high = depth[a], t = Integer.highestOneBit(high), k = Integer
                .numberOfTrailingZeros(t); t > 0; t >>>= 1, k--) {
            if ((low ^ high) >= t) {
                if (spar[k][sa] != spar[k][sb]) {
                    low |= t;
                    sa = spar[k][sa];
                    sb = spar[k][sb];
                } else {
                    high = low | t - 1;
                }
            }
        }
        return spar[0][sa];
    }

    protected static int ancestor(int a, int m, int[][] spar) {
        for (int i = 0; m > 0 && a != -1; m >>>= 1, i++) {
            if ((m & 1) == 1)
                a = spar[i][a];
        }
        return a;
    }

    public static int[][] logstepParents(int[] par) {
        int n = par.length;
        int m = Integer.numberOfTrailingZeros(Integer.highestOneBit(n - 1)) + 1;
        int[][] pars = new int[m][n];
        pars[0] = par;
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                pars[j][i] = pars[j - 1][i] == -1 ? -1 : pars[j - 1][pars[j - 1][i]];
            }
        }
        return pars;
    }


    public static int[][] parents3(int[][] g, int root) {
        int n = g.length;
        int[] par = new int[n];
        Arrays.fill(par, -1);

        int[] depth = new int[n];
        depth[0] = 0;

        int[] q = new int[n];
        q[0] = root;
        for (int p = 0, r = 1; p < r; p++) {
            int cur = q[p];
            for (int nex : g[cur]) {
                if (par[cur] != nex) {
                    q[r++] = nex;
                    par[nex] = cur;
                    depth[nex] = depth[cur] + 1;
                }
            }
        }
        return new int[][]{par, q, depth};
    }

    Node segmentTree[];

    int query(int v, int l, int r, int x, int tl, int tr) {
        if (l > tr || tl > r) return 0;
        if (l <= tl && tr <= r) {
            return countLE(segmentTree[v], x);
        }
        int tm = (tl + tr) / 2;
        return query(v + v, l, r, x, tl, tm) + query(v + v + 1, l, r, x, tm + 1, tr);
    }

    void set(int v, int pos, int value, int tl, int tr) {
        insert(segmentTree[v], new Node(value)); //it means we add value in treap of the node v
        if (tl == tr) return;
        int tm = (tl + tr) / 2;
        if (pos <= tm) set(v + v, pos, value, tl, tm);
        else set(v + v + 1, pos, value, tm + 1, tr);
    }

    void update(int v, int pos, int value, int tl, int tr, int old) {
        remove(find(segmentTree[v], old));
        insert(segmentTree[v], new Node(value)); //it means we add value in treap of the node v
        if (tl == tr) return;
        int tm = (tl + tr) / 2;
        if (pos <= tm) update(v + v, pos, value, tl, tm, old);
        else update(v + v + 1, pos, value, tm + 1, tr, old);
    }

    class Node {
        public int v; // value
        public int dup; // multiplicity
        public long priority;
        public Node left, right, parent;

        public int count;

        public Node(int v) {
            this.v = v;
            this.dup = 1;
            this.parent = null;
            priority = gen.nextLong();
            count = 1;
        }

        public void update() {
            count = dup;
            if (left != null) count += left.count;
            if (right != null) count += right.count;
        }

        public String toString() {
            System.out.println("HELLO");
            StringBuilder sb = new StringBuilder();
            if (dup > 1) {
                sb.append(v + "x" + dup);
            } else {
                sb.append(v);
            }
            return sb.toString();
        }

        public String toString(String indent) {
            StringBuilder sb = new StringBuilder();
            if (left != null) sb.append(left.toString(indent + "  "));
            sb.append(indent).append(this).append("\n");
            if (right != null) sb.append(right.toString(indent + "  "));
            return sb.toString();
        }
    }

    protected static Random gen = new Random();

    // count less than equal too
    public static int countLE(Node n, int x) {
        if (n == null) return 0;
        if (n.parent != null && n.parent.left == n && n.parent.v <= x) {
            return n.count;
        } else {
            int ret = countLE(n.left, x);
            if (n.v <= x) ret += n.dup;
            if (n.v < x) ret += countLE(n.right, x);
            return ret;
        }
    }

//	public static Node first(Node n, int len)
//	{
//		if(n == null)return null;
//		Node res = first(n.left, len);
//		if(res != null)return res;
//		if(n.r-n.l >= len)return n;
//		return first(n.right, len);
//	}
//
//	public static Node ceil(Node n, int x)
//	{
//		if(n == null)return null;
//		if(n.l <= x && x < n.r)return n;
//		if(x < n.l){
//			Node res = ceil(n.left, x);
//			if(res == null)res = n;
//			return res;
//		}
//		return ceil(n.right, x);
//	}

    public static Node insert(Node n, Node x) {
        if (n == null) {
            return x;
        }
        if (x.v == n.v) {
            // already inserted
            n.dup++;
        } else if (x.v < n.v) {
            n.left = insert(n.left, x);
            n.left.parent = n;
            if (n.priority < n.left.priority) {
                n = rotateRight(n);
                if (n.right != null) n.right.update();
            }
        } else {
            n.right = insert(n.right, x);
            n.right.parent = n;
            if (n.priority < n.right.priority) {
                n = rotateLeft(n);
                if (n.left != null) n.left.update();
            }
        }
        if (n.left == x || n.right == x) x.update();
        n.update();
        return n;
    }

    public static void disconnect(Node n) {
        if (n == null) return;
        n.left = n.right = n.parent = null;
        n.update();
    }

    public static void propagate(Node n) {
        for (; n != null; n = n.parent) n.update();
    }

    public static Node find(Node n, int x) {
        if (n == null) return null;
        if (x == n.v) return n;
        if (x < n.v) return find(n.left, x);
        return find(n.right, x);
    }

    public static Node remove(Node t) {
        while (true) {
            if (t == null) return null;
            t.dup--;
            if (t.dup > 0) {
                propagate(t);
                return t;
            }
            if (t.left == null && t.right == null) {
                // leaf
                if (t.parent != null) {
                    if (t.equals(t.parent.left)) t.parent.left = null;
                    if (t.equals(t.parent.right)) t.parent.right = null;
                    propagate(t.parent);
                }
                disconnect(t);
                break;
            } else if (t.left != null && t.right != null) {
                if (t.left.priority < t.right.priority) {
                    rotateRight(t);
                } else {
                    rotateLeft(t);
                }
                continue;
            } else if (t.left != null) {
                Node p = t.parent;
                Node c = t.left;
                if (p != null) {
                    if (t.equals(p.left)) p.left = c;
                    if (t.equals(p.right)) p.right = c;
                }
                c.parent = p;
                disconnect(t);
                if (p != null) propagate(p);
                break;
            } else if (t.right != null) {
                Node p = t.parent;
                Node c = t.right;
                if (p != null) {
                    if (t.equals(p.left)) p.left = c;
                    if (t.equals(p.right)) p.right = c;
                }
                c.parent = p;
                disconnect(t);
                propagate(p);
                break;
            }
        }
        return t;
    }

    private static Node rotateRight(Node n) {
        Node A = n;
        Node B = n.left;
        Node E = B != null ? B.right : null;
        Node H = A.parent;
        if (H != null) {
            if (A.equals(H.right)) {
                H.right = B;
            } else {
                H.left = B;
            }
        }
        if (B != null) B.parent = H;
        if (B != null) B.right = A;
        A.parent = B;
        A.left = E;
        if (E != null) E.parent = A;
        return B;
    }

    private static Node rotateLeft(Node n) {
        Node A = n;
        Node C = n.right;
        Node F = C != null ? C.left : null;
        Node H = A.parent;
        if (H != null) {
            if (A.equals(H.right)) {
                H.right = C;
            } else {
                H.left = C;
            }
        }
        if (C != null) C.parent = H;
        if (C != null) C.left = A;
        A.parent = C;
        A.right = F;
        if (F != null) F.parent = A;
        return C;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new HLD().main1();
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
