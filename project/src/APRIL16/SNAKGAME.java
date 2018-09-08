package APRIL16;

import java.io.*;
import java.util.*;
import java.math.*;

class SNAKGAME {
    boolean a[][];
    boolean visit[][];
    int n, m, size;
    Snake snake[] = new Snake[2];
    int dx[] = {1, 0, -1, 0};
    int dy[] = {0, 1, 0, -1};
    Random rn, rm;

    //------------> Solution starts here!!
    @SuppressWarnings("Main Logic")
    void solve() {
        double t1 = System.currentTimeMillis(), t2;
        n = ii();
        m = ii();
        rn = new Random(n);
        rm = new Random(m);
        int k = ii();
        a = new boolean[n][m];
        for (int i = 0; i < k; i++) {
            a[ii() - 1][ii() - 1] = true;
        }
        makeNew2();
        while (true) {
            String s = is();
            t2 = System.currentTimeMillis();
            if (t2 - t1 >= 8200) {
                out.println("EXIT");
                out.flush();
                break;
            }
            if (s.equals("MOVE")) {
                int fromx = ii() - 1, fromy = ii() - 1, x = ii() - 1, y = ii() - 1;
                computerMove(new Point(fromx, fromy), new Point(x, y));
            } else if (s.equals("NEW")) {
                int x = ii() - 1, y = ii() - 1;
                a[x][y] = true;
                snake[1] = new Snake(x, y);
            } else if (s.equals("FREEZE")) {
                int turns = ii();
            } else if (s.equals("EXIT")) {
                return;
            }
            nextMove1();
            if (exit)
                return;
        }
    }

    void nextMove() {
        int x = snake[0].endx, y = snake[0].endy;
        boolean move = false;
        for (int i = 0; i < 4; i++)
            if (valid(x + dx[i], y + dy[i]) && !a[x + dx[i]][y + dy[i]]) {
                a[x + dx[i]][y + dy[i]] = true;
                move = true;
                snake[0].update(x, y, x + dx[i], y + dy[i]);
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + dx[i] + 1) + " " + (y + dy[i] + 1));
                out.flush();
                break;
            }
        if (!move) {
            x = snake[0].startx;
            y = snake[0].starty;
            for (int i = 0; i < 4; i++)
                if (valid(x + dx[i], y + dy[i]) && !a[x + dx[i]][y + dy[i]]) {
                    a[x + dx[i]][y + dy[i]] = true;
                    move = true;
                    snake[0].update(x, y, x + dx[i], y + dy[i]);
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + dx[i] + 1) + " " + (y + dy[i] + 1));
                    out.flush();
                    break;
                }
        }
        if (!move)
            makeNew();
    }

    // x+ 0
    //x- 1
    //y+ 2
    //y- 3
    void nextMove1() {
        int x = snake[0].endx, y = snake[0].endy;
        int dir = snake[0].direction;
        boolean move = false;
        if (dir == 0) {
            if (valid(x + 1, y) && !a[x + 1][y]) {
                a[x + 1][y] = true;
                move = true;
                snake[0].update(x, y, x + 1, y);
                snake[0].direction = 0;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 2) + " " + (y + 1));
                out.flush();
            } else if (!move && valid(x, y + 1) && !a[x][y + 1]) {
                a[x][y + 1] = true;
                move = true;
                snake[0].update(x, y, x, y + 1);
                snake[0].direction = 2;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + (y + 2));
                out.flush();
            } else if (!move && valid(x, y - 1) && !a[x][y - 1]) {
                a[x][y - 1] = true;
                move = true;
                snake[0].update(x, y, x, y - 1);
                snake[0].direction = 3;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + y);
                out.flush();
            } else if (!move && valid(x - 1, y) && !a[x - 1][y]) {
                a[x - 1][y] = true;
                move = true;
                snake[0].update(x, y, x - 1, y);
                snake[0].direction = 1;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + x + " " + (y + 1));
                out.flush();
            }
        }
        if (dir == 1) {
            if (!move && valid(x - 1, y) && !a[x - 1][y]) {
                a[x - 1][y] = true;
                move = true;
                snake[0].update(x, y, x - 1, y);
                snake[0].direction = 1;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + x + " " + (y + 1));
                out.flush();
            } else if (!move && valid(x, y + 1) && !a[x][y + 1]) {
                a[x][y + 1] = true;
                move = true;
                snake[0].update(x, y, x, y + 1);
                snake[0].direction = 2;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + (y + 2));
                out.flush();
            } else if (!move && valid(x, y - 1) && !a[x][y - 1]) {
                a[x][y - 1] = true;
                move = true;
                snake[0].update(x, y, x, y - 1);
                snake[0].direction = 3;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + y);
                out.flush();
            } else if (!move && valid(x + 1, y) && !a[x + 1][y]) {
                a[x + 1][y] = true;
                move = true;
                snake[0].update(x, y, x + 1, y);
                snake[0].direction = 0;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 2) + " " + (y + 1));
                out.flush();
            }
        }
        if (dir == 2) {
            if (valid(x + 1, y) && !a[x + 1][y]) {
                a[x + 1][y] = true;
                move = true;
                snake[0].update(x, y, x + 1, y);
                snake[0].direction = 0;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 2) + " " + (y + 1));
                out.flush();
            } else if (!move && valid(x - 1, y) && !a[x - 1][y]) {
                a[x - 1][y] = true;
                move = true;
                snake[0].update(x, y, x - 1, y);
                snake[0].direction = 1;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + x + " " + (y + 1));
                out.flush();
            } else if (!move && valid(x, y + 1) && !a[x][y + 1]) {
                a[x][y + 1] = true;
                move = true;
                snake[0].update(x, y, x, y + 1);
                snake[0].direction = 2;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + (y + 2));
                out.flush();
            } else if (!move && valid(x, y - 1) && !a[x][y - 1]) {
                a[x][y - 1] = true;
                move = true;
                snake[0].update(x, y, x, y - 1);
                snake[0].direction = 3;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + y);
                out.flush();
            }
        }
        if (dir == 3) {
            if (valid(x + 1, y) && !a[x + 1][y]) {
                a[x + 1][y] = true;
                move = true;
                snake[0].update(x, y, x + 1, y);
                snake[0].direction = 0;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 2) + " " + (y + 1));
                out.flush();
            } else if (!move && valid(x - 1, y) && !a[x - 1][y]) {
                a[x - 1][y] = true;
                move = true;
                snake[0].update(x, y, x - 1, y);
                snake[0].direction = 1;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + x + " " + (y + 1));
                out.flush();
            } else if (!move && valid(x, y - 1) && !a[x][y - 1]) {
                a[x][y - 1] = true;
                move = true;
                snake[0].update(x, y, x, y - 1);
                snake[0].direction = 3;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + y);
                out.flush();
            } else if (!move && valid(x, y + 1) && !a[x][y + 1]) {
                a[x][y + 1] = true;
                move = true;
                snake[0].update(x, y, x, y + 1);
                snake[0].direction = 2;
                out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + (y + 2));
                out.flush();
            }
        }
        x = snake[0].startx;
        y = snake[0].starty;
        dir = snake[0].direction;
        if (!move) {
            if (dir == 0) {
                if (valid(x + 1, y) && !a[x + 1][y]) {
                    a[x + 1][y] = true;
                    move = true;
                    snake[0].update(x, y, x + 1, y);
                    snake[0].direction = 0;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 2) + " " + (y + 1));
                    out.flush();
                } else if (!move && valid(x, y + 1) && !a[x][y + 1]) {
                    a[x][y + 1] = true;
                    move = true;
                    snake[0].update(x, y, x, y + 1);
                    snake[0].direction = 2;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + (y + 2));
                    out.flush();
                } else if (!move && valid(x, y - 1) && !a[x][y - 1]) {
                    a[x][y - 1] = true;
                    move = true;
                    snake[0].update(x, y, x, y - 1);
                    snake[0].direction = 3;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + y);
                    out.flush();
                } else if (!move && valid(x - 1, y) && !a[x - 1][y]) {
                    a[x - 1][y] = true;
                    move = true;
                    snake[0].update(x, y, x - 1, y);
                    snake[0].direction = 1;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + x + " " + (y + 1));
                    out.flush();
                }
            }
            if (dir == 1) {
                if (!move && valid(x - 1, y) && !a[x - 1][y]) {
                    a[x - 1][y] = true;
                    move = true;
                    snake[0].update(x, y, x - 1, y);
                    snake[0].direction = 1;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + x + " " + (y + 1));
                    out.flush();
                } else if (!move && valid(x, y + 1) && !a[x][y + 1]) {
                    a[x][y + 1] = true;
                    move = true;
                    snake[0].update(x, y, x, y + 1);
                    snake[0].direction = 2;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + (y + 2));
                    out.flush();
                } else if (!move && valid(x, y - 1) && !a[x][y - 1]) {
                    a[x][y - 1] = true;
                    move = true;
                    snake[0].update(x, y, x, y - 1);
                    snake[0].direction = 3;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + y);
                    out.flush();
                } else if (!move && valid(x + 1, y) && !a[x + 1][y]) {
                    a[x + 1][y] = true;
                    move = true;
                    snake[0].update(x, y, x + 1, y);
                    snake[0].direction = 0;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 2) + " " + (y + 1));
                    out.flush();
                }
            }
            if (dir == 2) {
                if (valid(x + 1, y) && !a[x + 1][y]) {
                    a[x + 1][y] = true;
                    move = true;
                    snake[0].update(x, y, x + 1, y);
                    snake[0].direction = 0;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 2) + " " + (y + 1));
                    out.flush();
                } else if (!move && valid(x - 1, y) && !a[x - 1][y]) {
                    a[x - 1][y] = true;
                    move = true;
                    snake[0].update(x, y, x - 1, y);
                    snake[0].direction = 1;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + x + " " + (y + 1));
                    out.flush();
                } else if (!move && valid(x, y + 1) && !a[x][y + 1]) {
                    a[x][y + 1] = true;
                    move = true;
                    snake[0].update(x, y, x, y + 1);
                    snake[0].direction = 2;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + (y + 2));
                    out.flush();
                } else if (!move && valid(x, y - 1) && !a[x][y - 1]) {
                    a[x][y - 1] = true;
                    move = true;
                    snake[0].update(x, y, x, y - 1);
                    snake[0].direction = 3;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + y);
                    out.flush();
                }
            }
            if (dir == 3) {
                if (valid(x + 1, y) && !a[x + 1][y]) {
                    a[x + 1][y] = true;
                    move = true;
                    snake[0].update(x, y, x + 1, y);
                    snake[0].direction = 0;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 2) + " " + (y + 1));
                    out.flush();
                } else if (!move && valid(x - 1, y) && !a[x - 1][y]) {
                    a[x - 1][y] = true;
                    move = true;
                    snake[0].update(x, y, x - 1, y);
                    snake[0].direction = 1;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + x + " " + (y + 1));
                    out.flush();
                } else if (!move && valid(x, y - 1) && !a[x][y - 1]) {
                    a[x][y - 1] = true;
                    move = true;
                    snake[0].update(x, y, x, y - 1);
                    snake[0].direction = 3;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + y);
                    out.flush();
                } else if (!move && valid(x, y + 1) && !a[x][y + 1]) {
                    a[x][y + 1] = true;
                    move = true;
                    snake[0].update(x, y, x, y + 1);
                    snake[0].direction = 2;
                    out.println("MOVE " + (x + 1) + " " + (y + 1) + " " + (x + 1) + " " + (y + 2));
                    out.flush();
                }
            }
        }
        if (!move)
            makeNew2();
    }

    boolean exit = false;

    void computerMove(Point from, Point to) {
        a[to.x][to.y] = true;
        if ((snake[0].startx == from.x && snake[0].starty == from.y) || (snake[0].endx == from.x && snake[0].endy == from.y))
            snake[0].update(from.x, from.y, to.x, to.y);
        else
            snake[1].update(from.x, from.y, to.x, to.y);
    }

    void makeNew() {
        Point start = new Point(0, 0);
        boolean make = false;
        int max = 0;
        visit = new boolean[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                visit[i][j] = a[i][j];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    size = 0;
                    dfs(new Point(i, j));
                    if (size > max) {
                        make = true;
                        max = size;
                        start.x = i;
                        start.y = j;
                    }
                }
            }
        }
        if (make) {
            a[start.x][start.y] = true;
            out.println("NEW " + (start.x + 1) + " " + (start.y + 1));
        } else {
            out.println("EXIT");
            exit = true;
        }
        snake[0] = new Snake(start.x, start.y);
        out.flush();
    }

    int curi = 0;

    void makeNew1() {
        boolean make = false;
        for (; curi < n; curi++) {
            for (int curj = 0; curj < m; curj++) {
                if (!a[curi][curj]) {
                    a[curi][curj] = true;
                    out.println("NEW " + (curi + 1) + " " + (curj + 1));
                    snake[0] = new Snake(curi, curj);
                    make = true;
                    break;
                }
            }
            if (make)
                break;
        }
        if (!make) {
            out.println("EXIT");
            exit = true;
        }
        out.flush();
    }

    void makeNew2() {
        boolean make = false;
        for (int i = 0; i < 30000; i++) {
            int x = rn.nextInt(n);
            int y = rm.nextInt(m);
//            int x = (int) (Math.random() * n);
//            int y = (int) (Math.random() * m);
            if (!a[x][y]) {
                a[x][y] = true;
                out.println("NEW " + (x + 1) + " " + (y + 1));
                snake[0] = new Snake(x, y);
                make = true;
                break;
            }
        }
        if (!make) {
            out.println("EXIT");
            exit = true;
        }
        out.flush();
    }

    void dfs(Point s) {
        visit[s.x][s.y] = true;
        size++;
        for (int i = 0; i < 4; i++) {
            int x = s.x + dx[i];
            int y = s.y + dy[i];
            if (valid(x, y) && !visit[x][y]) {
                dfs(new Point(x, y));
            }
        }
    }

    boolean valid(int x, int y) {
        if (0 <= x && x < n && 0 <= y && y < m)
            return true;
        return false;
    }

    class Point {
        int x, y;

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }
    // x+ 0
    //x- 1
    //y+ 2
    //y- 3

    class Snake {
        int startx, starty;
        int endx, endy;
        int direction;

        Snake(int x, int y) {
            startx = endx = x;
            starty = endy = y;
        }

        void update(int cornerx, int cornery, int updatex, int updatey) {
            if (cornerx == endx && cornery == endy) {
                if (updatex > endx) direction = 0;
                if (updatex < endx) direction = 1;
                if (updatey > endy) direction = 2;
                if (updatey < endy) direction = 3;
                endx = updatex;
                endy = updatey;
            } else {
                if (updatex > startx) direction = 0;
                if (updatex < startx) direction = 1;
                if (updatey > starty) direction = 2;
                if (updatey < starty) direction = 3;
                startx = updatex;
                starty = updatey;
            }
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
                    new SNAKGAME().main1();
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

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }
}