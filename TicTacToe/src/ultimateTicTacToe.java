import java.util.Random;
import java.util.Scanner;

public class ultimateTicTacToe {
    Scanner in;

    public static void main(String[] args) {
        new ultimateTicTacToe().play();
    }
    //computer O
    //player X

    Grid grid[];
    int gridTurn;
    Random random;

    void play() {
        init();
        while (!gameDraw()) {
            player();
            if (gameDraw()) break;
            bot();
        }
        System.out.println("Game Draw!");
    }

    void bot() {
        if (gridTurn == -1) {
            boolean found = false;
            while (!found) {
                gridTurn = random.nextInt(9);
                if (grid[gridTurn].winner == '_') {
                    found = true;
                }
            }
        }
        boolean found = false;
        int idx = 0;
        while (!found) {
            idx = random.nextInt(9);
            if (grid[gridTurn].a[idx] == '_')
                found = true;
        }
        System.out.println(gridTurn + " " + idx);
        grid[gridTurn].a[idx] = 'O';
        display();
        upadteGrid(gridTurn, 'O');
        if (gameWin('O')) {
            System.out.println("Computer wins");
            System.exit(0);
        }
        gridTurn = idx;
        if (grid[gridTurn].winner != '_')
            gridTurn = -1;
    }

    void player() {
        int id = in.nextInt(), idx = in.nextInt();
        if (gridTurn == -1) gridTurn = id;
        if (id != gridTurn) {
            System.out.println("Invalid move! you loose");
            System.exit(0);
        }
        if (grid[gridTurn].winner != '_') {
            System.out.println("Invalid move! you loose");
            System.exit(0);
        }
        if (grid[gridTurn].a[idx] != '_') {
            System.out.println("Invalid move! you loose");
            System.exit(0);
        }
        grid[gridTurn].a[idx] = 'X';
        display();
        char player = 'X';
        upadteGrid(gridTurn, player);
        if (gameWin('X')) {
            System.out.println("You won");
            System.exit(0);
        }
        gridTurn = idx;
//        System.out.println(grid[gridTurn].winner);
        System.out.println();
        if (grid[gridTurn].winner != '_')
            gridTurn = -1;
    }

    void init() {
        random = new Random();
        in = new Scanner(System.in);
        gridTurn = -1;
        grid = new Grid[9];
        for (int i = 0; i < 9; i++)
            grid[i] = new Grid();
        display();
    }

    boolean gameDraw() {
        boolean draw = true;
        for (int i = 0; i < 9; i++) {
            if (grid[i].winner == '_') {
                for (int j = 0; j < 9; j++) {
                    if (grid[i].a[j] == '_') {
                        draw = false;
                        return draw;
                    }
                }
            }
        }
        return draw;
    }

    void display() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(grid[i].a[j] + " ");
            System.out.print("   ");
        }
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 6; j++)
                System.out.print(grid[i].a[j] + " ");
            System.out.print("   ");
        }
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 6; j < 9; j++)
                System.out.print(grid[i].a[j] + " ");
            System.out.print("   ");
        }
        System.out.println();
        System.out.println();
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(grid[i].a[j] + " ");
            System.out.print("   ");
        }
        System.out.println();
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++)
                System.out.print(grid[i].a[j] + " ");
            System.out.print("   ");
        }
        System.out.println();
        for (int i = 3; i < 6; i++) {
            for (int j = 6; j < 9; j++)
                System.out.print(grid[i].a[j] + " ");
            System.out.print("   ");
        }
        System.out.println();
        System.out.println();
        for (int i = 6; i < 9; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(grid[i].a[j] + " ");
            System.out.print("   ");
        }
        System.out.println();
        for (int i = 6; i < 9; i++) {
            for (int j = 3; j < 6; j++)
                System.out.print(grid[i].a[j] + " ");
            System.out.print("   ");
        }
        System.out.println();
        for (int i = 6; i < 9; i++) {
            for (int j = 6; j < 9; j++)
                System.out.print(grid[i].a[j] + " ");
            System.out.print("   ");
        }
        System.out.println();
        System.out.println();
    }

    void upadteGrid(int i, char player) {
        if (grid[i].a[0] == player && grid[i].a[1] == player && grid[i].a[2] == player) {
//            System.out.println("win");
            grid[i].winner = player;
            return;
        }
        if (grid[i].a[3] == player && grid[i].a[4] == player && grid[i].a[5] == player) {
//            System.out.println("win");
            grid[i].winner = player;
            return;
        }
        if (grid[i].a[6] == player && grid[i].a[7] == player && grid[i].a[8] == player) {
//            System.out.println("win");
            grid[i].winner = player;
            return;
        }
        if (grid[i].a[0] == player && grid[i].a[4] == player && grid[i].a[8] == player) {
//            System.out.println("win");
            grid[i].winner = player;
            return;
        }
        if (grid[i].a[2] == player && grid[i].a[4] == player && grid[i].a[6] == player) {
//            System.out.println("win");
            grid[i].winner = player;
            return;
        }
        if (grid[i].a[0] == player && grid[i].a[3] == player && grid[i].a[6] == player) {
//            System.out.println("win");
            grid[i].winner = player;
            return;
        }
        if (grid[i].a[1] == player && grid[i].a[4] == player && grid[i].a[7] == player) {
//            System.out.println("win");
            grid[i].winner = player;
            return;
        }
        if (grid[i].a[2] == player && grid[i].a[5] == player && grid[i].a[8] == player) {
//            System.out.println("win");
            grid[i].winner = player;
            return;
        }
        boolean draw = true;
        for (int j = 0; j < 9; j++)
            if (grid[i].a[j] == '_') {
                draw = false;
                break;
            }
        if (draw) grid[i].winner = 'D';
    }

    boolean gameWin(char player) {
        if (grid[0].winner == player && grid[1].winner == player && grid[2].winner == player)
            return true;
        if (grid[3].winner == player && grid[4].winner == player && grid[5].winner == player)
            return true;
        if (grid[6].winner == player && grid[7].winner == player && grid[8].winner == player)
            return true;
        if (grid[0].winner == player && grid[4].winner == player && grid[8].winner == player)
            return true;
        if (grid[2].winner == player && grid[4].winner == player && grid[6].winner == player)
            return true;
        if (grid[0].winner == player && grid[3].winner == player && grid[6].winner == player)
            return true;
        if (grid[1].winner == player && grid[4].winner == player && grid[7].winner == player)
            return true;
        if (grid[2].winner == player && grid[5].winner == player && grid[8].winner == player)
            return true;
        return false;
    }
}

class Grid {
    /*
        0 1 2
        3 4 5
        6 7 8
     */
    char a[] = new char[9];
    char winner;

    Grid() {
        winner = '_';
        for (int i = 0; i < 9; i++) {
            a[i] = '_';
        }
    }
}