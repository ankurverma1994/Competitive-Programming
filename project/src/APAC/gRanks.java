package APAC;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;

class gRanks {

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int T = ii();
        for (int tc = 1; tc <= T; tc++) {
            int p = ii(), s[] = iia(p), n = ii();
            Athelets a[] = new Athelets[n * p];
            HashMap<String, Integer> map = new HashMap<>();
            int w[] = new int[n];
            String names[][] = new String[n][p];
            for (int i = 0; i < n; i++) {
                w[i] = ii();
                for (int j = 0; j < p; j++)
                    names[i][j] = is();
            }
            int m = ii();

            for (int i = 0; i < n * p; i++) a[i] = new Athelets();
            int curr = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < p; j++) {
                    if (map.containsKey(names[i][j])) {
                        int index = map.get(names[i][j]);
                        a[index].addmarks(1L * w[i] * s[j]);
                    } else {
                        map.put(names[i][j], curr);
                        a[curr].name = names[i][j];
                        a[curr++].addmarks(1L * w[i] * s[j]);
                    }
                }
            }
            for (int i = 0; i < curr; i++) a[i].update(m);
            Arrays.sort(a, 0, curr);
            int rank = 1;
            for (int i = 0; i < curr; i++) {
//                out.println(a[i].name + " " + a[i].totalScore);
                out.println(rank + " " + a[i].name);
                int count = 0;
                for (int j = i + 1; j < curr; j++) {
                    if (a[i].totalScore == a[j].totalScore) {
                        out.println(rank + " " + a[j].name);
                        count++;
                    } else break;
                }
                i += count;
                rank += count + 1;
            }
        }
    }

    class Athelets implements Comparable<Athelets> {
        String name;
        ArrayList<Long> score;
        long totalScore;

        Athelets() {
            score = new ArrayList<>();
        }

        void addmarks(long marks) {
            score.add(marks);
        }

        void update(int max) {
            Collections.sort(score, new Comparator<Long>() {
                @Override
                public int compare(Long o1, Long o2) {
                    return Long.compare(o2, o1);
                }
            });
            totalScore = 0;
            for (int i = 0; i < score.size() && i < max; i++)
                totalScore += score.get(i);
        }

        @Override
        public int compareTo(Athelets o) {
            if (totalScore != o.totalScore)
                return Long.compare(o.totalScore, totalScore);
            return name.compareTo(o.name);
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
                    new gRanks().main1();
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
//        out = new PrintWriter("A:\\out.txt");
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        obj = check.isEmpty() ? new FileInputStream("A:\\Codeground\\Rankings\\in1.txt") : new ByteArrayInputStream(check.getBytes());
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
