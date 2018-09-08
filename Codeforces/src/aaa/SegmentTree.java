package aaa;

class SegmentTree {
    int tree[];
    int a[];
    int n;

    int operation(int a, int b) {
        return a + b;
    }

    SegmentTree(int a[], int n) {
        tree = new int[4 * n];
        this.a = new int[n];
        this.n = n;
        for (int i = 0; i < n; i++)
            this.a[i] = a[i];
        build(0, n - 1, 0);
    }

    //  TODO check this function
    void build(int s, int e, int c) {
        if (s == e) {
            tree[c] = a[s];
            return;
        }
        int m = (s + e) >> 1;
        build(s, m, 2 * c + 1);
        build(m + 1, e, 2 * c + 2);
        tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
    }

    void update(int x, int v) {
        put(0, n - 1, 0, x, v);
    }

    void put(int s, int e, int c, int x, int v) {
        if (s == e) {
            tree[c] = a[s] = v;
            return;
        }
        int m = (s + e) >> 1;
        if (x <= m) put(s, m, 2 * c + 1, x, v);
        else put(m + 1, e, 2 * c + 2, x, v);
        tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
    }

    int query(int l, int r) {
        return get(0, n - 1, 0, l, r);
    }

    int get(int s, int e, int c, int l, int r) {
        if (s == l && e == r) return tree[c];
        int m = (s + e) >> 1;
        if (r <= m) return get(s, m, 2 * c + 1, l, r);
        if (l > m) return get(m + 1, e, 2 * c + 2, l, r);
        return operation(get(s, m, 2 * c + 1, l, m), get(m + 1, e, 2 * c + 2, m + 1, r));
    }
}