input = [1,2,3,4,5]

class ListNode:
  def __init__(self, val, next=None): 
    self.val = val
    self.next = next


def createLinkedList(arr):
    head = None
    curr = None

    for val in arr:
        if head is None:
          head = ListNode(val)
          curr = head
        else:
           curr.next = ListNode(val)
           curr = curr.next

    return head


def printAList(ListNode):
    curr = ListNode

    print("Printing....")
    while curr is not None:
       print(curr.val)
       curr = curr.next

# Given the head of a singly linked list, reverse the list, 
# and return the reversed list.

def reverseList(head) -> [ListNode]:
  # Ended up relearning my first solution since it is a clean 3 pointer solution
  curr = head
  newList = None

  while curr != None:
    nextNode = curr.next
    curr.next = newList
    newList = curr
    curr = nextNode

  return newList


listInput = createLinkedList(input)
print("This is the input list we are given:")
printAList(listInput)

output = reverseList(listInput)
print("This is our reversed output:")
printAList(output)