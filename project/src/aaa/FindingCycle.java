package aaa;

import java.util.ArrayList;

class FindingCycle {
    int n;
    int graph[][];
    int color[];
    int parent[];
    int cycle_st, cycle_end;

    boolean dfs(int v) {
        color[v] = 1;
        for (int i = 0; i < graph[v].length; i++) {
            int to = graph[v][i];
            if (color[to] == 0) {
                parent[to] = v;
                if (dfs(to)) return true;
            } else if (color[to] == 1) {
                cycle_end = v;
                cycle_st = to;
                return true;
            }
        }
        color[v] = 2;
        return false;
    }

    void run() {
        // ...Read the count ...

        parent = new int[n];
        color = new int[n];
        cycle_st = -1;
        for (int i = 0; i < n; i++)
            if (dfs(i))
                break;

        if (cycle_st == -1)
            System.out.println("Acyclic");
        else {
            System.out.println("Cyclic");
            ArrayList<Integer> cycle = new ArrayList<>();
            cycle.add(cycle_st);
            for (int v = cycle_end; v != cycle_st; v = parent[v])
                cycle.add(v);

        }
    }
//            cycle.push_back (cycle_st);
//            for (int v = cycle_end; v = cycle_st;! v = p [v])
//                cycle.push_back (v);
//            cycle.push_back (cycle_st);
//            reverse (cycle.begin (), cycle.end ());
//            for (size_t i = 0; i <cycle.size (); ++ i)
//                printf ( "% d", cycle [i] +1);
//        }
//    }
}