import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Supply English words to register. Confirm input with enter: \n");
        String input = userInput.nextLine();
        List<String> splitInput = List.of(input.toUpperCase().split("[^\\p{L}0-9']+"));

        BinTree tree = new BinTree();

        tree.addTree(splitInput);
        tree.inorderTraversal(tree.root);
    }
}