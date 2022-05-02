package com.practice;

public class Main {

    public static void main(String[] args) {
        int[][] mat = new int[][]{{1,1,0,0,0},{1,1,1,1,0},{1,0,0,0,0},{1,1,0,0,0},{1,1,1,1,1}};
        int k = 3;
        kWeakestRows(mat, k);
    }

    public static int[] kWeakestRows(int[][] mat, int k) {
        int[] weakestRows = new int[k];
        int rows = mat.length;
        int[] sum = new int[rows];
        int add = 0;
        int columns = 0;


        for(int i=0; i<rows; i++) {
            columns = mat[i].length;
            for(int j=0; j<columns; j++) {
                add = add + mat[i][j];
            }
            sum[i] = add;
            add = 0;
            System.out.println(sum[i]);
        }

        //loop through while n != k with n starting at 0
        //find smallest and replace it with 101 since columns can't be greater than 100
        //each time smallest is found add it to weakestRows
        int n = 0;
        while(n!=k) {
            int min = 0;
            for(int i=0; i<sum.length; i++) {
                if(sum[min] > sum[i]) {
                    min = i;
                }
            }
            weakestRows[n] = min;
            sum[min] = 101;
            n++;
        }


        return weakestRows;
    }
}
