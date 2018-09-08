package aaa;

class TrieXOR {
    public static void main(String[] args) {

    }

    static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        void add(int x) {
            TrieNode c = root;
            for (int i = 19; i >= 0; i--) {
                int b = ((1 << i) & x) != 0 ? 1 : 0;
                if (c.child[b] == null)
                    c.child[b] = new TrieNode();
                c = c.child[b];
            }
        }

        int max(int x) {
            int ans = 0;
            TrieNode c = root;
            for (int i = 19; i >= 0; i--) {
                int b = ((1 << i) & x) != 0 ? 1 : 0;
                if (c.child[1 - b] != null) {
                    ans += 1 << i;
                    c = c.child[1 - b];
                } else
                    c = c.child[b];
            }
            return ans;
        }
    }

    static class TrieNode {
        TrieNode child[];

        TrieNode() {
            child = new TrieNode[2];
        }
    }
}
