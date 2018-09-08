import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class DijiskstraGoodImplementation {
    static int n, m;
    static ArrayList<Node> g[], h[];
    static int parent[];
    static long oo = (long) (1e14);

    static public long[] Dijkstra(int s, ArrayList<Node> g[]) {
        long[] dist = new long[g.length];
        boolean[] v = new boolean[g.length];
        parent = new int[g.length];
        Arrays.fill(dist, oo);
        Arrays.fill(parent, -1);
        dist[s] = 0;
        Comparator<Node> comp = new MyComparator();
        PriorityQueue<Node> q = new PriorityQueue<Node>(2 * g.length, comp);
        q.add(new Node(s, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (!v[now.x]) {
                v[now.x] = true;
                for (int i = 0; i < g[now.x].size(); i++) {
                    int curr = g[now.x].get(i).x;
                    long w = g[now.x].get(i).dist;
                    if (dist[now.x] + w < dist[curr]) {
                        dist[curr] = dist[now.x] + w;
                        parent[curr] = now.x;
                        q.add(new Node(curr, dist[curr]));
                    }
                }
            }
        }

        return dist;
    }

    static public long[] Dij(int s, int g[][][]) {
        long inf = Long.MAX_VALUE - 10;
        int n = g.length;
        long dis[] = new long[n];
        boolean visit[] = new boolean[n];
        int parent[] = new int[n];
        Arrays.fill(dis, inf);
        Arrays.fill(parent, -1);
        dis[s] = 0;
        Comparator<Node> comp = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.dist > o2.dist) return 1;
                if (o1.dist < o2.dist) return -1;
                return 0;
            }
        };
        PriorityQueue<Node> q = new PriorityQueue<>(2 * n, comp);
        q.add(new Node(s, 0));
        while (!q.isEmpty()) {
            Node now = q.poll();
            int u = now.x;
            if (!visit[u]) {
                visit[u] = true;
                for (int i = 0; i < g[u].length; i++) {
                    int curr = g[u][i][0];
                    long w = g[u][i][1];
                    if (dis[u] + w < dis[curr]) {
                        dis[curr] = dis[u] + w;
                        parent[curr] = u;
                        q.add(new Node(curr, dis[curr]));
                    }
                }
            }
        }
        return dis;
    }

    static class MyComparator implements Comparator<Node> {
        public int compare(Node n0, Node n1) {
            if (n0.dist > n1.dist)
                return 1;
            else if (n0.dist < n1.dist)
                return -1;

            return 0;
        }
    }

    static public class Node {
        int x;
        long dist;

        public Node(int i, long j) {
            x = i;
            dist = j;
        }
    }
}