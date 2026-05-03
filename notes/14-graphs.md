# Graphs

## Overview

Breadth-First Search (BFS) and Depth-First Search (DFS) are the two primary traversal strategies for graphs.

## Depth-First Search (DFS)

- Goes deep to each child before going broad to each neighbor
- Done recursively using a helper such as `hasPath()` to check each node, node by node, until we find the one we are looking for
- Needs an `isVisited` flag to identify that we have found the node and to avoid being stuck in an infinite loop

[DFS Implementation](../Practice%20Technical%20DS%26A/DFS%20Basic%20Implementation)

## Breadth-First Search (BFS)

- Goes broad to each neighbor before going deep
- Iterative — uses a queue
- Add all the children to the queue, then pull out the first element, check if it has a path, and if it is not the final element add all of its children to the queue

## Further Study

This section covers the basics. For more practice:

- [C# Graph Solutions](../C%23/NeetCode%20Blind%20150/Graphs) — NeetCode Blind 150 graph problems in C#
- [Python NeetCode Blind 75](../Python/NeetCode%20Blind%2075/) — Includes graph-related problems across categories
- [Number of Islands (Java)](../LeetCode%20Sets/FindtheNumberOfIslands) — Classic graph problem using DFS

---

[← Back to Index](index.md)
