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

def topKFrequent(k,nums) -> list:
    # Loop arr and add arr items to key and val as k = 1,2,3
    dict = {}
    list = []

    if(len(nums) == 1):
        return [nums[0]]

    if(len(nums) == 0):
        return [0]

    i = 0
    for num in nums:
        if(k == 0):
            return list
        if(len(dict) == 0):
            dict[num] = k
            continue 
        if(num in dict.keys()):
            dict[num] = k
            if(dict.get(num) == 1):
                continue
            k = k - 1
            list.append(num)
        else:
            dict[num] = k
        i += 1

    return list

def main():
    # k = 2
    # nums = [1,2,2,3,3,3]
    # nums = [7,7]
    # k = 1
    nums = [1,2,2,3,3,3,4,4,5]
    k = 3
    print(topKFrequent(k,nums))

main()