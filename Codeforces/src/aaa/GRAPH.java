package aaa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ankurverma1994
 */
class GRAPH {
    public static void main(String[] args) {
    }

    boolean visit[];

    //iterative DFS
    void dfs1(ArrayList<Integer> graph[], int s) {
        Stack<Integer> st = new Stack<>();
        st.push(s);
        while (!st.isEmpty()) {
            int v = st.pop();
            if (!visit[v]) {
                visit[v] = true;
                connComp++;
                for (int i = 0; i < graph[v].size(); i++) {
                    int w = graph[v].get(i);
                    st.push(w);
                }
            }
        }
    }

    //recursive DFS
    void dfs(ArrayList<Integer> graph[], int s) {
        visit[s] = true;
        //connComp++;
        for (int i = 0; i < graph[s].size(); i++) {
            if (!visit[graph[s].get(i)]) {
                dfs(graph, graph[s].get(i));
            }
        }
    }

    //coloured DFS
    String color[]; // initialize colour to white

    void dfsc(ArrayList<Integer> graph[], int s) {
        color[s] = "gray";
        for (int i = 0; i < graph[s].size(); i++) {
            if (color[i].compareTo("white") == 0)
                dfs(graph, i);
        }
        color[s] = "black";
        /**
         ** Black color here is not used, but you can use it sometimes.
         ** Time complexity : O(n + m).
         **/
    }

    //conneted component
    long connComp = 0;

    long MaxConnectedComp(ArrayList<Integer> graph[], int n) {
        visit = new boolean[n];
        long ans = 1;
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                connComp = 0;
                dfs1(graph, i);
                ans = Math.max(ans, connComp);
            }
        }
        return ans;
    }

    /*
    *   Distance of vertex u from v is d[u].
    *   Time complexity : O(n + m).
    */
    /*
        Path from node to root
        while(parent[z]!=z){
                        ans+= (value[z]*counter );
                        z=parent[z];
                        counter++;
                    }
       No need for counter
    */
    void bfs(int v, ArrayList<Integer> graph[]) {
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
    }

    // Funtion that implements Dijkstra's single source shortest path algorithm
// for a graph represented using adjacency matrix representation
    void dijkstra(int graph[][], int src) {
        int d[] = new int[graph.length]; // declare globally
        boolean sptSet[] = new boolean[graph.length];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[src] = 0;
        for (int count = 0; count < graph.length - 1; count++) {
            int u = minDistance(d, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < graph.length; v++)
                if (!sptSet[v] && graph[u][v] > 0 && d[u] != Integer.MAX_VALUE && d[u] + graph[u][v] < d[v])
                    d[v] = d[u] + graph[u][v];
        }
    }

    int minDistance(int dist[], boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = 0;
        for (int v = 0; v < dist.length; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }
}