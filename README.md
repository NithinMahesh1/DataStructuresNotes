# DataStructuresNotes


### Brief Introduction: 

Comprehensive Notes on all things related to Data Structures 

<br/>

## Arrays:

The size of an array must be provided before storing data.

![DataStructuresNotes](images/Array_BigO_Notation.jpg)

Example: For example, let us say, we want to store marks of all students in a class, we can use an array to store them. This helps in reducing the use of a number of variables as we dont need to create a separate variable for marks of every subject. All marks can be accessed by simply traversing the array. 

### Arrays in Java

* In Java, all arrays are dynamically allocated. (discussed below)
* Since arrays are objects in Java, we can find their length using the object property length. This is different from C/C++, where we find length using sizeof.
* A Java array variable can also be declared like other variables with [] after the data type.
* The variables in the array are ordered, and each has an index beginning from 0.
* Java array can be also be used as a static field, a local variable, or a method parameter.
* The size of an array must be specified by int or short value and not long.
* The direct superclass of an array type is Object
* Every array type implements the interfaces Cloneable and java.io.serializable

An array can contain primitives (int, char, etc.) and object (or non-primitive) references of a class depending on the definition of the array. In the case of primitive data types, the actual values are stored in contiguous memory locations.

![DataStructuresNotes](images/Arrays1.png)

Example of array declarations in Java:
```
// both are valid declarations
int intArray[]; 
or int[] intArray; 

byte byteArray[];
short shortsArray[];
boolean booleanArray[];
long longArray[];
float floatArray[];
double doubleArray[];
char charArray[];

// an array of references to objects of
// the class MyClass (a class created by
// user)
MyClass myClassArray[]; 

Object[]  ao,        // array of Object
Collection[] ca;  // array of Collection
                     // of unknown type
```

Instantiating an Array in Java

When an array is declared, only a reference of an array is created. To create or give memory to the array, you create an array like this: The general form of new as it applies to one-dimensional arrays appears as follows: 
```
var-name = new type [size];
```

Here, type specifies the type of data being allocated, size determines the number of elements in the array, and var-name is the name of the array variable that is linked to the array. To use new to allocate an array, you must specify the type and number of elements to allocate.

Example: 
```
int intArray[];    //declaring array
intArray = new int[20];  // allocating memory to array
```
OR
```
int[] intArray = new int[20]; // combining both statements in one
```

Here is an example of an array of objects:
```

// Java program to illustrate creating
//  an array of objects
 
class Student
{
    public int roll_no;
    public String name;
    Student(int roll_no, String name)
    {
        this.roll_no = roll_no;
        this.name = name;
    }
}
 
// Elements of the array are objects of a class Student.
public class GFG
{
    public static void main (String[] args)
    {
        // declares an Array of integers.
        Student[] arr;
 
        // allocating memory for 5 objects of type Student.
        arr = new Student[5];
 
        // initialize the first elements of the array
        arr[0] = new Student(1,"aman");
 
        // initialize the second elements of the array
        arr[1] = new Student(2,"vaibhav");
 
        // so on...
        arr[2] = new Student(3,"shikar");
        arr[3] = new Student(4,"dharmesh");
        arr[4] = new Student(5,"mohit");
 
        // accessing the elements of the specified array
        for (int i = 0; i < arr.length; i++)
            System.out.println("Element at " + i + " : " +
                        arr[i].roll_no +" "+ arr[i].name);
    }
}
```
Multidimensional Arrays:

Multidimensional arrays are arrays of arrays with each element of the array holding the reference of other arrays. These are also known as Jagged Arrays. A multidimensional array is created by appending one set of square brackets ([]) per dimension. Examples: 
```
int[][] intArray = new int[10][20]; //a 2D array or matrix
int[][][] intArray = new int[10][20][10]; //a 3D array
```

Java Example:
```
public class multiDimensional
{
    public static void main(String args[])
    {
        // declaring and initializing 2D array
        int arr[][] = { {2,7,9},{3,6,1},{7,4,2} };
 
        // printing 2D array
        for (int i=0; i< 3 ; i++)
        {
            for (int j=0; j < 3 ; j++)
                System.out.print(arr[i][j] + " ");
 
            System.out.println();
        }
    }
}
```

C# Example:
```
using System;

namespace HelloWorld 
{
    class HelloWorld {
        static void Main(string[] args) {
         
           int[,] array2d = new int[3,3]{ {2,7,9},{3,6,1},{7,4,2} };

            for(int i=0; i<array2d.GetLength(0); i++) {
                for(int j=0; j<array2d.GetLength(1); j++) {
                    Console.WriteLine(array2d[i,j] + " ");
                }
            }
        }
    }
}
```

Output:
```
2 7 9 
3 6 1 
7 4 2 
```

![DataStructuresNotes](images/Blank-Diagram-Page-1-13.jpeg)

Cloning of arrays:

When you clone a single-dimensional array, such as Object[], a “deep copy” is performed with the new array containing copies of the original array’s elements as opposed to references.

```
// Java program to demonstrate
// cloning of one-dimensional arrays
 
class Test
{   
    public static void main(String args[])
    {
        int intArray[] = {1,2,3};
         
        int cloneArray[] = intArray.clone();
         
        // will print false as deep copy is created
        // for one-dimensional array
        System.out.println(intArray == cloneArray);
         
        for (int i = 0; i < cloneArray.length; i++) {
            System.out.print(cloneArray[i]+" ");
        }
    }
}

Output:
false
1 2 3 
```

Cloning of two dimensional arrays:

A clone of a multi-dimensional array (like Object[][]) is a “shallow copy,” however, which is to say that it creates only a single new array with each element array a reference to an original element array, but subarrays are shared. 

Example in C#:
```
using System;

namespace HelloWorld 
{
    class HelloWorld {
        static void Main(string[] args) {
         
           
            int[,] intArray = new int[2,3] {{1,2,3},{4,5,6}};

            int[,] cloneArray = intArray.Clone() as int[,];


            Console.WriteLine("This is the intial Array");
            for(int i=0; i<intArray.GetLength(0); i++) {
                for(int j=0; j<intArray.GetLength(1); j++) {
                    Console.WriteLine(intArray[i,j] + " ");
                }
            }


            Console.WriteLine("This is the clone of the Array");
            for(int i=0; i<cloneArray.GetLength(0); i++) {
                for(int j=0; j<cloneArray.GetLength(1); j++) {
                    Console.WriteLine(cloneArray[i,j] + " ");
                }
            }

            // will print false
            Console.WriteLine(intArray == cloneArray);
            
            // will print true as shallow copy is created
            // i.e. sub-arrays are shared
            Console.WriteLine(intArray[0,0] == cloneArray[0,0]);
            Console.WriteLine(intArray[1,1] == cloneArray[1,1]);

        }
    }
}

Output:This is the intial Array
1
2
3
4
5
6
This is the clone of the Array
1
2
3
4
5
6
False
True
True
```


![DataStructuresNotes](images/multi_dimensional_array_cloning.jpeg)

Array Rotation Practice Problem:

Write a function rotate(arr[], d, n) that rotates arr[] of size d by n elements.

My Solution:
```
package com.company;

import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {
	    /*
	        Write a function rotate(arr[], d, n) that rotates arr[] of size d by n elements.

	        Input:

	        d = 2
	        n = 7
	        array = {1,2,3,4,5,6,7}

	        Expected Output:

	        rotatedArray = {3,4,5,6,7,1,2}
	    */

        int d = 7;
        int n = 2;

        int[] arr = new int[]{1,2,3,4,5,6,7};

        arr = rotate(arr, n, d);
        for(int i=0; i<arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static int[] rotate(int[] arr, int n, int d) {

        int[] temp = new int[n];
        int[] rotatedArr = new int[arr.length];

        int count = -1;
        int nCount = n;
        int placement = d - n;
        int placementCount = 0;
        for(int i=0; i<arr.length; i++) {

            if(placementCount == temp.length) {
                break;
            }

            if(nCount < arr.length) {
                rotatedArr[i] = arr[nCount];
                nCount++;
            }

            count ++;
            if(count < n) {
                temp[i] = arr[count];
            }
            else if(nCount == arr.length) {
                rotatedArr[placement] = temp[placementCount];
                temp[placementCount]--;
                placementCount++;
                placement++;
            }

        }

        System.out.println("This is the rotated array");
        return rotatedArr;
    }
}
```


 

<br/>

## Linked Lists:

A linked list is a linear data structure (like arrays) where each element is a separate object. Each element (that is node) of a list is comprised of two items: the data and a reference to the next node. 

![DataStructuresNotes](images/LinkedList_BigO_Notation.jpg)

Types of Linked List : 
1. Singly Linked List: In this type of linked list, every node stores address or reference of the next node in the list and the last node has the next address or reference as NULL. For example 1->2->3->4->NULL 

2. Doubly Linked List: In this type of Linked list, there are two references associated with each node, One of the reference points to the next node and one to the previous node. The advantage of this data structure is that we can traverse in both directions and for deletion, we dont need to have explicit access to the previous node. Eg. NULL<-1<->2<->3->NULL 

3. Circular Linked List: Circular linked list is a linked list where all nodes are connected to form a circle. There is no NULL at the end. A circular linked list can be a singly circular linked list or a doubly circular linked list. The advantage of this data structure is that any node can be made as starting node. This is useful in the implementation of the circular queues in the linked list. Eg. 1->2->3->1 [The next pointer of the last node is pointing to the first]  

Example: Consider the previous example where we made an array of marks of students. Now if a new subject is added to the course, its marks are also to be added to the array of marks. But the size of the array was fixed and it is already full so it can not add any new element. If we make an array of a size lot more than the number of subjects it is possible that most of the array will remain empty. We reduce the space wastage Linked List is formed which adds a node only when a new element is introduced. Insertions and deletions also become easier with a linked list. 
One big drawback of a linked list is, random access is not allowed. With arrays, we can access ith element in O(1) time. In the linked list, it takes O(i) time. 

Implementing a simple Linked List in Java:
```
//Main File aka driver
package com.practice;

public class Main {

    public static void main(String[] args) {
	    LinkedList list = new LinkedList();

        list = LinkedList.insert(list, 0);
        list = LinkedList.insert(list, 1);

        Node curr = list.head;
        while(curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }

    }
}
```

```
// Node Java Class
package com.practice;

public class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        next = null;
    }
}
```

```
//LinkedList Java Class
package com.practice;

public class LinkedList {
    Node head;

    public static LinkedList insert(LinkedList list, int data) {
        Node new_node =  new Node(data);
        new_node.next = null;

        if(list.head == null) {
            list.head = new_node;
        }
        else {
            Node last = list.head;
            while(last.next != null) {
                last = last.next;
            }
            last.next = new_node;
        }
        return list;
    }
}
```

Other common LinkedList methods:

```
// Print List

    public static void printList(LinkedList list) {
        Node curr = list.head;

        while(curr != null) {
            System.out.println(curr.info);
            curr = curr.next;
        }
    }
```

```
// List Size

   public static int size(LinkedList list) {
        Node curr = list.head;

        int counter = 0;
        while(curr != null) {
            curr = curr.next;
            counter++;
        }
        return counter;
    }
```

```
// Insert At Position

public static Node insertAt(LinkedList list , int position, int data) {
        Node headNode = list.head;

        if (position < 1)
            System.out.print("Invalid position");

        // if position is 1 then new node is
        // set in front of head node
        // head node is changing.
        if (position == 1) {
            Node newNode = new Node(data);
            newNode.next = headNode;
            list. head = newNode;
        } else {
            while (position-- != 0) {
                if (position == 1) {
                    // adding Node at required position
                    Node newNode = new Node(data);

                    // Making the new Node to point to
                    // the old Node at the same position
                    newNode.next = headNode.next;

                    // Replacing current with new Node
                    // to the old Node to point to the new Node
                    headNode.next = newNode;
                    break;
                }
                headNode = headNode.next;
            }
            if (position != 1)
                System.out.print("Position out of range");
        }
        return list.head;
    }
```

```
// Delete Node At Position

    public static void deleteNode(LinkedList list, int position){
        if(list.head == null) {
            return;
        }

        Node temp = list.head;

        if(position == 0) {
            list.head = temp.next;
            return;
        }

        for(int i=1; temp != null && i < position-1; i++) {
            temp = temp.next;
        }

        if(temp == null || temp.next == null) {
            return;
        }

        Node next =  temp.next.next;

        temp.next = next;
    }
```

```
// Reverse a Linked List

    public static LinkedList reverse(LinkedList list) {
        Node prev = null;
        Node current = list.head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        list.head = prev;

        return listreverseLinkedListExample.
    }
```
Cool gif Example of Reverse in action:
![DataStructuresNotes](images/reverseLinkedListExample.gif)


<br/>

## Stacks:

A stack or LIFO (last in, first out) is an abstract data type that serves as a collection of elements, with two principal operations: push, which adds an element to the collection, and pop, which removes the last element that was added. In stack both the operations of push and pop take place at the same end that is top of the stack. It can be implemented by using both array and linked list.  

![DataStructuresNotes](images/Stack_BigO_Notation.jpg)

Example: Stacks are used for maintaining function calls (the last called function must finish execution first), we can always remove recursion with the help of stacks. Stacks are also used in cases where we have to reverse a word, check for balanced parenthesis, and in editors where the word you typed the last is the first to be removed when you use undo operation. Similarly, to implement back functionality in web browsers. 

Simple Stack Implementation:
```
// Main Class aka driver

package com.practice;

public class Main {

    public static void main(String[] args) {
        Stack stack = new Stack(5);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.pop();
        stack.push(4);

        stack.print();
    }
}
```

```
// Actual Stack Class

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
```
Other possibilities with Stacks are: empty() and search(Object element). We can also use a Vector as such:
```
public class Stack<E> extends Vector<E>
```


<br/>

## Queues:

A queue or FIFO (first in, first out) is an abstract data type that serves as a collection of elements, with two principal operations: enqueue, the process of adding an element to the collection. (The element is added from the rear side) and dequeue the process of removing the first element that was added. (The element is removed from the front side). It can be implemented by using both array and linked list. 

![DataStructuresNotes](images/Queue_BigO_Notation.jpg)

Example: Queue as the name says is the data structure built according to the queues of a bus stop or train where the person who is standing in the front of the queue(standing for the longest time) is the first one to get the ticket. So any situation where resources are shared among multiple users and served on a first come first serve basis. Examples include CPU scheduling, Disk Scheduling. Another application of queue is when data is transferred asynchronously (data not necessarily received at the same rate as sent) between two processes. Examples include IO Buffers, pipes, file IO, etc. 

Circular Queue The advantage of this data structure is that it reduces wastage of space in case of array implementation, As the insertion of the (n+1),’th element is done at the 0’th index if it is empty. 

This is a basic Queue Java Implementation:

```
// Main Class aka Driver

public class Main {

    public static void main(String[] args) {
        Queue queue = new Queue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        queue.dequeue();
        queue.first();

        queue.print();
    }
}
```

```
// Queue Java Class

public class Queue {
    private static int queue[];
    private static int front, rear, capacity;

    Queue(int size) {
        front = rear = 0;
        capacity = size;
        queue = new int[capacity];
    }

    public static void enqueue(int data) {
        if(capacity == rear) {
            System.out.println("Queue is full");
            return;
        }

        queue[rear] = data;
        rear++;
    }

    public static void dequeue() {
        if(front == rear) {
            System.out.println("Queue is empty");
            return;
        }

        else {
            for (int i = 0; i < rear - 1; i++) {
                queue[i] = queue[i + 1];
            }

            if (rear < capacity) {
                queue[rear] = 0;
            }
            rear--;
        }

        return;
    }

    public static void first() {
        if(front == rear) {
            System.out.println("Queue is empty");
            return;
        }

        System.out.print("This is the first element in queue: " + queue[front]);
    }

    public static void print() {
        if(front == rear) {
            System.out.println("Queue is full");
            return;
        }

        System.out.println("Printing out the queue");
        for(int i=front; i<rear; i++) {
            System.out.print(queue[i]);
        }
    }
    
}
```

Priority Queue is a queue in which every element has a a priority determining the order in which it is dequeued. Note as well if there are two elements with the same priorities they are served according to their order in the queue. 

These are the two types of priority Queues:

1) Ascending Order: As the name suggests, in ascending order priority queue, the element with a lower priority value is given a higher priority in the priority list. For example, if we have the following elements in a priority queue arranged in ascending order like 4,6,8,9,10. Here, 4 is the smallest number, therefore, it will get the highest priority in a priority queue.

2) Descending order: The root node is the maximum element in a max heap, as you may know. It will also remove the element with the highest priority first. As a result, the root node is removed from the queue. This deletion leaves an empty space, which will be filled with fresh insertions in the future. The heap invariant is then maintained by comparing the newly inserted element to all other entries in the queue.

The simplest implementation of this would be using an array.

```
public static void main(String args[])
{
    PriorityQueue<String> pq = new PriorityQueue<>();

    pq.add("Geeks");
    pq.add("For");
    pq.add("Geeks");

    System.out.println(pq);
}

Output: [For, Geeks, Geeks]
```

Note also that when implementing Priority Queue it is ascending by default. We can change this by creating a custom comparator to change this to be descending. 

For Example:

```
static class CustomIntegerComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 < o2 ? 1 : -1;
    }
}
```

Now implementing this as such:

```
testIntegersPQ.add(11);
    testIntegersPQ.add(5);
    testIntegersPQ.add(-1);
    testIntegersPQ.add(12);
    testIntegersPQ.add(6);

    System.out.println("Integers stored in reverse order of priority in a Priority Queue\n");
    while (!testIntegersPQ.isEmpty()) {
        System.out.println(testIntegersPQ.poll());
    }

Output: 12 6 5 -1
```


<br/>

## Binary Tree:

Unlike Arrays, Linked Lists, Stack, and queues, which are linear data structures, trees are hierarchical data structures. 
A binary tree is a tree data structure in which each node has at most two children, which are referred to as the left child and the right child. It is implemented mainly using Links. 

Binary Tree Representation: A tree is represented by a pointer to the topmost node in the tree. If the tree is empty, then the value of the root is NULL. A Binary Tree node contains the following parts. 
1. Data 
2. Pointer to left child 
3. Pointer to the right child 

A Binary Tree can be traversed in two ways: 
Depth First Traversal: Inorder (Left-Root-Right), Preorder (Root-Left-Right), and Postorder (Left-Right-Root) 
Breadth-First Traversal: Level Order Traversal 

Binary Tree Properties

![DataStructuresNotes](images/BinaryTreeProperties.jpg)

Examples: One reason to use binary trees or trees, in general, is for the things that form a hierarchy. They are useful in File structures where each file is located in a particular directory and there is a specific hierarchy associated with files and directories. Another example where Trees are useful is storing hierarchical objects like JavaScript Document Object Model considers HTML page as a tree with nesting of tags as parent-child relations. 

Binary Search Tree 
In Binary Search Tree is a Binary Tree with the following additional properties where any NODE, its value is: 
1. Greater than or equal to the value of any node in it's left subtree, and 
2. Less than the value of any node in its right subtree

Time Complexity

![DataStructuresNotes](images/BinarySearchTree_BigO_Notation.jpg)

BST provides moderate access/search (quicker than Linked List and slower than arrays). 
BST provides moderate insertion/deletion (quicker than Arrays and slower than Linked Lists). 

Examples: Its main use is in search applications where data is constantly entering/leaving and data needs to be printed in sorted order. For example in implementation in E-commerce websites where a new product is added or product goes out of stock and all products are listed in sorted order. 

Binary Search Implementation for finding index of a value in a sorted array:

```
// Main Class aka driver class

public class Main {

    public static void main(String[] args) {
        // Find Target 41 using binary search algorithm

        int[] sortedArr = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};

        BinarySearch.binarySearch(41, sortedArr);
    }
}
```

```
public class BinarySearch {

    public static int binarySearch(int x, int[] sortedArr) {

        int numOfArray = 0;
        for(int n : sortedArr) {
            numOfArray++;
        }

        int lower = 0, upper = numOfArray-1;

        while(lower <= upper) {
            int curr = (lower + upper)/2;

            //Check if x is at mid
            if(sortedArr[curr] == x) {
                System.out.println("Located at index: " +curr);
                System.out.println("This is the value: " +sortedArr[curr]);
                return curr;
            }

            //If x is greater ignore left half
            if(sortedArr[curr] < x) {
                lower = curr+1;
            }

            //If x is lower ignored right half
            if(sortedArr[curr] > x) {
                upper = curr-1;
            }
        }

        return -1;
    }

}
```

Implementation from CS 187 using a custom implementation of the IntegerComparator:

Takes 4 steps always to get to the result:

```
// Main Java

public class Main {

    public static void main(String[] args) {
        // Find Target 41 using binary search algorithm

        int[] sortedArr = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};

        BinarySearch.binarySearch(41, sortedArr);
    }
}
```

```
public class BinarySearch {

    public static int binarySearch(int x, int[] sortedArr) {

        int numOfArray = 0;
        for(int n : sortedArr) {
            numOfArray++;
        }

        int lower = 0, upper = numOfArray-1;
        int count = 0;
        while(lower <= upper) {
            int curr = (lower + upper)/2;
            IntegerComparator comparator = new IntegerComparator();
            int result = comparator.compare(x,sortedArr[curr]);

            if(result == 0) {
                count++;
                System.out.println("We found " +sortedArr[curr]+ " at index: " +curr);
                System.out.println("This took us " +count+ " steps to get here");
                return curr;
            }

            else if(result < 0) {
                upper = curr - 1;
            }

            else {
                lower = curr + 1;
            }
            count++;
        }

        return -1;
    }

}
```

```
// Custom Comparator

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        if(o1 == o2) {
            return 0;
        }
        else if(o1 > o2) {
            return 1;
        }
        else {
            return -1;
        }
    }

}
```

Back to Binary Trees. There are three ways to recursively traverse: pre-order, in-order, and post-order.

![DataStructuresNotes](images/BT_Traversal.jpg)

* Pre-order: visit the current node, left subtree, right subtree
* In-order: visit the left subtree, current node, right subtree
* Post-order: visit the left subtree, right subtree, current node

To find the height of a full binary tree of height h it would be 2^h

Math of a full binary tree and the height with N nodes:

![DataStructuresNotes](images/Math_BT_Height.jpg)

Binary Seach in a sorted array and BST are similar in the sense that we can find a value the same way:

![DataStructuresNotes](images/SearchInBST.jpg)

We can implement BSTNode class as follows:

![DataStructuresNotes](images/BSTNodeClass.jpg)

The Basic operations of BSTs are as follows:

Must Maintain BST ordering property:
* add(elem): insert a new node to BST
* remove(elem): remove node containing elem
Exploit BST ordering property:
* contains(elem): return true if tree contains node containing elem
* get(elem): find a tree node with info matching elem, return a reference to it; otherwise return null
Elegant recursive solution:
* size: return count of nodes in BST

We can implement the BinarySearchTree class as follows:

![DataStructuresNotes](images/BinarySearchTreeClass.jpg)

Summary of insertion:
* First, find the node to insert the new element to. This is much the same process as trying to find an element that turns out not to exist.
* Once you've found the node, insert the new element as either its left child or right child depending on the comparison result
* Note that the new element is always inserted into BST as a leaf node!

Inserting occurs as follows:

![DataStructuresNotes](images/InsertBSTExample.jpg)

The Remove Operation:

remove(elem): remove node containing elem
* The most complicated in BST operations
* We must ensure when we remove an element that we maintain the binary search tree property
* No need to memorize the code, but given a BST and a node to remove, you need to be able to draw the resulting BST after removal

Three cases for remove:
* Remove a leaf (no children): removing a leaf is simply a matter of setting the appropriate link of its parent to null
* Removing a node with only one child: make the reference from the parent skip over the removed node and point to the child of the node we intend to remove
* Removing a node with two children: replaces the node's info with the info from another node in the tree so that the search property is retained - then remove this other node

Example of a tricky situation of removing a node with two children:
![DataStructuresNotes](images/BSTtwonoderemoval.jpg)

CS 187 Implementation of contains method:
![DataStructuresNotes](images/BSTContainsMethod.jpg)

CS 187 Implementation of get method:
![DataStructuresNotes](images/BSTGetMethod.jpg)

CS 187 Implementation of size method:
![DataStructuresNotes](images/BSTSizeMethod.jpg)

Lets take a look at balancing a BST:
* Another way to think about balance is to look at how the tree hieght h is related to the number of nodes N. Ideally we want to garauntee that h = 0(log N)
* Because inesertion and deletion can cause the BST to become unbalanced, we need to perform extra steps to restore the balance (or preserve the height garauntee)
* Such trees are called self-balancing trees (aka height-balanced trees)

Examples of balanced trees:
![DataStructuresNotes](images/BalancedTrees.jpg)

Examples of Self-Balancing Trees:
* AVL Tree: Guarantees that at any node. the height difference between its left and right subtrees is no more than 1.
* Red-Black tree: Colors each node with red/black (requires extra bit per node), and enforces color compatability rules during insertion/deletion to maintain height guarantee.
* Scapegoat tree: When tree becomes unbalanced, find a 'scapegoat' and re-balances the sub-tree rooted at the scapegoat
* 2-3 tree (not binary tree): Each interior node either stores 1 data element and has 2 node children, or stores 2 data elements and has 3 children

DJW's approach to balancing:
* The user manually calls a balance() method from time to time (at the users discretion) to rebuild the tree into a balanced version.
* Does not qualify as self balancing since it is done manually

![DataStructuresNotes](images/DJWBalanceTree.jpg)

DJW how to implement:
* We export all nodes into an array sorted in ascending order: do an in-order traversal and save the result to an array (or queue)
* We build the balanced tree with a sorted array as follows: (similar to binary search) you start from the entire array, pick the middle element to make it a tree node, then recurse on the left and right sub-arrays

![DataStructuresNotes](images/insertDJW.jpg)

![DataStructuresNotes](images/improvisedVersionDJW.jpg)

Balance Method Summary:
* First performs an in-order traversal to store all nodes into array, then calls recursive insertTree (orsortedArray2BST) to build a balanced tree from the sorted array
* Instead of balancing the whole tree, you can choose to balance a sub-tree (this is a step you will need for scapegoat tree)

<!-- TODO we will come back to implementations of scapegoat shortly -->

<br/>

## Sorting:

Bubble Sort:

Intuition
* Round 1: find the largest number
* Round 2: find the second largest number
* Round 3: find the third largest number
* ....

* Bubble sort finds the largest number in each round by repeatedely comparing every two adjacent numbers, and swapping them if the one on the left is larger

![DataStructuresNotes](images/BubbleSort.jpg)

How does this work:
* After one pass, we find the largest number in that round
* Its like the biggest 'bubble' floats to the top of the surface, hence the name 'bubble sort'

Lets look at an example of this: 45 1 9 30 21
* In the second pass, we repeat the same process, but have only N-1 numbers to work on
* In the third pass, we only have N-2 numbers to work on
* ....
* Repeat until we are left with just 1 number
* How many comparisons in total do we have to perform?
    * How many comparisons in the 1st, 2nd, 3rd round?

```
(N-1) + (N-2) + (N-3) + ... 1 = N(N-1)/2 = O(N^2)
```
Here is the CS 187 Implementation of this:
![DataStructuresNotes](images/bubblesortimplementation.jpg)
![DataStructuresNotes](images/swapmethod.jpg)


Selection Sort:

* Better than bubble sort that can lead to a large number of swaps (each3 assignments)
* We can improve it by doing only one swap per outer loop, reducing the number of swaps to O(N)

* Idea
    * Keep track of the index of the smallest element in each round
    * Swap the smallest element towards the beginning of the array after each round
    * Repeat the above two steps

Lets take a look at an example: 70 67 3 18 30
* This is slight different from bubble sort as you build up the sorted array from the left (smallest elements)
* Number of swaps?
    * O(N) in all cases
* Number of comparisons?
    * Same as before: N(N-1)/2 = O(N^2)
So while selection sort reduces the number of swaps, it does not reduce the number of comparisons

CS 187 Implementation:
![DataStructuresNotes](images/SelectionSort.jpg)


Insertion Sort:

What it looks like:
![DataStructuresNotes](images/InsertionSortExample.jpg)

* Idea
    * Build up the sorted array from the left (similar to selection sort)
    * Assume the left portion of the array is partially sorted, take the first element in the right portion, insert it to the left portion at the correct position (just like inserting an element to a sorted array)
    * Repeat until done

CS 187 Implementation:
![DataStructuresNotes](images/InsertionSortImplementation.jpg)

Lets take a look at an example of this: 16 8 47 52 9
* Note that our Insertion Sort code does not use swap (unlike Bubble Sort and Selection Sort). Insetad, it uses a copy at the end of the outer loop. 
    * Swap involves 3 copies, so triples the cost
* Number of copies:
    * Best case(while condition never true): N
    * Worst case: N(N-1)/2


Merge Sort:

* Idea
    * Divide and conquer 
    * Can be implemented recursively or iteratively 
    * Cost is O(N log N), much faster than simple sorting 
    * Requies additional memory space
        * A temporary array as larfe as the input array

Merging two sorted arrays:
* This is a key step to merge sort
* Assume arrays A and B are already sorted 
* Merge them into array C such that C contains all elements from A and B, and remains sorted
* Note that two arrays may have different sizes. In fact, one of them may be empty! Must correctly handle all cases
* Example:
    A: 23 47 81 95
    B: 7 14 39 55 62 74

Summary of Merge Sort two sorted arrays:
1. Start from the first elements of A and B
2. Compare and copy the smaller of the smaller of the two to C 
3. Increment index of C as well as the array where you picked the smaller element from
4. Repeat
5. If reaching the ened of either A or B, quit loop and append the remaining elements to C

CS 187 Implementation:
![DataStructuresNotes](images/MergeSortImplementation.jpg)

Merge Sort Method:
With the merge() method, merge sort is quite simple:
* Divide the array into halves
* Sort each half(Conquer). How? Recursion!
* Call merge() to merge two halves
* Whats the best case of the recurison? 
When there is only 1 element left to sort, its trivially sorted, so return immediately

Example of merge sort in action:
![DataStructuresNotes](images/MergeSortExample.jpg)
![DataStructuresNotes](images/MergeSortExample2.jpg)

CS 187 Implementation 2:
![DataStructuresNotes](images/MergeSortImplementation2.jpg)




<br/>

## Binary Heap:

A Binary Heap is a Binary Tree with the following properties. 
1) It’s a complete tree (All levels are completely filled except possibly the last level and the last level has all keys as left as possible). This property of Binary Heap makes them suitable to be stored in an array. 
2) A Binary Heap is either Min Heap or Max Heap. In a Min Binary Heap, the key at the root must be minimum among all keys present in Binary Heap. The same property must be recursively true for all nodes in Binary Tree. Max Binary Heap is similar to Min Heap. It is mainly implemented using an array. 

![DataStructuresNotes](images/BinarySearchTree_BigO_Notation2.jpg)

Example: Used in implementing efficient priority queues, which in turn are used for scheduling processes in operating systems. Priority Queues are also used in Dijkstra’s and Prim’s graph algorithms. 
The Heap data structure can be used to efficiently find the k smallest (or largest) elements in an array, merging k sorted arrays, a median of a stream, etc. 
Heap is a special data structure and it cannot be used for searching a particular element. 

<br/>

## Hashing:

HashingHash Function: A function that converts a given big input key to a small practical integer value. The mapped integer value is used as an index in the hash table. A good hash function should have the following properties 
1) Efficiently computable. 
2) Should uniformly distribute the keys (Each table position equally likely for each key) 

Hash Table: An array that stores pointers to records corresponding to a given phone number. An entry in a hash table is NIL if no existing phone number has a hash function value equal to the index for the entry. 

Collision Handling: Since a hash function gets us a small number for a key which is a big integer or string, there is the possibility that two keys result in the same value. The situation where a newly inserted key maps to an already occupied slot in the hash table is called collision and must be handled using some collision handling technique. Following are the ways to handle collisions: 

Chaining: The idea is to make each cell of the hash table point to a linked list of records that have the same hash function value. Chaining is simple but requires additional memory outside the table. 
Open Addressing: In open addressing, all elements are stored in the hash table itself. Each table entry contains either a record or NIL. When searching for an element, we one by one examine table slots until the desired element is found or it is clear that the element is not in the table. 

![DataStructuresNotes](images/BinarySearchTree_BigO_Notation3.jpg)

Hashing seems better than BST for all the operations. But in hashing, elements are unordered and in BST elements are stored in an ordered manner. Also, BST is easy to implement but hash functions can sometimes be very complex to generate. In BST, we can also efficiently find floor and ceil of values. 

Example: Hashing can be used to remove duplicates from a set of elements. Can also be used to find the frequency of all items. For example, in web browsers, we can check visited URLs using hashing. In firewalls, we can use hashing to detect spam. We need to hash IP addresses. Hashing can be used in any situation where want search() insert() and delete() in O(1) time. 

This article is contributed by Abhiraj Smit. Please write comments if you find anything incorrect, or you want to share more information about the topic discussed above.