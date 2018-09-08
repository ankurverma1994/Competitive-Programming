package LTIME32;
/**
 * @copyright ankurverma1994
 */

import java.io.*;
import java.util.*;

class Main {
    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii(), source = ii();
        int nodes = n * n / 2;
        ArrayList<Integer> graph[] = new ArrayList[nodes];
        for (int i = 0; i < nodes; i++)
            graph[i] = new ArrayList<>();
        int curr = 0;
        for (int i = 0; i < n / 2; i++) {
            int len = 2 * i + 1;
            int count = 1;
            while (count < len) {
//                out.print((curr+1)+" "+(curr+2)+" ");
                graph[curr].add(curr + 1);
                graph[curr + 1].add(curr);
                curr++;
                count++;
            }
//            out.println();
            curr++;
        }
        for (int i = n / 2 - 1; i >= 0; i--) {
            int len = 2 * i + 1;
            int count = 1;
            while (count < len) {
//                out.print((curr+1)+" "+(curr+2)+" ");
                graph[curr].add(curr + 1);
                graph[curr + 1].add(curr);
                curr++;
                count++;
            }
//            out.println();
            curr++;
        }
        if (n == 2) {
            graph[0].add(1);
            graph[1].add(0);
        } else if (n == 4) {
            graph[0].add(2);
            graph[2].add(0);
            graph[1].add(4);
            graph[4].add(1);
            graph[3].add(6);
            graph[6].add(3);
            graph[5].add(7);
            graph[7].add(5);
        } else if (n == 6) {
            graph[0].add(2);
            graph[2].add(0);
            graph[1].add(5);
            graph[5].add(1);
            graph[3].add(7);
            graph[7].add(3);
            graph[4].add(9);
            graph[9].add(4);
            graph[6].add(11);
            graph[11].add(6);
            graph[8].add(13);
            graph[13].add(8);
            graph[10].add(14);
            graph[14].add(10);
            graph[12].add(16);
            graph[16].add(12);
            graph[15].add(17);
            graph[17].add(15);
        } else if (n == 8) {
            graph[0].add(2);
            graph[2].add(0);
            graph[1].add(5);
            graph[5].add(1);
            graph[3].add(7);
            graph[7].add(3);
            graph[4].add(10);
            graph[10].add(4);
            graph[6].add(12);
            graph[12].add(6);
            graph[8].add(14);
            graph[14].add(8);
            graph[9].add(16);
            graph[16].add(9);
            graph[11].add(18);
            graph[18].add(11);
            graph[13].add(20);
            graph[20].add(13);
            graph[15].add(22);
            graph[22].add(15);
            graph[17].add(23);
            graph[23].add(17);
            graph[19].add(26);
            graph[26].add(19);
            graph[21].add(28);
            graph[28].add(21);
            graph[24].add(28);
            graph[28].add(24);
            graph[26].add(30);
            graph[30].add(26);
            graph[29].add(31);
            graph[31].add(29);
        }
        int d[] = bfs(source - 1, graph);
        for (int i = 0; i < d.length; i++)
            out.print((d[i]) + " ");
        out.println();
    }

    int[] bfs(int v, ArrayList<Integer> graph[]) {
        int d[] = new int[graph.length];
        int parent[] = new int[graph.length];
        Arrays.fill(d, Integer.MAX_VALUE / 2);
        d[v] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int w = 0; w < graph[u].size(); w++) {
                if (d[graph[u].get(w)] == Integer.MAX_VALUE / 2) {
                    d[graph[u].get(w)] = d[u] + 1;
                    parent[graph[u].get(w)] = u;
                    q.add(graph[u].get(w));
                }
            }
        }
        return d;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new Main().main1();
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