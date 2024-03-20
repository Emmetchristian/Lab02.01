import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The MorseBST class represents a binary search tree implementation for Morse code translation.
 * It provides methods for converting text to Morse code and Morse code to text.
 *
 * @version 3/20/2024
 * @author Emmet Christian
 */


public class MorseBST {

    private ArrayList<String> dotsdashes;
    private final String charSet = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,?-/:';()=@ ");
    private MorseTreeNode root;

    /**
     * Constructs a MorseBST with the given root node.
     */
    public MorseBST() {

        this.root = null;
    }


  //  public MorseBST(MorseTreeNode root) {
  //      this.root = root;
  //  }

 //   public MorseTreeNode getRoot() {
  //      return root;
   // }

   // public void setRoot(MorseTreeNode root) {
    //    this.root = root;
  //  }
/**
    public void preorderTraverse(MorseTreeNode root) {
        if (root == null) {
            return;
        } else {
            System.out.print(root.getValue() + " ");
            preorderTraverse(root.getLeft());
            preorderTraverse(root.getRight());
        }
    }

    public void inorderTraverse(MorseTreeNode root) {
        if (root == null) {
            return;
        } else {
            inorderTraverse(root.getLeft());
            System.out.println(root.getValue() + " ");
            inorderTraverse(root.getRight());
        }
    }


    public void postorderTraverse(MorseTreeNode root) {
        if (root == null) {
            return;
        } else {
            postorderTraverse(root.getLeft());
            postorderTraverse(root.getRight());
            System.out.print(root.getValue() + " ");
        }
    }
**/


    private boolean textToSignal(MorseTreeNode node, String letter, ArrayList<String> dotsdashes) {

        //System.out.println("node's alpha = " + node.getValue().getAlpha());
        if (node == null) {
            return false;
        } else if (node.getValue().getAlpha().equals(letter)) {
            return true;
        } else {
            if (textToSignal(node.getLeft(), letter, dotsdashes)) {
                dotsdashes.add(0, ".");
                return true;
            } else if (textToSignal(node.getRight(), letter, dotsdashes)) {
                dotsdashes.add(0, "-");
                return true;
            }
        }
        //System.out.println("hey we got to line 77");
        return false;
    }

    /**
     * Converts given text to morse
     * @param text
     * @return
     */
    public String textToMorse(String text) {
        MorseTreeNode node = root;
        String signalBuild = "";

        if (!validCharSet(text)) {
            System.out.println("Invalid character set");
        }

        else {
            for (int i = 0; i < text.length(); i++) {
                dotsdashes = new ArrayList<>();
                if (textToSignal(node, "" + text.charAt(i), dotsdashes)) {
                    signalBuild += dotsdashes.toString().replace(", ", "").replace("[", "").replace("]", "");
                    //System.out.println("DEBUG: " + dotsdashes);
                }
                //else
                //System.out.println("hey false on line 93");
                signalBuild += " ";
            }
            return signalBuild;
        }
        return null;
    }

    /**
     * converts given morse input to text
     * @param morse
     * @return
     */
    public String morseToText(String morse) {
        if (!validMorse(morse)) {
            System.out.println("not valid morse");
        } else {
            StringBuilder s = new StringBuilder();
            String[] breakup = morse.split(" / ");
            for (String words : breakup) {
                String[] letters = words.split(" ");
                for (String letter : letters) {
                    s.append(getLetter(letter));
                }
                s.append(" ");
            }
            return s.toString();
        }
        return null;
    }

    private boolean validMorse(String morse) {

        for (char morseOptions : morse.toCharArray()) {
            if (morseOptions != '.' && morseOptions != '-' && morseOptions != ' ' && morseOptions != '/') {
                return false;
            }
        }
        return true;
    }

    private boolean validCharSet(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (charSet.indexOf(text.charAt(i)) == -1)
                return false;
        }
        return true;
    }


    /**
     * gets the letter asociated with the input in the tree
     * @param code
     * @return the alpha value of the node
     */
    private String getLetter(String code) {
        MorseTreeNode node = root;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '.')
                node = node.getLeft();
            else
                node = node.getRight();
        }
        return node.getValue().getAlpha();
    }

    /**
     * contains all values associated with each element of the tree
     */
    public void buildMorseTree() {
        //layer 1
        root = new MorseTreeNode(new MorseSymbol(" ", ""));

        //layer 2
        root.setLeft(new MorseTreeNode(new MorseSymbol("E", ".")));
        root.setRight(new MorseTreeNode(new MorseSymbol("T", "-")));

        //layer 3
        root.getLeft().setLeft(new MorseTreeNode(new MorseSymbol("I", "..")));
        root.getLeft().setRight(new MorseTreeNode(new MorseSymbol("A", ".-")));
        root.getRight().setLeft(new MorseTreeNode(new MorseSymbol("N", "-.")));
        root.getRight().setRight(new MorseTreeNode(new MorseSymbol("M", "--")));

        //layer 4
        root.getLeft().getLeft().setLeft(new MorseTreeNode(new MorseSymbol("S", "...")));
        root.getLeft().getLeft().setRight(new MorseTreeNode(new MorseSymbol("U", "..-")));
        root.getLeft().getRight().setLeft(new MorseTreeNode(new MorseSymbol("R", ".-.")));
        root.getLeft().getRight().setRight(new MorseTreeNode(new MorseSymbol("W", ".--")));
        root.getRight().getLeft().setLeft(new MorseTreeNode(new MorseSymbol("D", "-..")));
        root.getRight().getLeft().setRight(new MorseTreeNode(new MorseSymbol("K", "-.-")));
        root.getRight().getRight().setLeft(new MorseTreeNode(new MorseSymbol("G", "--.")));
        root.getRight().getRight().setRight(new MorseTreeNode(new MorseSymbol("O", "---")));

        //layer 5
        root.getLeft().getLeft().getLeft().setLeft(new MorseTreeNode(new MorseSymbol("H", "....")));
        root.getLeft().getLeft().getLeft().setRight(new MorseTreeNode(new MorseSymbol("V", "...-")));
        root.getLeft().getLeft().getRight().setLeft(new MorseTreeNode(new MorseSymbol("F", "..-.")));
        root.getLeft().getLeft().getRight().setRight(new MorseTreeNode(new MorseSymbol()));
        root.getLeft().getRight().getLeft().setLeft(new MorseTreeNode(new MorseSymbol("L", ".-..")));
        root.getLeft().getRight().getLeft().setRight(new MorseTreeNode(new MorseSymbol()));
        root.getLeft().getRight().getRight().setLeft(new MorseTreeNode(new MorseSymbol("P", ".--.")));
        root.getLeft().getRight().getRight().setRight(new MorseTreeNode(new MorseSymbol("J", ".---")));
        root.getRight().getLeft().getLeft().setLeft(new MorseTreeNode(new MorseSymbol("B", "-...")));
        root.getRight().getLeft().getLeft().setRight(new MorseTreeNode(new MorseSymbol("X", "-..-")));
        root.getRight().getLeft().getRight().setLeft(new MorseTreeNode(new MorseSymbol("C", "-.-.")));
        root.getRight().getLeft().getRight().setRight(new MorseTreeNode(new MorseSymbol("Y", "-.--")));
        root.getRight().getRight().getLeft().setLeft(new MorseTreeNode(new MorseSymbol("Z", "--..")));
        root.getRight().getRight().getLeft().setRight(new MorseTreeNode(new MorseSymbol("Q", "--.-")));
        root.getRight().getRight().getRight().setLeft(new MorseTreeNode(new MorseSymbol()));
        root.getRight().getRight().getRight().setRight(new MorseTreeNode(new MorseSymbol()));

        //layer 6
        root.getLeft().getLeft().getLeft().getLeft().setLeft(new MorseTreeNode(new MorseSymbol("5", ".....")));
        root.getLeft().getLeft().getLeft().getLeft().setRight(new MorseTreeNode(new MorseSymbol("4", "....-")));
        root.getLeft().getLeft().getLeft().getRight().setLeft(new MorseTreeNode(new MorseSymbol()));
        root.getLeft().getLeft().getLeft().getRight().setRight(new MorseTreeNode(new MorseSymbol("3", "...--")));
        root.getLeft().getLeft().getRight().getLeft().setLeft(new MorseTreeNode(new MorseSymbol()));
        root.getLeft().getLeft().getRight().getLeft().setRight(new MorseTreeNode(new MorseSymbol()));
        root.getLeft().getLeft().getRight().getRight().setLeft(new MorseTreeNode(new MorseSymbol()));
        root.getLeft().getLeft().getRight().getRight().setRight(new MorseTreeNode(new MorseSymbol("2", "..---")));
        root.getLeft().getRight().getLeft().getLeft().setLeft(new MorseTreeNode(new MorseSymbol()));
        root.getLeft().getRight().getLeft().getLeft().setRight(new MorseTreeNode(new MorseSymbol()));
        root.getLeft().getRight().getLeft().getRight().setLeft(new MorseTreeNode(new MorseSymbol("+", ".-.-.")));
        root.getLeft().getRight().getLeft().getRight().setRight(new MorseTreeNode(new MorseSymbol()));
        root.getLeft().getRight().getRight().getLeft().setLeft(new MorseTreeNode(new MorseSymbol()));
        root.getLeft().getRight().getRight().getLeft().setRight(new MorseTreeNode(new MorseSymbol()));
        root.getLeft().getRight().getRight().getRight().setLeft(new MorseTreeNode(new MorseSymbol()));
        root.getLeft().getRight().getRight().getRight().setRight(new MorseTreeNode(new MorseSymbol("1", ".----")));
        root.getRight().getLeft().getLeft().getLeft().setLeft(new MorseTreeNode(new MorseSymbol("6", "-....")));
        root.getRight().getLeft().getLeft().getLeft().setRight(new MorseTreeNode(new MorseSymbol("=", "-...-")));
        root.getRight().getLeft().getLeft().getRight().setLeft(new MorseTreeNode(new MorseSymbol("/", "-..-.")));
        root.getRight().getLeft().getLeft().getRight().setRight(new MorseTreeNode(new MorseSymbol()));
        root.getRight().getLeft().getRight().getLeft().setLeft(new MorseTreeNode(new MorseSymbol()));
        root.getRight().getLeft().getRight().getLeft().setRight(new MorseTreeNode(new MorseSymbol(";", "-.-.-")));
        root.getRight().getLeft().getRight().getRight().setLeft(new MorseTreeNode(new MorseSymbol("(", "-.--.")));
        root.getRight().getLeft().getRight().getRight().setRight(new MorseTreeNode(new MorseSymbol()));
        root.getRight().getRight().getLeft().getLeft().setLeft(new MorseTreeNode(new MorseSymbol("7", "--...")));
        root.getRight().getRight().getLeft().getLeft().setRight(new MorseTreeNode(new MorseSymbol()));
        root.getRight().getRight().getLeft().getRight().setLeft(new MorseTreeNode(new MorseSymbol()));
        root.getRight().getRight().getLeft().getRight().setRight(new MorseTreeNode(new MorseSymbol()));
        root.getRight().getRight().getRight().getLeft().setLeft(new MorseTreeNode(new MorseSymbol("8", "---..")));
        root.getRight().getRight().getRight().getLeft().setRight(new MorseTreeNode(new MorseSymbol()));
        root.getRight().getRight().getRight().getRight().setLeft(new MorseTreeNode(new MorseSymbol("9", "----.")));
        root.getRight().getRight().getRight().getRight().setRight(new MorseTreeNode(new MorseSymbol("0", "-----")));

        //layer 7 (THIS LAYER IS NOT FULLY POPULATED AND DOES NOT NEED TO BE DUE TO HAVING NO NODES UNDERNEATH IT (and also because im lazy)
        root.getLeft().getRight().getLeft().getRight().getLeft().setRight(new MorseTreeNode(new MorseSymbol(".", ".-.-.-")));
        root.getRight().getRight().getLeft().getLeft().getRight().setRight(new MorseTreeNode(new MorseSymbol(".", "--..--")));
        root.getLeft().getLeft().getRight().getRight().getLeft().setLeft(new MorseTreeNode(new MorseSymbol("?", "..--..")));
        root.getRight().getLeft().getLeft().getLeft().getLeft().setRight(new MorseTreeNode(new MorseSymbol("-", "-....-")));
        root.getRight().getRight().getRight().getLeft().getLeft().setLeft(new MorseTreeNode(new MorseSymbol(":", "---...")));
        root.getLeft().getRight().getRight().getRight().getRight().setLeft(new MorseTreeNode(new MorseSymbol("'", ".----.")));
        root.getRight().getLeft().getRight().getRight().getLeft().setRight(new MorseTreeNode(new MorseSymbol(")", "-.--.-")));
        root.getLeft().getRight().getRight().getLeft().getRight().setLeft(new MorseTreeNode(new MorseSymbol("@", ".--.-.")));
    }

    /**
     * collects user input and calls methods based on selection
     * @param args
     */
    public static void main(String[] args) {
        MorseBST bst = new MorseBST();
        bst.buildMorseTree();

        //bst.preorderTraverse(bst.getRoot());

        while (true) {
            System.out.println("* MORSE CODE TRANSLATOR * \n Please select from the following menu options \n 1: Text to Morse \n 2: Morse to Text  \n 3: Quit the program");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String choice = myObj.nextLine();  // Read user input
            System.out.println("Your selection: " + choice);

            if (choice.equals("1")) {
                //System.out.print("Input text: " + myObj.nextLine().toUpperCase());
                System.out.println("Input text: ");
                System.out.println(bst.textToMorse(myObj.nextLine().toUpperCase()));

            } else if (choice.equals("2")) {
                System.out.print("Input text: ");
                System.out.println(bst.morseToText(myObj.nextLine().toUpperCase()));

            } else if (choice.equals("3")) {
                System.out.println("DONE");
                break;

            } else {
                System.out.println("That is not one of the choices put either 1, 2, or 3");
            }
        }
    }
}
   