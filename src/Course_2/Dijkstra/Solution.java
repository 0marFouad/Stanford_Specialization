package Course_2.Dijkstra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {

    public static class Node implements Comparable<Node>{
        public int id;
        public int weight;
        public Node(int id, int weight){
            this.id = id;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.weight, n.weight);
        }
    }


    public static int[] dijkstra(int src){
        Graph graph = new Graph(200);
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        Node srcNode = new Node(src, 0);
        frontier.add(srcNode);
        HashMap<Integer, Node> nodeInFrontier = new HashMap<>();
        boolean[] isVisited = new boolean[201];
        for(int i=0;i<=200;i++){
            isVisited[i] = false;
        }
        nodeInFrontier.put(src, srcNode);
        int[] dist = new int[201];
        for(int i=0;i<=200;i++){
            dist[i] = 1000000;
        }
        dist[src] = 0;
        while (!frontier.isEmpty()){
            Node getNext = frontier.poll();
            dist[getNext.id] = getNext.weight;
            nodeInFrontier.remove(getNext.id);
            for(int i=0;i<graph.adjList.get(getNext.id).size();i++){
                int to = graph.adjList.get(getNext.id).get(i);
                if(isVisited[to]){
                    continue;
                }
                if(!isVisited[to]){
                    if(nodeInFrontier.containsKey(to)){
                        Node n = new Node(to, Math.min(nodeInFrontier.get(to).weight, dist[getNext.id] + graph.weights[getNext.id][to]));
                        frontier.remove(nodeInFrontier.get(to));
                        frontier.add(n);
                        nodeInFrontier.remove(to);
                        nodeInFrontier.put(to,n);
                        continue;
                    }
                }
                Node n = new Node(to, dist[getNext.id] + graph.weights[getNext.id][to]);
                frontier.add(n);
                nodeInFrontier.put(to,n);
            }
            isVisited[getNext.id] = true;
        }
        return dist;
    }

    public static void main(String[] args){
        int[] dist = dijkstra(1);
        for(int i=1;i<dist.length;i++){
            System.out.println(i + " " + dist[i]);
        }
    }


}
