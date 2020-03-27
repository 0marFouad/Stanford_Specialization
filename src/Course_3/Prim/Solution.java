package Course_3.Prim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Solution {
    public static class Edge implements Comparable<Edge>{
        public int to;
        public int weight;
        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge n) {
            return Integer.compare(this.weight, n.weight);
        }
    }


    public static int getMinimumSpanningTreeCost(){
        Graph g = new Graph(500);
        PriorityQueue<Edge> frontier = new PriorityQueue<>();
        g.visit(1);
        HashMap<Integer, Edge> node_in_frontier = new HashMap<>();
        for(int i=0;i<g.adjList.get(1).size();i++){
            Edge n = new Edge(g.adjList.get(1).get(i), g.weights[1][g.adjList.get(1).get(i)]);
            frontier.add(n);
            node_in_frontier.put(g.adjList.get(1).get(i), n);
        }
        int min_cost = 0;
        while(g.num_visited < g.num_of_vertices){
            Edge new_one = frontier.poll();
            g.visit(new_one.to);
            min_cost += new_one.weight;
            for(int i=0;i<g.adjList.get(new_one.to).size();i++){
                int going_to = g.adjList.get(new_one.to).get(i);
                int going_weight = g.weights[new_one.to][g.adjList.get(new_one.to).get(i)];
                if(g.isVisited(going_to)){
                    continue;
                }
                if(node_in_frontier.containsKey(going_to)){
                    if(node_in_frontier.get(going_to).weight > going_weight){
                        frontier.remove(node_in_frontier.get(going_to));
                        Edge new_e = new Edge(going_to,going_weight);
                        node_in_frontier.put(going_to, new_e);
                        frontier.add(new_e);
                    }
                }else{
                    Edge new_e = new Edge(going_to,going_weight);
                    node_in_frontier.put(going_to, new_e);
                    frontier.add(new_e);
                }
            }
        }
        return min_cost;
    }

    public static void main(String[] args){
        System.out.println(getMinimumSpanningTreeCost());
    }
}
