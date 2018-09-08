//package Codevita2016;
/**
 * Created by ankurverma1994.
 * My code is awesome!
 */

import java.util.*;
import java.io.*;
import java.math.*;

class FootballLeague {

    final int mod = (int) 1e9 + 7;
    final double eps = 1e-6;
    final double pi = Math.PI;
    final long inf = Long.MAX_VALUE / 2;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        int n = ii();
        String teamName[] = isa(n);
        HashMap<String, Integer> hash = new HashMap<>();
        Table[] leaderBoard = new Table[n];
        for (int i = 0; i < n; i++) {
            hash.put(teamName[i], i);
            leaderBoard[i] = new Table(teamName[i]);
        }
        int m = ii();
        HashMap<String, Integer> matches = new HashMap<>();
        for (int M = 0; M < m; M++) {
            String team1 = is(), team2 = is();
            int goal1 = ii(), goal2 = ii();
            if (team1.equals(team2)) {
                out.println("Invalid Input");
                return;
            }
            String map = team1 + " " + team2;
            int value = 0;
            if (matches.containsKey(map)) {
                value = matches.get(map);
                if (value == 2) {
                    out.println("Invalid Input");
                    return;
                }
            }
            matches.put(map, value + 1);
            map = team2 + " " + team1;
            matches.put(map, value + 1);

            int index = hash.get(team1);
            leaderBoard[index].GF += goal1;
            leaderBoard[index].GA += goal2;
            int points = goal1 > goal2 ? 2 : goal1 == goal2 ? 1 : 0;
            leaderBoard[index].points += points;

            index = hash.get(team2);
            leaderBoard[index].GF += goal2;
            leaderBoard[index].GA += goal1;
            points = goal2 > goal1 ? 2 : goal1 == goal2 ? 1 : 0;
            leaderBoard[index].points += points;
        }
        for (int i = 0; i < n; i++)
            leaderBoard[i].prepare();
        Arrays.sort(leaderBoard);
        for (int i = 0; i < n; i++)
            out.println(leaderBoard[i].originalName);
    }

    class Table implements Comparable<Table> {
        String originalName, name;
        int GF, GA, GD, points;

        Table(String team) {
            originalName = team;
            name = team.toLowerCase();
        }

        void prepare() {
            GD = GF - GA;
        }

        @Override
        public int compareTo(Table o) {
            if (points < o.points)
                return 1;
            if (points > o.points)
                return -1;
            if (GD < o.GD)
                return 1;
            if (GD > o.GD)
                return -1;
            if (GF < o.GF)
                return 1;
            if (GF > o.GF)
                return -1;
            return originalName.compareTo(o.originalName);
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
                    new FootballLeague().main1();
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
