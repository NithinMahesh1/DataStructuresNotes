# You are given an 2-D array points where points[i] = [xi, yi] 
# represents the coordinates of a point on an X-Y axis plane. You are also given an integer k.

# Return the k closest points to the origin (0, 0).

# The distance between two points is defined as the Euclidean distance (sqrt((x1 - x2)^2 + (y1 - y2)^2)).

# You may return the answer in any order.

# Example 1:
# Input: points = [[0,2],[2,2]], k = 1
# Output: [[0,2]]
# Explanation : The distance between (0, 2) and the origin (0, 0) is 2. 
# The distance between (2, 2) and the origin is sqrt(2^2 + 2^2) = 2.82842. 
# So the closest point to the origin is (0, 2).


# Example 2:
# Input: points = [[0,2],[2,0],[2,2]], k = 2
# Output: [[0,2],[2,0]]
# Explanation: The output [2,0],[0,2] would also be accepted.

import heapq
import math
class Solution:
    def kClosest(self, points: list[list[int]], k: int) -> list[list[int]]:
        # Loop through and calculate the distance sqrt(x^2 + y^2) for each list in the lists
        # Then we want to push it to a max heap
        # We then use a tuple to store into the max heap with it being the distance and actual point
        # Then at the end we can pop and append to a list of the max heap values and return their point
        heap = []
        list = []

        for point in points:
            x = point[0]
            y = point[1]
            dist = math.sqrt(x**2+y**2)
            heapq.heappush(heap,(dist,point))

        while k != 0:
            list.append(heapq.heappop(heap)[1])
            k -= 1

        return list


def main():
    points = [[0,2],[2,2]]
    k = 1
    solution = Solution()
    solution.kClosest(points,k)

main()