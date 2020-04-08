package Course_4.FloydWarshall;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    static int GRAPH_SIZE = 1000;
    public static int[][] readData(String filename){
        int[][] graph = new int[GRAPH_SIZE+1][GRAPH_SIZE+1];
        for(int i=1;i<=GRAPH_SIZE;i++){
            for(int j=1;j<=GRAPH_SIZE;j++){
                if(i != j){
                    graph[i][j] = Integer.MAX_VALUE;
                }else{
                    graph[i][j] = 0;
                }
            }
        }
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_4/FloydWarshall/" + filename);
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                String[] splitted = st.split(" ");
                int from = Integer.parseInt(splitted[0]);
                int to = Integer.parseInt(splitted[1]);
                int weight = Integer.parseInt(splitted[2]);
                graph[from][to] = weight;
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return graph;
    }

    public static void Floyd_Warshall(int[][] graph){
        for(int k=1;k<=GRAPH_SIZE;k++){
            for(int i=1;i<=GRAPH_SIZE;i++){
                for(int j=1;j<=GRAPH_SIZE;j++){
                    if(graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE){
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }
    }

    public static boolean isNegativeCycle(int[][] graph){
        for(int i=1;i<=GRAPH_SIZE;i++){
            if(graph[i][i] < 0){
                return true;
            }
        }
        return false;
    }

    public static int getShortestShortest(int[][] graph){
        int min = Integer.MAX_VALUE;
        for(int i=1;i<=GRAPH_SIZE;i++){
            for(int j=1;j<=GRAPH_SIZE;j++){
                if(i != j)
                    min = Math.min(min, graph[i][j]);
            }
        }
        return min;
    }

    public static void main(String[] args){
        int[][] g3 = readData("g3.txt");
        Floyd_Warshall(g3);
        boolean isCycle3 = isNegativeCycle(g3);
        System.out.println("Third Graph has Neg. Cycle ?: " + isCycle3);
        if(!isCycle3){
            System.out.println("Third Graph Shortest Shortest Path: " + getShortestShortest(g3));
        }
    }
}
