package com.practice;

public class Main {

    public static int reverse(int inputInt) {
        int modInt = inputInt;
//        String resultString = "";
        int reverseInt = 0;

        String stringAnswer = String.valueOf(inputInt);
        if(stringAnswer.length() == 1) {
            System.out.print(inputInt);
            return inputInt;
        }

        stringAnswer = new StringBuilder(String.valueOf(inputInt)).reverse().toString();
        System.out.print(stringAnswer);


//        // 321 -> 1
//        // 32 -> 2
//        // 3 -> 0;
//        while(modInt != 0) {
//            String s = String.valueOf(inputInt);
//            s = s.trim();
//            modInt = modInt%10;
//            if(modInt == 0) {
//                reverseInt = Integer.parseInt(resultString);
//                return reverseInt;
//            }
//            resultString = resultString + String.valueOf(modInt);
//        }

        reverseInt = Integer.parseInt(stringAnswer);
        return reverseInt;
    }

    public static void main(String[] args) {
        int inputInt = 9;

	    reverse(inputInt);
    }
}
