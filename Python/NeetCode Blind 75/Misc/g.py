import random
# Python homework - write a random number generator. Write some SQL statements.
class Solution:
    def ranGen(self) -> int:
        return random.randomrange(0,float("inf"))

def main():
    solution = Solution()
    print(solution.ranGen())



# How do you make a class immutable
# What is immutable vs mutable
# How does garbage collection work in Python?
# What is an access modifier
# Which objects will be garbage collected
# http://geeksforgeeks.org/garbage-collection-python/


main()