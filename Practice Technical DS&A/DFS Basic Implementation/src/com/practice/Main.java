package com.practice;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(4);

        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println(
                "Following is Depth First Traversal "
                        + "(starting from vertex 2)");

        graph.DFS(2);
//
//        Graph g = new Graph(6);
//
//        g.addEdge(0, 1);
//        g.addEdge(0, 2);
//        g.addEdge(1, 0);
//        g.addEdge(1, 3);
//        g.addEdge(2, 0);
//        g.addEdge(2, 3);
//        g.addEdge(3, 4);
//        g.addEdge(3, 5);
//        g.addEdge(4, 3);
//        g.addEdge(5, 3);
//
//        System.out.println("Following is the Depth First Traversal");
//        g.DFS(0);
    }
}
