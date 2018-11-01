package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
     * albeit it is done in Java. Inorder means = Left first, then cur_node,
     * then right Therefore, we will recursively iterate over left side first,
     * then add our current node's data, and the iterate over right side.
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


  public static void main(String[] args) {
    BinaryTree bt = new BinaryTree();

    bt.addData(1);
    bt.addData(2);
    bt.addData(3);
    bt.addData(4);
    bt.addData(5);
    bt.addData(6);
    bt.addData(7);

    System.out.println(bt); // prints 1 2 3 4 5 6 7

    for (Object d : bt.toList()) {
      System.out.println((int) d);
    }
    /*
     * the above loop will print the following: 4 2 5 1 6 3 7
     */
  }
}
