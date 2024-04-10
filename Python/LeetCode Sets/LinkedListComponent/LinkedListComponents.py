# You are given the head of a linked list containing unique integer values and an integer array nums
# that is a subset of the linked list values.

# Return the number of connected components in nums where two values are connected
# if they appear consecutively in the linked list.


# Example 1:
# Input: head = [0,1,2,3], nums = [0,1,3]
# Output: 2
# Explanation: 0 and 1 are connected, so [0, 1] and [3] are the two connected components.


# Example 2:
# Input: head = [0,1,2,3,4], nums = [0,3,1,4]
# Output: 2
# Explanation: 0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.


# Iterate through linked list
# Two pointers curr and next
# If next contains one of the values from the arr nums we keep checking until there is not
# Increment a counter if this is the case


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def createLinkedList(arr) -> [ListNode]:
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


def printAList(ListNode):
    print("Printing inputted list")
    curr = ListNode

    while curr is not None:
        print(curr.val)
        curr = curr.next


def numComponents(head: [ListNode], nums: [int]) -> int:
    print("test")

    return 0


input = [0,1,2,3]
head = createLinkedList(input)
printAList(head)

nums = [0,1,3]
numComponents(head,nums)