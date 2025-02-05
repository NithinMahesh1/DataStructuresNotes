# Solving basic Fibonacci Sequence

class Solution:
    def fibonacci(self,n):
        # F(n) = F(n-1) + F(n-2)
        print(n)

        return self.fibonacci(n-1)+self.fibonacci(n-2)        


def main():
    solution = Solution()
    n = 0
    solution.fibonacci(n)

main()