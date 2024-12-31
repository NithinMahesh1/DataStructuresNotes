# You are given the beginning of a linked list head, and an integer n.

# Remove the nth node from the end of the list and return the beginning of the list.

# Example 1:
# Input: head = [1,2,3,4], n = 2
# Output: [1,2,4]

# Example 2:
# Input: head = [5], n = 1
# Output: []

# Example 3:
# Input: head = [1,2], n = 2
# Output: [2]

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        # We loop through with two pointers
        # First pointer L is incremented one
        # Second pointer R is incremented + n
        # L and R distance will be n each time until R is at None
        # L will point to dummy which is one behind
        # When R reaches the end we use L.next.next to skip that node and point to next
        dummy = L = ListNode(0,head)
        R = head
        
        # First we will loop to get R to correct increment of n
        i = 1
        while i < n:
            R = R.next
            i += 1
            if(i == n):
                break

        while R != None:
            if(R.next == None):
                L.next = L.next.next
                break

            L = L.next
            R = R.next

        dummy = dummy.next
        
        return dummy

    
    def buildList(self,arr):
        curr = None
        head = None

        for nums in arr:
            if head == None:
                head = ListNode(nums)
                curr = head
            else:
                curr.next = ListNode(nums)
                curr = curr.next
        return head
        
    def printList(self,head):
        curr = head
        while curr:
            print(curr.val)
            curr = curr.next


def main():
    solution = Solution()
    # head = [1,2,3,4]
    # n = 2
    head = [5]
    n = 1
    head = solution.buildList(head)
    solution.printList(solution.removeNthFromEnd(head, n))

main()