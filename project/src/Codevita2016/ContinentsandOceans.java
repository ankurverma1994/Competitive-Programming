//package Codevita2016;

import java.io.*;
import java.util.*;

class ContinentsandOceans {
    public static void main(String args[]) throws IOException {
        new ContinentsandOceans().main();
    }

    ArrayList<Integer> ans;
    int row = 15, col = 60;
    char grid[][];
    boolean visit[][];
    int count;
    int dx[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    int dy[] = {-1, -1, -1, 0, 0, 1, 1, 1};

    void main() throws IOException {
        /* Initialization*/
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String path = obj.readLine().trim();
        BufferedReader br = new BufferedReader(new FileReader(path));
        ans = new ArrayList<>();
        grid = new char[row][col];
        visit = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visit[i][j] && grid[i][j] == '#') {
                    count = 0;
                    dfs1(i, j);
                    ans.add(count);
                }
            }
        }

        Collections.sort(ans);
        for (int i = ans.size() - 1, j = 1; i >= 0; i--, j++) {
            out.printf("Island %d: %d\n", j, ans.get(i));
        }
        out.println("Number of continents: " + ans.size());

        out.flush();
        out.close();
    }

    void dfs1(int i, int j) {
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(i, j));
        while (!st.isEmpty()) {
            Pair v = st.pop();
            if (!visit[v.x][v.y]) {
                visit[v.x][v.y] = true;
                count++;
                for (int w = 0; w < 8; w++) {
                    if (valid(v.x + dx[w], v.y + dy[w]))
                        st.push(new Pair(v.x + dx[w], v.y + dy[w]));
                }
            }
        }
    }

    class Pair {
        int x, y;

        Pair(int a, int b) {
            x = a;
            y = b;
        }
    }

    void dfs(int i, int j) {
        visit[i][j] = true;
        count++;
        for (int v = 0; v < 8; v++) {
            int x = i + dx[v], y = j + dy[v];
            if (valid(x, y))
                dfs(x, y);
        }
    }

    boolean valid(int i, int j) {
        if (0 <= i && i < row && 0 <= j && j < col && grid[i][j] == '#' && !visit[i][j])
            return true;
        return false;
    }

}