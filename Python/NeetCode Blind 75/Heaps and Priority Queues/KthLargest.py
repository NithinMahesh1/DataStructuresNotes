# Design a class to find the kth largest integer in a stream of values, including duplicates. 
# E.g. the 2nd largest from [1, 2, 3, 3] is 3. The stream is not necessarily sorted.

# Implement the following methods:

# constructor(int k, int[] nums) Initializes the object given an integer k and the stream of integers nums.
# int add(int val) Adds the integer val to the stream and returns the kth largest integer in the stream.

# Example 1:
# Input:
# ["KthLargest", [3, [1, 2, 3, 3]], "add", [3], "add", [5], "add", [6], "add", [7], "add", [8]]

# Output:
# [null, 3, 3, 3, 5, 6]

# Explanation:
# KthLargest kthLargest = new KthLargest(3, [1, 2, 3, 3]);
# kthLargest.add(3);   // return 3
# kthLargest.add(5);   // return 3
# kthLargest.add(6);   // return 3
# kthLargest.add(7);   // return 5
# kthLargest.add(8);   // return 6

import heapq
class KthLargest:
    def __init__(self, k: int, nums: list[int]):
        self.k = k
        self.heap = []
        for num in nums:
            self.add(num)

    def add(self, val: int) -> int:
        # Adding the new value to the heap
        heapq.heappush(self.heap,val)

        # If the heap gets bigger than the len of k,
        # we remove the smallest element
        if(len(self.heap) > self.k):
            heapq.heappop(self.heap)
        
        # The first value will always be the min value
        # Since we keep the heap to always be of size k
        # We pop if the len gets larger and keep the greatest values at the end
        # This way we always pop and return the kth smallest
        return self.heap[0]


def main():
    k = 3
    nums = [1,2,3,3]
    obj = KthLargest(k,nums)
    print(obj.add(3))
    print(obj.add(3))
    print(obj.add(3))
    print(obj.add(5))
    print(obj.add(6))
    
main()