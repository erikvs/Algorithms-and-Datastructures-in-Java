public class Board {

    int free = 0, size, Board[][];

    //constructor for board
    public Board(int size, int x, int y) {
    //defines a fresh board of size by size and defines each space as free
    this.size = size;
    Board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                    Board[i][j] = free;
        }
        //Mark defined starting space as "first move"
        Board[x][y] = 1;
    }

    //method for finding a path - input param is starting space
    boolean solveBoard(int counter, int x, int y) {

        //Stop criterion is having moved all the spaces
        if (counter == size * size +1) {
            return true;
        }

        //All possible moves
        int moveX[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int moveY[] = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int k = 0; k < 8; k++){
            int newX = x + moveX[k];
            int newY = y + moveY[k];

            //Checking if it is legal to move to new position
            if (newX >= 0 && newX < size && newY >= 0 && newY < size && Board[newX][newY] == free) {
                //increments counter and decrements it when backtracked
                Board[newX][newY] = counter;

                //try to find a new way forward recursively
                if (solveBoard(counter+1, newX, newY)) {
                    Board[newX][newY] = counter;
                    return true;
                }

                //Backtracking
                Board[newX][newY] = free;
            }
        }
        return false;
    }

    public String toString(){
        String result = "\n";
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
                if (Board[i][j] == free)
                    result += "* ";
                else
                    //if a path is found, print a board which has tracked knights movement
                    result += Board[i][j] + " ";
            result += "\n";
        }
        return result;
    }
}
