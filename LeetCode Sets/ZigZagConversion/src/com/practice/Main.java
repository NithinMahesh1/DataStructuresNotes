package com.practice;

public class Main {

    /**

         P   A   H   N
         A P L S I I G
         Y   I   R

         Input: s = "PAYPALISHIRING", numRows = 3
         Output: "PAHNAPLSIIGYIR"

         Input: s = "PAYPALISHIRING", numRows = 4
         Output: "PINALSIGYAHRPI"
         Explanation:
         P     I    N
         A   L S  I G
         Y A   H R
         P     I

     */

    public static void main(String[] args) {
	    String s = "PAYPALISHIRING";
        int numRows = 3;

        convert(s,numRows);
    }

    public static String convert(String s, int numRows) {
        String result = "";

        // Create a 2D matrix array of strings
        // loop only rows and add each letter in first column till numRows
        // second iteration we


        // We get the number of indices for the middle input values from subtracting numRows-2
        int k = 3;
        int n = 9;

        int[] inputs = new int[n];

        for(int i=0; i <= n-1; i++) {
            if(i == 0) {
                inputs[i] = 1;
                continue;
            }
            inputs[i] = i+1;
        }

        int test = 0;




        char[][] zigzag = new char[numRows][numRows];

        int j=0;
        for(int i=0; i<s.length(); i++) {
            char character = s.charAt(i);
            if(i <= numRows-1) {
                zigzag[i][j]= character;
            }
            // add a helper method to call next row (row with zig zags)
            // need to add a check for how many middle values we need to zigzag before continuing
        }

        for(int i=0; i<zigzag.length; i++) {
            for(int z=0; z<zigzag[i].length; z++) {
                System.out.print (zigzag[i][j]);
            }
            System.out.println();
        }

        return result;
    }
}
