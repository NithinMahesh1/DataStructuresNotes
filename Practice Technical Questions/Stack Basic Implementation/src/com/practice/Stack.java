package com.practice;

public class Stack {
    private static int arr[];
    private static int capacity;
    private static int top;

    Stack(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    public static void push(int x) {
        if(isFull()) {
            System.out.println("Stack Overflow Error");
        }

        top++;
        arr[top] = x;
    }

    public static void pop() {
        if(isEmpty()) {
            System.out.println("Stack Underflow Error");
        }

        arr[top] = 0;
        top--;
    }

    public static int peep() {
        if(isEmpty()) {
            System.out.println("Stack is empty");
        }

        return arr[top];
    }

    public static void print() {
        if(isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }

       for (int n : arr) {
           System.out.print(n + " ");
       }
    }

    public static boolean isFull() {
        return top == capacity - 1;
    }

    public static boolean isEmpty() {
        return top == -1;
    }
}
