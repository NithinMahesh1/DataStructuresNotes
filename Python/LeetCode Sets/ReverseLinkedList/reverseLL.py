# Given the head of a singly linked list, reverse the list, and return the reversed list.

# Example 1:
# Input: head = [1,2,3,4,5]
# Output: [5,4,3,2,1]

# Example 2:
# Input: head = [1,2]
# Output: [2,1]

# Example 3:
# Input: head = []
# Output: []

class Node:
  def __init__(self, val, next=None): 
    self.val = val
    self.next = next

# Creating a single node
head = Node(1)
head.next = Node(2)
head.next.next = Node(3)

curr = head
new_list = None

while curr != None:
  next_node = curr.next
  # Now point the curr node backwards to new_list
  curr.next = new_list
  new_list = curr
  curr = next_node


while new_list:
  print(new_list.val)
  new_list = new_list.next