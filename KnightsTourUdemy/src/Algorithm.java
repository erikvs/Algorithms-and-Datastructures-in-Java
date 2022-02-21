public class Algorithm {

    //Board variable
    private int[][] solutionMatrix;

    //All valid moves
    private int xMoves[] = {2, 1, -1, -2, -2, -1, 1, 2};
    private int yMoves[] = {1, 2, 2, 1, -1, -2, -2, -1};

    //Generate a chess board
    public Algorithm() {
        this.solutionMatrix = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        initializeBoard();

    }

    //method for generating 8x8 board
    private void initializeBoard() {

        for (int i = 0; i < Constants.BOARD_SIZE; i++)
            for (int j = 0; j < Constants.BOARD_SIZE; j++)
                //tracking if a cell has been visited
                solutionMatrix[i][j] = Integer.MIN_VALUE;
    }

    public void solveTour(int xStart, int yStart) {

        //defining starting point as top right (also defines start of stepcount) reworked to take user input
        solutionMatrix[xStart][yStart] = 1;

        //staring with step 2 because step 1 is the starting position
        if (solve(2, 0, 0)) {
            printSolution();
        } else {
            System.out.println("No feasible solution...");
        }
    }

    //method for tracking tour on board
    private boolean solve(int stepCount, int x, int y) {
        System.out.println("Attempting to solve");

        //if we have visited every single cell of the chessboard
        if (stepCount == Constants.BOARD_SIZE * Constants.BOARD_SIZE + 1) {
            return true;
        }
        //checking if a move is valid
        for (int i = 0; i < Constants.NUM_OF_MOVES; i++) {
            int nextX = x + xMoves[i];
            int nextY = x + yMoves[i];

            if (isStepValid(nextX, nextY)) {
                solutionMatrix[nextX][nextY] = stepCount;

                if (solve(stepCount + 1, nextX, nextY)) {
                    return true;
                }

                //BACKTRACK
                solutionMatrix[nextX][nextY] = Integer.MIN_VALUE;
            }
        }
        return false;
    }

    //method for checking if move is outside of board
    private boolean isStepValid(int x, int y) {

        if (x < 0 || x >= Constants.BOARD_SIZE) {
            return false;
        }
        if (y < 0 || y >= Constants.BOARD_SIZE) {
            return false;
        }
        if (solutionMatrix[x][y] != Integer.MIN_VALUE) {
            return false;
        }
        return true;
    }


    //method for printing final solution
    private void printSolution() {
        for (int i = 0; i < Constants.BOARD_SIZE; i++)
            for (int j = 0; j < Constants.BOARD_SIZE; j++)
                System.out.println(solutionMatrix[i][j] + " ");

        System.out.println();
    }
}
