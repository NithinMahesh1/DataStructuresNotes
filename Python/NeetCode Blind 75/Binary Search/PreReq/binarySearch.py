class Solution:
	def binarySearch(self, nums: list[int], n: int) -> int:
		# Essentially is mid = (L+R)/2
		# If n < mid:
		# 	R = mid
		# If n > mid:
		# 	L = mid
		# If n == mid:
		# 	return mid
		
		mid = 0
		l,r = 0,len(nums)-1
		while(mid != n):
			mid = int((nums[l]+nums[r])/2)
			if(n == mid):
				return mid
			if(n < mid):
				r = mid
			if(n > mid):
				l = mid
			
		return 0


def main():
	solution = Solution()
	# nums=[1,2,3,4,5]
	nums = [1,2,3,4,5,6,7,8,9]
	n=2
	solution.binarySearch(nums,n)


main()