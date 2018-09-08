package aaa;

import java.util.Arrays;

public class SuffixAutomaton {
    public int size;
    public int[] len; // initialã��ã��ã�®æ��ç�­çµ�è·¯é�·
    public int[] link; // Failure Link
    public int[][] next; // ã�¤ã��

    private SuffixAutomaton(int sz) {
        size = sz;
        len = new int[sz];
        link = new int[sz];
        next = new int[sz][];
    }

    public static int enc(char c) {
        return c == '$' ? 26 : c - 'a';
    }

    public static char dec(int n) {
        return n == 26 ? '$' : (char) ('a' + n);
    }

    public static SuffixAutomaton buildSuffixAutomaton(char[] a) {
        int n = a.length;
        int[] len = new int[2 * n];
        int[] link = new int[2 * n];
        int[][] next = new int[2 * n][27];
        int[] original = new int[2 * n];
        Arrays.fill(link, -1);
        for (int i = 0; i < 2 * n; i++) {
            Arrays.fill(next[i], -1);
        }
        Arrays.fill(original, -1);

        len[0] = 0;
        link[0] = -1;
        int last = 0;
        int sz = 1;

        // extend
        for (char c : a) {
            int v = enc(c);
            int cur = sz++;
            len[cur] = len[last] + 1;
            int p;
            for (p = last; p != -1 && next[p][v] == -1; p = link[p]) {
                next[p][v] = cur;
            }
            if (p == -1) {
                link[cur] = 0;
            } else {
                int q = next[p][v];
                if (len[p] + 1 == len[q]) {
                    link[cur] = q;
                } else {
                    int clone = sz++;
                    original[clone] = original[q] != -1 ? original[q] : q;
                    len[clone] = len[p] + 1;
                    System.arraycopy(next[q], 0, next[clone], 0, next[q].length);
                    link[clone] = link[q];
                    for (; p != -1 && next[p][v] == q; p = link[p]) {
                        next[p][v] = clone;
                    }
                    link[q] = link[cur] = clone;
                }
            }
            last = cur;
        }

        // topological sort
        int[] nct = new int[sz];
        for (int i = 0; i < sz; i++) {
            for (int e : next[i]) {
                if (e != -1) nct[e]++;
            }
        }
        int[] ord = new int[sz];
        int p = 1;
        ord[0] = 0;
        for (int r = 0; r < p; r++) {
            for (int e : next[ord[r]]) {
                if (e != -1 && --nct[e] == 0) ord[p++] = e;
            }
        }
        int[] iord = new int[sz];
        for (int i = 0; i < sz; i++) iord[ord[i]] = i;

        SuffixAutomaton sa = new SuffixAutomaton(sz);
        for (int i = 0; i < sz; i++) {
            sa.len[i] = len[ord[i]];
            sa.link[i] = link[ord[i]] != -1 ? iord[link[ord[i]]] : -1;
            sa.next[i] = next[ord[i]];
            for (int j = 0; j < sa.next[i].length; j++) sa.next[i][j] = sa.next[i][j] != -1 ? iord[sa.next[i][j]] : -1;
        }

        return sa;
    }

    public long numberOfDistinctSubstrings() {
        long[] dp = new long[size];
        for (int i = size - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int e : next[i]) {
                if (e != -1) dp[i] += dp[e];
            }
        }
        return dp[0] - 1; // remove empty
    }
}