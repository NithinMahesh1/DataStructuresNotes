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


def mergeTwoLists(list1, list2) -> [ListNode]: 
    # If lists are empty then return an empty list 
    # If either list is empty then we output the not empty list
    if(list1 == None and list2 == None):
        return ListNode
    if(list1 == None):
        return list2
    if(list2 == None):
        return list1
    
    # Loop through and find compare values from list 1 and list 2
    # If they are the same append both in no specific order
    # If val1 is smaller than val2 append val1 then val2
    # If val2 is smaller than val1 append val2 then val1
    else:
        mergedList = ListNode()
        curr = mergedList

        while list1 and list2:
            if list1.val <= list2.val:
                curr.next = list1
                list1 = list1.next
            else:
                curr.next = list2
                list2 = list2.next
            
            curr = curr.next
    
    # Since we alternate removing values from each list to curr and mergedList
    # we eventually will need to add the last value from the list thats not empty
    # also since the while terminates because one of the lists will be empty
    if list1:
        curr.next = list1
    elif list2:
        curr.next = list2

    # We get rid of the head of the list 
    # since we initialize with 0 in beginning
    mergedList = mergedList.next

    return mergedList



input1 = [1,2,4]
input2 = [1,3,4]

list1 = createALinkedList(input1)
list2 = createALinkedList(input2)

merge = mergeTwoLists(list1,list2)
printAList(merge)


# Visual explanation:

# list1: 1 -> 2 -> 4 -> None
# list2: 1 -> 3 -> 4 -> None

# mergedList:  -> None
#               ^
#               |
#              curr

# Iteration 1:
#     curr.next = list1  # mergedList -> 1 -> None
#     list1 = 2 -> 4 -> None
#     curr = 1 -> None

# Iteration 2:
#     curr.next = list2  # mergedList -> 1 -> 1 -> None
#     list2 = 3 -> 4 -> None
#     curr = 1 -> 1 -> None

# Iteration 3:
#     curr.next = list1  # mergedList -> 1 -> 1 -> 2 -> None
#     list1 = 4 -> None
#     curr = 1 -> 1 -> 2 -> None

# Iteration 4:
#     curr.next = list2  # mergedList -> 1 -> 1 -> 2 -> 3 -> None
#     list2 = 4 -> None
#     curr = 1 -> 1 -> 2 -> 3 -> None

# Iteration 5:
#     curr.next = list1  # mergedList -> 1 -> 1 -> 2 -> 3 -> 4 -> None
#     list1 = None
#     curr = 1 -> 1 -> 2 -> 3 -> 4 -> None

# curr.next = None  # Adjust to remove the dummy node
# mergedList = mergedList.next  # Skip the dummy node

# Output: 1 -> 1 -> 2 -> 3 -> 4 -> None
