package Course_3.Scheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Solution {
    public static int[][] readData(){
        int[][] data = new int[10000][2];
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_3/Scheduler/jobs.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int i = 0;
            while ((st = br.readLine()) != null){
                String[] splitted = st.split(" ");
                data[i][0] = Integer.parseInt(splitted[0]);
                data[i][1] = Integer.parseInt(splitted[1]);
                i++;
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return data;
    }


    public static void sortStrategy(boolean isRatio, int[][] data){
        if(isRatio){
            Arrays.sort(data, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    double quantityOne = (double)o1[0]/(double)o1[1];
                    double quantityTwo = (double)o2[0]/(double)o2[1];
                    return Double.compare(quantityTwo, quantityOne);
                }
            });
        }else{
            Arrays.sort(data, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    int quantityOne = o1[0] - o1[1];
                    int quantityTwo = o2[0] - o2[1];
                    if (quantityOne != quantityTwo){
                        return Integer.compare(quantityTwo, quantityOne);
                    }else{
                        return Integer.compare(o2[0],o1[0]);
                    }

                }
            });
        }
    }


    public static void main(String[] args){
        int[][] data = readData();
        sortStrategy(true,data);
        for(int i=0;i<data.length;i++){
            System.out.println(data[i][0] + " " + data[i][1]);
        }
        long minimumRequired = 0;
        int lengthSoFar = 0;
        for(int i=0;i<data.length;i++){
            lengthSoFar += data[i][1];
            minimumRequired += data[i][0]*lengthSoFar;
        }
        System.out.println(minimumRequired);
    }


}
