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
        # First we want to reverse the list
        # Then iterate it again but this time remove the nth node
        # Loop it once more after removing and reverse it again

        curr = head
        prev = None

        while curr:
            nextnode = curr.next
            curr.next = prev
            prev = curr
            curr = nextnode

        i = 0
        curr = prev
        prev = None
        while curr:
            i += 1
            nextnode = curr.next
            if i == n:
                temp = nextnode.next
                nextnode.next = prev
                curr = nextnode
                nextnode = temp
            else:               
                curr.next = prev
                nextnode.next = curr
                prev = curr
                curr = nextnode
            


        print(prev)

    
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
    head = [1,2,3,4]
    n = 2
    head = solution.buildList(head)
    solution.printList(solution.removeNthFromEnd(head, n))

main()