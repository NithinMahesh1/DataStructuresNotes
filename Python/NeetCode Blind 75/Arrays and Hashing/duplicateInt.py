# Given an integer array nums, return true if any value appears more than once in the array, otherwise return false.

# Example 1
# Input: nums = [1, 2, 3, 3]
# Output: true

# Example 2
# Input: nums = [1, 2, 3, 4]
# Output: false

def hasDuplicate(nums) -> bool:
    # Create dictionary
    # Append to dict if the value does not exist
    # if val does exist then we return true
    # if we reach the end of the nums arr return false

    dict = {}
    
    i = 0
    for num in nums:
        if(dict.get(num) == None):
            dict[num] = num
        else:
            return True
        i += 1
    
    return False

def main():
    # nums = [1,2,3,3]
    nums = [1, 2, 3, 4]
    val = hasDuplicate(nums)
    print(val)

main()