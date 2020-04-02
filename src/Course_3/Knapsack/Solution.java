package Course_3.Knapsack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Solution {
    public static class State{
        int idx;
        long W;
        State(int idx, long W){
            this.idx = idx;
            this.W = W;
        }
        @Override
        public boolean equals(Object o) {
            State ss = (State) o;
            if(this.idx == ss.idx && this.W == ss.W){
                return true;
            }else{
                return false;
            }

        }
        @Override
        public int hashCode() {
            String combined = W + "," + idx;
            return combined.hashCode();
        }
    }

    public static void readData(){
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_3/Knapsack/small.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int i = 0;
            while ((st = br.readLine()) != null){
                String[] nums = st.split(" ");
                weights[i] = Long.parseLong(nums[1]);
                values[i] = Long.parseLong(nums[0]);
                i++;
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static long[] weights = new long[100];
    public static long[] values = new long[100];

    public static long getMaxValue_Rec(int idx, long W, HashMap<State, Long> mem){
        if (mem.containsKey(new State(idx, W))){
            return mem.get(new State(idx, W));
        }
        if(idx >= weights.length || W == 0){
            return 0;
        }
        long first = 0;
        if(W-weights[idx] >= 0){
            first = values[idx] + getMaxValue_Rec(idx+1, W-weights[idx],mem);
        }
        long second = getMaxValue_Rec(idx+1, W,mem);
        mem.put(new State(idx, W), Math.max(first, second));
        return Math.max(first, second);
    }

    public static long getMaxValue_Itr(int W){
        long[][] grid = new long[values.length+1][W+1];
        for(int i=1;i<=W;i++){
            for(int idx=values.length-1;idx>=0;idx--){
                long n1 = grid[idx+1][i];
                long n2 = 0;
                if(i-weights[idx] >= 0){
                    n2 =  values[idx] + grid[idx+1][i-(int)weights[idx]];
                }
                grid[idx][i] = Math.max(n1,n2);
            }
        }
        return grid[0][W];
    }


    public static void main(String[] args){
        readData();
        System.out.println(getMaxValue_Itr(10000));
    }

}
