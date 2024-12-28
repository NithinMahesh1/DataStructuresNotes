# You are given the heads of two sorted linked lists list1 and list2.

# Merge the two lists into one sorted linked list and return the head of the new sorted linked list.

# The new list should be made up of nodes from list1 and list2.

# Example 1:
# Input: list1 = [1,2,4], list2 = [1,3,5]
# Output: [1,1,2,3,4,5]

# Example 2:
# Input: list1 = [], list2 = [1,2]
# Output: [1,2]

# Example 3:
# Input: list1 = [], list2 = []
# Output: []

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def mergeTwoLists(self, list1: ListNode, list2: ListNode) -> ListNode:
        # Using a dummy allows us to avoid edge cases
        # Where either list is empty
        dummy = curr = ListNode()

        while list1 and list2:
            if(list1.val < list2.val):
                curr.next = ListNode(list1.val)
                list1 = list1.next
            else:
                curr.next = ListNode(list2.val)
                list2 = list2.next

            curr = curr.next

        curr.next = list1 or list2

        # Dummy is essentially an anchor node
        # curr is used to build and loop to append to dummy
        return dummy.next


    def printList(self, head):
        curr = head
        while curr:
            print(curr.val)
            curr = curr.next
        

    def buildLinkedLists(self,arr):
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

def main():
    solution = Solution()

    # list1 = solution.buildLinkedLists([1,2,4])
    # list2 = solution.buildLinkedLists([1,3,5])
    list1 = solution.buildLinkedLists([1,2])
    list2 = solution.buildLinkedLists([1,3,5])

    solution.printList(solution.mergeTwoLists(list1,list2))


main()