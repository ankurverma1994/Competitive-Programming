package aaa;

class Trie {
    int ALPHA = 2; // number of distinct alphabets
    Node root;

    class Node {
        int cnt;
        Node child[];

        Node() {
            cnt = 0;
            child = new Node[ALPHA];
        }
    }

    // freq 1 for addition in trie
    // freq 0 for deletion in trie
    void add(char s[], int freq) {
        int n = s.length;
        Node curr = root;
        for (int i = 0; i < n; i++) {
            int val = s[i] - 'a';
            if (curr.child[val] == null)
                curr.child[val] = new Node();
            curr = curr.child[val];
            curr.cnt += 1;
        }
    }

    void numberAdd(int x, int freq) {
        Node curr = root;
        for (int i = 30; i >= 0; i--) {
            int bit = ((1 << i) & x) > 0 ? 1 : 0; // ith bit
            if (curr.child[bit] == null)
                curr.child[bit] = new Node();
            curr = curr.child[bit];
            curr.cnt += freq;
        }
    }

    int maxXOR(int x) {
        Node curr = root;
        int ans = 0;
        for (int i = 30; i >= 0; i--) {
            int bit = ((1 << i) & x) > 0 ? 1 : 0;
            int oppBit = 1 - bit;
            if (curr.child[oppBit] != null && curr.child[oppBit].cnt > 0) {
                ans += (1 << i);
                curr = curr.child[oppBit];
            } else {
                curr = curr.child[bit];
            }
        }
        return ans;
    }
}