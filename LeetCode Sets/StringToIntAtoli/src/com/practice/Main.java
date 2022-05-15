package com.practice;

public class Main {

    public static void main(String[] args) {
	    String input1 = "42";
        String input2 = "-42";
        String input3 = "4193 with words";




        myAtoi(input2);
    }

//    public static int myAtoli(String s) {
//        String concat = "";
//        int result = 0;
//        boolean positive = false;
//        boolean negative = false;
//
//        // Loop through each char in string as if it is an int
//            // However if there is a negative or positive we will add this at the end of the result string and ignore it for now
//            // Add a try for each char and if it is not an int we break the loop
//        // concat to the result string
//
//        for(int i=0; i<s.length(); i++) {
//            char eachChar = s.charAt(i);
//            String add = String.valueOf(eachChar);
//
//            int convert = 0;
//
//            if(add.contains("-")) {
//                negative = true;
//                continue;
//            }
//            else if(add.contains("+")) {
//                positive = true;
//                continue;
//            }
//            else if(add.contains(" ")) {
//                continue;
//            }
//
//            try {
//                convert = Integer.parseInt(add);
//                concat = concat + convert;
//            }
//            catch(Exception e) {
//                break;
//            }
//        }
//
//        result = Integer.parseInt(concat);
//        if(negative == true) {
//            result = Integer.parseInt("-" + concat);
//        }
//        if(positive ==  true) {
//            result = Integer.parseInt("+" + concat);
//        }
//
//        System.out.println(result);
//        return result;
//    }

    // Their Solution is better:

    public static int myAtoi(String input) {
        int sign = 1;
        int result = 0;
        int index = 0;
        int n = input.length();

        // Discard all spaces from the beginning of the input string.
        while (index < n && input.charAt(index) == ' ') {
            index++;
        }

        // sign = +1, if it's positive number, otherwise sign = -1.
        if (index < n && input.charAt(index) == '+') {
            sign = 1;
            index++;
        } else if (index < n && input.charAt(index) == '-') {
            sign = -1;
            index++;
        }

        // Traverse next digits of input and stop if it is not a digit
        while (index < n && Character.isDigit(input.charAt(index))) {
            int digit = input.charAt(index) - '0';

            // Check overflow and underflow conditions.
            if ((result > Integer.MAX_VALUE / 10) ||
                    (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                // If integer overflowed return 2^31-1, otherwise if underflowed return -2^31.
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            // Append current digit to the result.
            result = 10 * result + digit;
            index++;
        }

        // We have formed a valid number without any overflow/underflow.
        // Return it after multiplying it with its sign.
        System.out.println(sign*result);
        return sign * result;
    }
}
