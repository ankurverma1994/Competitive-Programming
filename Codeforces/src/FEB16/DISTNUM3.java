package FEB16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 *
 * @credits: https://sites.google.com/site/indy256/algo/treap_set
 * @credits: http://codeforces.com/blog/entry/22893?#comment-273758
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class DISTNUM3 {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    void solve() {
        n = ii();
        segmentTree = new Node[4 * n];
        for (int i = 0; i < 4 * n; i++) {
            segmentTree[i] = new Node(Integer.MAX_VALUE);
            segmentTree[i].priority = Long.MAX_VALUE;
        }
        b = new int[n + 1];
        for (int i = 1; i <= n; i++) b[i] = ii();
        a = new int[n + 1];
        map = new HashMap<>();
        for (int i = n; i > 0; i--) {
            if (map.containsKey(b[i])) {
                a[i] = map.get(b[i]);
                map.put(b[i], i);
            } else {
                a[i] = n + 10;
                map.put(b[i], i);
            }
        }
        set = new HashMap<>();
        TreeSet<Integer> temp = new TreeSet<>();
        for (int i = 1; i <= n; i++) {
            if (set.containsKey(b[i])) {
                temp = set.get(b[i]);
                temp.add(i);
                set.put(b[i], temp);
            } else {
                temp.clear();
                temp.add(-1);
                temp.add(n + 10);
                temp.add(i);
                set.put(b[i], temp);
            }
        }
        // build
        for (int i = 1; i <= n; i++) {
            set(1, i, a[i], 1, n);
        }
        int q = ii();
        for (int i = 0; i < q; i++) {
            int type = ii();
            if (type == 0) {
                // update
                int x = ii();
                int v = ii();
                // purani hatani hai
                temp = set.get(b[x]);
                int prev = temp.lower(x);
                if (prev != -1) {
                    update(1, prev, a[x], 1, n, a[prev]);
                    a[prev] = a[x];
                }
                temp.remove(x);
                set.put(b[x], temp);
                // nayi add karni hai
                temp = set.get(v);
                if (temp == null) {
                    temp = new TreeSet<>();
                    temp.add(-1);
                    temp.add(n + 10);
                }
                prev = temp.lower(x);
                if (prev != -1) {
                    update(1, prev, x, 1, n, a[prev]);
                    a[prev] = x;
                }
                temp.add(x);
                int next = temp.higher(x);
                update(1, x, next, 1, n, a[x]);
                a[x] = next;
                set.put(v, temp);

                b[x] = v;
            } else {
                int l = ii(), r = ii();
                int ans = query(1, l, r, r, 1, n);
                ans = r - l + 1 - ans;
                System.out.println(ans);
            }
            System.out.println(Arrays.toString(b));
            System.out.println(Arrays.toString(a));
        }
//        int q = ii();
//        for (int i = 0; i < q; i++) {
//            int type = ii();
//            if (type == 0) {
//                // update
//                int x = ii();
//                int v = ii();
//                int old = a[x];
//                a[x] = v;
//                update(1, x, v, 1, n, old);
//            } else {
//                int l = ii(), r = ii();
//                int x = ii();
//                int ans = query(1, l, r, x, 1, n);
//                ans = r - l + 1 - ans;
//                out.println(ans);
//            }
//        }
    }

    HashMap<Integer, Integer> map;
    HashMap<Integer, TreeSet<Integer>> set;
    int n, b[], a[];

    void update(int x, int v) {
        TreeSet<Integer> temp = set.get(b[x]);
        int prev = temp.lower(x);
        if (prev != -1) {
            update(1, prev, a[x], 1, n, a[prev]);
            a[prev] = a[x];
        }
        temp.remove(x);
        set.put(b[x], temp);
        // nayi add karni hai
        temp = set.get(v);
        if (temp == null) {
            temp = new TreeSet<>();
            temp.add(-1);
            temp.add(n + 10);
        }
        prev = temp.lower(x);
        if (prev != -1) {
            update(1, prev, x, 1, n, a[prev]);
            a[prev] = x;
        }
        temp.add(x);
        int next = temp.higher(x);
        update(1, x, next, 1, n, a[x]);
        a[x] = next;
        set.put(v, temp);

        b[x] = v;
    }

    int query_tree(int l, int r, int end) {
        int ans = query(1, l, r, r, 1, end);
        ans = r - l + 1 - ans;
        return ans;
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
//        new Thread(null, new Runnable() {
//            public void run() {
//                try {
        new DISTNUM3().main1();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (StackOverflowError e) {
//                    System.out.println("RTE");
////                    System.out.println(set.toString());
//                }
//            }
//        }, "1", 1 << 26).start();
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
