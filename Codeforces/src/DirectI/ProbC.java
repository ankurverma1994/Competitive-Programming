package DirectI;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */
/*
A substitution cipher is a cipher where you decide to replace all instances of 'A' with some character S['A'] (where S['A'] may or may not be equal to 'A'); all instances of 'B' with some character S['B']; all instances of 'C' with some character S['C'] and so on. Of course, there are no letters x and y such that S[x] = S[y]. In other words, all S[x] are mutually distinct.

For the purpose of this problem you may assume that all plain text and cipher text only contains of upper case english letters and spaces. Also, spaces are never replaced with any other character. Thus, the length and order of words in the plain text and cipher text remain exactly the same.

The key for a substitution cipher is, of course, the mapping from the set of alphabet on to itself. Consider the following substitution cipher key. Note that we do not show the mapping for each character to save space. The mappings for only those characters that will appear in the plain text in our example are shown.

A -> I
B -> L
D -> R
E -> E
H -> H
I -> A
L -> B
R -> K
S -> T
T -> W

The above key encrypts the plaintext "THIS IS READ ABLE" to "WHAT AT KEIR ILBE". Different substitution cipher keys (or mappings) may generate different cipher texts. We are interested in the set of cipher texts generated by some substitution cipher key, such that, each word in the cipher text is an anagram of the original word. In fact, we want you to compute the number of different cipher texts that can be generated such that each word in the cipher text is an anagram of the original word. Note that the problem asks for the number of such cipher texts, NOT the number of such substitution cipher keys.

For clarity, an anagram of a word is another word that can be formed by rearranging the letters of the given word. A rearrangement is not allowed to introduce new characters or delete any character. The original word is also considered an anagram of itself.

Consider the substitution cipher key below. Again, please note that we do not show mappings for each character.

A -> E
B -> L
D -> R
E -> A
H -> T
I -> S
L -> B
R -> D
S -> I
T -> H

This key encrypts the plaintext "THIS IS READ ABLE" to "HTSI SI DAER ELBA". Note how each word in the cipher text is an anagram of the original word. In fact, the plain text "THIS IS READ ABLE" can be mapped to 32 different cipher texts through substitution cipher scheme such that each word in the cipher text is an anagram of the respective word in the plain text.

Input

The first line in input contains the number T, then number of test cases. The next T lines contain the description of plain text for each test case. Each description starts with the number of words in the plain text, followed by a single space, followed by words seprated by single space characters. Each plain text will contain at most 4 words (at least 1). The description will contain no leading or trailing whitespaces. Each word in the plain text will contain at most 4 characters (at least 1). Each character in the plain text will be an upper case english letter. There may be at most 10 test cases in a test file. There are no empty lines separating test cases.

Output

For each test case, print a number on a line by itself. This number should be the number of distinct cipher texts that can be achieved by any substitution cipher key such that each word in the cipher text is an anagram of the respective word in the plain text. Please note that the original plain text is also counted towards the set of possible cipher texts achievable.

Sample Input

4
4 THIS IS READ ABLE
4 I LOVE CODE CHEF
3 CODE YOUR SELF
2 DONT COPY

Sample Output

32
4
72
36

Explanation

For the second sample input, the four possible cipher texts are "I LOVE CODE CFEH", "I LOVE CODE CHEF", "I VOLE CODE CFEH" and "I VOLE CODE CHEF".
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class ProbC {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            n = ii();
            s = isa(n);
            dic = new char[26];
            Arrays.fill(dic, '$');
            per = new ArrayList[n];
            for (int i = 0; i < n; i++) per[i] = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                index = i;
                permute(s[i].toCharArray(), 0, s[i].length() - 1);
            }
            map = new HashSet<>();
            solve(0, "", dic);
            out.println(map.size());
        }
    }

    HashSet<String> map;

    void solve(int i, String A, char dic[]) {
        if (i >= n) {
            map.add(A);
            return;
        }
        char old[] = new char[26];
        for (int z = 0; z < 26; z++) old[z] = dic[z];
        for (int j = 0; j < per[i].size(); j++) {
            String k = per[i].get(j);
            char x[] = k.toCharArray();
            char org[] = s[i].toCharArray();
            boolean valid = true;

            for (int z = 0; z < 26; z++) dic[z] = old[z];

            for (int z = 0; z <  x.length; z ++) {
                if (dic[org[z ] - 'A'] == '$') {
                    for (int l = 0; l < 26; l++) {
                        if (dic[l] == x[z]) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid) dic[org[z ] - 'A'] = x[z];
                } else {
                    if (dic[org[z ] - 'A'] != x[z]) valid = false;
                }
            }
            if (valid) solve(i + 1, A + k, dic);
        }
    }

    int n;
    String s[];
    char dic[];
    ArrayList<String> per[];
    int index;

    void permute(char a[], int l, int r) {
        int i;
        if (l == r) {
            String p = "";
            for (char c : a) p += c;
            per[index].add(p);
        } else {
            for (i = l; i <= r; i++) {
                char t = a[l];
                a[l] = a[i];
                a[i] = t;
                permute(a, l + 1, r);
                t = a[l];
                a[l] = a[i];
                a[i] = t;
            }
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
                    new ProbC().main1();
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

    String is() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
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

    long il() {
        long num = 0;
        int b;
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

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    float nf() {
        return Float.parseFloat(is());
    }

    double id() {
        return Double.parseDouble(is());
    }

    char ic() {
        return (char) skip();
    }

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = ii();
        return a;
    }

    long[] ila(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++) a[i] = il();
        return a;
    }

    String[] isa(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++) a[i] = is();
        return a;
    }

    double[][] idm(int n, int m) {
        double a[][] = new double[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = id();
        return a;
    }

    int[][] iim(int n, int m) {
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = ii();
        return a;
    }
}
