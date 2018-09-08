package HackJan16;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class BennyQueries {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), q = ii();
        int a[] = iia(n);
        Trie t = new Trie();
        for (int i = 0; i < n; i++) {
            t.add(a[i], i);
        }
        for (int Q = 0; Q < q; Q++) {
            int l = ii() - 1, r = ii() - 1, x = ii();
            out.println(t.max(x, l, r));
        }
    }

    static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        void add(int x, int idx) {
            TrieNode c = root;
            for (int i = 19; i >= 0; i--) {
                int b = ((1 << i) & x) != 0 ? 1 : 0;
                if (c.child[b] == null)
                    c.child[b] = new TrieNode();
                c = c.child[b];
                c.list.add(idx);
            }
        }

        int max(int x, int l, int r) {
            int ans = 0;
            TrieNode c = root;
            for (int i = 19; i >= 0; i--) {
                int b = ((1 << i) & x) != 0 ? 1 : 0;
                if (c.child[1 - b] != null) {
                    boolean flag = false;
//                    for (int k = 0; k < c.child[1 - b].list.size(); k++) {
//                        int val = c.child[1 - b].list.get(k);
//                        if (l <= val && val <= r) {
//                            flag = true;
//                            break;
//                        }
//                    }

                    int start = 0, end = c.child[1 - b].list.size();
                    while (start < end) {
                        int mid = (start + end) >> 1;
                        if (c.child[1 - b].list.get(mid) < l)
                            start = mid + 1;
                        else if (c.child[1 - b].list.get(mid) > r)
                            end = mid;
                        else {
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {
                        ans += 1 << i;
                        c = c.child[1 - b];
                    } else {
                        c = c.child[b];
                    }
                } else
                    c = c.child[b];
            }
            return ans;
        }
    }

    static class TrieNode {
        TrieNode child[];
        MyArrayList list;

        TrieNode() {
            child = new TrieNode[2];
            list = new MyArrayList();
        }
    }

    static public class MyArrayList {

        private int[] myStore;
        private int actSize = 0;

        public MyArrayList() {
            myStore = new int[2];
        }

        public int get(int index) {
            if (index < actSize)
                return myStore[index];
            else
                throw new ArrayIndexOutOfBoundsException();
        }

        public void add(int obj) {
            if (myStore.length - actSize <= 1)
                increaseListSize();
            myStore[actSize++] = obj;
        }

        public int size() {
            return actSize;
        }

        public void clear() {
            actSize = 0;
        }

        private void increaseListSize() {
            myStore = Arrays.copyOf(myStore, myStore.length * 2);
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
                    new BennyQueries().main1();
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