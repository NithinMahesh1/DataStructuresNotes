package com.practice;


import java.util.*;

public class Main {

    public static void main(String[] args) {
        int x = -321;
        reverse(x);
    }

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


}