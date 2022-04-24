package com.practice;

/*

Given the root of a binary search tree (BST) and a key, delete the node with that key in the BST if it exists, and return the root node.

• When deleting a node with no children, the solution should remove the node from the tree.
• When deleting a node with one child, the solution should remove the node and replace it with its child.
• When deleting a node with two children, the solution should swap the node's value with either its inorder successor or inorder predecessor, and then call delete on the inorder successor or inorder predecessor. This node will have at-most one child and can be deleted according to one of the two simpler cases above.

For example, consider the following BST.

		  15
		/	 \
	   /	  \
	  /		   \
	 10		   20
	/  \	  /  \
   /	\	 /	  \
  8		12	16	  25

Input: key = 25
Output:

		  15
		/	 \
	   /	  \
	  /		   \
	 10		   20
	/  \	  /
   /	\	 /
  8		12	16

Input: key = 15
Output:

		  12					  16
		/	 \			  		/	 \
	   /	  \			 	   /	  \
	  /		   \		 or   /		   \
	 10		   20		   	 10		   20
	/		  /  \		  	/  \		 \
   /		 /	  \		   /	\	 	  \
  8			16	  25	  8		12		  25

*/

public class Main {

    /*
		A BST node is defined as:

		class Node
		{
			int data;			// data field
			Node left, right;	// pointer to the left and right child

			Node() {}
			Node(int data) { this.data = data; this.left = this.right = null; }
		}
	*/

    public static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    public static Node deleteNode(Node root, int key) {
          Node curr = root;
          Node parent = null;


          while(curr != null && curr.data != key) {
              parent = curr;
              if(key < curr.data) {
                  curr = curr.left;
              }
              else {
                  curr = curr.right;
              }
          }

          if(curr == root) {
              return root;
          }


//        TODO - if node has no children remove from tree
          if(curr.left == null && curr.right == null) {
              if(curr != root) {
                  if (parent.left == curr) {
                      parent.left = null;
                  }
                  else {
                      parent.right = null;
                  }
              }
              else {
                  root = null;
              }
          }


//        TODO - if node has two children remove and replace with the inOrder predecessor
          else if(curr.left != null && curr.right != null) {
              // We need a helper method to find the inorder minimum value to replace the key node and replace the data in the key node with the new minimum value
                // call delete node recursively to remove the child we replaced with (covered by other cases)
              Node minimum = getMinimum(curr.left);
              int replaceVal = minimum.data;
              deleteNode(root,replaceVal);
              curr.data = replaceVal;
          }


//        TODO - if node has one child remove and replace with child
          else {
            Node child = (curr.left != null) ? curr.left : curr.right;

            if(curr == parent.left) {
                parent.left = child;
            }
            else {
                parent.right = child;
            }
          }

        return root;
    }

    public static Node getMinimum(Node curr){
        while(curr != null) {
            if(curr.left != null) {
                curr = curr.left;
            }
            break;
        }

        return curr;
    }

    public static Node insert(Node root, int key) {
        Node curr = root;

        if(root == null) {
            return new Node(key);
        }

        if(key < root.data) {
            root.left = insert(root.left, key);
        }

        if(key > root.data) {
            root.right = insert(root.right, key);
        }

        return root;
    }

    public static void inorder(Node root) {
        if(root == null) {
            return;
        }

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }


    public static void main(String[] args) {
//        int[] keys = new int[] {15,10,20,8,4,12,17,16,25};
//        int[] keys = new int[] {15,10,20,8,4,3,5,12,17,16,25,24,27};
//        int[] keys = new int[] {15,10,20,8,4,3,5,12,17,16,25};
        int[] keys = new int[] {15,10,20,8,4,12,13,14,17,16,25};

        Node root = null;
        for(int key : keys) {
            root = insert(root, key);
        }

        root = deleteNode(root, 12);
        inorder(root);
    }
}
