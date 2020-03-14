package Course_2.TwoSum;



import Course_2.StreamingMedians.Median_Maintenance;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Driver {
    static HashMap<Long, Integer> map;
    public static int isThere2Sum(long x, long target){
        map.put(x, map.get(x) - 1);
        int ans = 0;
        if(target - x == x){
            ans += map.get(x);
        }else{
            ans += map.getOrDefault(target - x,0);
        }
        map.put(x, map.get(x) + 1);
        return ans;
    }
    public static void main(String[] args){
        map = readData();
        ArrayList<Long> keys = new ArrayList<>();
        HashMap<Integer, Boolean> isDone = new HashMap<>();
        for(int i=-10000;i<=10000;i++){
            isDone.put(i,false);
        }
        for(long x : map.keySet()){
            keys.add(x);
        }
        long answer = 0;
        for(int j=0;j<keys.size();j++){
            long x = keys.get(j);
            System.out.println(j);
            for(int i=-10000;i<=10000;i++){
                if(!isDone.get(i)){
                    int v = isThere2Sum(x, i);
                    if(v != 0){
                        isDone.put(i,true);
                    }
                }
            }
        }
        for(int i=-10000;i<=10000;i++){
            if(isDone.get(i)){
                answer++;
            }
        }
        System.out.println("ANSWER " + answer);
    }


    public static HashMap<Long, Integer> readData(){
        HashMap<Long, Integer> data = new HashMap<>();
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_2/TwoSum/data.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                Long from = Long.parseLong(st);
                data.putIfAbsent(from,data.getOrDefault(from,0) + 1);
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return data;
    }
}
