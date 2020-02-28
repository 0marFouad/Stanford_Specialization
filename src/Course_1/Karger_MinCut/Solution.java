package Course_1.Karger_MinCut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {

    public static class Edge implements Comparable<Edge>{
        int first;
        int second;
        Edge(int first, int second){
            this.first = Math.min(first,second);
            this.second = Math.max(first,second);
        }

        @Override
        public int compareTo(Edge o)
        {
            return Integer.compare(this.first, o.first);
        }

        @Override
        public int hashCode() {
            return first*2000 + second;
        }
    }

    public static List<Edge> readData(){
        List<Edge> data = new ArrayList<>();
        HashSet<Integer> mp = new HashSet<>();
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_1/Karger_MinCut/graph.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int c = 1;
            while ((st = br.readLine()) != null){
                String[] vals = st.split("\t");
                for(int i=1;i<vals.length;i++){
                    Edge new_one = new Edge(c, Integer.parseInt(vals[i]));
                    if(mp.contains(new_one.first*2000 + new_one.second)){
                        continue;
                    }
                    mp.add(new_one.first*2000 + new_one.second);
                    data.add(new Edge(c,Integer.parseInt(vals[i])));
                }
                c++;
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return data;
    }

    public static int run_karger(List<Edge> edges, int seed){
        Random random = new Random();
        random.setSeed(seed);
        int vertices = 200;
        while(vertices > 2){
            int random_edge = random.nextInt(edges.size());
            Edge selected_edge = edges.get(random_edge);
            if(selected_edge.first == selected_edge.second){
                continue;
            }
            for(int i=0;i<edges.size();i++){
                if(edges.get(i).first == selected_edge.first){
                    edges.get(i).first = selected_edge.second;
                }
                if(edges.get(i).second == selected_edge.first){
                    edges.get(i).second = selected_edge.second;
                }
            }
            vertices--;
        }
        for(int i=0;i<edges.size();i++){
            if(edges.get(i).first == edges.get(i).second){
                edges.remove(i);
                i--;
            }
        }
        return edges.size();
    }

    public static void main(String[] args){
        int saved = Integer.MAX_VALUE;
        for(int i=0;i<40000;i++){
            List<Edge> r = readData();
            saved = Math.min(saved, run_karger(r,32801+i));
        }
        System.out.println(saved);
    }

}
//2073