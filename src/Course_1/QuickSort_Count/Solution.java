package Course_1.QuickSort_Count;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    public static int partition(int[] data, int s_idx, int e_idx){
        if(e_idx - s_idx == 1){
            return s_idx;
        }
        int n = e_idx - s_idx + 1;
        int i = 0; // the place where pivot is supposed to be so far
        int j = 1; // iterating variable
        while(j<n){
            if(data[j+s_idx]<=data[s_idx]){
                i++;
                int temp = data[j+s_idx];
                data[j+s_idx] = data[i + s_idx];
                data[i+s_idx] = temp;
            }
            j++;
        }
        int temp = data[s_idx];
        data[s_idx] = data[i + s_idx];
        data[i+s_idx] = temp;
        return i+s_idx;
    }

    public static int quick_sort(int[] data, int s_idx, int e_idx, String piv){
        //base case
        if(e_idx <= s_idx){
            return 0;
        }
        if(e_idx - 1 == s_idx){
            if(data[s_idx] > data[e_idx]){
                int temp = data[e_idx];
                data[e_idx] = data[s_idx];
                data[s_idx] = temp;
            }
            return 1;
        }
        //find pivot
        if(piv.equals("last")){
            int temp = data[s_idx];
            data[s_idx] = data[e_idx];
            data[e_idx] = temp;
        }else if(piv.equals("median_of_3")){
            int first = data[s_idx];
            int last = data[e_idx];
            int med = data[s_idx + (e_idx-s_idx)/2];
            int[][] meds = {{first,s_idx},{med,s_idx + (e_idx-s_idx)/2},{last,(e_idx)}};
            Arrays.sort(meds, new Comparator<int[]>() {
                @Override
                public int compare(int[] ints, int[] t1) {
                    return Integer.compare(t1[0],ints[0]);
                }
            });
            int temp = data[s_idx];
            data[s_idx] = meds[1][0];
            data[meds[1][1]] = temp;
        }
        //partition
        int idx = partition(data,s_idx,e_idx);
        //call the other halves
        return quick_sort(data,s_idx,idx-1,piv) + quick_sort(data,idx+1,e_idx,piv) + e_idx - s_idx;
    }


    public static int count_comparisons(){
        //read data from file
        int[] data = new int[10000];
        int c = 0;
        File file = new File("/home/fo2sh/Desktop/Educational/Algorithms/Stanford_Assignments/src/Course_1/QuickSort_Count/Data.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                data[c] = (Integer.parseInt(st));
                c++;
            }
        }catch (IOException e){
            System.out.println(e);
        }
        //call quick sort recursive function
        int[] data1 = {5,4,3,2,1};
        return quick_sort(data,0,data.length-1,"median_of_3");
    }


    public static void main(String[] args){
        int ans = count_comparisons();
        System.out.println(ans);
    }
}
