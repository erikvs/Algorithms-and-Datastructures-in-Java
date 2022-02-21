import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner userInput = new Scanner(System.in);
        System.out.println("Generate a chessboard of n x n size where n the integer: ");
        int size = userInput.nextInt();
        //TODO: start position input


        //Creating the board defined from user input, all spaces are free
        Board.createBoard(size);



    }
}
