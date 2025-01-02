# You are given an array of k linked lists lists, where each list is sorted in ascending order.

# Return the sorted linked list that is the result of merging all of the individual linked lists.

# Example 1:
# Input: lists = [[1,2,4],[1,3,5],[3,6]]
# Output: [1,1,2,3,3,4,5,6]

# Example 2:
# Input: lists = []
# Output: []

# Example 3:
# Input: lists = [[]]
# Output: []

from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:    
    def mergeKLists(self, lists: list[Optional[ListNode]]) -> Optional[ListNode]:
        # Seems like this problem is similar to mergeTwoSortedLists but this time with k lists
        # We can loop the list of lists call a helper mergeTwoLists and handle merging two first
        if len(lists) == 0 or len(lists) == 1:
            return lists

        for i in range(1,len(lists)):
            lists[i] = self.mergeTwo(lists[i - 1], lists[i])

        return lists[-1]
    
    def mergeTwo(self,list1: ListNode, list2: ListNode):
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

        return dummy.next
        
    def buildList(self,lists: list[list[int]]) -> list[ListNode]:
        returnList = []

        for sublist in lists:
            head = None
            curr = None
            for vals in sublist:                
                if(head is None):
                    head = ListNode(vals)
                    curr = head
                else:
                    curr.next = ListNode(vals)
                    curr = curr.next
            returnList.append(head)

        return returnList


def main():
    lists = [[1,2,4],[1,3,5],[3,6]]
    solution = Solution()    
    lists = solution.buildList(lists)
    solution.mergeKLists(lists)
    

main()