package com.practice;

public class Main {

    /*
        This is one of a few select problems to learn DFS (https://www.geeksforgeeks.org/top-10-interview-question-depth-first-search-dfs/)

        Find the number of islands using DFS: (https://www.geeksforgeeks.org/find-number-of-islands/)

        Given a boolean 2D matrix, find the number of islands. A group of connected 1s forms an island. For example, the below matrix contains 5 islands

        Input : mat[][] = {{1, 1, 0, 0, 0},
                           {0, 1, 0, 0, 1},
                           {1, 0, 0, 1, 1},
                           {0, 0, 0, 0, 0},
                           {1, 0, 1, 0, 1}}

        Output: 5
    */


    public static void main(String[] args) {
        // Use DFS to traverse from [0,0] to begin with
        // Create variables for dealing with moving to adjacent nodes either up, right, left, down in that order
        // If our current node is 1 we will check in all directions for adjacent ones (we will also flip the 1 to a 0 and mark as visited
        // When we find a 1 we will increment a counter until we have checked all nodes in the matrix

        int[][] mat = new int[][]{{1, 1, 0, 0, 0}, {0, 1, 0, 0, 1}, {1, 0, 0, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 1, 0, 1}};
        findNumIslands(mat);
    }

    public static int findNumIslands(int[][] mat) {
        int counter = 0;
        int rowsLength = mat.length;
        int columnsLength = mat[0].length;

        // Move each direction and flip 1's to 0's
        // Upon finding the first 1 we will increment a counter

        int[][] up = new int[][]{{-1,0}};
        int[][] right = new int[][]{{0,1}};
        int[][] down = new int[][]{{1,0}};
        int[][] left = new int[][]{{0,-1}};

//        int[][] curr = new int[][]{{0,2}};
//        int thisiscurr = mat[curr[0][0]][curr[0][1]];

        int curr = mat[0][0];

        //Use DFS to flip to 0 and also mark as visited
        // while curr != mat.length-1 and mat[0].length-1

        return -1;
    }
}
