import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

class Codeground {
    final static int mod = (int) 1e9 + 7;
    static long fac[];
    static boolean visit[];
    static ArrayList<Integer> tree[];
    static long ans;
    static int source;

    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(obj.readLine());
        pre(n + 1);
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<>();
        visit = new boolean[n];
        ans = 1;

        for (int i = 0; i < n - 1; i++) {
            args = obj.readLine().split(" ");
            int u = Integer.parseInt(args[0]) - 1;
            int v = Integer.parseInt(args[1]) - 1;
            tree[u].add(v);
            tree[v].add(u);
        }
        source = Integer.parseInt(obj.readLine()) - 1;

        dfs1(source);
        System.out.println(ans);
    }

    //iterative DFS doesn't causes RE.
    public static void dfs1(int s) {
        Stack<Integer> st = new Stack<Integer>();
        st.push(s);
        while (!st.isEmpty()) {
            int v = st.pop();
            if (!visit[v]) {
                visit[v] = true;
                if (v == source)
                    ans = (ans * fac[tree[v].size()]) % mod;
                else
                    ans = (ans * fac[tree[v].size() - 1]) % mod;
                for (int i = 0; i < tree[v].size(); i++) {
                    int w = tree[v].get(i);
                    st.push(w);
                }
            }
        }
    }

    public static void pre(int n) {
        fac = new long[n + 1];
        fac[0] = 1;
        for (int i = 1; i <= n; i++)
            fac[i] = (i * fac[i - 1]) % mod;
    }
}