package aaa;

import java.util.*;
import java.util.stream.IntStream;

class StringUtils {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(suffixArray("34521")));
    }

    public static int numberOfDistinctSubstring(int sa[], int lcp[]) {
        int n = sa.length;
        int charactersBacheHai[] = new int[n];
        for (int i = 0; i < n; i++) {
            charactersBacheHai[i] = n - sa[i];
        }
        int ans = charactersBacheHai[0];
        for (int i = 1; i < n; i++)
            ans = ans + charactersBacheHai[i] - lcp[i - 1];
        return ans;
    }

    // sort suffixes of S in O(n*log(n))
    public static int[] suffixArray(CharSequence S) {
        /*suffix array btata hai ki shuru se kitne characters nahi hai*/
        int n = S.length();
        int[] sa = IntStream.range(0, n).mapToObj(i -> n - 1 - i).
                sorted((a, b) -> Character.compare(S.charAt(a), S.charAt(b))).mapToInt(Integer::intValue).toArray();
        int[] classes = S.chars().toArray();
        for (int len = 1; len < n; len *= 2) {
            int[] c = classes.clone();
            for (int i = 0; i < n; i++) {
                classes[sa[i]] = i > 0 && c[sa[i - 1]] == c[sa[i]] && sa[i - 1] + len < n && c[sa[i - 1] + len / 2] == c[sa[i] + len / 2] ? classes[sa[i - 1]] : i;
            }
            int[] cnt = IntStream.range(0, n).toArray();
            int[] s = sa.clone();
            for (int i = 0; i < n; i++) {
                int s1 = s[i] - len;
                if (s1 >= 0)
                    sa[cnt[classes[s1]]++] = s1;
            }
        }
        return sa;
    }

    // longest common prefixes array in O(n)
    public static int[] lcp(int[] sa, CharSequence s) {
        int n = sa.length;
        int[] rank = new int[n];
        for (int i = 0; i < n; i++)
            rank[sa[i]] = i;
        int[] lcp = new int[n - 1];
        for (int i = 0, h = 0; i < n; i++) {
            if (rank[i] < n - 1) {
                for (int j = sa[rank[i] + 1]; Math.max(i, j) + h < s.length() && s.charAt(i + h) == s.charAt(j + h); ++h)
                    ;
                lcp[rank[i]] = h;
                if (h > 0)
                    --h;
            }
        }
        return lcp;
    }
}