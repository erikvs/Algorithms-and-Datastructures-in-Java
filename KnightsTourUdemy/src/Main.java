import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        System.out.println("At what X coordinate does the Knight start: ");
        int xStart = userInput.nextInt();
        System.out.println("At what Y coordinate does the Knight start: ");
        int yStart = userInput.nextInt();

        Algorithm algorithm = new Algorithm();
        algorithm.solveTour(xStart, yStart);
    }
}
