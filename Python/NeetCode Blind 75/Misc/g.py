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


# Chat GPT Solutions:

# Query Writing
# Basic SQL:
#   * Write a query to select all columns from a table where a certain condition is met.
#       SELECT * FROM table_name WHERE condition;

#   * How would you retrieve the top 5 highest-paid employees from an Employees table?
#       SELECT * FROM Employees ORDER BY Salary DESC LIMIT 5;

# Joins & Relationships:
#   * Explain the difference between INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN.
#       INNER JOIN returns only matching rows between both tables.
#       LEFT JOIN returns all records from the left table and matching records from the right table.
#       RIGHT JOIN returns all records from the right table and matching records from the left table.
#       FULL JOIN returns all records from both tables, with NULLs where there is no match.

#   * Given two tables: Orders(OrderID, CustomerID, OrderDate) and Customers(CustomerID, Name),
#     write a query to get all customers and their orders, including customers who have not placed an order.
#       SELECT Customers.CustomerID, Customers.Name, Orders.OrderID
#       FROM Customers
#       LEFT JOIN Orders ON Customers.CustomerID = Orders.CustomerID;

# Aggregation & Grouping:
#   * How would you calculate the total sales per customer in a Sales table?
#       SELECT CustomerID, SUM(SaleAmount) AS TotalSales
#       FROM Sales
#       GROUP BY CustomerID;

#   * What is the difference between HAVING and WHERE?
#       WHERE filters individual rows before aggregation.
#       HAVING filters groups after aggregation (e.g., filtering grouped results with SUM or COUNT).

# Subqueries & Common Table Expressions (CTEs):
#   * Write a query using a subquery to find employees earning above the average salary.
#       SELECT * FROM Employees
#       WHERE Salary > (SELECT AVG(Salary) FROM Employees);

#   * When would you use a CTE instead of a subquery?
#       - When you need to reference the same subquery multiple times.
#       - When improving readability by breaking down complex queries.
#       - When using recursion, which CTEs support but subqueries do not.

# Schema Design
# Normalization & Indexing:
#   * What is database normalization? Explain 1NF, 2NF, and 3NF with examples.
#       - 1NF (First Normal Form): No duplicate columns, unique rows, atomic values.
#         Example: No multiple phone numbers in a single field.
#       - 2NF (Second Normal Form): 1NF + all non-key attributes must depend on the entire primary key.
#         Example: A table with OrderID and ProductID should store product details separately.
#       - 3NF (Third Normal Form): 2NF + no transitive dependency (no indirect relationships).
#         Example: A Customer table should not store City/State; it should reference an Address table.
#     This is a good video explaining it: https://www.youtube.com/watch?v=J-drts33N8g&ab_channel=LearnLearnScratchTutorials

#   * What are the different types of indexes in SQL Server, and when would you use each?
#       - Clustered Index: Sorts and stores rows physically (one per table, usually primary key).
#       - Non-Clustered Index: Stores pointers to data (faster lookups but more storage usage).
#       - Unique Index: Ensures all values in a column are unique.
#       - Full-Text Index: Used for searching text-heavy columns.
#       Good explanation on this video: https://www.youtube.com/watch?v=BIlFTFrEFOI&ab_channel=CockroachDB
#           - Two types
#               * Clustered Index: Sort and store the data rows in table or view based on their key values. Only one clustered index
#                                  per a table. Data rows also stored in order based on the clustered index key. Only store tables so don't
#                                  take extra storage.
#               * Non Clustered Index: Seperate structure from data rows. Store in index key values and pointers (row locaters) to the
#                                      actual data rows. Stored in a seperate place from the actual table and take space. 

#   * How would you design a database schema for an e-commerce platform with users, products, and orders?
#       - Users(UserID, Name, Email, PasswordHash)
#       - Products(ProductID, Name, Price, StockQuantity)
#       - Orders(OrderID, UserID, OrderDate, TotalAmount)
#       - OrderDetails(OrderDetailID, OrderID, ProductID, Quantity, PriceAtPurchase)
#       - Foreign keys: Orders.UserID → Users.UserID, OrderDetails.OrderID → Orders.OrderID, etc.

# Constraints & Relationships:
#   * What are primary keys and foreign keys?
#       - Primary Key: A unique identifier for a table (e.g., UserID in Users table).
#       - Foreign Key: A reference to a primary key in another table to enforce relationships.

#   * How would you enforce referential integrity between tables?
#       - By using FOREIGN KEY constraints with CASCADE options for updates/deletes.
#       - e.g. with foreign key constraint if we remove the primary key from the main table the foreign key in the related table
#              would be orphaned with no link to the data. Basically if an attempt is made to remove a row in the primary key table
#              we would first need to remove the foreign key data or change it to another reference. 
#       - CASCADE options basically define or set an action to occur when the user tries to delete or update a key to which an existing
#         foreign keys point.

# Stored Procedures & Triggers:
#   * What are the benefits of using stored procedures?
#       - Improves performance by reducing query compilation time.
#       - Enhances security by limiting direct table access.
#       - Promotes code reusability and maintainability.

#   * Write a simple stored procedure that inserts a new customer into a Customers table.
#       CREATE PROCEDURE InsertCustomer
#       @Name VARCHAR(100), @Email VARCHAR(255)
#       AS
#       BEGIN
#           INSERT INTO Customers (Name, Email) VALUES (@Name, @Email);
#       END;

#   * What are SQL triggers, and when would you use them?
#       - Triggers are special procedures that execute automatically in response to INSERT, UPDATE, or DELETE.
#       - Used for logging, enforcing business rules, or synchronizing tables.

# Performance Tuning
# Indexing & Query Optimization:
#   * How do indexes improve query performance, and when should you avoid them?
#       - Indexes speed up searches by reducing the number of scanned rows.
#       - Avoid too many indexes as they slow down INSERT, UPDATE, DELETE operations.

#   * How would you identify slow-performing queries in SQL Server?
#       - Use `EXPLAIN` or `SHOW EXECUTION PLAN` to analyze query execution.
#       - Check `sys.dm_exec_requests` for long-running queries.
#       - Use `SQL Profiler` or `Extended Events` to track slow queries.

#   * Explain the purpose of the SQL Execution Plan. How do you use it to optimize queries?
#       - The execution plan shows how SQL Server executes a query.
#       - Helps identify slow operations like full table scans or missing indexes.
#       - Optimize queries by adding indexes, rewriting joins, or breaking down queries.

# Transactions & Locking:
#   * What are SQL Server transaction isolation levels, and how do they affect performance?
#       - Read Uncommitted: Fastest, but allows dirty reads.
#       - Read Committed: Prevents dirty reads but allows non-repeatable reads.
#       - Repeatable Read: Prevents dirty and non-repeatable reads.
#       - Serializable: Prevents all issues but can cause blocking.
#       - Snapshot: Uses versioning to prevent blocking.

#   * What is the difference between deadlock and blocking, and how do you resolve them?
#       - Blocking: One query holds a resource while another waits.
#       - Deadlock: Two queries wait on each other indefinitely.
#       - Resolve by:
#           - Using proper indexing.
#           - Avoiding long transactions.
#           - Using `WITH (NOLOCK)` carefully.
#           - Identifying deadlocks using `sys.dm_tran_locks`.

# Partitioning & Caching:
#   * What is table partitioning, and when should you use it?
#       - Splitting a large table into smaller partitions for better performance.
#       - Useful for large datasets like logs, where older data can be archived.

#   * How would you optimize queries on a table with millions of records?
#       - Use indexing to speed up lookups.
#       - Partition large tables to distribute data.
#       - Avoid `SELECT *`, fetch only required columns.
#       - Use proper data types to reduce storage.
#       - Optimize joins using indexed columns.



# C#
# Take home homework to create a small random number generator based on probabilities, 
# and to write an SQL query selecting data from given tables:

# This is using an array of probabilities for a given arr of integers
#using System;

# public class RandomNumberGenerator
# {
#     private Random _random = new Random();
    
#     public int GenerateWithProbability(int[] numbers, double[] probabilities)
#     {
#         // Compute cumulative probability
#         double[] cumulative = new double[probabilities.Length];
#         cumulative[0] = probabilities[0];
        
#         for (int i = 1; i < probabilities.Length; i++)
#         {
#             cumulative[i] = cumulative[i - 1] + probabilities[i];
#         }
        
#         // Generate a random number between 0 and 1
#         double randomValue = _random.NextDouble(); 
        
#         // Find the number corresponding to the random value
#         for (int i = 0; i < cumulative.Length; i++)
#         {
#             if (randomValue < cumulative[i])
#                 return numbers[i];
#         }
        
#         return numbers[numbers.Length - 1]; // Fallback (should not be reached)
#     }
# }

# // Example usage:
# public class Program
# {
#     public static void Main()
#     {
#         var rng = new RandomNumberGenerator();
        
#         int[] numbers = {1, 2, 3};
#         double[] probabilities = {0.2, 0.5, 0.3};
        
#         Console.WriteLine(rng.GenerateWithProbability(numbers, probabilities));
#     }
# }


# Difference between float and double?
# The difference is in precision. The float is 6-9 significant figures and double is about 15-17 significant figures.
# This means that double is safer to use for calculations. There is also Decimal in C# that is a decimal number from 0-9 sig figs
# and has more precision than float and double. 
# Float is 32 bit, double is 64 bit, and decimal is 128 bit.

# Time complexity of BFS/DFS
# Based off of the number of vertices

# Look into Graph Traversal Problems

# Practice the rest of the Dynamic Programming Questions in C#