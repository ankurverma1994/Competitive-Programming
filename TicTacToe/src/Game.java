import java.util.*;

public class Game {

    byte[] smallSquare;
    byte[] bigSquare;
    byte[] movesperBigSquare;
    byte currentPlayer;
    byte noOfFreeSquare;
    private Stack<Integer> playedMoves;

    final byte EMPTY = 0;
    final byte CROSS = 1;
    final byte CIRCLE = 2;
    final byte DRAW = 3;

    /**
     * random numbers. Size 2*81.
     */
    long[][] randomsmallNumbers;
    long[][] randombignumbers;
    Map<Long, Integer> hitMap;

    public Game() {
        this.currentPlayer = CROSS;

        this.noOfFreeSquare = 81;

        this.smallSquare = new byte[81];
        this.bigSquare = new byte[9];
        this.movesperBigSquare = new byte[9];
        this.playedMoves = new Stack<Integer>();

        for (int i = 0; i < this.smallSquare.length; i++) {
            this.smallSquare[i] = EMPTY;
        }

        for (int i = 0; i < this.bigSquare.length; i++) {
            this.bigSquare[i] = EMPTY;
            this.movesperBigSquare[i] = 0;
        }

        this.computeRandoms();
        this.hitMap = new HashMap<>();
    }

    int getCurrentPlayer() {
        return this.currentPlayer;
    }

    ArrayList<Integer> getSuccessors() {
        ArrayList<Integer> successors = new ArrayList<Integer>();

        if (this.playedMoves.empty()) {
            for (int i = 0; i < 81; i++) {
                successors.add(i);
            }

            return successors;
        }

        int lastMove = this.playedMoves.peek();

        int square = lastMove / 9;
        int newSquare = lastMove - square * 9;

        if (this.bigSquare[newSquare] == EMPTY) {

            int firstBabySquare = newSquare * 9;

            for (int i = firstBabySquare; i < firstBabySquare + 9; i++) {
                if (this.smallSquare[i] == EMPTY) {
                    successors.add(i);
                }
            }
        } else {
            for (int i = 0; i < this.bigSquare.length; i++) {
                if (this.bigSquare[i] == EMPTY) {
                    int tmp = i * 9;
                    for (int j = tmp; j < tmp + 9; j++) {
                        if (this.smallSquare[j] == EMPTY) {
                            successors.add(j);
                        }
                    }
                }
            }
        }

        return successors;
    }

    /**
     * Play the move for the current player.
     */
    void play(int move) {
        int square = move / 9;

        this.smallSquare[move] = this.currentPlayer;
        this.movesperBigSquare[square]++;
        this.noOfFreeSquare--;

        if (this.winDaddySquare(move)) {
            this.bigSquare[square] = this.currentPlayer;
            this.noOfFreeSquare -= (9 - this.movesperBigSquare[square]);
        } else if (this.movesperBigSquare[square] == 9) {
            this.bigSquare[square] = DRAW;
        }

        this.playedMoves.push(move);
        this.addHit();

        this.changePlayer();
    }

    void unplay() {

        int lastMove = this.playedMoves.pop();

        int square = lastMove / 9;

        if (this.bigSquare[square] != EMPTY) {
            this.bigSquare[square] = EMPTY;
            this.noOfFreeSquare += (9 - this.movesperBigSquare[square]);
        }

        this.smallSquare[lastMove] = EMPTY;
        this.movesperBigSquare[square]--;
        this.noOfFreeSquare++;

        this.changePlayer();
    }

    /**
     * Change the current player.
     */
    void changePlayer() {
        this.currentPlayer = (this.currentPlayer == CROSS) ? CIRCLE : CROSS;
    }

    int isEndOfGame() {

        // There are not enough moves played to have a winning situation.
        if (this.playedMoves.size() < 17) {
            return 0;
        }

        int lastMove = this.playedMoves.peek();

        int normalizedMove = lastMove / 9;

        if (this.winHorizontally(this.bigSquare, normalizedMove)
                || this.winVertically(this.bigSquare, normalizedMove)
                || this.winDiagonally(this.bigSquare, normalizedMove)) {
            return this.currentPlayer == CROSS ? CIRCLE : CROSS;
        }

        if (this.noOfFreeSquare == 0) {
            return DRAW;
        }

        return 0;
    }

    private boolean winHorizontally(byte[] table, int normalizedMove) {
        int tmp = normalizedMove / 3;
        tmp *= 3;
        return table[tmp] != DRAW && table[tmp] != EMPTY && table[tmp] == table[tmp + 1] && table[tmp + 1] == table[tmp + 2];
    }

    private boolean winVertically(byte[] table, int normalizedMove) {
        int tmp = normalizedMove % 3;
        return table[tmp] != DRAW && table[tmp] != EMPTY && table[tmp] == table[tmp + 3] && table[tmp + 3] == table[tmp + 6];
    }

    private boolean winDiagonally(byte[] table, int normalizedMove) {
        // Normalized Move is even.
        if (normalizedMove % 2 != 0 || table[4] == DRAW || table[4] == EMPTY) {
            return false;
        }

        return table[4] == table[0] && table[4] == table[8] || table[4] == table[2] && table[4] == table[6];
    }

    private boolean winDaddySquare(int move) {
        byte[] subtable = new byte[9];
        int normalizedMove = move % this.bigSquare.length;
        int rg = move / 9;
        rg *= 9;

        for (int i = 0; i < 9; i++) {
            subtable[i] = this.smallSquare[i + rg];
        }

        return this.winHorizontally(subtable, normalizedMove)
                || this.winVertically(subtable, normalizedMove)
                || this.winDiagonally(subtable, normalizedMove);
    }

    public int getScore(int player, int winner) {
        int adversary = player == CIRCLE ? CROSS : CIRCLE;

        if (player == winner) {
            return Integer.MAX_VALUE;
        } else if (adversary == winner) {
            return Integer.MIN_VALUE;
        }

        return 0;
    }

    public int new_eval(int player) {
        int[] babyTableEval = new int[9];

        int adversary = player == CIRCLE ? CROSS : CIRCLE;

        for (int i = 0; i < 9; i++) {
            if (this.bigSquare[i] == player) {
                babyTableEval[i] = 100;
                break;
            } else if (this.bigSquare[i] == adversary) {
                babyTableEval[i] = -100;
                break;
            } else if (this.bigSquare[i] == DRAW) {
                babyTableEval[i] = 0;
                break;
            } else if (this.bigSquare[i] == EMPTY) {
                int startIndex = i * 9;

                babyTableEval[i] = 0;

                for (int j = startIndex; j < startIndex + 3; j++) {
                    if ((this.smallSquare[j] == EMPTY || this.smallSquare[j] == player)
                            && (this.smallSquare[j + 3] == EMPTY || this.smallSquare[j + 3] == player)
                            && (this.smallSquare[j + 6] == EMPTY || this.smallSquare[j + 6] == player)) {
                        for (int k = j; k < j + 9; k += 3) {
                            if (this.smallSquare[k] == player) {
                                babyTableEval[i]++;
                            }
                        }

                    }
                    if ((this.smallSquare[j] == EMPTY || this.smallSquare[j] == adversary)
                            && (this.smallSquare[j + 3] == EMPTY || this.smallSquare[j + 3] == adversary)
                            && (this.smallSquare[j + 6] == EMPTY || this.smallSquare[j + 6] == adversary)) {
                        for (int k = j; k < j + 9; k += 3) {
                            if (this.smallSquare[k] == adversary) {
                                babyTableEval[i]--;
                            }
                        }
                    }
                }

                for (int j = startIndex; j < startIndex + 9; j += 3) {
                    if ((this.smallSquare[j] == EMPTY || this.smallSquare[j] == player)
                            && (this.smallSquare[j + 1] == EMPTY || this.smallSquare[j + 1] == player)
                            && (this.smallSquare[j + 2] == EMPTY || this.smallSquare[j + 2] == player)) {
                        for (int k = j; k < j + 3; k++) {
                            if (this.smallSquare[k] == player) {
                                babyTableEval[i]++;
                            }
                        }
                    }
                    if ((this.smallSquare[j] == EMPTY || this.smallSquare[j] == adversary)
                            && (this.smallSquare[j + 1] == EMPTY || this.smallSquare[j + 1] == adversary)
                            && (this.smallSquare[j + 2] == EMPTY || this.smallSquare[j + 2] == adversary)) {
                        for (int k = j; k < j + 3; k++) {
                            if (this.smallSquare[k] == adversary) {
                                babyTableEval[i]--;
                            }
                        }
                    }
                }

                if ((this.smallSquare[0] == EMPTY || this.smallSquare[0] == player)
                        && (this.smallSquare[4] == EMPTY || this.smallSquare[4] == player)
                        && (this.smallSquare[8] == EMPTY || this.smallSquare[8] == player)) {
                    for (int k = startIndex; k < startIndex + 9; k += 4) {
                        if (this.smallSquare[k] == player) {
                            babyTableEval[i]++;
                        }
                    }
                }
                if ((this.smallSquare[0] == EMPTY || this.smallSquare[0] == adversary)
                        && (this.smallSquare[4] == EMPTY || this.smallSquare[4] == adversary)
                        && (this.smallSquare[8] == EMPTY || this.smallSquare[8] == adversary)) {
                    for (int k = startIndex; k < startIndex + 9; k += 4) {
                        if (this.smallSquare[k] == adversary) {
                            babyTableEval[i]--;
                        }
                    }
                }
                if ((this.smallSquare[2] == EMPTY || this.smallSquare[2] == player)
                        && (this.smallSquare[4] == EMPTY || this.smallSquare[4] == player)
                        && (this.smallSquare[6] == EMPTY || this.smallSquare[6] == player)) {
                    for (int k = startIndex + 2; k < startIndex + 7; k += 2) {
                        if (this.smallSquare[k] == player) {
                            babyTableEval[i]++;
                        }
                    }
                }
                if ((this.smallSquare[0] == EMPTY || this.smallSquare[0] == adversary)
                        && (this.smallSquare[4] == EMPTY || this.smallSquare[4] == adversary)
                        && (this.smallSquare[8] == EMPTY || this.smallSquare[8] == adversary)) {
                    for (int k = startIndex + 2; k < startIndex + 7; k += 2) {
                        if (this.smallSquare[k] == adversary) {
                            babyTableEval[i]--;
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 3; i++) {
            ArrayList<Byte> tmp = new ArrayList<Byte>();
            for (int j = 0; j < 9; j += 3) {
                tmp.add(bigSquare[i + j]);
            }

            if (tmp.contains(DRAW) || (tmp.contains(CIRCLE) && tmp.contains(CROSS))) {
                break;
            }

            for (int j = 0; j < 9; j += 3) {
                res += babyTableEval[i + j];
            }
        }
        for (int i = 0; i < 9; i += 3) {
            List<Byte> tmp = new ArrayList<Byte>();
            for (int j = 0; j < 3; j++) {
                tmp.add(bigSquare[i + j]);
            }

            if (tmp.contains(DRAW) || (tmp.contains(CIRCLE) && tmp.contains(CROSS))) {
                break;
            }

            for (int j = 0; j < 3; j++) {
                res += babyTableEval[i + j];
            }
        }
        List<Byte> tmp = new ArrayList<Byte>();
        for (int j = 0; j < 9; j += 4) {
            tmp.add(bigSquare[j]);
        }
        if (!(tmp.contains(DRAW) || (tmp.contains(CIRCLE) && tmp.contains(CROSS)))) {
            for (int j = 0; j < 9; j += 4) {
                res += babyTableEval[j];
            }
        }

        // On évalue la formation de grandes anti diagonales.
        tmp.clear();
        for (int j = 2; j < 7; j += 2) {
            tmp.add(bigSquare[j]);
        }
        if (!(tmp.contains(DRAW) || (tmp.contains(CIRCLE) && tmp.contains(CROSS)))) {
            for (int j = 2; j < 7; j += 2) {
                res += babyTableEval[j];
            }
        }

        return res;
    }

    private void computeRandoms() {
        Random rand = new Random();

        this.randomsmallNumbers = new long[2][81];
        this.randombignumbers = new long[3][9];
        for (int player = 0; player < 2; player++) {
            for (int babySquare = 0; babySquare < 81; babySquare++) {
                this.randomsmallNumbers[player][babySquare] = rand.nextLong();
            }
        }

        for (int player = 0; player < 3; player++) {
            for (int daddySquare = 0; daddySquare < 9; daddySquare++) {
                this.randombignumbers[player][daddySquare] = rand.nextLong();
            }
        }
    }

    long getHash() {
        long hash = 0;

        for (int i = 0; i < smallSquare.length; i++) {
            int daddySquare = i / 9;
            if (bigSquare[daddySquare] != EMPTY) {
                hash ^= randombignumbers[bigSquare[daddySquare] - 1][daddySquare];
                i += 9;
            } else if (smallSquare[i] != EMPTY) {
                hash = hash ^ randomsmallNumbers[smallSquare[i] - 1][i];
            }
        }

        return hash;
    }

    void addHit() {
        long hash = this.getHash();
        int hits = 1;

        if (hitMap.containsKey(hash)) {
            hits = hitMap.get(hash) + 1;
        }

        //hitMap.put(hash, hits);
    }

    String changeToString() {
        String display = "";
        String[] representation = {" ", "X", "O", "-"};

        for (int b = 0; b < 81; b += 27) {
            for (int i = 0; i < 9; i += 3) {
                for (int j = 0; j < 27; j += 9) {
                    for (int k = 0; k < 3; k++) {
                        if (this.bigSquare[(b + i + j + k) / 9] != EMPTY) {
                            display += " " + representation[this.bigSquare[(b + i + j + k) / 9]] + " ";
                        } else {
                            display += " " + representation[this.smallSquare[b + i + j + k]] + " ";
                        }
                    }

                    if (j != 18) {
                        display += " || ";
                    }
                }
                display += "\n";
            }

            if (b != 54) {
                display += "===================================\n";
            }
        }
        return display;
    }

    void displayHitMap() {
        for (Map.Entry<Long, Integer> entry : hitMap.entrySet()) {
            Long hash = entry.getKey();
            Integer nbHits = entry.getValue();

            if (nbHits > 100000) {
                System.out.println("Hash : " + hash + " ; Nb d'occurences : " + nbHits);
            }
        }
    }
}
