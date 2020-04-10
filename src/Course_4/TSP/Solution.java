package Course_4.TSP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    public static int SIZE = 25;
    public static double[][] dp = new double[33554432][25];
    public static double[][] graph = new double[SIZE+1][SIZE+1];
    public static void readData(){
        double[][] data = new double[25][2];
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_4/TSP/tsp.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int i=0;
            while ((st = br.readLine()) != null){
                String[] splitted = st.split(" ");
                data[i][0] = Double.parseDouble(splitted[0]);
                data[i][1] = Double.parseDouble(splitted[1]);
                i++;
            }
        }catch (IOException e){
            System.out.println(e);
        }
        for (int i=0;i<25;i++){
            for(int j=0;j<25;j++){
                graph[i+1][j+1] = Math.sqrt(Math.pow((data[i][0] - data[j][0]),2.0) + Math.pow((data[i][1] - data[j][1]),2.0));
            }
        }
    }

    public static boolean findNode(int set, int node){
        int mask = 1;
        mask = mask << node;
        int result = set & mask;
        return result != 0;
    }

    public static int addNode(int set, int node){
        int mask = 1;
        mask = mask << node;
        return set | mask;
    }

    public static int generateSetExcept(int current){
        int set = 0;
        for(int i=0;i<4;i++){
            if (i != current){
                set = addNode(set,i);
            }
        }
        return set;
    }

    public static int removeNode(int set, int node){
        int mask = 1;
        mask = mask << node;
        mask = mask ^ Integer.MAX_VALUE;
        return set & mask;
    }

    public static double TSP(int current, int set){
        if(set == 0){
            return graph[current][0];
        }
        if(dp[set][current] != -1){
            return dp[set][current];
        }
        double min = Double.MAX_VALUE;
        for(int i=0;i<SIZE;i++){
            if(findNode(set,i)){
                min = Math.min(min, graph[current][i] + TSP(i, removeNode(set,i)));
            }
        }
        dp[set][current] = min;
        return min;
    }



    public static void main(String[] args){
        readData();
        for (int i=0;i<33554432;i++){
            for (int j=0;j<25;j++){
                dp[i][j] = -1;
            }
        }
        System.out.println(TSP(0,generateSetExcept(0)));
    }
}
