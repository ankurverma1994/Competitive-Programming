

import java.util.*;

public class Main {

    static int TIMEOUT = 4000;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Scanner scan = new Scanner(System.in);

        Game g = new Game();

        int tour = 1;

        while (g.isEndOfGame() == 0) {
            if (tour % 2 == 0) {
                AlphaBeta ab = new AlphaBeta(g);
                g.play(ab.run(TIMEOUT));
                System.out.println(g.changeToString());
            } else {
                int move;
                ArrayList<Integer> successors = g.getSuccessors();
                do {
                    System.out.println("Enter values from 1 to 9 (Big Square, Small square): ");
                    int bigSquare = scan.nextInt() - 1;
                    int smallSquare = scan.nextInt() - 1;
                    move = bigSquare * 9 + smallSquare;
                } while (!successors.contains(move));
                g.play(move);
                System.out.println(g.changeToString());
            }
            tour++;
        }

        System.out.println("Temps total :" + (System.currentTimeMillis() - startTime) / 1000 + "s");

        g.displayHitMap();

    }
}
