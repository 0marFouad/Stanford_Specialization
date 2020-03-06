package Course_2;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


public class Solution {
    public static int NUM_OF_NODES = 875714;
    //public static int NUM_OF_NODES = 5;
    public static ArrayList<ArrayList<Integer>> graph;
    public static ArrayList<ArrayList<Integer>> revGraph;
    public static int[] direct_map;
    public static boolean[] fvisited;
    public static boolean[] svisited;
    public static ArrayList<ArrayList<Integer>> readData(ArrayList<ArrayList<Integer>> rdata){
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();
        for(int i=0;i<=NUM_OF_NODES;i++){
            data.add(new ArrayList<>());
            rdata.add(new ArrayList<>());
        }
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_2/Kosaraju/SCC.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                String[] vals = st.split(" ");
                int from = Integer.parseInt(vals[0]);
                int to = Integer.parseInt(vals[1]);
                data.get(from).add(to);
                rdata.get(to).add(from);
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return data;
    }

    public static void DFS_first_pass(int[] t,int node){
        Stack<Integer> frontier = new Stack<>();
        Stack<Integer> saved = new Stack<>();
        frontier.add(node);
        fvisited[node] = true;
        while (!frontier.empty()){
            int cur = frontier.pop();
            saved.add(cur);
            for(int i=0;i<revGraph.get(cur).size();i++){
                if(!fvisited[revGraph.get(cur).get(i)]){
                    frontier.add(revGraph.get(cur).get(i));
                    fvisited[revGraph.get(cur).get(i)] = true;
                }
            }
        }
        while (!saved.empty()){
            int c = saved.pop();
            direct_map[t[0]] = c;
            t[0]++;
        }
    }

    public static int DFS_second_pass(int node){
        Stack<Integer> frontier = new Stack<>();
        Stack<Integer> saved = new Stack<>();
        frontier.add(node);
        svisited[node] = true;
        while (!frontier.empty()){
            int cur = frontier.pop();
            saved.add(cur);
            for(int i = 0; i< graph.get(cur).size(); i++){
                if(!svisited[graph.get(cur).get(i)]){
                    frontier.add(graph.get(cur).get(i));
                    svisited[graph.get(cur).get(i)] = true;
                }
            }
        }
        return saved.size();
    }



    public static void DFS_Loop(){
        fvisited = new boolean[NUM_OF_NODES+1];
        direct_map = new int[NUM_OF_NODES+1];
        int[] t = new int[1];
        for(int i=1;i<graph.size();i++){
            if(!fvisited[i])
                DFS_first_pass(t,i);
        }
    }



    public static void main(String[] args){
        revGraph = new ArrayList<>();
        graph = readData(revGraph);
        DFS_Loop();
        svisited = new boolean[NUM_OF_NODES+1];
        ArrayList<Integer> answer = new ArrayList<>();
        for(int i=direct_map.length-1;i>=0;i--){
            if(direct_map[i] == 0 || svisited[direct_map[i]]){
                continue;
            }
            int n = DFS_second_pass(direct_map[i]);
            if(n!=0){
                answer.add(n);
            }
        }
        Collections.sort(answer);
        for(int i=0;i<answer.size();i++){
            System.out.println(answer.get(i));
        }
    }
}
