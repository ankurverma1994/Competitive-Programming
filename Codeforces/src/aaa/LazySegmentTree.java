package aaa;

class LazySegmentTree {
    int tree[], lazy[];
    int a[];
    int n;

    int operation(int a, int b) {
        return a + b;
    }

    public LazySegmentTree(int a[], int n) {
        tree = new int[4 * n];
        lazy = new int[4 * n];
        this.a = new int[n];
        this.n = n;
        for (int i = 0; i < n; i++)
            this.a[i] = a[i];
        build(0, n - 1, 0);
    }

    public void build(int s, int e, int c) {
        if (s == e) {
            tree[c] = a[s];
            return;
        }
        int m = (s + e) >> 1;
        build(s, m, 2 * c + 1);
        build(m + 1, e, 2 * c + 2);
        tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
    }

    void push(int node, int start, int end) {
        if (lazy[node] != 0) {
            // This node needs to be updated
            // TODO for sum, change according to operation
            tree[node] += (end - start + 1) * lazy[node];    // Update it
            if (start != end) {
                lazy[node * 2 + 1] += lazy[node];                  // Mark child as lazy
                lazy[node * 2 + 2] += lazy[node];                // Mark child as lazy
            }
            lazy[node] = 0;                                  // Reset it
        }
    }

    // a[x] += v
    void update(int l, int r, int val) {
        updateRange(0, 0, n - 1, l, r, val);
    }

    int query(int l, int r) {
        return queryRange(0, 0, n - 1, l, r);
    }

    void updateRange(int node, int start, int end, int l, int r, int val) {
        push(node, start, end);
        if (start > end || start > r || end < l)              // Current segment is not within range [l, r]
            return;
        if (start >= l && end <= r) {
            // Segment is fully within range
            // TODO for sum, change according to operation
            tree[node] += (end - start + 1) * val;
            if (start != end) {
                // Not leaf node
                lazy[node * 2 + 1] += val;
                lazy[node * 2 + 2] += val;
            }
            return;
        }
        int mid = (start + end) / 2;
        updateRange(node * 2 + 1, start, mid, l, r, val);        // Updating left child
        updateRange(node * 2 + 2, mid + 1, end, l, r, val);   // Updating right child
        tree[node] = operation(tree[node * 2 + 1], tree[node * 2 + 2]);        // Updating root with max value
    }

    int queryRange(int node, int start, int end, int l, int r) {
        if (start > end || start > r || end < l)
            return 0;         // Out of range
        push(node, start, end);
        if (start >= l && end <= r)             // Current segment is totally within range [l, r]
            return tree[node];
        int mid = (start + end) / 2;
        int p1 = queryRange(node * 2 + 1, start, mid, l, r);         // Query left child
        int p2 = queryRange(node * 2 + 2, mid + 1, end, l, r); // Query right child
        return operation(p1, p2);
    }
}