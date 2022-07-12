package com.practice;

//https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
    int V;
    public LinkedList<Integer> adj[];

    Graph(int v) {
        this.V = v;
        adj = new LinkedList[v];

        for(int i=0; i<v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void DFSHelper(int v, boolean[] visited) {
        // Mark the current node as visited
        visited[v] = true;
        System.out.print(v + " ");

        // Iterate through the current vertex and its adjacent edges
        // If it is not visited we visit that index and call helper again
        Iterator<Integer> iter = adj[v].listIterator();
        while(iter.hasNext()) {
            int n = iter.next();
            if(!visited[n]) {
                DFSHelper(n,visited);
            }
        }

    }

    void DFS(int v) {
        boolean visited[] = new boolean[V];
        DFSHelper(v, visited);
    }
}