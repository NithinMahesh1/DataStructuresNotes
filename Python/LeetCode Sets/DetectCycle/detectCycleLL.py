# Given head, the head of a linked list, determine if the linked list has a cycle in it.
# There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
# Return true if there is a cycle in the linked list. Otherwise, return false.

# Input: head = [3,2,0,-4], pos = 1
# Output: true
# Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

# Input: head = [1,2], pos = 0
# Output: true
# Explanation: There is a cycle in the linked list, where the tail connects to the 0th node

# Input: head = [1], pos = -1
# Output: false
# Explanation: There is no cycle in the linked list.



# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


inputVals = [3,2,0,-4]
inputList = ListNode(0)
cycle = 1

def addVals(arr):
    head = None
    curr = None
    cycleNode = None

    for i in range(0,len(arr)):
        newNode = ListNode(arr[i])
        if head == None:
            head = newNode
            curr = head
        else:
            curr.next = newNode
            curr = curr.next

        if i == cycle:
            cycleNode = newNode

        if cycle is not None:
            curr.next = cycleNode

    return head

def printList(ListNode):
    visited = set()
    while ListNode:
        # creates an empty set
        if ListNode in visited:
            print()
            break
        visited.add(ListNode)
        print(ListNode.val)
        ListNode = ListNode.next    

inputList = addVals(inputVals)
isCycle = False

printList(inputList)


# curr = inputList
# nextNode = curr.next.next
# while inputList:
#     if curr.val != nextNode.val:
#         curr = curr.next
#         nextNode = nextNode.next.next
#     else:
#         isCycle = True
#         break

# print(isCycle)


# This is my submission to this problem on LeetCode
# This way is better uses array to check what has been visited

curr = inputList
visited = []

while curr != None:
    visited.append(curr)
    curr = curr.next
    if curr in visited:
        print(True)
        break

print(False)



