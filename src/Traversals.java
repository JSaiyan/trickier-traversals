import java.lang.classfile.Signature;
import java.lang.classfile.instruction.ThrowInstruction;
import java.util.*;
import javax.xml.validation.Validator;

public class Traversals 
{

  /**
   * Returns the sum of the values of all leaf nodes in the given tree of integers.
   * A leaf node is defined as a node with no children.
   * If node is null, this method returns 0.
   *
   * @param node the node of the tree
   * @return the sum of leaf node values, or 0 if the tree is null
   */
  public static int sumLeafNodes(TreeNode<Integer> node) 
  {
    //If node is null, this method returns 0. //base.case
    if (node == null) return 0;

    //making sum = 0
    //starts 0
    int sum = 0;

    // check to see if the leaf has no childern
    //adds value of node.value to sum
    if(node.left == null && node.right == null) sum += node.value;

    //add value from the method call to sum varaible 
    sum += sumLeafNodes(node.left);
    sum += sumLeafNodes(node.right);
    

    return sum;

  }

  /**
   * Counts the number of internal nodes (non-leaf nodes) in the given tree of integers.
   * An internal node has at least one child.
   * If node is null, this method returns 0.
   *
   * @param node the node of the tree
   * @return the count of internal nodes, or 0 if the tree is null
   */
  public static int countInternalNodes(TreeNode<Integer> node) 
  {
    //base case
    if(node == null) return 0;

    //if both are null return 0
    if ( node.left == null && node.right == null) return 0;

    int count = 1;
    
      count += countInternalNodes(node.left);
      count += countInternalNodes(node.right);
  
    return count;
    
  }

  /**
   * Creates a string by concatenating the string representation of each node's value
   * in a post-order traversal of the tree. For example, if the post-order visitation
   * encounters values "a", "b", and "c" in that order, the result is "abc".
   * If node is null, returns an empty string.
   *
   * @param node the node of the tree
   * @param <T>  the type of values stored in the tree
   * @return a post-order traversal string, or an empty string if the tree is null
   */
  public static <T> String buildPostOrderString(TreeNode<T> node) 
  {
    //base case
    if (node == null) return "";

    //create left and right to assigne node left and right
    String left = buildPostOrderString(node.left);
    String right = buildPostOrderString(node.right);

    //post order check left then right 
    return left + right + node.value; 

  }

  /**
   * Collects the values of all nodes in the tree level by level, from top to bottom.
   * If node is null, returns an empty list.
   *
   * @param node the node of the tree
   * @param <T>  the type of values stored in the tree
   * @return a list of node values in a top-to-bottom order, or an empty list if the tree is null
   */
  public static <T> List<T> collectLevelOrderValues(TreeNode<T> node) 
  {
    //list to store resuklt
    List<T> arrayL = new ArrayList<>();
    if (node == null) return arrayL;

    //Add the current node's value to the result list
    arrayL.add(node.value);
    //get l and r level values
    List<T> left = collectLevelOrderValues(node.left);
    List<T> right = collectLevelOrderValues(node.right);

    //we need to iterate over the larger size in order to extract all values
    //checking if left is longer than or =

    //filter long vs short 
    List<T>longList = null;
    List<T>shortList = null;

    if(left.size() >= right.size())
    {
      longList = left;
      shortList = right;
    }
    else if (left.size() >= right.size())
    {
      longList = right;
      shortList = left;
    }
   

    //merge and loop through the long list 
    if(longList != null)
    {
      for (int i =0; i < longList.size(); i++)
      {
        arrayL.add(longList.get(i));
        //add short
        if(shortList != null && i < shortList.size())
        {
          arrayL.add(shortList.get(i));
        }
      }
    }
    return arrayL;

  }

  /**
   * Counts the distinct values in the given tree.
   * If node is null, returns 0.
   *
   * @param node the node of the tree
   * @return the number of unique values in the tree, or 0 if the tree is null
   */
  public static int countDistinctValues(TreeNode<Integer> node) 
  {
    //basecase
    if (node == null) return 0;
  
    //this will let us know the amount of distinct values
    return countDistinctHelper(node).size();

  }

  
  public static Set<Integer> countDistinctHelper(TreeNode<Integer> node)
  {
    //creating hashset that will contain these unique values
    Set<Integer> distinctInteger = new HashSet<>();
    if(node == null)
    {
      return distinctInteger;
    }

    //creating object left to node.left 
    //creating object right to node.right
    Set<Integer> left = countDistinctHelper(node.left);
    Set<Integer> right = countDistinctHelper(node.right);


    //add all values from the sets that were return to the left and right varaibles 
    //sets can not contain dupes they have to be unique
    distinctInteger.addAll(left);
    distinctInteger.addAll(right);
    //make sure the value of the node currently is added to the distinctInteger set
    distinctInteger.add(node.value);

    return distinctInteger;

  }

  /**
   * Determines whether there is at least one root-to-leaf path in the tree
   * where each successive node's value is strictly greater than the previous node's value.
   * If node is null, returns false.
   *
   * @param node the node of the tree
   * @return true if there exists a strictly increasing root-to-leaf path, false otherwise
   */
  public static boolean hasStrictlyIncreasingPath(TreeNode<Integer> node) 
  {
    //base case
    if (node == null)
    {
      return false;
    }

    //check left and right 
    boolean left = hasStrictlyIncreasingPath(node.left);
    boolean right = hasStrictlyIncreasingPath(node.right);

    //if node has no children return true
    if (node.left == null && node.right == null)
    {
      return true;
    }

    //left child exists and continues a strictly increasing path
    if (left && node.value < node.left.value)
    {
      return true;
    }
    //if the right child exists and continues a strictly increasing path
    if (right && node.value < node.right.value)
    {
      return true;
    }

   return false;
  }



  // OPTIONAL CHALLENGE
  /**
   * Checks if two trees have the same shape. Two trees have the same shape
   * if they have exactly the same arrangement of nodes, irrespective of the node values.
   * If both trees are null, returns true. If one is null and the other is not, returns false.
   *
   * @param nodeA the node of the first tree
   * @param nodeB the node of the second tree
   * @param <T>   the type of values stored in the trees
   * @return true if the trees have the same shape, false otherwise
   */
  public static <T> boolean haveSameShape(TreeNode<T> nodeA, TreeNode<T> nodeB) 
  {

  }


  // OPTIONAL CHALLENGE
  // Very challenging!
  // Hints:
  // List.copyOf may be helpful
  // Consider adding a helper method
  // Consider keeping the current path in a separate variable
  // Consider removing the current node from the current path after the node's subtrees have been traversed.
  /**
   * Finds all paths from the root to every leaf in the given tree.
   * Each path is represented as a list of node values from root to leaf.
   * The paths should be added pre-order.
   * If node is null, returns an empty list.
   * 
   * Example:
   *
   *         1
   *        / \
   *       2   3
   *      / \    \
   *     4   5    6
   * 
   * Expected output:
   *   [[1, 2, 4], [1, 2, 5], [1, 3, 6]]
   * 
   * @param node the root node of the tree
   * @return a list of lists, where each inner list represents a root-to-leaf path in pre-order
   */
  public static <T> List<List<T>> findAllRootToLeafPaths(TreeNode<T> node) 
  {
    return null;
  }
}
