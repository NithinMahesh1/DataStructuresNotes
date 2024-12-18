class Solution:
	def binarySearch(self, nums: list[int], n: int) -> int:
		# If mid is at target return
		# If mid val is > than target we make R less than mid by one
			# this is because it should be the left half checked
		# Otherwise if it is mid val is less than n we should check right
			# that is why we increase L to mid
		L,R = 0,len(nums)-1
		while L <= R:
			mid = (L+R)//2
			if(nums[mid] == n):
				print(nums[mid])
				return nums[mid]
			if(nums[mid] > n):
				R = mid - 1
			else:
				L = mid + 1

		return -1


def main():
	solution = Solution()
	# nums=[1,2,3,4,5]
	# nums = [1,2,3,4,5,6,7,8,9]
	# n=2
	nums = [1, 3, 5, 7, 9, 11]
	n = 7
	solution.binarySearch(nums,n)


main()