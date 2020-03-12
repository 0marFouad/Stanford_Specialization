package Course_2.Dijkstra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graph {
    private static int INFINITY = 1000000;
    public ArrayList<ArrayList<Integer>> adjList;
    private int num_of_vertices;
    public int[][] weights;

    public Graph(int num_of_vertices){
        this.num_of_vertices = num_of_vertices;
        weights = new int[num_of_vertices+1][num_of_vertices+1]; // 1-indexed
        for(int i=0;i<=num_of_vertices;i++){
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
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_2/Dijkstra/graph.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                String[] vals = st.split("\t");
                int from = Integer.parseInt(vals[0]);
                for(int i=1;i<vals.length;i++){
                    String[] splits = vals[i].split(",");
                    if(splits[0].length() ==  0){
                        continue;
                    }
                    int to = Integer.parseInt(splits[0]);
                    int weight = Integer.parseInt(splits[1]);
                    adjList.get(from).add(to);
                    weights[from][to] = weight;
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }



}
