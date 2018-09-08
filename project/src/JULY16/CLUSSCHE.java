package JULY16;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;

class CLUSSCHE {
    final int inf = Integer.MAX_VALUE / 2;
    HashMap<Integer, Integer> load;

    int n, m, b, c, d[][], cost;

    //------------> Solution starts here!!
    void solve() {
        n = ii();
        m = ii();
        b = ii();
        c = ii();
        d = iim(n, n);
        cost = 0;

        floyd();

        load = new HashMap<>();
        Random gen = new Random();
        for (int i = 0; i < n; i++) load.put(i, 0);


        for (int M = 0; M < m; M++) {
            Jobs jobs[] = new Jobs[b];
            for (int B = 0; B < b; B++) {
                int node = ii() - 1;
                int power = ii();
                jobs[B] = new Jobs(node, power, B);
            }
            Arrays.sort(jobs);

            int index[] = new int[b];
            int bestindex[];
            for (int i = 0; i < b; i++) index[i] = i;
            bestindex = index;
            int difference = inf;
            for (int i = 0; i < 1; i++) {
//                for (int j = 0; j < n; j++) {
//                    out.println(load.get(j) + " "+cost);
//                }
//                out.println();
//                int diff = compute(jobs, index);
//                if (diff < difference) {
//                    bestindex = index;
//                    difference = diff;
////                    System.err.println(diff);
//                }
                index = shuffle(index, gen);
            }
//            System.out.println(difference);
//            out.println(Arrays.toString(bestindex));
            go(jobs, bestindex);
        }
    }

    public static int[] shuffle(int[] a, Random gen) {
        for (int i = 0, n = a.length; i < n; i++) {
            int ind = gen.nextInt(n - i) + i;
            int d = a[i];
            a[i] = a[ind];
            a[ind] = d;
        }
        return a;
    }

    int compute(Jobs jobs[], int indices[]) {
        int cost1 = cost + 0;
        HashMap<Integer, Integer> load1 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            load1.put(i, load.get(i));
        }


        for (int B = 0; B < b; B++) {
            int node = jobs[indices[B]].node, power = jobs[indices[B]].power;
            int minNode = node, minVal = inf;
            for (int i = 0; i < n; i++) {
                int value = load1.get(i);
                if (minVal > value && (d[node][i] + cost1) <= c) {
                    minVal = value;
                    minNode = i;
                } else if (minVal == value && (d[node][i] + cost1) <= c) {
                    if (d[node][i] < d[node][minNode])
                        minNode = i;
                }
            }
            load1.put(minNode, load1.get(minNode) + power);
            cost1 = cost1 + d[node][minNode];
        }

        int min = inf, max = -inf;
        for (int i = 0; i < n; i++) {
            int val = load1.get(i);
            min = Math.min(val, min);
            max = Math.max(val, max);
        }

        return Math.abs(max - min);
    }

    void go(Jobs jobs[], int indices[]) {
        int ans[] = new int[b];
        for (int B = 0; B < b; B++) {
            int node = jobs[indices[B]].node, power = jobs[indices[B]].power;
            int minNode = node, minVal = inf;
            for (int i = 0; i < n; i++) {
                int value = load.get(i);
                if (minVal > value && (d[node][i] + cost) <= c) {
                    minVal = value;
                    minNode = i;
                } else if (minVal == value && (d[node][i] + cost) <= c) {
                    if (d[node][i] < d[node][minNode])
                        minNode = i;
                }
            }
            load.replace(minNode, load.get(minNode) + power);
            cost = cost + d[node][minNode];
            ans[jobs[indices[B]].index] = minNode + 1;
        }
        for (int i : ans) out.println(i);
        out.flush();
    }

    void floyd() {
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
    }

    class Jobs implements Comparable<Jobs> {
        int node, power, index;

        Jobs(int a, int b, int i) {
            node = a;
            power = b;
            index = i;
        }

        @Override
        public int compareTo(Jobs o) {
            return Integer.compare(o.power, power);
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new CLUSSCHE().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        obj = check.isEmpty() ? new FileInputStream("A:\\in.txt") : new ByteArrayInputStream(check.getBytes());
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

    int[][] iim(int n, int m) {
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = ii();
        return a;
    }
}