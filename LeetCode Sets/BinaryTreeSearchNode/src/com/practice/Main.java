package com.practice;

/*

Given the root of a binary search tree (BST) and a key, search for the node with that key in the BST.

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
Output: true

Input: key = 5
Output: false

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

        Node(int key) {
            data = key;
            this.left = this.right = null;
        }
    }

    public static boolean search(Node root, int key) {
        Node curr = root;

        if(root.data == key) {
            System.out.print("true");
            return true;
        }

        while(curr != null) {
            if(curr.data == key) {
                System.out.print("true");
                return true;
            }
            if(key < curr.data) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }

        System.out.print("false");
        return false;
    }

    public static Node insert(Node root, int key) {
        Node curr = root;
        Node parent = null;

        if(root == null) {
            return new Node(key);
        }

        while(curr != null) {
            parent = curr;
            if(key < curr.data) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }

        if(key < parent.data) {
            parent.left = new Node(key);
        }
        else {
            parent.right = new Node(key);
        }

        return root;
    }


    public static void main(String[] args) {
        int[] keys = new int[] {15, 10, 20, 8, 12, 16, 25};

        Node root = null;
        for(int key : keys) {
            root = insert(root, key);
        }

        search(root, 16);
    }

}
