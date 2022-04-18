package com.practice;

/*
Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that all its elements lies in [low, high].
Trimming the tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant).
It can be proven that there is a unique answer.

Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
*/


import java.util.ArrayList;

public class Main {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
//        int[] keys = new int[] {3,0,4};
        ArrayList<Integer> keys = new ArrayList<>();
        keys.add(3);
        keys.add(0);
        keys.add(4);
        keys.add(null);
        keys.add(2);
        keys.add(null);
        keys.add(null);
        keys.add(1);

        int low = 1;
        int high = 2;

//        constructTree(keys);
        TreeNode root = null;
        trimBST(root, low, high);

    }

    public static TreeNode constructTree(ArrayList<Integer> keys) {
        TreeNode root = null;

        for(int key : keys) {
            root = insert(root, key);
        }

        return root;
    }

    public static TreeNode insert(TreeNode root, int key) {
        TreeNode curr = root;
        TreeNode parent = null;

        if(curr == null) {
            return new TreeNode(key);
        }

        while(curr != null) {
            parent = curr;
            curr = curr.left;
//            curr.left = new TreeNode(key);
        }


        return root;
    }

    /*
    * This is the actual solution no need to do all the setup
    * Turns out you can test the solution within leet code itself
    */

    public static TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return root;
        if (root.val > high) return trimBST(root.left, low, high);
        if (root.val < low) return trimBST(root.right, low, high);

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
