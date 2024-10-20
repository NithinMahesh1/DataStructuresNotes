# Given an integer array nums and an integer k, return the k most frequent elements within the array.
# The test cases are generated such that the answer is always unique.
# You may return the output in any order.

# Example 1:
# Input: nums = [1,2,2,3,3,3], k = 2
# Output: [2,3]

# Example 2:
# Input: nums = [7,7], k = 1
# Output: [7]

# Example 3:
# Input: nums = [1,2,2,3,3,3,4,4,5], k = 3
# Output: [2,3,4]

from collections import Counter

def topKFrequent(k,nums) -> list:
	# Use the collections Counter instead
    # Find the most frequent and figure out how to remove the excess
	sortedList = Counter(nums).most_common(k)
	list = []
	
	for vals in sortedList:
		list.append(vals[0])
	
	return list


def main():
    k = 2
    nums = [1,2,2,3,3,3]
    # nums = [7,7]
    # k = 1
    # nums = [1,2,2,3,3,3,4,4,5]
    # k = 3
    print(topKFrequent(k,nums))

main()