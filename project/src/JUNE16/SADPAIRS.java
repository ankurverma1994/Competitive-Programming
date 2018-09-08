package JUNE16;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class SADPAIRS {
    boolean visit[];
    int g[][];
    HashMap<Integer, Integer> map[];
    ArrayList elements[];
    int len, a[];
    HashSet<Integer> articulation;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), m = ii();
        a = iia(n);
        int total[] = new int[1000001];
        map = new HashMap[n];
        elements = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            map[i] = new HashMap<>();
            elements[i] = new ArrayList();
        }

        for (int i : a) total[i]++;
        long ans = 0;
        for (int i : total) ans += nC2(i);
        int from[] = new int[m];
        int to[] = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = ii() - 1;
            to[i] = ii() - 1;
        }


        g = packU(n, from, to, m);
        visit = new boolean[n];
        len = 0;
        timer = 0;
        tin = new int[n];
        fup = new int[n];
        articulation = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                dfs(i, -1);
                len++;
            }
        }


        long intermediateAns[] = new long[len];
        for (int i = 0; i < len; i++) {
            long sum = 0;
            for (Map.Entry<Integer, Integer> entry : map[i].entrySet()) {
                int val = entry.getValue();
                long C = nC2(val);
                ans -= C;
                sum += C;
            }
            intermediateAns[i] = sum;
        }


//        out.println(ans);
        long finalAns[] = new long[n];
        for (int i = 0; i < len; i++) {
            int N = elements[i].size();
            for (int j = 0; j < N; j++) {
                visit = new boolean[n];
                long z = ans + intermediateAns[i];
                if (!articulation.contains(elements[i].get(j))) {
                    int val = map[i].get(a[elements[i].get(j)]);
                    z = ans + nC2(val) - nC2(val - 1);
                } else
                    for (int k = 0; k < N; k++) {
                        if (k == j) continue;
                        map1 = new HashMap<>();
                        if (!visit[elements[i].get(k)]) {
                            dfs1(elements[i].get(k), elements[i].get(j));
                            for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
                                int val = entry.getValue();
                                z -= nC2(val);
                            }
                        }
                    }
                finalAns[elements[i].get(j)] = z;
            }
        }
        for (long i : finalAns) out.println(i);
    }

    HashMap<Integer, Integer> map1;

    void dfs1(int u, int skip) {
        visit[u] = true;
        add1(a[u]);
        for (int i = 0; i < g[u].length; i++) {
            int v = g[u][i];
            if (v == skip) continue;
            if (!visit[v])
                dfs1(v, skip);
        }
    }

    void add1(int i) {
        if (map1.containsKey(i)) map1.put(i, map1.get(i) + 1);
        else map1.put(i, 1);
    }

    int timer, fup[], tin[];

    void dfs(int u, int p) {
        visit[u] = true;
        tin[u] = fup[u] = timer++;
        elements[len].add(u);
        add(a[u]);
        int children = 0;
        for (int i = 0; i < g[u].length; i++) {
            int v = g[u][i];
            if (v == p) continue;
            if (visit[v]) fup[u] = Math.min(fup[u], tin[v]);
            else {
                dfs(v, u);
                fup[u] = Math.min(fup[u], fup[v]);
                if (fup[v] >= tin[u] && p != -1) {
                    articulation.add(u);
                }
                children++;
            }
        }
        if (p == -1 && children > 1) {
            articulation.add(u);
        }
    }

    void add(int i) {
        if (map[len].containsKey(i)) map[len].put(i, map[len].get(i) + 1);
        else map[len].put(i, 1);
    }

    long nC2(int n) {
        if (n <= 1) return 0;
        return 1L * n * (n - 1) / 2;
    }

    // for undirected graph, max is length of from or to array
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

    class ArrayList {

        private int[] myStore;
        private int actSize = 0;

        public ArrayList() {
            myStore = new int[2];
        }

        public int get(int index) {
            if (index < actSize)
                return myStore[index];
            else
                throw new ArrayIndexOutOfBoundsException();
        }

        public void add(int obj) {
            if (myStore.length - actSize <= 1)
                increaseListSize();
            myStore[actSize++] = obj;
        }

        public int size() {
            return actSize;
        }

        public void clear() {
            actSize = 0;
        }

        private void increaseListSize() {
            myStore = Arrays.copyOf(myStore, myStore.length * 2);
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
                    new SADPAIRS().main1();
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

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }
}
