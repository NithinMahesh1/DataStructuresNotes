package com.practice;

public class Stack {
    public static int arr[];
    public static int top;
    public static int capacity;

    Stack(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    public static void push(int x) {
        if(isFull()) {
            System.out.print("Stack Overflow");
        }
        top++;
        arr[top] = x;
    }

    public static int pop() {
        if(isEmpty()) {
            System.out.println("Stack Underflow");
        }
        top--;
        int x = arr[top];
        return x;
    }

    public static boolean isFull() {
        return top == capacity-1;
    }

    public static boolean  isEmpty() {
        return top == -1;
    }

    public static Stack print(Stack stack) {

        return
    }
}
