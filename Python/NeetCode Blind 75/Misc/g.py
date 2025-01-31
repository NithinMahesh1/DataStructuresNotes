# Python homework - write a random number generator. Write some SQL statements.
# This solution is the easy way
import random
class Solution:
    def ranGen(self) -> int:
        return int(random.uniform(0,int(1e9)))

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
#             int randomNum = random.Next();
#             return randomNum;
#         }
#         public static void Main(string[] args) {
#             Console.WriteLine(ranGen());
#         }
#     }
# }


# Implementation from scratch
# Python:


# How do you make a class immutable

# What is immutable vs mutable
# How does garbage collection work in Python?
# What is an access modifier
# Which objects will be garbage collected
# http://geeksforgeeks.org/garbage-collection-python/


main()