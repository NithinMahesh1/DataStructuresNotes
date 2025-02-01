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

# How do you make a class immutable


# What is immutable vs mutable?:
# Mutable vs immutable is that immuatble classes are classes that cannot be modified after creation.
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


# What is an access modifier
# Which objects will be garbage collected
# http://geeksforgeeks.org/garbage-collection-python/


main()