import java.util.Random;

public class Board {

    int used = 0, free = 1, Board[][];


    public static int[][] createBoard(int size) {
        int free = 1, Board[][];
    Board = new int[size][size];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++)
                    Board[x][y] = free;
        }
        return Board;
    }

    //takes starting position X and Y coordinates +  the size of the board
    boolean solveBoard(int x, int y, int size) {
        int movesTotal = 0;
        int movesToHere = 0;

        //Stop criterion is having moved all the spaces, -1 cause we start at one space
        if (movesToHere == (size * size) - 1) {
            return true;
        }

        //Mark space as used
        Board[x][y] = used;

        //All possible moves
        int moveX[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int moveY[] = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int k = 0; k < 8; k++){
            int newX = x + moveX[k];
            int newY = y + moveY[k];

            //Checking if it is legal to move to new position
            if (newX >= 0 && newY < size && newX < size && Board[newX][newY] == free) {
                //TODO: this is where I increment my movetotal?
                //try to find a new way forward recursively
                if (solveBoard(newX, newY, size)) {
                    Board[newX][newY] = used;
                    return true;
                }
                //TODO: correct placement of backtracking?
                //Backtracking
                Board[newX][newY] = free;

            }

        }
        // I SPRINGERPROBLEMET må dette siste tilfellet, der vi ikke
        // finner noen lovlig løsning med start i rute (i,j),
        // behandles litt anderledes. I labyrinten er det ingen vits i
        // å gå tilbake til en rute der vi har vært før. I
        // springerproblemet er det ikke slik, der må vi nå markere at
        // ruten er blitt ledig igjen, slik at den kan brukes på nytt
        // i senere forsøk på å bygge ut en løsning. Øvrig
        // datastruktur som brukes til å lagre løsningen må også
        // oppdateres slik at dette steget som ikke ledet til løsning
        // blir fjernet.
        return false;
    }

    //Show the solution for generated board
    private void printSolution(int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++)
                System.out.println(Board[x][y]+ " ");

            System.out.println();
        }
    }
}
