package aaa;

import java.util.Arrays;

public class SegmentTreeRMQ {
    /* This code is pick up from uwi's previous submission */
    public int M, H, N;
    public int[] st;

    public SegmentTreeRMQ(int n) {
        N = n;
        M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
        H = M >>> 1;
        st = new int[M];
        Arrays.fill(st, 0, M, Integer.MAX_VALUE);
    }

    public SegmentTreeRMQ(int[] a) {
        N = a.length;
        M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
        H = M >>> 1;
        st = new int[M];
        for (int i = 0; i < N; i++) {
            st[H + i] = a[i];
        }
        Arrays.fill(st, H + N, M, Integer.MAX_VALUE);
        for (int i = H - 1; i >= 1; i--) propagate(i);
    }

    public void update(int pos, int x) {
        st[H + pos] = x;
        for (int i = (H + pos) >>> 1; i >= 1; i >>>= 1) propagate(i);
    }

    private void propagate(int i) {
        st[i] = Math.min(st[2 * i], st[2 * i + 1]);
    }

    public int min(int l, int r) {
        return l >= r ? 0 : min(l, r, 0, H, 1);
    }

    private int min(int l, int r, int cl, int cr, int cur) {
        if (l <= cl && cr <= r) {
            return st[cur];
        } else {
            int mid = cl + cr >>> 1;
            int ret = Integer.MAX_VALUE;
            if (Math.max(l, cl) < Math.min(r, mid)) {
                ret = Math.min(ret, min(l, r, cl, mid, 2 * cur));
            }
            if (Math.max(l, mid) < Math.min(r, cr)) {
                ret = Math.min(ret, min(l, r, mid, cr, 2 * cur + 1));
            }
            return ret;
        }
    }
}