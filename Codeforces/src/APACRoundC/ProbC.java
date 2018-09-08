package APACRoundC;
/**
 * Created by ankurverma1994
 * My code is awesome!
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
        int TC = ii();
        for (int tc = 1; tc <= TC; tc++) {
            out.printf("Case #%d: ", tc);
            HashMap<String, Integer> mapping = new HashMap<>();
            int n = 0;
            int N = ii();
            String s[] = new String[N];
            for (int i = 0; i < N; i++) s[i] = is();
            for (int i = 0; i < N; i++) {
                StringBuilder sb = new StringBuilder();
                boolean skip = false;
                for (int j = 0; j < s[i].length(); j++) {
                    char c = s[i].charAt(j);
                    if (skip) {
                        if (c == '(') skip = false;
                        continue;
                    }
                    if (c == '=' || c == ')' || c == ',') {
                        if (c == '=') skip = true;
                        if (sb.toString().length() == 0) continue;
                        if (!mapping.containsKey(sb.toString())) {
                            mapping.put(sb.toString(), n);
                            n++;
                        }
                        sb = new StringBuilder();
                    } else {
                        sb.append(c);
                    }
                }
            }
//            for (Map.Entry<String, Integer> es : mapping.entrySet())
//                out.println(es.getKey() + " " + es.getValue());
            HashSet<Integer> graph[] = new HashSet[n];
            for (int i = 0; i < n; i++) graph[i] = new HashSet<>();
            boolean valid[] = new boolean[n];
            for (int i = 0; i < N; i++) {
                int first = 0;
                StringBuilder sb = new StringBuilder();
                boolean skip = false;
                for (int j = 0; j < s[i].length(); j++) {
                    char c = s[i].charAt(j);
                    if (skip) {
                        if (c == '(') {
                            skip = false;
                            if (s[i].charAt(j + 1) == ')') {
                                valid[first] = true;
                                break;
                            }
                        }
                        continue;
                    } else if (c == '=') {
                        skip = true;
                        first = mapping.get(sb.toString());
                        sb = new StringBuilder();
                    } else if (c == ')' || c == ',') {
                        int v = mapping.get(sb.toString());
                        sb = new StringBuilder();
                        graph[v].add(first);
                    } else {
                        sb.append(c);
                    }
                }
            }
//            for (int i = 0; i < n; i++) {
//                out.print(i + ":  ");
//                out.println(Arrays.toString(graph[i].toArray()));
//            }
//            out.println(Arrays.toString(valid));
            int m = 0;
            for (int i = 0; i < n; i++)
                m += graph[i].size();
            int from[] = new int[m];
            int to[] = new int[m];
            for (int i = 0, c = 0; i < n; i++) {
                Object ne[] = graph[i].toArray();
                for (int j = 0; j < ne.length; j++) {
                    from[c] = i;
                    to[c++] = (int) ne[j];
                }
            }
            int g[][] = packD(n, from, to, m);
//            out.println(Arrays.deepToString(g));
            int gr[][] = packD(n, to, from, m);
            boolean good = true;
            visited = new boolean[n];
            recStack = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (gr[i].length == 0) {
                    if (!valid[i]) {
                        good = false;
                        break;
                    }
                }
                // check for loop
                if (isCyclic(i, g)) {
                    good = false;
                    break;
                }
            }
            out.println(good ? "GOOD" : "BAD");
        }
    }

    boolean isCyclic(int v, int g[][]) {
        if (visited[v] == false) {
            visited[v] = true;
            recStack[v] = true;

            // Recur for all the vertices adjacent to this vertex
//            list<int>::iterator i;
            for (int i = 0; i < g[v].length; i++) {
                int next = g[v][i];
                if (!visited[next] && isCyclic(next, g))
                    return true;
                else if (recStack[next])
                    return true;
            }

        }
        recStack[v] = false;  // remove the vertex from recursion stack
        return false;
    }

    boolean visited[], recStack[];

    // for directed graph, max is length of from or to array
    static int[][] packD(int n, int[] from, int[] to, int max) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int i = 0; i < max; i++) p[from[i]]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < max; i++) {
            g[from[i]][--p[from[i]]] = to[i];
        }
        return g;
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
//        out = new PrintWriter(System.out);
//        out = new PrintWriter("/home/ankurverma1994/C-small-output.txt");
        out = new PrintWriter("/home/ankurverma1994/C-large-output.txt");
//        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        obj = check.isEmpty() ? new FileInputStream("/home/ankurverma1994/Downloads/C-small-attempt0.in") : new ByteArrayInputStream(check.getBytes());
        obj = check.isEmpty() ? new FileInputStream("/home/ankurverma1994/Downloads/C-large.in") : new ByteArrayInputStream(check.getBytes());
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
