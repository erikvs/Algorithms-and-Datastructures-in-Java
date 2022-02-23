import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        System.out.println("Generate a chessboard of n x n size where n is the integer: ");
        int size = userInput.nextInt();
        System.out.println("X coordinate for knight starting position: ");
        int x = userInput.nextInt();
        System.out.println("Y coordinate for knight starting position: ");
        int y = userInput.nextInt();

        //Creating the board
        Board board = new Board(size, x, y);

        //Finding path
        boolean solveBoard = board.solveBoard(2, x, y);

        //Printing board and path (if any was found)
        System.out.println(board);
        if (!solveBoard){
            System.out.println("NO VIABLE PATH");
        }
    }
}
