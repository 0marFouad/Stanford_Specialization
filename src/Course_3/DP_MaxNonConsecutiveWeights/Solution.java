package Course_3.DP_MaxNonConsecutiveWeights;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static long[] readData(){
        long[] data = new long[1000];
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_3/DP_MaxNonConsecutiveWeights/data.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int i = 0;
            while ((st = br.readLine()) != null){
                data[i] = Integer.parseInt(st);
                i++;
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return data;
    }

    public static void main(String[] args){
        long[] data = readData();
        long[] res = new long[1000];
        StringBuilder[] res1 = new StringBuilder[2];
        res[0] = data[0];
        res[1] = data[1];
        res1[0] = new StringBuilder("1");
        if(data[1] > data[0]){
            res1[1] = new StringBuilder("01");
        }else{
            res1[1] = new StringBuilder("10");
        }
        for(int i=2;i<1000;i++){
            StringBuilder newS;
            if(res[i-1] > res[i-2] + data[i]){
                res[i] = res[i-1];
                newS = new StringBuilder(res1[1] + "0");
            }else{
                res[i] = res[i-2] + data[i];
                newS = new StringBuilder(res1[0] + "01");
            }
            res1[0] = res1[1];
            res1[1] = newS;
        }
    }
}
