# You are given the heads of two sorted linked lists list1 and list2.

# Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

# Return the head of the merged linked list.

# Example 1:
# Input: list1 = [1,2,4], list2 = [1,3,4]
# Output: [1,1,2,3,4,4]

# Example 2:
# Input: list1 = [], list2 = []
# Output: []

# Example 3:
# Input: list1 = [], list2 = [0]
# Output: [0]


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def createALinkedList(arr) -> [ListNode]:
    head = None
    curr = None

    for num in arr:
        if head is None:
            head = ListNode(num)
            curr = head
        else:
            curr.next = ListNode(num)
            curr = curr.next

    return head

def printAList(ListNode):
    curr = ListNode

    while curr != None:
        print(curr.val)
        curr = curr.next

input1 = [1,2,4]
input2 = [1,3,4]

list1 = createALinkedList(input1)
printAList(list1)