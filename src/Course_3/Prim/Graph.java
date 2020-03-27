package Course_3.Prim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graph {
    private static int INFINITY = 1000000;
    public ArrayList<ArrayList<Integer>> adjList;
    public int num_of_vertices;
    public int[][] weights;
    private Boolean[] visited;
    public int num_visited;

    public void visit(int node_id){
        if(node_id >= 0 && node_id < visited.length){
            visited[node_id] = true;
            num_visited++;
        }
    }

    public boolean isVisited(int node_id){
        if(node_id >= 0 && node_id < visited.length){
            return visited[node_id];
        }
        return false;
    }

    public Graph(int num_of_vertices){
        this.num_of_vertices = num_of_vertices;
        this.num_visited = 0;
        this.visited = new Boolean[num_of_vertices+1];
        weights = new int[num_of_vertices+1][num_of_vertices+1]; // 1-indexed
        for(int i=0;i<=num_of_vertices;i++){
            visited[i] = false;
            for(int j=0;j<=num_of_vertices;j++){
                weights[i][j] = INFINITY;
            }
        }
        adjList = new ArrayList<>();
        for(int i=0;i<=num_of_vertices;i++){
            adjList.add(new ArrayList<>());
        }
        readData();
    }

    public void readData(){
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();
        for(int i=0;i<=num_of_vertices;i++){
            data.add(new ArrayList<>());
        }
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_3/Prim/edges.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                String[] vals = st.split(" ");
                int from = Integer.parseInt(vals[0]);
                int to = Integer.parseInt(vals[1]);
                int edge_cost = Integer.parseInt(vals[2]);
                data.get(from).add(to);
                weights[from][to] = edge_cost;
                data.get(to).add(from);
                weights[to][from] = edge_cost;
            }
        }catch (IOException e){
            System.out.println(e);
        }
        adjList = data;
    }



}
