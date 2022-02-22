import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner userInput = new Scanner(System.in);
        System.out.println("Generate a chessboard of n x n size where n the integer: ");
        int size = userInput.nextInt();
        //TODO: start position input


        //Creating the board
        Board board = new Board(size);

        //Finding path
        boolean solveBoard = board.solveBoard(1,3,3); //TODO: input param should be user input

        //Printing board and path (if any was found)
        System.out.println(board);
        if (!solveBoard){
            System.out.println("NO VIABLE PATH");
        }

    }
}
