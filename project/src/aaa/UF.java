package aaa;

import java.util.TreeMap;
import java.io.*;

public class UF {

    // parent[i] = parent of i
    // rank[i] = rank of subtree rooted at i (never more than 31)
    public int rank[], parent[], size[];
    public int comp, n;     // number of components
    public long val[];
    public TreeMap<Long, Integer> treeMap;

    public UF(int N, int a[]) {
        treeMap = new TreeMap<>();
        comp = n = N;
        parent = new int[N];
        size = new int[N];
        rank = new int[N];
        val = new long[N];
        for (int i = 0; i < N; i++) {
            val[i] = a[i];
            add(a[i]);
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void add(long val) {
        if (treeMap.containsKey(val))
            treeMap.put(val, treeMap.get(val) + 1);
        else
            treeMap.put(val, 1);
    }

    public void remove(long x) {
        int y = treeMap.get(x);
        if (y == 1)
            treeMap.remove(x);
        else
            treeMap.put(x, y - 1);
    }

    // val[x]+=v. This cannot be change
    public void update(int x, long v) {
        int RootX = find(x);
        remove(val[RootX]);
        val[RootX] += v;
        add(val[RootX]);
    }

    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
    }

    public int NumberOfConnectedComponents() {
        return comp;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        size[rootQ] = size[rootP] = size[rootP] + size[rootQ];

        remove(val[rootP]);
        remove(val[rootQ]);
        val[rootP] = val[rootQ] = val[rootP] + val[rootQ];
        add(val[rootP]);

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

    public static void main(String[] args)throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());
        int a[]=new int[n];
        for(int i=0;i<n;i++)
            a[i]=i+1;
        int m=Integer.parseInt(reader.readLine());
    }
}
//public class UF {
//
//    // parent[i] = parent of i
//    // rank[i] = rank of subtree rooted at i (never more than 31)
//    public int rank[], parent[], size[];
//    public int comp, n;     // number of components
//
//    public UF(int N) {
//        comp = n = N;
//        parent = new int[N];
//        size = new int[N];
//        rank = new int[N];
//        for (int i = 0; i < N; i++) {
//            parent[i] = i;
//            size[i] = 1;
//        }
//    }
//
//    public int find(int p) {
//        while (p != parent[p]) {
//            parent[p] = parent[parent[p]];    // path compression by halving
//            p = parent[p];
//        }
//        return p;
//    }
//
//    public int NumberOfConnectedComponents() {
//        return comp;
//    }
//
//    public boolean connected(int p, int q) {
//        return find(p) == find(q);
//    }
//
//    public void union(int p, int q) {
//        int rootP = find(p);
//        int rootQ = find(q);
//        if (rootP == rootQ) return;
//        size[rootQ] = size[rootP] = size[rootP] + size[rootQ];
//
//        // make root of smaller rank point to root of larger rank
//        if (rank[rootP] < rank[rootQ]) {
//            parent[rootP] = rootQ;
//        } else if (rank[rootP] > rank[rootQ]) {
//            parent[rootQ] = rootP;
//        } else {
//            parent[rootQ] = rootP;
//            rank[rootP]++;
//        }
//        comp--;
//    }
//
//    // size of connected component in which i belongs
//    public int size(int i) {
//        int root = find(i);
//        return size[root];
//    }
//}
