package binary-tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
// **********************************************************
// Assignment1:
// Student1: Muhammad Ali
// UTORID user_name: alimuh53
// UT Student #: 1003939642
// Author:
//
// Student2: Luke Zhang
// UTORID user_name: zhan4908
// UT Student #: 1004136231
// Author:
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

/*
 * the provided starter code may contain warnings of raw type. This is OK,
 * because we have'nt yet learned generics. After having learnt generics, we
 * will revisit this code, and remove the warnings out.
 */
public class BinaryTree {

  private Node root;

  public void addData(int d) {
    Node toAdd = new Node(d);
    if (root == null) {
      root = toAdd;
    } else {
      LinkedList ll = new LinkedList();
      ll.add(root);
      while (!(ll.isEmpty())) {
        Node currentNode = (Node) ll.poll();
        if (currentNode.getLeftNode() == null) {
          currentNode.setLeftNode(toAdd);
          break;
        } else if (currentNode.getRightNode() == null) {
          currentNode.setRightNode(toAdd);
          break;
        } else {
          /*
           * remember, the queue is FIFO, and due to this we add first the left
           * node followed by the right node.
           */
          ll.add(currentNode.getLeftNode());
          ll.add(currentNode.getRightNode());
        }
      }
    }
  }

  /**
   * Returns a String representation of the binary tree using breadth traversal.
   *
   * @return output, a String that is a representation of the binary tree in
   *         breadth traversal
   */
  public String toString() {
    // Using a queue, it will make the breadth traversal extremely simple
    Queue<Node> queue = new LinkedList<Node>();
    String output = "";
    queue.add(root);
    /*
     * To perform breadth search we will read the root, then it's children, then
     * the children of it's children and so on
     */
    while (queue.isEmpty() == false) {
      Object temp = null;
      temp = queue.remove();
      // Cast the object to a node, so that we can obtain the data
      Node curr = (Node) temp;
      output += curr.getData() + " ";
      // If the node has left and right children, they will be added to the
      // queue
      if (curr.getLeftNode() != null) {
        queue.add(curr.getLeftNode());
      }
      if (curr.getRightNode() != null) {
        queue.add(curr.getRightNode());
      }
    }
    // Remove the last space which is unnecessary
    output = output.substring(0, output.length() - 1);
    return output;
  }

  /**
   * Returns an array list of all data (ints) from the binary tree, starting at
   * root, in inorder-traversal.
   *
   * @return values a list that is a collection of all the data (ints)
   *         accumulated from traversing through all nodes in in-order traversal
   */
  public ArrayList toList() {
    // create an empty ArrayList
    ArrayList values = new ArrayList();
    // call helper method to populate values with all Node data
    addSubTree(root, values);
    // finally, return the array of integer values in in-order traversal
    return values;

  }

  /**
   * Populates a given array list with all data (ints) from the tree starting at
   * the given Node temp onward, in inorder-traversal
   *
   * @param temp a current node/root from which to begin checking in-order
   *        traversal and start adding data from
   * @param values a list that is a collection of all the data (ints)
   *        accumulated from traversing through all nodes in in-order traversal
   */
  private void addSubTree(Node temp, ArrayList values) {
    /*
     * The following code is similar to common inorder recursive traversals
     * albeit it is done in Java (instead of Python like last year). Inorder
     * means = Left first, then cur_node, then right Therefore, we will
     * recursively iterate over left side first, then add our current node's
     * data, and the iterate over right side.
     */
    // if a left side exists for the current node
    if (temp.getLeftNode() != null) {
      addSubTree(temp.getLeftNode(), values);
    }
    // checked left, now add cur_node's data to arraylist
    values.add(temp.getData());
    // check right, if right side exists for current node
    if (temp.getRightNode() != null) {
      addSubTree(temp.getRightNode(), values);
    }
  }

  /*
   * You can modify the main function in any way you like we will not mark your
   * main function. We have provided some sample code inside the main function
   * that may make it easier for you to debug your code and know what the
   * expected output is.
   */
  public static void main(String[] args) {
    BinaryTree bt = new BinaryTree();
    /*
     * adding the following ints in a binary tree. Remember, the addData adds
     * the ints level by level and from left to right. I will first ask you to
     * run the starter code and debug out the addData so that you are familiar
     * with how it works and trace the creation of the tree using pen and paper.
     */
    bt.addData(1);
    bt.addData(2);
    bt.addData(3);
    bt.addData(4);
    bt.addData(5);
    bt.addData(6);
    bt.addData(7);

    System.out.println(bt); // must print 1 2 3 4 5 6 7

    /*
     * Printing the list of the binary tree. Remember the list of the binary
     * tree must contain the ints in inOrder traversal. The for loop below will
     * give you back a null pointer exception when trying to run the starer
     * code, this is because toList() method inside the BinaryTree returns back
     * a null.
     */
    for (Object d : bt.toList()) {
      System.out.println((int) d);
    }
    /*
     * the above loop will print the following: 4 2 5 1 6 3 7
     */
  }
}
