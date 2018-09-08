package NOV16;
/**
 * Created by ankurverma1994
 * My code is awesome!
 */

import java.io.*;
import java.util.*;

class URBANDEV {

    SegmentTree tree;
    HashMap<String, Integer> map;
    long ans = 0;
    long points[];

    //------------> Solution starts here!!
    void solve() {
        int n = ii();
        Line line1[] = new Line[n];
        Line line2[] = new Line[n];
        points = new long[n];
        for (int i = 0; i < n; i++) {
            int x1 = ii(), y1 = ii(), x2 = ii(), y2 = ii();

            line1[i] = new Line(x1, y1, x2, y2, i);
            line2[i] = new Line(y1, x1, y2, x2, i);
        }
        solve(n, line1);
        solve(n, line2);
        out.println(ans / 2);
        for (long x : points) out.print(x + " ");
        out.println();
    }

    void solve(int n, Line line[]) {
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x1 = line[i].x1, y1 = line[i].y1, x2 = line[i].x2, y2 = line[i].y2;
            if (map.containsKey(x1 + " " + y1))
                map.put(x1 + " " + y1, map.get(x1 + " " + y1) + 1);
            else
                map.put(x1 + " " + y1, 1);

            if (map.containsKey(x2 + " " + y2))
                map.put(x2 + " " + y2, map.get(x2 + " " + y2) + 1);
            else
                map.put(x2 + " " + y2, 1);
        }
        ArrayList<Event> event = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (line[i].x1 == line[i].x2) event.add(new Event(line[i], 0, line[i].x1));
            else {
                event.add(new Event(line[i], 1, line[i].x1));
                event.add(new Event(line[i], 2, line[i].x2));
            }
        }
        Collections.sort(event, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                if (o1.x != o2.x) return Integer.compare(o1.x, o2.x);
                if (o1.type == 0) return 1;
                if (o2.type == 0) return -1;
                return 0;
            }
        });
        tree = new SegmentTree(100000 + 1);
        TreeSet<Integer> container = new TreeSet<>();
        ArrayList<Integer> buffer = new ArrayList<>();
        int lastX = 0, start = 0;
        while (start < event.size() && event.get(start).type != 1) start++;
        if (start < event.size()) {
            lastX = event.get(start).x;
            insert(event.get(start).line.y1);
            container.add(event.get(start++).line.index);
        }
        for (int i = start; i < event.size(); i++) {
            int x = event.get(i).x, type = event.get(i).type;
            if (lastX != x) {
                buffer.forEach(this::delete);
                buffer.clear();
                lastX = x;
            }
            if (type == 1) {
                container.add(event.get(i).line.index);
                insert(event.get(i).line.y1);
            }
            if (type == 2) {
                container.remove(event.get(i).line.index);
                buffer.add(event.get(i).line.y1);
            }
            if (type == 0) {
                long ans = count(event.get(i).line.y1, event.get(i).line.y2);
                if (map.get(event.get(i).line.x1 + " " + event.get(i).line.y1) > 1)
                    ans--;
                if (map.get(event.get(i).line.x2 + " " + event.get(i).line.y2) > 1)
                    ans--;
                this.ans += ans;
                points[event.get(i).line.index] = ans;
            }
        }
    }

    void insert(int x) {
        tree.update(x, 1);
    }

    void delete(int x) {
        tree.update(x, -1);
    }

    int count(int x, int y) {
        return tree.query(x, y);
    }

    class Event {
        Line line;
        int type;
        int x;

        // type 0 vertical 1 startHorizontal 2 endHorizontal
        Event(Line line, int type, int x) {
            this.line = line;
            this.type = type;
            this.x = x;
        }
    }

    class Line {
        int x1, y1, x2, y2, index;

        Line(int x1, int y1, int x2, int y2, int index) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.index = index;
        }
    }

    class SegmentTree {
        int tree[];
        int a[];
        int n;

        int operation(int a, int b) {
            return a + b;
        }

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
            a = new int[n];
        }

        void update(int x, int v) {
            put(0, n - 1, 0, x, v);
        }

        void put(int s, int e, int c, int x, int v) {
            if (s == e) {
                tree[c] += v;
                a[s] += v;
                return;
            }
            int m = (s + e) >> 1;
            if (x <= m) put(s, m, 2 * c + 1, x, v);
            else put(m + 1, e, 2 * c + 2, x, v);
            tree[c] = operation(tree[2 * c + 1], tree[2 * c + 2]);
        }

        int query(int l, int r) {
            return get(0, n - 1, 0, l, r);
        }

        int get(int s, int e, int c, int l, int r) {
            if (s == l && e == r) return tree[c];
            int m = (s + e) >> 1;
            if (r <= m) return get(s, m, 2 * c + 1, l, r);
            if (l > m) return get(m + 1, e, 2 * c + 2, l, r);
            return operation(get(s, m, 2 * c + 1, l, m), get(m + 1, e, 2 * c + 2, m + 1, r));
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new URBANDEV().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
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
}
