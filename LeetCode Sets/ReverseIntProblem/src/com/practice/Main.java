package com.practice;

import java.util.Stack;

public class Main {

    // Actually way easier way of doing this:
    /*
        String test = "this";
        for(int i=test.length()-1; i>=0; i--) {
            System.out.print(test.charAt(i));
        }
    */

    public static int reverse(int x) {
        String s = String.valueOf(x);
        Stack<String> stack = new Stack<>();

        for(int i=0; i<s.length(); i++) {
            String add = String.valueOf(s.charAt(i));
            stack.push(add);
        }

        String stringResult = "";
        while(!(stack.isEmpty())) {
            if(stack.peek().contains("-")) {
                stringResult = "-" + stringResult;
                break;
            }
            else {
                String reverseString = stack.pop();
                stringResult = stringResult + reverseString;
            }
        }

        int result = Integer.parseInt(stringResult);

        return result;
    }

    public static void main(String[] args) {
        int inputInt = -321;

	    reverse(inputInt);
    }
}
