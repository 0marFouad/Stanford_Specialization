package Course_4.Two_SAT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {
    public static HashMap<Integer, Integer> clauseToNode = new HashMap<>();
    public static HashMap<Integer, Integer> nodeToClause = new HashMap<>();
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> revGraph = new ArrayList<>();
    public static int time = 0;

    public static void readData(String filename){
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_4/Two_SAT/2sat" + filename + ".txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                String[] splitted = st.split(" ");
                int from = Integer.parseInt(splitted[0]);
                int to = Integer.parseInt(splitted[1]);
                addEdge(-from, to);
                addEdge(-to, from);
            }
        }catch (IOException e){
            System.out.println(e);
        }
        for(int i=0;i<graph.size();i++){
            revGraph.add(new ArrayList<>());
        }
        for(int i=0;i<graph.size();i++){
            for (int j=0;j<graph.get(i).size();j++){
                int from = graph.get(i).get(j);
                int to = i;
                revGraph.get(from).add(to);
            }
        }
    }

    public static ArrayList<Integer> getNodeNeighbours(int clauseVar){
        if(clauseToNode.containsKey(clauseVar)){
            return graph.get(clauseToNode.get(clauseVar));
        }else{
            int to = graph.size();
            clauseToNode.put(clauseVar, to);
            nodeToClause.put(to, clauseVar);
            graph.add(new ArrayList<>());
            return graph.get(to);
        }
    }

    public static void addEdge(int fromClause, int toClause){
        ArrayList<Integer> fromNeighbours = getNodeNeighbours(fromClause);
        getNodeNeighbours(toClause);
        fromNeighbours.add(clauseToNode.get(toClause));

    }


    public static void DFS1(int node, HashSet<Integer> visited, int[] finish_time){
        if(visited.contains(node)){
            return;
        }
        visited.add(node);
        ArrayList<Integer> neighbours = revGraph.get(node);

        for(int i=0;i<neighbours.size();i++){
            DFS1(neighbours.get(i),visited,finish_time);
        }
        finish_time[time] = node;
        time++;
    }

    public static void DFS2(int node, HashSet<Integer> visited, int[] group_of_each, int group){
        if(visited.contains(node)){
            return;
        }
        visited.add(node);
        ArrayList<Integer> neighbours = graph.get(node);
        for(int i=0;i<neighbours.size();i++){
            DFS2(neighbours.get(i),visited,group_of_each,group);
        }
        group_of_each[node] = group;
    }

    public static boolean isSatisfiable(int[] groups){
        for (Map.Entry<Integer,Integer> entry : nodeToClause.entrySet()){
            if(groups[entry.getKey()] == groups[clauseToNode.get(-entry.getValue())]){
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args){
        readData("6");
        HashSet<Integer> visited = new HashSet<>();
        int[] finish_time = new int[graph.size()];
        for(int i=0;i<finish_time.length;i++){
            finish_time[i] = -1;
        }
        for(int i=0;i<revGraph.size();i++){
            DFS1(i,visited,finish_time);
        }
        int g = 0;
        HashSet<Integer> visited2 = new HashSet<>();
        int[] groups = new int[graph.size()];
        for (int i=finish_time.length-1;i>=0;i--){
            if(finish_time[i] != -1)
                DFS2(finish_time[i], visited2, groups, g);
            g++;
        }
        System.out.println(isSatisfiable(groups));
    }
}
