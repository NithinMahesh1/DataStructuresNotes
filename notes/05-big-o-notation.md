# Big O Notation

## Time Complexity

Big O notation describes the upper bound of an algorithm's growth rate as input size increases.

![Big O Time Complexity](../images/Big%20O%20Time%20Complexity.png)

| Notation | Name | Example |
|----------|------|---------|
| O(1) | Constant | Array access by index, hash table lookup |
| O(log n) | Logarithmic | Binary search |
| O(n) | Linear | Linear search, single loop |
| O(n log n) | Log-linear | Merge sort, heap sort, quick sort (avg) |
| O(n²) | Quadratic | Bubble sort, selection sort, nested loops |
| O(2^n) | Exponential | Recursive Fibonacci, power set |
| O(n!) | Factorial | Generating all permutations |

## Space Complexity

**O(1):**
- The space required by an algorithm does not grow with the input or size of the data on which we are using the algorithm.
- If you have a traversal algorithm for traversing the list which allocates a single pointer to do so, the traversal algorithm is considered to be of O(1) space complexity.
- Additionally, if that traversal algorithm needs not 1 but 1000 pointers, the space complexity is still considered to be O(1).
- However, if the algorithm needs to allocate 'N' pointers when traversing a list of size N (i.e., 3 pointers for 3 elements, 10 pointers for 10 elements, 1000 pointers for 1000 elements), then the algorithm is considered to have a space complexity of O(N). This is true even when 'N' is very small, e.g., N=1.

[Citation](https://stackoverflow.com/questions/43260889/what-is-o1-space-complexity#:~:text=o(1)%20space%20complexity%20means,%E2%80%93%20Rodrigo%20Gonzalez)

## Quick Reference — Data Structure Operations

| Data Structure | Access | Search | Insertion | Deletion |
|----------------|--------|--------|-----------|----------|
| Array | O(1) | O(n) | O(n) | O(n) |
| Linked List | O(n) | O(n) | O(1) | O(1) |
| Stack | O(n) | O(n) | O(1) | O(1) |
| Queue | O(n) | O(n) | O(1) | O(1) |
| BST (balanced) | O(log n) | O(log n) | O(log n) | O(log n) |
| Hash Table | N/A | O(1) avg | O(1) avg | O(1) avg |
| Binary Heap | O(n) | O(n) | O(log n) | O(log n) |

Each data structure section includes its specific Big O analysis with diagrams.

---

[← Back to Index](index.md)
