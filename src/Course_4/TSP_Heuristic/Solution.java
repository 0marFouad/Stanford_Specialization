package Course_4.TSP_Heuristic;

import sun.security.krb5.internal.crypto.Des;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class Solution {
    static boolean[] visited = new boolean[33708];
    public static class Destination implements Comparable<Destination>{
        int index;
        double distance;
        public Destination(int index,double x1, double y1, double x2, double y2){
            this.index = index;
            double x_diff2 = ((x1 - x2) * (x1 - x2));
            double y_diff2 = ((y1 - y2) * (y1 - y2));
            x_diff2 += y_diff2;
            distance  = Math.sqrt(x_diff2);
        }


        @Override
        public int compareTo(Destination destination) {
            if(Double.compare(this.distance, destination.distance) == 0){
                return Integer.compare(this.index,destination.index);
            }else{
                return Double.compare(this.distance,destination.distance);
            }
        }
    }
    public static double[][] readData(){
        double[][] data = new double[33708][2];
        for(int i=0;i<33708;i++){
            visited[i] = false;
        }
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_4/TSP_Heuristic/tsp.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int i=0;
            while ((st = br.readLine()) != null){
                String[] splitted = st.split(" ");
                data[i][0] = Double.parseDouble((splitted[1]));
                data[i][1] = Double.parseDouble((splitted[2]));
                i++;
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return data;
    }

    public static Destination getNeighbour(int current_node, double[][] data){
        PriorityQueue<Destination> pq = new PriorityQueue<>();
        for(int i=0;i<33708;i++){
            if(!visited[i]){
                pq.add(new Destination(i, data[current_node][0], data[current_node][1], data[i][0], data[i][1]));
            }
        }
        return pq.poll();
    }


    public static void main(String[] args){
        double[][] data = readData();
        double dist = 0;
        int cur = 0;
        visited[0] = true;
        for(int i=0;i<33707;i++){
            Destination new_d = getNeighbour(cur, data);
            cur = new_d.index;
            dist += new_d.distance;
            visited[cur] = true;
        }
        Destination x = new Destination(-1,data[cur][0],data[cur][1],data[0][0],data[0][1]);
        dist += x.distance;
        System.out.println(dist);
    }
}
