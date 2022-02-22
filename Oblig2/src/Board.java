import java.util.Random;

public class Board {

    int used = 0, free = 1, size, Board[][];


    //constructor for board
    public Board(int size) {
    //defines a fresh board of size by size and defines each space as free
    this.size = size;
    Board = new int[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++)
                    Board[x][y] = free;
        }
    }

    //method for finding a path - input param is starting space
    boolean solveBoard(int counter, int x, int y) {

        int movesToHere = 0;

        //Stop criterion is having moved all the spaces
        //TODO: my understanding of what the end should be
        if (movesToHere == size * size + 1) {
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
                Board[newX][newY] = counter;

                //try to find a new way forward recursively
                if (solveBoard(counter +1, newX, newY)) {
                    Board[newX][newY] = used;
                    return true;
                }

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
}
