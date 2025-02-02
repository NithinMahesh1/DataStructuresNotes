# Python homework - write a random number generator. Write some SQL statements.
# This solution is the easy way
import random
class Solution:
    def ranGen(self) -> int:
        # uniform() basically just a range of random numbers range as a float
        # In this case we are giving it a range from 0 to 1e9
        # int(1e9) -> is converting 1e9 to an int which is scientific notation
        # Essentially that is 1 * 10^9
        return int(random.randint(0,int(1e9)))

def main():
    solution = Solution()
    print(solution.ranGen())

# Just in case this is the C# solution as well:
# using System

# namespace gen
# {
#     public class gen {
#         public static int ranGen() {
#             Random random = new Random();
#             int randomNum = random.Next(0,(int)1e9);
#             return randomNum;
#         }
#         public static void Main(string[] args) {
#             Console.WriteLine(ranGen());
#         }
#     }
# }

# Explanation of Random.Next() -> 
# Random.Next() in C# generates a pseudo-random number using an internal algorithm. 
# It's deterministic, meaning if we set the same seed, it will produce the same sequence of numbers. 
# It typically uses a Linear Congruential Generator (LCG)

# How do you make a class immutable?:
# You can make them immutable in a few different ways, one can be using "readonly" field for private fields
# In custom classes we can also set "get" and not "set" for properties
# We can also set all fields in the constructor
# IEnumerable example, lazy loading (elems only computed or iterated when needed e.g. foreach loops) and how it is memory efficient

# What is immutable vs mutable?:
# Immutability is basically a class where it's state cannot be changed after its creation
# Mutable vs immutable is that immutable classes are classes that cannot be modified after creation.
# All fields must be read-only after initialization
# No setters or mutators should exist
# If a class contains collections they should be read only

# Example of immutable class
# public class Person
# {
#     public string Name { get; }
#     public int Age { get; }

#     public Person(string name, int age)
#     {
#         Name = name;
#         Age = age;
#     }
# }

# Why would we use immutable?:
# Thread safety
# Predictability
# Easier debugging

# Mutable Examples:
# List<T>, arrays, and custom classes with setters

# Immutable Examples:
# strings (need stringbuilder to make them mutable), int, and decimals


# How does garbage collection work in Python and C#?
# C# uses the CLR (Common Language Runtime), 
# which is basically the equivalent of the JVM and allows all .net languages to be run on it (c#, VB, etc).
# All .NET languages compile to CLR bytecode. CLR provides system level functionality like garbage collection.
# CLR runs the compiled code
# There are two ways it works: heaps (for creating objects new) and stacks (for simple data types and references)
# Mainly runs on a managed heap:
# When we initialize a new process some an address of space is allocated, this is the managed heap.
# It maintains a pointer to the address where to object in the heap will be allocated
# As we create new objects the runtime allocates memory for the address space following the first object
# We then release memory when the garbage collector performs a collection. Basically releases objects 
# that are no longer being used by the application.
# It chooses these to remove based off of objects in use, if we can't reach memory is freed, and if we are able to
# those objects are moved to compact memory and pointers updated, heap pointer to last reachable object
# Geeks for Geeks explanation:  

# Marking Phase: A list of all the live objects is created during the marking phase. 
# This is done by following the references from all the root objects. 
# All of the objects that are not on the list of live objects are potentially deleted from the heap memory.

# Relocating Phase: The references of all the objects that were on the list of 
# all the live objects are updated in the relocating phase so that they point to the new location 
# where the objects will be relocated to in the compacting phase.

# Compacting Phase: The heap gets compacted in the compacting phase as the space occupied by the dead objects is released and the live objects remaining are moved. 
# All the live objects that remain after the garbage collection are moved towards the older end of the heap memory in their original order. 

# Which objects will be garbage collected
# Answered above but basically objects that are unreachable from the application roots
# Check the managed heap looking for blocks of address space occupied by unreachable objects
# Remove these the compact the managed heap


# What is an access modifier
# This is important to Encapsulation and Inheritance
# public, private, protected, internal
# public: accessible anywhere in the program
# private: accessible only in the current class
# protected: accessible in same class and derived classes
# internal: accessible in only the same assembly or program

main()

# Misc:
# http://geeksforgeeks.org/garbage-collection-python/

# More possible:
# graph traversal
# dynamic programming
# Difference between a float and double
# bullet point about SQL Server:
# Query writing, schema design and performance tuning in Microsoft SQL Server 


# gpt questions to prep:

# Query Writing

# Basic SQL:
#   * Write a query to select all columns from a table where a certain condition is met.
#   * How would you retrieve the top 5 highest-paid employees from an Employees table?
#       SELECT * FROM Employees WHERE DESC(pay)

# Joins & Relationships:
#   * Explain the difference between INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN.
#       INNER JOIN is for combining both matching rows (may also contain duplicates), LEFT JOIN/RIGHT JOIN joins all matching records from their side from the other side. FULL JOIN joins all values rows matching and non matching rows. 
#   * Given two tables: Orders(OrderID, CustomerID, OrderDate) and Customers(CustomerID, Name), 
#     write a query to get all customers and their orders, including customers who have not placed an order.
#       SELECT CustomerID, OrderID, Name
#       FROM Orders
#       LEFT JOIN CustomerID ON Orders.CustomerID = CustomerID.CustomerID;

# Aggregation & Grouping:
#   * How would you calculate the total sales per customer in a Sales table?
#   * What is the difference between HAVING and WHERE?
#       HAVING is like a WHERE but applies only to groups as a whole where as the WHERE is for individual rows

# Subqueries & Common Table Expressions (CTEs):
#   * Write a query using a subquery to find employees earning above the average salary.
#       SELECT 
#   * When would you use a CTE instead of a subquery?


# Schema Design
# Normalization & Indexing:
#   * What is database normalization? Explain 1NF, 2NF, and 3NF with examples.
#   * What are the different types of indexes in SQL Server, and when would you use each?
#   * How would you design a database schema for an e-commerce platform with users, products, and orders?

# Constraints & Relationships:
#   * What are primary keys and foreign keys?
#   * How would you enforce referential integrity between tables?

# Stored Procedures & Triggers:
#   * What are the benefits of using stored procedures?
#   * Write a simple stored procedure that inserts a new customer into a Customers table.
#   * What are SQL triggers, and when would you use them?


# Performance Tuning
#   * Indexing & Query Optimization:
#   * How do indexes improve query performance, and when should you avoid them?
#   * How would you identify slow-performing queries in SQL Server?
#   * Explain the purpose of the SQL Execution Plan. How do you use it to optimize queries?

# Transactions & Locking:
#   * What are SQL Server transaction isolation levels, and how do they affect performance?
#   * What is the difference between deadlock and blocking, and how do you resolve them?

# Partitioning & Caching:
#   * What is table partitioning, and when should you use it?
#   * How would you optimize queries on a table with millions of records?