package aaa;
import java.util.*;
class DijisktrausingMinHeap{
    public static void main(String[] args) {    }

    public static long[] dijk(int[][][] g, int from)
    {
        /* This part of code is picked up from "uwi" previous submission */
        int n = g.length;
        long[] td = new long[n];

        Arrays.fill(td, Long.MAX_VALUE / 2); // max
        MinHeapL q = new MinHeapL(n);
        q.add(from, 0);
        td[from] = 0;

        while(q.size() > 0){
            int cur = q.argmin();
            q.remove(cur);

            for(int[] e : g[cur]){
                int next = e[0];
                long nd = td[cur] + e[1];
                if(nd < td[next]){
                    td[next] = nd;
                    q.update(next, nd);
                }
            }
        }
        return td;
    }

    public static class MinHeapL {
        /* This part of code is picked up from "uwi" previous submission */
        public long[] a;
        public int[] map;
        public int[] imap;
        public int n;
        public int pos;
        public static long INF = Long.MAX_VALUE;

        public MinHeapL(int m)
        {
            n = Integer.highestOneBit((m+1)<<1);
            a = new long[n];
            map = new int[n];
            imap = new int[n];
            Arrays.fill(a, INF);
            Arrays.fill(map, -1);
            Arrays.fill(imap, -1);
            pos = 1;
        }

        public long add(int ind, long x)
        {
            int ret = imap[ind];
            if(imap[ind] < 0){
                a[pos] = x; map[pos] = ind; imap[ind] = pos;
                pos++;
                up(pos-1);
            }
            return ret != -1 ? a[ret] : x;
        }

        public long update(int ind, long x)
        {
            int ret = imap[ind];
            if(imap[ind] < 0){
                a[pos] = x; map[pos] = ind; imap[ind] = pos;
                pos++;
                up(pos-1);
            }else{
                a[ret] = x;
                up(ret);
                down(ret);
            }
            return x;
        }

        public long remove(int ind)
        {
            if(pos == 1)return INF;
            if(imap[ind] == -1)return INF;

            pos--;
            int rem = imap[ind];
            long ret = a[rem];
            map[rem] = map[pos];
            imap[map[pos]] = rem;
            imap[ind] = -1;
            a[rem] = a[pos];
            a[pos] = INF;
            map[pos] = -1;

            up(rem);
            down(rem);
            return ret;
        }

        public long min() { return a[1]; }
        public int argmin() { return map[1]; }
        public int size() {	return pos-1; }

        private void up(int cur)
        {
            for(int c = cur, p = c>>>1;p >= 1 && a[p] > a[c];c>>>=1, p>>>=1){
                long d = a[p]; a[p] = a[c]; a[c] = d;
                int e = imap[map[p]]; imap[map[p]] = imap[map[c]]; imap[map[c]] = e;
                e = map[p]; map[p] = map[c]; map[c] = e;
            }
        }

        private void down(int cur)
        {
            for(int c = cur;2*c < pos;){
                int b = a[2*c] < a[2*c+1] ? 2*c : 2*c+1;
                if(a[b] < a[c]){
                    long d = a[c]; a[c] = a[b]; a[b] = d;
                    int e = imap[map[c]]; imap[map[c]] = imap[map[b]]; imap[map[b]] = e;
                    e = map[c]; map[c] = map[b]; map[b] = e;
                    c = b;
                }else{
                    break;
                }
            }
        }
    }
}