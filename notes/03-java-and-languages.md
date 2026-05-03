# Java & Language Concepts

## Managed vs Unmanaged Languages

With memory collection, a managed language deals with removing the memory from the heap after the program executes.
- **Managed languages** remove this automatically (most modern languages do this)
- **Unmanaged languages** require the coder to remove the memory from the heap (such as C and C++)

## Garbage Collector

- A garbage collector is for automatic memory management
- It removes the used memory from the heap once the program has run
- In Java the output file is `.java` files
- In C# it is CLR files (Common Language Runtime)

## Java Runtime Environment

**JVM (Java Virtual Machine):**
- Java programs compile to byte code that can be run on a JVM
- Used for garbage collection
- You need it to run the byte code produced by a Java compiler but do not specifically have one installed
- It is possible to bundle a JVM with a Java program so when the user downloads your program they have Java installed along with it
- Major benefit: a Java app can run on any operating system that can run a JVM

**JRE (Java Runtime Environment):**
- The environment that the JVM runs on
- Contains JVM, class libraries, and other files excluding development tools such as compiler and debugger
- You can run code on the JRE but can't develop or compile on it

**JDK (Java Development Kit):**
- A superset of JRE
- Contains everything that the JRE has along with development tools such as compiler, debugger, etc.

## Collections

- Framework that provides architecture to store and manipulate a group of objects
- Java collections can perform operations such as searching, sorting, insertion, manipulation, and deletion
- Contains classes:
    * ArrayList, Vector, LinkedList, PriorityQueue, HashSet, LinkedHashSet, TreeSet
- Contains Interfaces:
    * Set, List, Queue, Deque
- `List<E> list = new List<E>();` is not a collection but an interface

## Java Tips & Tricks

**Converting char to int at an index in a String:**
- `int digit = string.charAt(index) - '0';`
- You need to add the `- '0'`

**Character.isDigit():**
- Checks whether there is a digit at that index

**Integer.MAX_VALUE:**
- Checks if the integer is within 32-bit range integer range in memory

### Pre-Increment vs Post-Increment

**Post-Increment:**
```
// initialize i
int i = 0;
System.out.println("Post-Increment");

// i values is incremented to 1 after returning
// current value i.e; 0
System.out.println(i++);

Output: 0
```

**Pre-Increment:**
```
// initialized to 0
int j = 0;
System.out.println("Pre-Increment");

// j is incremented to 1 and then it's value is
// returned
System.out.println(++j);

Output: 1
```

---

[← Back to Index](index.md)
