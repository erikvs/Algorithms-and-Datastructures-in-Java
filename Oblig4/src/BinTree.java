import java.util.List;
import java.util.Objects;

class BinTree
{

    /** lecturers code lines 8 through 30 **/
    class Node
    {
        String word;
        int count;
        Node left, right;

        // constructor for new node
        public Node(String W)
        {
            word = W;
            count = 1;
            left = right = null;
        }
    }

    // root of tree
    Node root;

    // constructor for tree
    BinTree()
    {
        root = null;
    }

    void addWord(String word)
    {
        root = recursiveAddWord(root, word);
    }

    // recursive function to add a node to the tree
    Node recursiveAddWord(Node root, String word)
    {

        // if tree is empty add a new node
        if (root == null)
        {
            root = new Node(word);
            return root;
        }

        // if tree is not empty, check where to place next word

        // if the word to add is equal to one in the tree, iterate the counter for that node
        if (Objects.equals(word, root.word)){
            root.count++;
        }

        // if new word is less than root word
        if (word.compareTo(root.word) < 0) {
            // add new word to left of root
            root.left = recursiveAddWord(root.left, word);
        }
        // if new word is greater than root word
        else if (word.compareTo(root.word) > 0){
            // add new word to the right of root
            root.right = recursiveAddWord(root.right, word);
        }
        return root;
    }

    // method to print content of tree
    void inorderTraversal(Node root)
    {
        if (root != null)
        {
            inorderTraversal(root.left);
            System.out.print(root.word + " occurs " + root.count + " times ");
            System.out.println();
            inorderTraversal(root.right);
        }
    }

    // method for adding tree to list
    void addTree(List<String> list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            addWord(list.get(i));
        }

    }

}
