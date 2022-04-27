/*

    What is static?
        - Static is mainly used for memory management
        - Used to share the same variable or method of a given class 
        - Users can apply this with variables, methods, blocks, nested classes
        - Belongs to the class then the instance of the class
        - Used as constant variable (as the name suggests) or a method that is the same for every instance of a class


    What languages are managed and unmanaged?
        - With memory collecion a managed language deals with removing the memory from the heap after the program executes
        - Managed languages remove this automatically (most modern languages do this)
        - Unmanaged languages require the coder to remove the memory from the heap (such as C and C++)

    
    What is the garbage collector in a language?
        - A garbage collector is for automatic memory management
        - It removes the used memory from the heap once the program has run

    What is the JVM?
        - JVM stands for Java Virtual Machine
        - Java programs compile to byte code that can be run on a JVM 
        - This is used for garbage collection
        - You need it to run the byte code produced by a java compiler but do not specifically have one installed
        - It is possible to bundle a JVM with a Java program so when the user downloads your program they have java installed along with it
        - This has the major benefit of having a Java app able to run on any operating system that can run a JVM

    JRE
        - The environment that the JVM runs on
        - Contains JVM, class libraries and other files excluding the development tools such as compiler and debugger
        - Meaning you can run code on the JRE but can't develop or compile on it

    JDK
        - A superset of JRE
        - Contains everything that the JRE has along with development tools such as compiler, debugger, etc.

    
    What are collections?
        - Framework that provides architecture to store and manipulate a group of objects
        - Java collections can perform operations such as searching, sorting, insertion, manipulation, and deletion
        - Contains classes:
            * ArrayList, Vector, LinkedList, PriorityQueue, Hashset, LinkedHashSet, TreeSet
        - Contains Interfaces:
            * Set, List, Queue, Dequeue
        - List<E> list = new List<E>(); is not a collection but an interface
    

    What is the output file from a garbage collector?
        - In Java this would be .java files 
        - In C# this would be clr files (common language runtime)

*/





public static int reverse(int num) {
    // 123%10 -> 3
    // ... 12%10 -> 2
    
    int getEachNum(num);

    // output 321
}


// This solution is too complicated 
// We can do this with a while loop 
// Figure out how to do that
public static void getEachNum(int num) {

    if() {

    }

    int mod = num%10;

    getEachNum(mod);
}