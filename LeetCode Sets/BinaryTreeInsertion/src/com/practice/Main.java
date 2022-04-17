package com.practice;

/*

Given the root of a binary search tree (BST) and an integer k, insert k into the BST. The solution should not rearrange the existing tree nodes and insert a new node with the given key at its correct position in BST.

Input: Below BST, k = 16

		  15
		/	 \
	   /	  \
	  /		   \
	 10		   20
	/  \		 \
   /	\	 	  \
  8		12		  25

Output:

		  15
		/	 \
	   /	  \
	  /		   \
	 10		   20
	/  \	  /  \
   /	\	 /	  \
  8		12	16	  25

You may assume that the key does not exist in the BST.

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

    public static class Node{
        int data;
        Node left, right;

        Node(int key) {
            data = key;
            this.left = this.right = null;
        }
    }

    public static void main(String[] args) {

        int keys[] = new int[]{15,10,20,8,12,16,25};

        Node root = constructTree(keys);
        inorder(root);
    }

    public static void inorder(Node root) {
        if(root == null) {
            return;
        }

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static Node insert(Node root, int k) {
        Node curr = root;
        Node parent = null;

        if(root == null) {
            return new Node(k);
        }

        while(curr != null) {
            parent = curr;
            if(k < curr.data) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }

        if(k < parent.data) {
            parent.left = new Node(k);
        }
        else {
            parent.right = new Node(k);
        }

        return root;
    }

    public static Node constructTree(int[] keys) {
        Node root = null;
        for(int key : keys) {
            root = insert(root, key);
        }
        return root;
    }
}
