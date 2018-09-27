
package TreeTime;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Function;


/**
 * Performs insertions and searches, using the same data set,on a binary search
 * tree and an AVL tree to empirically compare the performance of these
 * operations on the trees.
 *
 * @author Hayley Ma
 * @since 10-16-2017
 * @SEE AVLTree, AVLTreeException, BSTree, BSTreeException,
 */
public class TwoTreesAnalyzer {
    
    //for the first two tables
    public static void printTable(int tableNumber, String treeType, String textName) {        
        System.out.printf("Table %d:%s [%s]\n", tableNumber, treeType, textName);
        System.out.println("Level-order Traversal");
        System.out.print("===================================\n"
                + "Word\n"
                + "-----------------------------------\n");

    }
    
    //Function and PrintStream
    public static <E> void print(Function func, E object){
        func.apply(object);
    }
    /**
     * @param args the command line arguments
     * @throws AVLTreeException
     * @throws BSTreeException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws AVLTreeException, BSTreeException, IOException {
        //argument variable
        String textName = args[0];
        Scanner cin = new Scanner(new FileReader(textName));        
        BSTree<String> bsTree = new BSTree();
        AVLTree<String> avlTree = new AVLTree();
        String nextLine;
        while (cin.hasNext()) {
            nextLine = cin.next().toUpperCase();
            bsTree.insert(nextLine);
            avlTree.insert(nextLine);
        }
        cin.close();
        
        Function<String, PrintStream> func = x -> System.out.printf("%s%n", x);
        //table 1
        printTable(1, "Binary Search Tree", textName);        
        bsTree.levelTraverse(func);
        System.out.println("-----------------------------------\n");

        //table 2
        printTable(2, "AVL Tree", textName);
        avlTree.levelTraverse(func);
        System.out.println("-----------------------------------\n");

        //table 3
        System.out.print("Table 3:Number of Nodes vs Height vs Diameter\n"
                + "Using Data in [" + textName + "]\n"
                + "=============================================\n"
                + "Tree   # Nodes  Height   Diameter\n"
                + "---------------------------------------------\n");
        System.out.printf("BST %9d %8d %10d\n", bsTree.size(), bsTree.height(), bsTree.diameter());
        System.out.printf("AVL %9d %8d %10d\n", avlTree.size(), avlTree.height(), avlTree.diameter());
        System.out.println("---------------------------------------------\n");

        //table 4
        System.out.print("Table 4:Total Number of Node Accesses\n"
                + "Searching for all the Words in [" + textName + "]\n"
                + "==============================================\n"
                + "Tree         # Nodes\n"
                + "----------------------------------------------\n");
        
        cin = new Scanner(new FileReader(textName));
        int avlCounter = 0;
        int bstCounter = 0;
        while (cin.hasNext()) {
            nextLine = cin.next().toUpperCase();
            //# of node accesses is = to 1+depth
            bstCounter += bsTree.depth(nextLine)+1;
            avlCounter += avlTree.depth(nextLine)+1;            
        }        
        System.out.printf("BST %16d\n", bstCounter);
        System.out.printf("AVL %16d\n", avlCounter);
        System.out.println("----------------------------------------------\n");
    }
}
