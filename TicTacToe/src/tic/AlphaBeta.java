

import java.util.*;


public class AlphaBeta {
    Game game;

    private final ArrayList<Integer> bestMoves;
    private final int player;

    AlphaBeta(Game game) {
        System.out.println("AlphaBeta");

        this.game = game;
        this.bestMoves = new ArrayList<>();
        this.player = this.game.getCurrentPlayer();
    }

    int run(int timeout) {
        long firstTime = System.currentTimeMillis();
        int depth = 1;
        while (System.currentTimeMillis() - firstTime < timeout) {
            maxValue(Integer.MIN_VALUE, Integer.MAX_VALUE, this.bestMoves, depth++);
        }
        System.out.println("Execution time of AlphaBeta : " + (System.currentTimeMillis() - firstTime));
//        System.out.println(this.bestMoves.toString());
        return this.bestMoves.get(this.bestMoves.size() - 1);
    }

    int maxValue(int alpha, int beta, List<Integer> actionList, int depth) {
        int result = this.game.isEndOfGame();
        if (result != 0) {
            return this.game.getScore(this.player, result);
        }

        if (depth == 0) {
            return this.game.new_eval(this.player);
        }

        int v = Integer.MIN_VALUE;
        List<Integer> successors = this.game.getSuccessors();

        for (Integer move : successors) {
            List<Integer> tmp = new ArrayList<>();

            this.game.play(move);
            int vbis = minValue(alpha, beta, tmp, depth - 1);
            this.game.unplay();

            if (vbis > v) {
                v = vbis;
                actionList.clear();
                actionList.addAll(tmp);
                actionList.add(move);
            }

            if (v >= beta) {
                return v;
            }

            alpha = Math.max(alpha, v);
        }

        return v;
    }

    int minValue(int alpha, int beta, List<Integer> actionList, int depth) {

        int result = game.isEndOfGame();
        if (result != 0) {
            return game.getScore(this.player, result);
        }

        if (depth == 0) {
            return this.game.new_eval(this.player);
        }

        int v = Integer.MAX_VALUE;
        List<Integer> successors = game.getSuccessors();

        for (Integer move : successors) {
            List<Integer> tmp = new ArrayList<Integer>();

            game.play(move);
            int vbis = maxValue(alpha, beta, tmp, depth - 1);
            game.unplay();

            if (vbis < v) {
                v = vbis;
                actionList.clear();
                actionList.addAll(tmp);
                actionList.add(move);
            }

            if (v <= alpha) {
                return v;
            }

            beta = Math.min(beta, v);
        }

        return v;
    }
}
